package com.example.marketinall.domain.dto;

import com.example.marketinall.domain.entity.ItemFile;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ItemFileResponseDto {
    private String fileName;

    public static ItemFileResponseDto toDto(ItemFile itemFile) {
        ItemFileResponseDto response = new ItemFileResponseDto();
        response.fileName = itemFile.getFileName();
        return response;
    }

    public static List<ItemFileResponseDto> toDtoList(List<ItemFile> files) {
        return files.stream()
                .map(f -> ItemFileResponseDto.toDto(f))
                .collect(Collectors.toList());
    }
}
