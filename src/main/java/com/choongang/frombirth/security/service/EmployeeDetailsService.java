package com.choongang.frombirth.security.service;

import com.choongang.frombirth.command.UserVO;
import com.choongang.frombirth.common.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    @Autowired
    private HomeMapper homeMapper;

    //loginProcessingUrl이 호출될때 자동으로 호출시킵니다.
    @Override
    public EmployeeDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("form에서 전달된 아이디:" + username);


        UserVO vo = homeMapper.login(username);
        System.out.println("로그인 시도:" + vo);
        //로그인 성공
        if (vo != null) {
            System.out.println("로그인성공");
            return new EmployeeDetails(vo);
        }

        throw new UsernameNotFoundException("아이디 및 비밀번호를 확인하세요");
    }

}