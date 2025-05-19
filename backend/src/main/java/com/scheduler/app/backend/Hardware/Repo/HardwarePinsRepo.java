package com.scheduler.app.backend.Hardware.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.Hardware.Models.HardwarePins;


public interface HardwarePinsRepo extends JpaRepository<HardwarePins, Long>{

}
