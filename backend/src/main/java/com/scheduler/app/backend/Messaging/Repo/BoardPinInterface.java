package com.scheduler.app.backend.Messaging.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.app.backend.Messaging.Models.BoardPin;

public interface BoardPinInterface extends JpaRepository<BoardPin,Long>{

}
