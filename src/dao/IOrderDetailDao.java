package dao;

/**
 * 订单详情的操作接口
 */
import java.util.List;

import entity.OrderDetail;

public interface IOrderDetailDao {
//添加订单详情
	void add(OrderDetail od);

//查询所有的订单详情
	List<OrderDetail> query();

//根据订单id查询订单详情
	List<OrderDetail> findByOrderid(int id);
}
