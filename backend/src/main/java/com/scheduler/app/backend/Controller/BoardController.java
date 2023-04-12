package com.scheduler.app.backend.Controller;
import org.springframework.web.bind.annotation.RestController;
import com.scheduler.app.backend.Models.Board;
import com.scheduler.app.backend.Service.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;
    
    @PostMapping(value="/board/addboardtest")
    public String addBoardTest(@RequestBody Board board){
        String add="";
        try{
            add=boardService.addBoardTest(board);
        }catch(Exception err){

        }
        return add;
    }
    @PostMapping(value="/board/addboardscan/boards")
    public ArrayList<Board> addBoardScan(@RequestParam long board){
        ArrayList<Board> addedList=new ArrayList<Board>();
        addedList=boardService.scanNewBoards((int) board);
        return addedList;
    }
    
    @GetMapping(value="/board/getboards")
    public List<Board> all(){
        List<Board> output=new ArrayList<Board>();
        try{
            output=boardService.getBoards();
        }catch(Exception err){

        }
        return output;
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
        try{
            boardService.deleteAllBoards();
        }catch(Exception err){

        }
        return result;
    }

    @PutMapping(value="/board/updateboard/{id}")
    public Board updateBoard(@RequestBody Board board,@PathVariable long id){
        return boardService.updateBoard(board, id);
        
    }

}
