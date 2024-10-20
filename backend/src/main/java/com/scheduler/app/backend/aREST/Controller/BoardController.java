package com.scheduler.app.backend.aREST.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.Base.ControllerBase;
import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping(value = "/board")
public class BoardController extends ControllerBase{
    @Autowired
    private BoardService boardService;
   
    @PostMapping(value="/addboard")
    @ResponseBody
    public Board addBoardTest(@RequestBody Map<String, Object> payload){
        String name=(String)payload.get("name");
        String ip=(String) payload.get("ip");
        boolean arest=(boolean) payload.get("aRest");
        boolean status=(boolean) payload.get("status");
        return boardService.addBoardManual(name, ip, arest, status);
    }
    @PostMapping(value = "/addboardtest", consumes = {"application/xml","application/json"})
    @ResponseBody
    public void addBoardTestFull(@RequestBody Board board){
        //boardService.addBoardTestFull(board);
    }
    
    @PostMapping(value="/addboardscan")
    public ArrayList<Board> addBoardScan(){
        ArrayList<Board> addedList=new ArrayList<Board>();
        addedList=boardService.scanNewBoards();
        return addedList;
    }
    @PostMapping(value="/addboardip")
    public Board addBoardByIP(@RequestBody String ip){
        return boardService.addBoardByIp(ip);
    }
    
    @GetMapping(value="/getboards")
    public List<Board> all(){
        return boardService.getBoards();
    }
    @GetMapping(value="/getboard/{id}")
    public ResponseEntity<Optional<Board>> getBoard(@PathVariable long id){
        Optional<Board> board=boardService.findBoard(id);
        if(board!=null){
            return ResponseEntity.ok(board);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value="/deleteboard/{id}")
    public String deleteBoard(@PathVariable long id){
        String result="";
        result=boardService.deleteBoard(id);
        return result;
    }
    @DeleteMapping(value="/deleteboardall")
    public String deleteBoard(){
        String result="All boards deleted";
        boardService.deleteAllBoards();
        return result;
    }

    @PutMapping(value="/updateboard/{id}")
    public Board updateBoard(Board board,@PathVariable long id){
        return boardService.updateBoard(board, id);
        
    }

}
