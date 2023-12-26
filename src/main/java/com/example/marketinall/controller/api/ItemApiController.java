package com.example.marketinall.controller.api;

import com.example.marketinall.config.auth.PrincipalDetails;
import com.example.marketinall.domain.dto.ItemRequestDto;
import com.example.marketinall.domain.dto.ItemViewResponseDto;
import com.example.marketinall.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/item")
@RestController
public class ItemApiController {

    private final ItemService itemService;

    @PostMapping("/write")
    public ResponseEntity write(@RequestPart(value = "dto") ItemRequestDto dto,
                                @AuthenticationPrincipal PrincipalDetails principalDetails,
                                @RequestPart(value = "files", required = false) List<MultipartFile> files) {
        log.info("사용자 정보 : {}", principalDetails.getMember());
        Long savedItem = itemService.write(principalDetails.getMember(), dto, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity detail(@PathVariable Long itemId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        ItemViewResponseDto dto = itemService.detail(itemId, principalDetails);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping("/{itemId}")
    public ResponseEntity edit(@PathVariable Long itemId, @AuthenticationPrincipal PrincipalDetails principalDetails,
                               @RequestPart(value = "itemDto", required = false) ItemRequestDto itemRequestDto,
                               @RequestPart(value = "plusFileList", required = false) List<MultipartFile> plusFileList,
                               @RequestPart(value = "removeFileList", required = false) List<String> minusFileList) {
        itemService.edit(itemId, principalDetails.getMember(), itemRequestDto, plusFileList, minusFileList);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
