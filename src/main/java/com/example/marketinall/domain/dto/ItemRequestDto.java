package com.example.marketinall.domain.dto;

import com.example.marketinall.domain.entity.Category;
import com.example.marketinall.domain.entity.Item;
import com.example.marketinall.domain.entity.Member;
import com.example.marketinall.domain.enums.ItemStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemRequestDto {
    private Member member;
    private String title;
    private int price;
    private String content;
    private ItemStatus itemStatus;

    @Builder
    public ItemRequestDto(Member member, String title, int price, String content, ItemStatus itemStatus) {
        this.member = member;
        this.title = title;
        this.price = price;
        this.content = content;
        this.itemStatus = itemStatus;
    }

}