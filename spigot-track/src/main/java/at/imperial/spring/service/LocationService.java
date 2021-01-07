package at.imperial.spring.service;

import at.imperial.spring.domain.PlayerLocationData;
import at.imperial.spring.persistence.LocationRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void save(String name, int x, int z) {
        locationRepository.save(new PlayerLocationData(name, x, z));
    }
    
    public PlayerLocationData getLocation(String name) {
        return locationRepository.findByName(name);
    }

    public int getX(String name) {
        return locationRepository.findByName(name).getLocX();
    }

    public int getZ(String name) {
        return locationRepository.findByName(name).getLocZ();
    }
}
