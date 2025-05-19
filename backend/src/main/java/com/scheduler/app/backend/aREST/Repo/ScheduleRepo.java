package com.scheduler.app.backend.aREST.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.aREST.Models.Schedule;

public interface ScheduleRepo extends JpaRepository<Schedule,Long>{

    
} 