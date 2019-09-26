package com.user.facadeImpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.tcc.core.result.RestResponseBo;
import com.tcc.entity.User;
import com.tcc.facede.OrderInfoFacede;
import com.tcc.facede.UserFacede;
import com.tcc.utils.AppUtil;
import com.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author conlon123
 * @since 2019-04-03
 */
@Component
@Service(timeout = 10000, interfaceClass = UserFacede.class)
public class UserFacadeImpl implements UserFacede {
    private final Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);

    @Resource
    private UserDao userDao;

    @Reference(timeout = 50000, mock = "com.tcc.mock.OrderInfoFacedeMock", check = false)
    private OrderInfoFacede orderInfoFacede;


    /**
     * 查询用户表
     *
     * @param user 信息
     * @return 结果
     */
    @Override
    public List<User> selectList(User user) {
        EntityWrapper<User> entityWrapper = getEntityWrapper(user);
        return userDao.selectList(entityWrapper);
    }

    /**
     * 查询用户表
     *
     * @param id 信息
     * @return 结果
     */
    @Override
    public User selectUserById(Long id) {
        return userDao.selectById(id);
    }

    /**
     * 查询用户表
     *
     * @param user
     * @return 结果
     */
    @Override
    public User selectUserByOne(User user) {
        return userDao.selectOne(user);
    }

    /**
     * 修改用户表
     *
     * @param user 信息
     * @return 结果
     */
    @Override
    public int updateUser(User user) {
        return userDao.updateById(user);
    }

    /**
     * 新增用户表
     *
     * @param user 信息
     * @return 结果
     */
    @Override
    public int insertUser(User user) {
        return userDao.insert(user);
    }

    /**
     * 测试分布式事务
     * rollbackFor = Exception.class  抛出的异常粒度增大 ，一般使用默认的就可以了 。
     * 但是如果存在 非运行时异常 那么这里需要添加 该参数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public RestResponseBo insertUserAndOrderByTest() throws RuntimeException {
        User user = new User("userName_1", "password_1", "nickName_1", "13027383692", 1, new Date(),
                "userType_1", "openId_1", "userSex_1",
                "userCity_1", "userArea_1", "userProvice_1", "unionid_1", 30);
        Integer insertUser = this.insertUser(user);
        if (insertUser != 1) {
            logger.info("is Error Insert User");
            throw new RuntimeException("user insert error");
        }
        try {
            orderInfoFacede.insertOrderInfoTransactionalByTest();
        }catch (Exception e){
            logger.info("chained insert error");
            throw new RuntimeException("chained insert error");
        }
        return RestResponseBo.ok();
    }

    @Override
    public RestResponseBo testMock(Long Id) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (AppUtil.isNull(Id)) {
            throw new RpcException(500 ,"This Is Mock");
        }
        logger.info("Inner Test Mock");
        return RestResponseBo.ok();
    }

    /**
     * 公共查询条件
     *
     * @param user
     * @return
     */
    private EntityWrapper<User> getEntityWrapper(User user) {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        return entityWrapper;
    }


}
