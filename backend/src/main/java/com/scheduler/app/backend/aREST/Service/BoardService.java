package com.scheduler.app.backend.aREST.Service;
import org.springframework.stereotype.Service;

import com.scheduler.Base.Base;
import com.scheduler.Base.JsonObject.JsonObject;
import com.scheduler.app.backend.aREST.ArestV2Frame;
import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Models.Device;
import com.scheduler.app.backend.aREST.Repo.*;
import java.util.*;


@Service
public class BoardService extends Base {
    
    private final BoardRepo board;
    private final DeviceService deviceService;
    public ArestV2Frame arest=new ArestV2Frame();

    public BoardService(BoardRepo board, DeviceService deviceService) {
        this.board = board;
        this.deviceService = deviceService;
    }
   
  
    public Board addBoardTest(Board newBoard){
        return board.save(newBoard);
    }
    public Board addBoardManual(String name,String ip,boolean arest,boolean status){
        Board newBoard=new Board();
        newBoard.setName(name);
        newBoard.setIp(ip);
        newBoard.setArest(arest);
        newBoard.setStatus(status);
        // generate board Id
        List <Board> boards=board.findAll();
        if(!boards.isEmpty()){
            Board lastBoard=boards.get(boards.size()-1);
            String boardId=generateRandString(4)+lastBoard.getId();
            newBoard.setBoardId(boardId);
        }else{
            String boardId=generateRandString(4)+1;
            newBoard.setBoardId(boardId);
        }
        return board.save(newBoard);
    }
    public Board addBoard(Board entry){
        return board.save(entry);
    }
    public ArrayList<Board> scanNewBoards(){
        ArrayList<Board> addedList=new ArrayList<Board>();
        String ipAddress="192.168.1.";
        for(int i=0; i<255; i++){
            String ipTest=ipAddress+i;
            String rawJson=httpUtil.request(ipTest);
            if(rawJson!=""){
                Board add=addBoardIp(ipTest,rawJson);
                if(add!=null) addedList.add(add);
            }
        }
        return addedList;
    }

    public Board addBoardIp(String rawJson,String ip){
        Board add=null;
        if(arduinoboardCheck(rawJson)){
            Board newBoard=new Board();
            JsonObject json=jsonobj.jsonToObject(rawJson);
            if(arest.testBoardFrameWork(json,ip)){
                String boardId=json.findKeyValue("id");
                Board existingBoard=board.findBoardByBoardId(boardId);
                if(existingBoard!=null){
                    newBoard=existingBoard;
                }else{
                    newBoard.setBoardId(boardId);
                } 
                newBoard.setName(json.findKeyValue("name"));
                newBoard.setArest(true);
                newBoard.setIp(ip);
                List<Device> deviceList=deviceService.addDeviceFromScan(newBoard,ip,json);
                newBoard.setDevice(deviceList);
                Board save=addBoard(newBoard);
                add=save;
            }
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
        if(rec!=null){
            rec=obj;
            board.save(rec);
        }
        return rec;
    }
    
    public List<Board> getBoards(){
        List <Board> list=board.findAll();
        return list;
    }
    public Board getBoard(long id){
        return board.getReferenceById(id);
    }
    public String deleteBoard(long id){
        String output="Does not exist";
        if(board.existsById(id)){
            Board item=board.getReferenceById(id);
            // delete children
            for(int i=0; i<item.getDevice().size(); i++){
                if(item.getDevice().get(i).getRoutes().size()>0){
                    for(int x=0; x<item.getDevice().get(i).getRoutes().size(); x++){
                        if(item.getDevice().get(i).getRoutes().get(x).getMode().size()>0){
                            for(int y=0; y<item.getDevice().get(i).getRoutes().get(x).getMode().size(); y++){
                                item.getDevice().get(i).getRoutes().get(x).getMode().remove(y);
                            }
                        }
                        item.getDevice().get(i).getRoutes().remove(x);
                    }
                }
                item.getDevice().remove(i);
            }
            output="board remove "+item.getName();
            board.deleteById(id);
        }
        return output;
    }
    public void deleteAllBoards(){
        board.deleteAll();
        deviceService.deleteAllBoard();
        
    }
   
    public Optional<Board> findBoard(long id){
        //Board record;
        return board.findById(id);
        //return record;
    }
    
}
