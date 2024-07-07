package com.scheduler.app.backend.aREST.Repo;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduler.app.backend.aREST.Models.*;
public interface TaskRepo extends JpaRepository<Task, Long>{
    @Query(value="select * from scheduler.task where active= ?1",
    nativeQuery=true)
    List<Task> getAllTaskAct(boolean active);
}
