package com.hendisantika.controller;

import com.hendisantika.domain.Location;
import com.hendisantika.dto.location.AddLocationDto;
import com.hendisantika.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 16/02/22
 * Time: 10.58
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/location")
public class LocationController {

    public static final String LOCATIONS_PAGE = "location/locations";
    public static final String LOCATION_FORM_PAGE = "location/locationForm";
    public static final String BROWSE_LOCATIONS_PAGE = "location/browseLocations";
    public static final String LOCATION_DETAILS_PAGE = "location/details";

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public String displayLocationsPage() {
        return LOCATIONS_PAGE;
    }

    @GetMapping(value = "/browse")
    public String displayBrowseLocationsPage(Model model) {

        List<Location> locations = locationService.findAll();
        model.addAttribute("locations", locations);

        return BROWSE_LOCATIONS_PAGE;
    }

    @GetMapping(value = "/add")
    public String displayAddLocationPage(Model model) {
        model.addAttribute("dto", new AddLocationDto());
        model.addAttribute("mode", "add");

        return LOCATION_FORM_PAGE;
    }
}
