package com.scheduler.app.backend.aREST.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduler.app.backend.aREST.Models.Board;

public interface BoardRepo extends JpaRepository<Board, Long>{
    @Query(value="Select * from scheduler.board where onboard_id=?1",nativeQuery = true)
    Board getBoardId(int i);
}
