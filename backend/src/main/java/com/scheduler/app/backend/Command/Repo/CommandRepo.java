package com.scheduler.app.backend.Command.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduler.app.backend.Command.Models.Command;

public interface CommandRepo extends JpaRepository<Command, Long> {

    @Query(value="Select * from scheduler.command where command=?1 and className=?2",nativeQuery = true)
    Command findRouteByClass(String route,String className);

    @Query(value="Select * from scheduler.command where commandType=?1 and command=?2",nativeQuery = true)
    Command findCommand(String commandType,String command);
}