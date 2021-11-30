package ro.ubb.pm.bll.epics;


import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.pm.bll.sprints.SprintMapper;
import ro.ubb.pm.dal.EpicsRepository;
import ro.ubb.pm.dal.RolesRepository;
import ro.ubb.pm.model.Epic;
import ro.ubb.pm.model.Sprint;
import ro.ubb.pm.model.dtos.EpicDTO;
import ro.ubb.pm.model.dtos.SprintDTO;

public abstract class EpicMapperDecorator implements EpicMapper {

    private final EpicMapper epicMapper;

    @Autowired
    private EpicsRepository epicsRepository;

    public EpicMapperDecorator(EpicMapper epicMapper) {
        this.epicMapper = epicMapper;
    }

    @Override
    public Epic epicDTOToEpic(EpicDTO epicDTO) {
        return epicsRepository.findById(epicDTO.getId()).orElse(null);
    }
}
