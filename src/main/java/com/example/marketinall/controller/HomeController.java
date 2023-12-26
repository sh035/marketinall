package com.example.marketinall.controller;

import com.example.marketinall.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

//    private final ItemService itemService;

    @GetMapping("/")
    public String main() {
        return "home";
    }
}
