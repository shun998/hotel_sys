package service.impl;
/**
 * 订单的业务逻辑接口
 */
import java.util.List;

import dao.IOrdersDao;
import entity.Orders;
import factory.BeanFactory;
import service.IOrdersService;
import utils.PageBean;

public class OrdersService implements IOrdersService {

	IOrdersDao dao = BeanFactory.getInstance("ordersDao", IOrdersDao.class);
	//更新订单

	@Override
	public void update(Orders orders) {
		dao.update(orders);
	}
	//查询所有的订单
	@Override
	public List<Orders> query() {
		return dao.query();
	}
	//添加订单
	@Override
	public void add(Orders orders) {
		dao.add(orders);
	}
	//获取订单总数
	@Override
	public int getCount() {
		return dao.getCount();
	}
	//分页查询订单
	@Override
	public void getAll(PageBean<Orders> pb) {
		try {
			dao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
