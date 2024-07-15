package com.scheduler.app.backend.aREST.Repo;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scheduler.app.backend.aREST.Models.*;
public interface DeviceRepo  extends JpaRepository<Device, Long> {
    @Modifying
    @Transactional
    @Query(value = "update scheduler.device set state= :state,warning= :warning where id= :id",nativeQuery = true)
    int updateStateAndWarning(@Param("id")long id,@Param("state")String state,@Param("warning")String warning);
}
