package com.scheduler.app.backend.Command.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduler.app.backend.Command.Models.Command;
import com.scheduler.app.backend.Command.Models.CommandParameter;

public interface CommandParameterRepo extends JpaRepository<CommandParameter,Long> {

    @Query(value="select * from scheduler.commandparameter where command_id=?1 and pin=true order by parameterOrder",nativeQuery = true)
    List<CommandParameter> findPinParameters(long id);
}
