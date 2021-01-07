package at.imperial.spring.controller;

import at.imperial.spring.domain.PlayerLocationDTO;
import at.imperial.spring.service.LocationService;
import at.imperial.spring.utility.DTOMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LocationHandler {

    private final LocationService locationService;

    public LocationHandler(LocationService locationService) {
        this.locationService = locationService;
    }

    public Mono<ServerResponse> getLocationByName(ServerRequest request) {
        return ServerResponse.ok().body(locationService.getLocation(request.pathVariable("name"))
                .map(DTOMapper.INSTANCE::playerToPlayerDTO), PlayerLocationDTO.class);
    }
}