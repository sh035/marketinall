package com.example.marketinall.controller.api;

import com.example.marketinall.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class CategoryApiController {

    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getCategory(@RequestParam(required = false) Long id) {
        log.info("id = {} " , id);
        return ResponseEntity.ok(categoryService.findAllCategory(id));
    }
}
