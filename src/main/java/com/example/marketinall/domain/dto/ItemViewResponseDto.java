package com.example.marketinall.domain.dto;

import com.example.marketinall.domain.entity.Item;
import com.example.marketinall.domain.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemViewResponseDto {
    private Long id;
    private String member;
    private String title;
    private int price;
    private String content;
    private String itemStatus;
    private String createTime;
    private int viewCount;
    private List<ItemFileResponseDto> files;
    private boolean seller = false;

    public void sellerConfirmation() {
        this.seller = true;
    }
    public static ItemViewResponseDto toDto(Item item) {
        ItemViewResponseDto response = new ItemViewResponseDto();
        response.id = item.getId();
        response.member = item.getMember().getEmail();
        response.title = item.getTitle();
        response.price = item.getPrice();
        response.content = item.getContent();
        response.itemStatus = item.getItemStatus().getValue();
        response.viewCount = item.getViewCount();
        response.createTime = item.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        response.files = ItemFileResponseDto.toDtoList(item.getFiles());

        return response;
    }

    @Getter
    @Builder
    public static class MainProductResponseDto {
        private Long id;
        private String title;
        private String imgUrl;
        private int price;
    }
}
