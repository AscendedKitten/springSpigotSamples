package at.imperial.spring.utility;

import at.imperial.spring.domain.PlayerLocationDTO;
import at.imperial.spring.domain.PlayerLocationData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {
    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    PlayerLocationDTO playerToPlayerDTO(PlayerLocationData locData);
}
