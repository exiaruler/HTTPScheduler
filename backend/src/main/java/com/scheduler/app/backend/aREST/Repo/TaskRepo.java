package com.scheduler.app.backend.aREST.Repo;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scheduler.app.backend.aREST.Models.*;
public interface TaskRepo extends JpaRepository<Task, Long>{
    @Query(value="select * from scheduler.task where active= ?1",
    nativeQuery=true)
    List<Task> getAllTaskAct(boolean active);
    @Modifying
    @Transactional
    @Query(value = "update scheduler.task set retry= :tries where id= :id",nativeQuery = true)
    void updateAttempt(@Param("id")long id,@Param("tries")int tries);
}
