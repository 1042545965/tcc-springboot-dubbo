package com.consumer.controlle;



import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.tcc.core.result.RestResponseBo;
import com.tcc.entity.User;
import com.tcc.facede.OrderInfoFacede;
import com.tcc.facede.UserFacede;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author conlon123
 * @since 2019-04-03
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference(timeout = 5000 , mock = "com.tcc.mock.UserFacedeMock" , check = false)
    private UserFacede userFacede;

    @Reference(timeout = 50000 , mock = "com.tcc.mock.OrderInfoFacedeMock" , check = false)
    private OrderInfoFacede orderInfoFacede;

    @ResponseBody
    @GetMapping(value = "/userBatchDelete")
    public String deleteBatchIds(String item) {
        User user = userFacede.selectUserById(6L);
        return JSON.toJSONString(user);
    }

    @ResponseBody
    @GetMapping(value = "/testUserMock")
    public RestResponseBo testUserMock(Long id) {
        return userFacede.testMock(id);
    }


    @ResponseBody
    @GetMapping(value = "/testOrderMock")
    public RestResponseBo testOrderMock(Long id) {
//        try {
//            return orderInfoFacede.testOrderMock(id);
//        } catch (Exception e) {
//            logger.error("Exception Error "+e.getMessage());
//            return RestResponseBo.fail();
//        }
        return orderInfoFacede.testOrderMock(id);
    }


    @ResponseBody
    @GetMapping(value = "/insertTransactionalByTest")
    public RestResponseBo insertTransactionalByTest() {
        try {
            return userFacede.insertUserAndOrderByTest();
        }catch (Exception e){
            logger.error(e.getMessage());
            return RestResponseBo.fail(500);
        }

    }


    @ResponseBody
    @GetMapping(value = "/insertTransactionalByOrder")
    public RestResponseBo insertTransactionalByOrder() {
        try {
            return orderInfoFacede.insertOrderInfoTransactionalByTest();
        } catch (Exception e) {
            return RestResponseBo.fail();
        }
    }

}