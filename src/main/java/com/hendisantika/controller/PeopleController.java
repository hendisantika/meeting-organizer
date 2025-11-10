package com.hendisantika.controller;

import com.hendisantika.domain.User;
import com.hendisantika.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 10/11/25
 * Time: 07:20
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final UserRepository userRepository;

    @GetMapping
    public String showPeople(@AuthenticationPrincipal UserDetails userDetails,
                             @RequestParam(required = false) String search,
                             Model model) {
        User currentUser = userRepository.findByEmail(userDetails.getUsername());
        List<User> users = userRepository.findAll();

        // Remove current user from the list
        users.removeIf(u -> u.getId().equals(currentUser.getId()));

        // Simple search filter
        if (search != null && !search.isEmpty()) {
            String searchLower = search.toLowerCase();
            users.removeIf(u ->
                    !u.getFirstName().toLowerCase().contains(searchLower) &&
                            !u.getLastName().toLowerCase().contains(searchLower) &&
                            !u.getEmail().toLowerCase().contains(searchLower)
            );
        }

        model.addAttribute("users", users);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("search", search);
        return "people";
    }
}
