package com.example.marketinall.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/item")
public class ItemController {

    @GetMapping("/write")
    public String write(Model model) {
        return "item/write";
    }

    @GetMapping("/{itemId}")
    public String detail(@PathVariable Long itemId) {
        return "item/detail";
    }

    @GetMapping("/edit/{itemId}")
    public String edit(@PathVariable Long itemId) {
        return "item/edit";
    }
}
