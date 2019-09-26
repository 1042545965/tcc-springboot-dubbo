package com.tcc.facede;

import com.tcc.core.result.RestResponseBo;
import com.tcc.entity.OrderInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    /**
     * <p>
     * 订单信息表 服务类
     * </p>
     *
     * @author conlon123
     * @since 2019-04-24
     */
    public interface OrderInfoFacede {

    /**
     * 查询${tableComment}
     *
     * @param  orderInfo 信息
     * @return 结果
     */
    public List<OrderInfo> selectList(OrderInfo orderInfo);

    /**
     * 查询${tableComment}
     *
     * @param  id 信息
     * @return 结果
     */
    public OrderInfo selectOrderInfoById(Long id);

    /**
     * 查询${tableComment}
     *
     * @param orderInfo 信息
     * @return 结果
     */
    public OrderInfo selectOrderInfoByOne(OrderInfo orderInfo);

    /**
     * 修改${tableComment}
     *
     * @param  orderInfo 信息
     * @return 结果
     */
    public int updateOrderInfo(OrderInfo orderInfo);

    /**
     * 新增${tableComment}
     *
     * @param  orderInfo 信息
     * @return 结果
     */
    public int insertOrderInfo(OrderInfo orderInfo);

    @Transactional
    RestResponseBo insertOrderInfoTransactionalByTest();

    RestResponseBo testOrderMock(Long id);
    }
