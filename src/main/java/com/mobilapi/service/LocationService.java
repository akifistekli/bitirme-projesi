package com.mobilapi.service;

import com.mobilapi.domain.customer.Location;
import com.mobilapi.repository.LocationRepository;
import com.mobilapi.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AuthenticationService authenticationService;

    public List<Location> getAllLocationByAccount() {

        return locationRepository.findByAccount(authenticationService.getCurrentAccount());
    }

    public void createNewLocation(Location location) {
        locationRepository.save(location);
    }

    public Location getLocationById(Long id) {
        return locationRepository.findOne(id);
    }
}
