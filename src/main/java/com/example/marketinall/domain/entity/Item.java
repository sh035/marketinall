package com.example.marketinall.domain.entity;

import com.example.marketinall.domain.dto.ItemRequestDto;
import com.example.marketinall.domain.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;

    private int price;

    private String content;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    private int viewCount;


    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ItemFile> files = new ArrayList<>();

    @Builder
    public Item(Member member, String title, int price, String content, Category category, ItemStatus itemStatus) {
        this.member = member;
        this.title = title;
        this.price = price;
        this.content = content;
        this.category = category;
        this.itemStatus = itemStatus;
    }

    public void editItem(ItemRequestDto itemRequestDto) {
        this.title = title;
        this.price = price;
        this.content = content;
    }

    public void addViewCount(int viewCount) {
        this.viewCount = viewCount + 1;
    }

}
