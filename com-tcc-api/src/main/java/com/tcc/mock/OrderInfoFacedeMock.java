package com.tcc.mock;

import com.tcc.core.result.RestResponseBo;
import com.tcc.entity.OrderInfo;
import com.tcc.facede.OrderInfoFacede;

import java.util.List;

public class OrderInfoFacedeMock implements OrderInfoFacede {
    @Override
    public List<OrderInfo> selectList(OrderInfo orderInfo) {
        return null;
    }

    @Override
    public OrderInfo selectOrderInfoById(Long id) {
        return null;
    }

    @Override
    public OrderInfo selectOrderInfoByOne(OrderInfo orderInfo) {
        return null;
    }

    @Override
    public int updateOrderInfo(OrderInfo orderInfo) {
        return 0;
    }

    @Override
    public int insertOrderInfo(OrderInfo orderInfo) {
        return 0;
    }

    @Override
    public RestResponseBo insertOrderInfoTransactionalByTest() {
        return RestResponseBo.fail(20000 , "Inset One Order Error");
    }

    @Override
    public RestResponseBo testOrderMock(Long id) {
        return RestResponseBo.fail(500 , "Mock Test Order Error");
    }

    public OrderInfoFacedeMock() {
    }
}
