package com.scheduler.app.backend.Repo;
import com.scheduler.app.backend.Models.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepo extends JpaRepository<Board, Long>{
    
}
