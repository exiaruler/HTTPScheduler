package com.scheduler.app.backend.Messaging.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.Messaging.Models.BoardTask;

public interface BoardTaskInterface extends JpaRepository<BoardTask, Long> {

}
