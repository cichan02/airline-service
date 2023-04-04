package by.piskunou.solvdlaba.web.mapper;

import by.piskunou.solvdlaba.domain.AirlineAggregate;
import by.piskunou.solvdlaba.web.dto.AirlineDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    AirlineDTO toDTO(AirlineAggregate aggregate);

    AirlineAggregate toEntity(AirlineDTO dto);

    List<AirlineDTO> toDTO(List<AirlineAggregate> aggregates);

    List<AirlineAggregate> toAggregates(List<AirlineDTO> dtos);

}
