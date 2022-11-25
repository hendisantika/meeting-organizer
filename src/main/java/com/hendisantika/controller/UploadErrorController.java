package com.hendisantika.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * Project : meeting-organizer
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/25/22
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/uploadError")
public class UploadErrorController {

    public static final String UPLOAD_ERROR_PAGE = "error/uploadErrorPage";

    @GetMapping
    public String showUploadErorPage() {
        return UPLOAD_ERROR_PAGE;
    }
}
