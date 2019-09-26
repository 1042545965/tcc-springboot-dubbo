package com.tcc.mock;

import com.tcc.core.result.RestResponseBo;
import com.tcc.entity.User;
import com.tcc.facede.UserFacede;

import java.util.List;

public class UserFacedeMock implements UserFacede {
    @Override
    public List<User> selectList(User user) {
        return null;
    }

    @Override
    public User selectUserById(Long id) {
        return new User().setNickName("你被Mock啦");
    }

    @Override
    public User selectUserByOne(User user) {
        return null;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public RestResponseBo insertUserAndOrderByTest() {
        return RestResponseBo.fail(10000 , "Inset User And Order Error");
    }

    @Override
    public RestResponseBo testMock(Long Id) {
        return RestResponseBo.fail(500 , "Mock Test Error");
    }

    public UserFacedeMock() {
    }
}
