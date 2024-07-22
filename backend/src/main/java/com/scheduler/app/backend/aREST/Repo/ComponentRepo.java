package com.scheduler.app.backend.aREST.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.aREST.Models.Component;

public interface ComponentRepo extends JpaRepository<Component, Long>{

}
