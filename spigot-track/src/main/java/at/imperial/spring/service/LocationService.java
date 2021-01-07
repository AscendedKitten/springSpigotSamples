package at.imperial.spring.service;

import at.imperial.spring.domain.PlayerLocationData;
import at.imperial.spring.persistence.LocationRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LocationService {

    final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Mono<PlayerLocationData> save(PlayerLocationData locData) {
        return locationRepository.save(locData);
    }

    public Mono<Boolean> existsByName(String name) {
        return locationRepository.existsById(name);
    }

    public Mono<PlayerLocationData> getLocation(String name) {
        return locationRepository.findByName(name);
    }

}