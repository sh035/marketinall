package com.example.marketinall.controller.api;

import com.example.marketinall.domain.dto.SignupRequestDto;
import com.example.marketinall.domain.enums.Role;
import com.example.marketinall.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MemberApiControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("@NotNull nickname 에러 테스트")
    @WithMockUser
    void isValidObject() throws Exception {

       SignupRequestDto dto = SignupRequestDto.builder()
                .email("hi@naver.com")
                .password("12345678")
                .role(Role.USER)
                .build();

        String dtoJsonString = objectMapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/join")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(dtoJsonString))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    @DisplayName("로그인 실패 - email없음")
//    @WithMockUser
//    void login_fail1() throws Exception {
//        String email = "asd@naver.com";
//        String password = "1234";
//
//        when(memberService.login(any(),any()))
//                .thenThrow(new ApiException(ErrorCode.NOT_FOUND_EMAIL));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/login")
//                .with(csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(new LoginRequestDto(email,password))))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }

//    @Test
//    @DisplayName("로그인 실패 - password 틀림")
//    @WithMockUser
//    void login_fail2() throws Exception {
//        String email = "asd@naver.com";
//        String password = "1234";
//
//        when(memberService.login(any(),any()))
//                .thenThrow(new ApiException(ErrorCode.INVAILD_PASSWORD));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/member/login")
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(new LoginRequestDto(email,password))))
//                .andDo(print())
//                .andExpect(status().isUnauthorized());
//    }



}