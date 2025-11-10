package com.hendisantika.controller;

import com.hendisantika.domain.Meeting;
import com.hendisantika.domain.User;
import com.hendisantika.repository.LocationRepository;
import com.hendisantika.repository.MeetingRepository;
import com.hendisantika.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/25
 * Time: 07:05
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @GetMapping
    public String listMeetings(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        List<Meeting> meetings = meetingRepository.findByUsersContaining(user);
        model.addAttribute("meetings", meetings);
        model.addAttribute("user", user);
        return "meetings/list";
    }

    @GetMapping("/new")
    public String newMeetingForm(Model model) {
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "meetings/new";
    }

    @PostMapping("/new")
    public String createMeeting(
            @RequestParam("description") String description,
            @RequestParam("locationId") Long locationId,
            @RequestParam(value = "userIds", required = false) List<Long> userIds,
            @AuthenticationPrincipal UserDetails userDetails) {

        Meeting meeting = new Meeting();
        meeting.setDescription(description);
        meeting.setLocation(locationRepository.findById(locationId).orElse(null));

        List<User> users = new ArrayList<>();
        // Add creator
        users.add(userRepository.findByEmail(userDetails.getUsername()));

        // Add invited users
        if (userIds != null && !userIds.isEmpty()) {
            users.addAll(userRepository.findAllById(userIds));
        }

        meeting.setUsers(users);
        meetingRepository.save(meeting);

        return "redirect:/meetings";
    }

    @GetMapping("/{id}")
    public String viewMeetingDetails(@PathVariable Long id,
                                     @AuthenticationPrincipal UserDetails userDetails,
                                     Model model) {
        Optional<Meeting> meetingOpt = meetingRepository.findById(id);

        if (meetingOpt.isEmpty()) {
            model.addAttribute("error", "Meeting not found");
            return "redirect:/meetings";
        }

        Meeting meeting = meetingOpt.get();
        User currentUser = userRepository.findByEmail(userDetails.getUsername());

        // Check if user is part of this meeting
        boolean isMember = meeting.getUsers().stream()
                .anyMatch(user -> user.getId().equals(currentUser.getId()));

        if (!isMember) {
            model.addAttribute("error", "You don't have access to this meeting");
            return "redirect:/meetings";
        }

        model.addAttribute("meeting", meeting);
        model.addAttribute("currentUser", currentUser);

        return "meetings/detail";
    }
}
