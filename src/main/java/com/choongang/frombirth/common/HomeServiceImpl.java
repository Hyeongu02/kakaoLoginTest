package com.choongang.frombirth.common;

import com.choongang.frombirth.command.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMapper homeMapper;
    @Override
    public int registerUser(UserVO userVO) {
        return homeMapper.registerUser(userVO);
    }
}
