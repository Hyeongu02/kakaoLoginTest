package com.choongang.frombirth.common;

import com.choongang.frombirth.command.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HomeMapper {
    public int registerUser(UserVO userVO);

    public UserVO login(String username);
}
