package com.scheduler.app.backend.Service;
import org.springframework.stereotype.Service;
import com.scheduler.app.backend.Repo.*;
import com.scheduler.app.HTTPHandle.HttpUtil;
import com.scheduler.app.backend.Models.Board;
import com.scheduler.app.backend.Base;
import com.scheduler.app.backend.Service.DeviceService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BoardService extends Base {
    
    private final BoardRepo board;
    private final DeviceService deviceService;
   
    public BoardService(BoardRepo board, DeviceService deviceService) {
        this.board = board;
        this.deviceService = deviceService;
    }
   

    
    public String addBoardTest(Board newBoard){
        System.out.println(newBoard);
        Board add=board.save(newBoard);
        String boardAdd=add.getName();
        return boardAdd;
    }
    public Board addBoard(Board entry){
        return board.save(entry);
    }
    public ArrayList<Board> scanNewBoards(int deviceExpected){
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
                    int idConvert=Integer.parseInt(getDataByFieldBoard("id",boardRaw[0]).trim());
                    newBoard.setBoardId(idConvert);
                    Board save=addBoard(newBoard);
                    // save device 
                    deviceService.addDeviceFromScan(save,ipTest,getrawPart(rawJson,0));
                    Board saveItem=board.findById(save.getId()).get();
                    addedList.add(saveItem);
                }
            }
        }
        return addedList;
    }

    public Board updateBoard(Board obj,long id){
        Board rec=board.findById(id).get();
        Board save=null;
        if(rec!=null){
            rec.setName(obj.getName());
            rec.setIp(obj.getIp());
            rec.setBoardId(obj.getBoardId());
            rec.setStatus(obj.getStatus());
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
        //board.deleteAll();
        board.deleteAll(getBoards());
        
    }
   
    public Board findBoard(long id){
        Board test=new Board();
        test.getClass();
        return board.findById(id).get();
    }
    
}
