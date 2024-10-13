package com.choongang.frombirth.security.service;

import com.choongang.frombirth.command.UserVO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeDetails implements UserDetails {

    //멤버변수 선언
    private UserVO userVO;
    //객체생성
    public EmployeeDetails(UserVO userVO) {
        this.userVO = userVO;
    }

    //로그인시 권한을 리턴해주는 함수
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("권한줌");
        List<GrantedAuthority> list = new ArrayList<>();

        // departmentName과 positionName을 권한으로 설정
        list.add(new SimpleGrantedAuthority("ROLE_" + getNumber()));
        System.out.println(list);
        return list; // 권한 목록을 반환
    }

    //유저의 비밀번호 = 사원 생년월일 yymmdd
    @Override
    public String getPassword() {
        return userVO.getPassword();
    }
    //유저의 아이디 = 사원번호
    @Override
    public String getUsername() {
        return String.valueOf(userVO.getUsername());
    }

    public String getNumber() {
        return String.valueOf(userVO.getNumber());
    }


    //계정이 만료되지 않았습니까? (true = 네)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //계정이 락이 걸리지 않았습니까?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비밀번호 만료되지 않았습니까?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //계정 사용할 수 있습니까?
    @Override
    public boolean isEnabled() {
        return true;
    }

}