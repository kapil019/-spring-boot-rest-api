package com.example.fl.Translator;

import com.example.fl.Entity.User;
import com.example.fl.Response.SRO.UserResp;

public class UserTranslator {

    public UserResp translateuserRespFromModel(User user) {
        UserResp userResp = new UserResp();
        userResp.setId(user.getId());
        userResp.setEmail(user.getEmail());
        userResp.setName(user.getName());
        userResp.setPhone(user.getPhone());
        return userResp;
    }
}