package com.scheduler.app.backend.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.Models.*;
public interface TaskRepo extends JpaRepository<Task, Long>{
    
}
