//package com.example.marketinall.service;
//
//import com.example.marketinall.config.auth.PrincipalDetails;
//import com.example.marketinall.domain.dto.ItemRequestDto;
//import com.example.marketinall.domain.dto.ItemViewResponseDto;
//import com.example.marketinall.domain.entity.Item;
//import com.example.marketinall.domain.entity.Member;
//import com.example.marketinall.domain.enums.ItemStatus;
//import com.example.marketinall.domain.enums.Role;
//import com.example.marketinall.repository.ItemRepository;
//import com.example.marketinall.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//class ItemServiceTest {
//
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private ItemService itemService;
//    @Autowired private ItemRepository itemRepository;
//
//    private Member member;
//    private PrincipalDetails principalDetails;
//    private List<MultipartFile> fileList = new ArrayList<>();
//    @BeforeEach
//    void before() {
//        member = memberRepository.save(new Member("hi", "saab35@naver.com", "1234", Role.USER));
//        fileList.add(new MockMultipartFile("file1", "file1.jpg", MediaType.IMAGE_JPEG_VALUE, "hello".getBytes(StandardCharsets.UTF_8)));
//        fileList.add(new MockMultipartFile("file2", "file2.png", MediaType.IMAGE_PNG_VALUE, "hello2".getBytes(StandardCharsets.UTF_8)));
//    }
//
//    @Test
//    @DisplayName("상품 저장 성공")
//    void write_Success() {
//
//        //given
//        ItemRequestDto dto = ItemRequestDto.builder()
//                .title("제목")
//                .content("내용")
//                .price(1000)
//                .build();
//
//        //when
//        Long itemId = itemService.write(member, dto, fileList);
//
//        //then
//        Item item = itemRepository.findById(itemId).orElse(null);
//        Assertions.assertThat(item.getMember().getEmail()).isEqualTo(member.getEmail());
//        Assertions.assertThat(item.getFiles().size()).isEqualTo(2);
//
//    }
//
//    @Test
//    @DisplayName("상품 상세보기 성공")
//    public void success_detail() throws Exception {
//        //given
//        ItemRequestDto itemRequestDto = ItemRequestDto.builder()
//                .title("제목")
//                .content("내용")
//                .price(10000)
//                .itemStatus(ItemStatus.SELL)
//                .build();
//        //when
//        Long itemId = itemService.write(member, itemRequestDto, fileList);
//        ItemViewResponseDto detail = itemService.detail(itemId, principalDetails);
//
//        //then
//        Assertions.assertThat(itemRequestDto.getTitle()).isEqualTo(detail.getTitle());
//        Assertions.assertThat(itemRequestDto.getContent()).isEqualTo(detail.getContent());
//        Assertions.assertThat(itemRequestDto.getPrice()).isEqualTo(detail.getPrice());
//    }
//}