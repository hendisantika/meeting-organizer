package com.hendisantika.controller;

import com.hendisantika.domain.Location;
import com.hendisantika.dto.location.AddLocationDto;
import com.hendisantika.dto.location.EditLocationDto;
import com.hendisantika.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = "/add")
    public String addLocation(@Valid @ModelAttribute("dto") AddLocationDto dto,
                              BindingResult bindingResult,
                              @AuthenticationPrincipal UserDetails userDetails,
                              Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "add");
            return LOCATION_FORM_PAGE;
        }

        if (locationService.existsByNameIgnoreCase(dto.getName())) {
            model.addAttribute("NAME_TAKEN", true);
            model.addAttribute("mode", "add");
            return LOCATION_FORM_PAGE;
        }

        Location location = new Location();
        location.setName(dto.getName());
        location.setDescription(dto.getDescription());
        location.setMaxMembers(dto.getMaxMembers());

        locationService.addNewLocation(location, userDetails.getUsername());

        return "redirect:/location/browse";
    }

    @GetMapping(value = "/{id}/details")
    public String displayLocationDetails(@PathVariable Long id,
                                         @AuthenticationPrincipal UserDetails userDetails,
                                         Model model) {
        Location location = locationService.findOneById(id);

        if (location == null) {
            model.addAttribute("displayLocationNotFound", true);
            return LOCATIONS_PAGE;
        }

        boolean canEdit = locationService.canEditLocation(location, userDetails.getUsername());

        model.addAttribute("location", location);
        model.addAttribute("canEdit", canEdit);

        return LOCATION_DETAILS_PAGE;
    }

    @GetMapping(value = "/{locationId}/edit")
    public String displayEditLocationPage(@PathVariable Long locationId,
                                          @AuthenticationPrincipal UserDetails userDetails,
                                          Model model) {
        Location location = locationService.findOneById(locationId);

        if (location == null) {
            model.addAttribute("displayLocationNotFound", true);
            return LOCATIONS_PAGE;
        }

        if (!locationService.canEditLocation(location, userDetails.getUsername())) {
            return "redirect:/location/" + locationId + "/details";
        }

        EditLocationDto dto = new EditLocationDto(location);
        model.addAttribute("dto", dto);
        model.addAttribute("mode", "edit");
        model.addAttribute("locationId", locationId);

        return LOCATION_FORM_PAGE;
    }

    @PostMapping(value = "/{locationId}/edit")
    public String editLocation(@PathVariable Long locationId,
                               @Valid @ModelAttribute("dto") EditLocationDto dto,
                               BindingResult bindingResult,
                               @AuthenticationPrincipal UserDetails userDetails,
                               Model model) {

        Location location = locationService.findOneById(locationId);

        if (location == null) {
            model.addAttribute("displayLocationNotFound", true);
            return LOCATIONS_PAGE;
        }

        if (!locationService.canEditLocation(location, userDetails.getUsername())) {
            return "redirect:/location/" + locationId + "/details";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("locationId", locationId);
            return LOCATION_FORM_PAGE;
        }

        if (!locationService.isLocationNameAvailable(dto.getName(), location)) {
            model.addAttribute("NAME_TAKEN", true);
            model.addAttribute("mode", "edit");
            model.addAttribute("locationId", locationId);
            return LOCATION_FORM_PAGE;
        }

        location.setName(dto.getName());
        location.setDescription(dto.getDescription());
        location.setMaxMembers(dto.getMaxMembers());

        locationService.saveAndFlush(location);

        return "redirect:/location/" + locationId + "/details";
    }
}
