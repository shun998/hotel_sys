package dao;

/**
 * 订单的操作接口
 */
import java.util.List;

import entity.Orders;
import utils.PageBean;

public interface IOrdersDao {
//更新订单
	void update(Orders orders);

//查询所有的订单
	List<Orders> query();

//添加订单
	void add(Orders orders);

//获取订单总数
	int getCount();

//分页查询订单
	void getAll(PageBean<Orders> pb);

//获取订单的总记录数
	int getTotalCount();
}
