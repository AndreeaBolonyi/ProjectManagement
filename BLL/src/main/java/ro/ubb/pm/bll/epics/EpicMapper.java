package ro.ubb.pm.bll.epics;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.ubb.pm.bll.user.UserMapperDecorator;
import ro.ubb.pm.model.Epic;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.dtos.EpicDTO;
import ro.ubb.pm.model.dtos.SprintDTO;

@Mapper
@DecoratedWith(EpicMapperDecorator.class)
public interface EpicMapper {

    EpicMapper INSTANCE = Mappers.getMapper(ro.ubb.pm.bll.epics.EpicMapper.class);

    @Mapping(source = "project.id", target = "projectId")
    EpicDTO epicToEpicDTO(Epic epic);

    @Mapping(target = "sprints", ignore = true)
    @Mapping(target = "project", ignore = true)
    Epic epicDTOToEpic(EpicDTO epicDTO);
}
