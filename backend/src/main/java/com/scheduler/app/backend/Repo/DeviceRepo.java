package com.scheduler.app.backend.Repo;
import com.scheduler.app.backend.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface DeviceRepo  extends JpaRepository<Devices, Long> {
    
}
