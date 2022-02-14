package com.hendisantika.service;

import com.hendisantika.domain.Location;
import com.hendisantika.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 14/02/22
 * Time: 08.24
 * To change this template use File | Settings | File Templates.
 */
@Service
public class LocationManagementService implements LocationService {

    private final LocationRepository locationRepository;
    private final UserService userService;

    @Autowired
    public LocationManagementService(LocationRepository locationRepository, UserDetailsService userDetailsService, UserService userService) {
        this.locationRepository = locationRepository;
        this.userService = userService;
    }

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return locationRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public Location addNewLocation(Location location, String creatorEmail) {
        location.setCreatedBy(userService.findOneByEmail(creatorEmail));
        return locationRepository.saveAndFlush(location);
    }

    @Override
    public Location saveAndFlush(Location location) {
        return locationRepository.saveAndFlush(location);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location findOneById(Long id) {
        return locationRepository.findOne(id);
    }
}
