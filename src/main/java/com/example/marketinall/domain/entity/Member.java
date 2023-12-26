package com.example.marketinall.domain.entity;

import com.example.marketinall.config.oauth2.userinfo.AuthProvider;
import com.example.marketinall.config.oauth2.userinfo.OAuth2UserInfo;
import com.example.marketinall.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;


@Builder
@Entity
@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    private String email;

    private String password;

//    private String oauth2Id;
//    private String provider;
    private String authProvider;


    @Enumerated(EnumType.STRING)
    private Role role;

    //    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();


    public Member update(String nickname) {
        this.nickname = nickname;

        return this;
    }




}
