package com.hendisantika.controller;

import com.hendisantika.domain.Meeting;
import com.hendisantika.domain.User;
import com.hendisantika.repository.MeetingRepository;
import com.hendisantika.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
 * Date: 10/11/25
 * Time: 07:15
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository;

    @GetMapping
    public String showHistory(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        List<Meeting> meetings = meetingRepository.findByUsersContaining(user);
        model.addAttribute("meetings", meetings);
        model.addAttribute("user", user);
        return "history";
    }
}
