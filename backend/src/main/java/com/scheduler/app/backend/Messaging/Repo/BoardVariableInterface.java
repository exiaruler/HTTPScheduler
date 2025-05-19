package com.scheduler.app.backend.Messaging.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.Messaging.Models.BoardVariable;

public interface BoardVariableInterface extends JpaRepository<BoardVariable, Long> {

}
