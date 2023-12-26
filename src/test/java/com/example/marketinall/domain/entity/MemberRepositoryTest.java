//package com.example.marketinall.domain.entity;
//
//import com.example.marketinall.domain.enums.Role;
//import com.example.marketinall.repository.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void 회원등록() {
//        //given
//        Member member = new Member("엄", "asd@naver.com", "123", Role.USER);
//        //when
//        Member savedMember = memberRepository.save(member);
//        Member findMember = memberRepository.findById(savedMember.getId()).get();
//
//        //then
//        assertThat(findMember.getId()).isEqualTo(member.getId());
//        assertThat(findMember.getNickname()).isEqualTo(member.getNickname());
//        assertThat(findMember.getEmail()).isEqualTo(member.getEmail());
//        assertThat(findMember.getPassword()).isEqualTo(member.getPassword());
//        assertThat(findMember).isEqualTo(member);
//    }
//}