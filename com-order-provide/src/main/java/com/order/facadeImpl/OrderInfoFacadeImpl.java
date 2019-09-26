package com.order.facadeImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.order.dao.OrderInfoDao;
import com.tcc.core.result.RestResponseBo;
import com.tcc.entity.OrderInfo;
import com.tcc.exception.BaseException;
import com.tcc.facede.OrderInfoFacede;
import com.tcc.utils.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author conlon123
 * @since 2019-04-24
 */
@Component
@Service(timeout = 50000,interfaceClass = OrderInfoFacede.class)
public class OrderInfoFacadeImpl implements OrderInfoFacede {

    private final Logger logger = LoggerFactory.getLogger(OrderInfoFacadeImpl.class);

    @Resource
    private OrderInfoDao orderInfoDao;

    /**
     * 查询订单信息表
     *
     * @param  orderInfo 信息
     * @return 结果
     */
    @Override
    public List<OrderInfo> selectList(OrderInfo orderInfo) {
        EntityWrapper<OrderInfo> entityWrapper = getEntityWrapper(orderInfo);
        return orderInfoDao.selectList(entityWrapper);
    }

    /**
     * 查询订单信息表
     *
     * @param  id 信息
     * @return 结果
     */
    @Override
    public OrderInfo selectOrderInfoById(Long id) {
            return orderInfoDao.selectById(id);
    }

    /**
     * 查询订单信息表
     *
     * @param orderInfo 信息
     * @return 结果
     */
    @Override
    public OrderInfo selectOrderInfoByOne(OrderInfo orderInfo) {
            return orderInfoDao.selectOne(orderInfo);
    }

    /**
     * 修改订单信息表
     *
     * @param orderInfo 信息
     * @return 结果
     */
    @Override
    public int updateOrderInfo(OrderInfo orderInfo) {
            return orderInfoDao.updateById(orderInfo);
    }

    /**
     * 新增订单信息表
     *
     * @param orderInfo 信息
     * @return 结果
     */
    @Override
    public int insertOrderInfo(OrderInfo orderInfo) {
            return orderInfoDao.insert(orderInfo);
    }

    /**
     * 测试分布式链式调用事务
     * @param
     * @return
     */
    @Override
    @Transactional
    public RestResponseBo insertOrderInfoTransactionalByTest() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAppointDate(new Date());
        orderInfo.setChangeTime(new Date());
        int i = this.insertOrderInfo(orderInfo);
            i = i + 1;
        if (i != 1){
            logger.info("is Error Insert Order");
            throw new RuntimeException("Order Insert Error");
        }
        return RestResponseBo.ok();
    }

    @Override
    public RestResponseBo testOrderMock(Long id) {
        if (AppUtil.isNull(id)){
            throw new RpcException("This Is Order Mock");
        }
        logger.info("Inner Test Order Mock");
        return RestResponseBo.ok();
    }

    /**
     *  公共查询条件
     * @param orderInfo
     * @return
     */
    private EntityWrapper<OrderInfo> getEntityWrapper(OrderInfo orderInfo){
            EntityWrapper<OrderInfo> entityWrapper = new EntityWrapper<>();
        //条件拼接
//                    if (StringUtils.isNotBlank(orderInfo.getVerificationCode())){
//                    entityWrapper.eq("verification_code",orderInfo.getVerificationCode());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getOrderNo())){
//                    entityWrapper.eq("order_no",orderInfo.getOrderNo());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getAppointDate())){
//                    entityWrapper.eq("appoint_date",orderInfo.getAppointDate());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getOrderScan())){
//                    entityWrapper.eq("order_scan",orderInfo.getOrderScan());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getPublicHairId())){
//                    entityWrapper.eq("public_hair_id",orderInfo.getPublicHairId());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getUserId())){
//                    entityWrapper.eq("user_id",orderInfo.getUserId());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getGoodsId())){
//                    entityWrapper.eq("goods_id",orderInfo.getGoodsId());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getStoreId())){
//                    entityWrapper.eq("store_id",orderInfo.getStoreId());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getOrderStatus())){
//                    entityWrapper.eq("order_status",orderInfo.getOrderStatus());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getChangeStatus())){
//                    entityWrapper.eq("change_status",orderInfo.getChangeStatus());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getExpendStatus())){
//                    entityWrapper.eq("expend_status",orderInfo.getExpendStatus());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getCommentStatus())){
//                    entityWrapper.eq("comment_status",orderInfo.getCommentStatus());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getCreateTime())){
//                    entityWrapper.eq("create_time",orderInfo.getCreateTime());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getUpdateTime())){
//                    entityWrapper.eq("update_time",orderInfo.getUpdateTime());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getVersion())){
//                    entityWrapper.eq("version",orderInfo.getVersion());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getOrderMoney())){
//                    entityWrapper.eq("order_money",orderInfo.getOrderMoney());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getEmployeeId())){
//                    entityWrapper.eq("employee_id",orderInfo.getEmployeeId());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getStationNum())){
//                    entityWrapper.eq("station_num",orderInfo.getStationNum());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getChangeTime())){
//                    entityWrapper.eq("change_time",orderInfo.getChangeTime());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getFinishTime())){
//                    entityWrapper.eq("finish_time",orderInfo.getFinishTime());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getRefundRemark())){
//                    entityWrapper.eq("refund_remark",orderInfo.getRefundRemark());
//                    }
//                    if (StringUtils.isNotBlank(orderInfo.getRefundBy())){
//                    entityWrapper.eq("refund_by",orderInfo.getRefundBy());
//                    }
            return entityWrapper;
    }
}
