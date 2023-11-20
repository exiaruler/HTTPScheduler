package com.scheduler.app.backend.aREST.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduler.app.backend.aREST.Models.*;

public interface ModeRepo  extends JpaRepository<Mode, Long>{

}
