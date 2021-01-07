package at.imperial.spring.controller;

import at.imperial.spring.domain.PlayerLocationData;
import at.imperial.spring.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<PlayerLocationData> getByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(locationService.getLocation(name), HttpStatus.OK);
    }
}
