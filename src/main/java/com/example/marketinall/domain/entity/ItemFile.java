package com.example.marketinall.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemfile_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String filePath;

    private String fileName;
    private String repimgYn; // 대표이미지 여부

    @Builder
    public ItemFile(Item item, String filePath, String fileName, String repimgYn) {
        this.item = item;
        this.filePath = filePath;
        this.fileName = fileName;
        this.repimgYn = repimgYn;
    }

    public void ItemFileThumbnail(String repimgYn) {
        this.repimgYn = repimgYn;
    }

}
