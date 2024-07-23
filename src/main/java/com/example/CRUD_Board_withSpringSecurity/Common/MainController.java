package com.example.CRUD_Board_withSpringSecurity.Common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String home()
    {
        return "Common/home";
    }
}
