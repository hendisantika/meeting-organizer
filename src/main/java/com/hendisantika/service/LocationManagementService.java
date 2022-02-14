package com.hendisantika.service;

import com.hendisantika.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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
}
