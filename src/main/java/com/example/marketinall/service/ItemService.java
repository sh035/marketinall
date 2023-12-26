package com.example.marketinall.service;

import com.example.marketinall.config.auth.PrincipalDetails;
import com.example.marketinall.domain.dto.ItemRequestDto;
import com.example.marketinall.domain.dto.ItemViewResponseDto;
import com.example.marketinall.domain.entity.Item;
import com.example.marketinall.domain.entity.ItemFile;
import com.example.marketinall.domain.entity.Member;
import com.example.marketinall.domain.enums.ItemStatus;
import com.example.marketinall.repository.ItemFileRepository;
import com.example.marketinall.repository.ItemRepository;
import com.example.marketinall.util.exception.ApiException;
import com.example.marketinall.util.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemFileRepository itemFileRepository;

    @Value("${file.dir}")
    private String imgPath;
//  System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

    /**
     *
     * @param member
     * @param dto
     * 스프링 시큐리티로 회원만 접근 가능하게 해야함
     */
    public Long write(Member member, ItemRequestDto dto, List<MultipartFile> files) {
        Item item = Item.builder()
                .member(member)
                .title(dto.getTitle())
                .price(dto.getPrice())
                .content(dto.getContent())
                .itemStatus(ItemStatus.SELL)
                .build();
        itemRepository.save(item);



        fileSave(item, files);

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemViewResponseDto detail(Long itemId, PrincipalDetails principalDetails) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new ApiException(ErrorCode.NOT_FOUND_ID));

        ItemViewResponseDto responseDto = ItemViewResponseDto.toDto(item);

        if (principalDetails != null) {
            String sellerEmail = item.getMember().getEmail();
            String loginMemberEmail = principalDetails.getUsername();

            // 게시글 주인이면 수정, 삭제버튼 보여줌
            if (sellerEmail.equals(loginMemberEmail)) {
                responseDto.sellerConfirmation();
            }
        }
        item.addViewCount(item.getViewCount());

        return responseDto;
    }

    public void edit(Long itemId, Member member, ItemRequestDto itemRequestDto, List<MultipartFile> plusFile, List<String> minusFile) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new ApiException(ErrorCode.NOT_FOUND_ID));

        String sellerEmail = item.getMember().getEmail();
        String loginMemberEmail = member.getEmail();

        if (!sellerEmail.equals(loginMemberEmail)) {
            throw new ApiException(ErrorCode.DENY_EDIT_ACCESS);
        }

        item.editItem(itemRequestDto);

        if (plusFile != null) {
            fileSave(item, plusFile);
        }

        if (minusFile != null) {
            fileDelete(item, minusFile);
        }
    }

    public void delete(Long itemId, Member member) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
                new ApiException(ErrorCode.NOT_FOUND_ID));

        String sellerEmail = item.getMember().getEmail();
        String loginMemberEmail = member.getEmail();

        if (!sellerEmail.equals(loginMemberEmail)) {
            throw new ApiException(ErrorCode.DENY_DELETE_ACCESS);
        }

        itemRepository.delete(item);
    }

    private void fileSave(Item item, List<MultipartFile> files) {
        int index = 0;
        for (MultipartFile multipartFile : files) {
            UUID uuid = UUID.randomUUID();
            String filename = uuid + "_" +multipartFile.getOriginalFilename();

            Path path = Paths.get(imgPath + File.separator + filename).toAbsolutePath();

            try {
                multipartFile.transferTo(path.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ItemFile itemFile = ItemFile.builder()
                    .item(item)
                    .fileName(filename)
                    .filePath(imgPath)
                    .repimgYn(index++ == 0 ? "Y" : "N")
                    .build();
            item.getFiles().add(itemFile);
            itemFileRepository.save(itemFile);
        }
    }

    private void fileDelete(Item item, List<String> Files) {
        for (String deleteFileName : Files) {
            String fileName = URLDecoder.decode(deleteFileName, StandardCharsets.UTF_8);
            itemFileRepository.deleteByFileName(fileName);
        }

        item.getFiles().removeIf(itemFile -> Files.contains(itemFile.getFileName()));
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeMember(Item item) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!item.getMember().getEmail().equals(userEmail)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
