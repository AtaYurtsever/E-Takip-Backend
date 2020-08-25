package takip.back.mapper;

import org.mapstruct.Mapper;
import takip.back.DTO.EventDTO;
import takip.back.entity.Event;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDTO mapToDTO(Event event);

    Event  mapToEntity(EventDTO eventDTO);

    List<EventDTO> mapToDTO(List<Event> eventList);

    List<Event>  mapToEntity(List<EventDTO> eventDTOList);

}
