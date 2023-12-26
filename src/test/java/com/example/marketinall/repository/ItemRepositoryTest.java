//package com.example.marketinall.repository;
//
//import com.example.marketinall.domain.entity.Item;
//import com.example.marketinall.domain.entity.Member;
//import com.example.marketinall.domain.enums.ItemStatus;
//import com.example.marketinall.domain.enums.Role;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//public class ItemRepositoryTest {
//
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    ItemRepository itemRepository;
//
//
//    @Test
//    @DisplayName("상품 저장 테스트")
//    public void createItemTest() {
//
//        Member member = new Member("엄", "asd@naver.com", "123", Role.USER);
//        Member savedMember = memberRepository.save(member);
//
//        Item item = Item.builder()
//                .member(savedMember)
//                .title("테스트 상품")
//                .price(10000)
//                .content("테스트 상품 상세설명")
//                .itemStatus(ItemStatus.SELL)
//                .build();
//        Item savedItem = itemRepository.save(item);
//        System.out.println(savedItem.toString());
//    }
//}