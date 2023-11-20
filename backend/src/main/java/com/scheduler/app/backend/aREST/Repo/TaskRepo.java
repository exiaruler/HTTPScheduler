package com.scheduler.app.backend.aREST.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.aREST.Models.*;
public interface TaskRepo extends JpaRepository<Task, Long>{
    
}
