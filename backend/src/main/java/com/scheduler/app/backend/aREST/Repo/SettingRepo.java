package com.scheduler.app.backend.aREST.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.aREST.Models.Setting;

public interface SettingRepo extends JpaRepository<Setting, Long> {
    
}