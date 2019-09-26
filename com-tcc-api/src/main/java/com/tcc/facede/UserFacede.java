package com.tcc.facede;

import com.tcc.core.result.RestResponseBo;
import com.tcc.entity.User;

import java.util.List;

    /**
     * <p>
     * 用户表 服务类
     * </p>
     *
     * @author conlon123
     * @since 2019-04-03
     */
    public interface UserFacede {

    /**
     * 查询${tableComment}
     *
     * @param  user 信息
     * @return 结果
     */
    public List<User> selectList(User user);

    /**
     * 查询${tableComment}
     *
     * @param  id 信息
     * @return 结果
     */
    public User selectUserById(Long id);

    /**
     * 查询${tableComment}
     *
     * @param user 信息
     * @return 结果
     */
    public User selectUserByOne(User user);

    /**
     * 修改${tableComment}
     *
     * @param  user 信息
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 新增${tableComment}
     *
     * @param  user 信息
     * @return 结果
     */
    public int insertUser(User user);


    RestResponseBo insertUserAndOrderByTest() throws RuntimeException;

    /**
     *
     * @param Id
     * @return
     */
    RestResponseBo testMock(Long Id);
    }
