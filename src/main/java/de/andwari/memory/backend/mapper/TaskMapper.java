package de.andwari.memory.backend.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import de.andwari.memory.backend.model.rest.TimerModel;
import de.andwari.memory.backend.scheduler.db.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING)
public interface TaskMapper {

    @Mapping(target = "name", source = "task")
    TimerModel map(TaskEntity task);

}
