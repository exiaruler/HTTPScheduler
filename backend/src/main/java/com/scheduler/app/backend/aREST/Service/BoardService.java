package com.scheduler.app.backend.aREST.Service;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Models.Devices;
import com.scheduler.app.backend.aREST.Models.ScanDevice;
import com.scheduler.app.backend.aREST.Repo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class BoardService extends Base {
    
    private final BoardRepo board;
    private final DeviceService deviceService;
    private final ScanDeviceService scanVer;

    public BoardService(BoardRepo board, DeviceService deviceService, ScanDeviceService scanVer) {
        this.board = board;
        this.deviceService = deviceService;
        this.scanVer = scanVer;
    }
   
  
    public Board addBoardTest(Board newBoard){
        return board.save(newBoard);
    }
    public Board addBoard(Board entry){
        return board.save(entry);
    }
    public void addBoardTestFull(Board entry){
        Board newBoard=new Board();
        Object t=entry;
        System.out.println(t.toString());
        //newBoard.setIp(entry.equals());
        

    }
    public ArrayList<Board> scanNewBoards(){
        ArrayList<Board> addedList=new ArrayList<Board>();
        String ipAddress="192.168.1.";
        for(int i=0; i<255; i++){
            String ipTest=ipAddress+i;
            String rawJson=httpUtil.request(ipTest);
            if(rawJson!=""){
                if(arduinoboardCheck(rawJson)){
                    String jsonSection=getrawPart(rawJson,1);
                    String [] boardRaw=jsonSection.split(",");
                    Board newBoard=new Board();
                    newBoard.setName(getDataByFieldBoard("name",boardRaw[1]));
                    newBoard.setIp(ipTest);
                    String[] rawIdArr=getDataByFieldBoard("id",boardRaw[0]).trim().split("\\|");
                    Board existBoard=board.getBoardId(Integer.parseInt(rawIdArr[1]));
                    if(rawIdArr.length==2&&existBoard==null){
                        // don't save board if there no scan version because of directions
                        ScanDevice scanVersion=scanVer.getScan(rawIdArr[0]);
                        if(scanVersion!=null){
                            newBoard.setOnboardId(Integer.parseInt(rawIdArr[1]));
                            newBoard.setScanDeviceVersion(scanVersion.getId());
                            Board save=addBoard(newBoard);
                            // save device 
                            deviceService.addDeviceFromScan(save,ipTest,getrawPart(rawJson,0),scanVersion);
                            Board saveItem=board.findById(save.getId()).get();
                            addedList.add(saveItem);
                        }
                    }
                    /*
                    if(existBoard != null){
                        ScanDevice scanVersion=scanVer.getScan(rawIdArr[0]);
                        Board save=addBoard(newBoard);
                        // save device 
                        deviceService.addDeviceFromScan(save,ipTest,getrawPart(rawJson,0),scanVersion);
                        Board saveItem=board.findById(save.getId()).get();
                        addedList.add(saveItem);
                        
                    }
                    */
                }
            }
        }
        return addedList;
    }

    public Board addBoardIp(String rawJson,String ip){
        Board add=null;
        if(arduinoboardCheck(rawJson)){
            String jsonSection=getrawPart(rawJson,1);
            String [] boardRaw=jsonSection.split(",");
            Board newBoard=new Board();
            JsonObject json=jsonobj.jsonToObject(rawJson);
            newBoard.setName(json.findKeyValue("name"));
            newBoard.setIp(ip);
            String[] rawIdArr=json.findKeyValue("id").trim().split("\\|");
            int id=Integer.parseInt(rawIdArr[1]);
            //Board existBoard=board.getBoardId(id);
            if(rawIdArr.length==2){
                // don't save board if there no scan version because of directions
                ScanDevice scanVersion=scanVer.getScan(rawIdArr[0]);
                if(scanVersion!=null){
                    newBoard.setOnboardId(Integer.parseInt(rawIdArr[1]));
                    newBoard.setScanDeviceVersion(scanVersion.getId());
                    Board save=addBoard(newBoard);
                    // save device 
                    Set<Devices> deviceList=deviceService.addDeviceFromScan(save,ip,json,scanVersion);
                    save.setDevice(deviceList);
                    save=addBoard(save);
                    add=board.findById(save.getId()).get();
                }
            }
            
                    /*
                    if(existBoard != null){
                        ScanDevice scanVersion=scanVer.getScan(rawIdArr[0]);
                        Board save=addBoard(newBoard);
                        // save device 
                        deviceService.addDeviceFromScan(save,ipTest,getrawPart(rawJson,0),scanVersion);
                        Board saveItem=board.findById(save.getId()).get();
                        addedList.add(saveItem);
                        
                    }
                    */
            }
        return add;
    }
    public Board addBoardByIp(String ip){
        Board board=null;
        if(ip!=""){
            String jsonRaw=httpUtil.request(ip);
            if(jsonRaw!=""){
                board=addBoardIp(jsonRaw,ip);
            }
        }
        return board;
    }

    public Board updateBoard(Board obj,long id){
        Board rec=board.findById(id).get();
        Board save=null;
        if(rec!=null){
            rec.setName(obj.getName());
            rec.setIp(obj.getIp());
            rec.setOnboardId(obj.getOnboardId());
            rec.setStatus(obj.isStatus());
            save=board.save(rec);
        }
        return save;
    }
    
    public List<Board> getBoards(){
        List <Board> list=board.findAll();
        return list;
    }
    public String deleteBoard(long id){
        String output="Does not exist";
        if(board.existsById(id)){
            Optional<Board> item=board.findById(id);
            output="board remove "+item.get().getName();
            board.deleteById(id);
        }
        return output;
    }
    public void deleteAllBoards(){
        board.deleteAll();
        deviceService.deleteAllBoard();
        
    }
   
    public Board findBoard(long id){
        return board.findById(id).get();
    }
    
}
