package com.scheduler.app.backend.aREST.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.app.backend.aREST.Models.Board;
import com.scheduler.app.backend.aREST.Service.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;
   
    @PostMapping(value="/board/addboard")
    @ResponseBody
    public Board addBoardTest(@RequestBody Board board){
        return boardService.addBoardTest(board);
    }
    @PostMapping(value = "/board/addboardtest", consumes = {"application/xml","application/json"})
    @ResponseBody
    public void addBoardTestFull(@RequestBody Board board){
        //boardService.addBoardTestFull(board);
    }
    
    @PostMapping(value="/board/addboardscan")
    public ArrayList<Board> addBoardScan(){
        ArrayList<Board> addedList=new ArrayList<Board>();
        addedList=boardService.scanNewBoards();
        return addedList;
    }
    @PostMapping(value="/board/addboardip")
    public Board addBoardByIP(@RequestBody String ip){
        return boardService.addBoardByIp(ip);
    }
    
    @GetMapping(value="/board/getboards")
    public List<Board> all(){
        return boardService.getBoards();
    }
    @GetMapping(value="/board/getboard/{id}")
    public Board getBoard(@PathVariable long id){
        return boardService.findBoard(id);
    }
    @DeleteMapping(value="/board/deleteboard/{id}")
    public String deleteBoard(@PathVariable long id){
        String result="";
        try{
            result=boardService.deleteBoard(id);
        }catch(Exception err){

        }
        return result;
    }
    @DeleteMapping(value="/board/deleteboardall")
    public String deleteBoard(){
        String result="All boards deleted";
        boardService.deleteAllBoards();
        return result;
    }

    @PutMapping(value="/board/updateboard/{id}")
    public Board updateBoard(Board board,@PathVariable long id){
        return boardService.updateBoard(board, id);
        
    }

}
