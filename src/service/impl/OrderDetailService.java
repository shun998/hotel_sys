package service.impl;

import java.util.List;

import dao.IOrderDetailDao;
import entity.OrderDetail;
import factory.BeanFactory;
import service.IOrderDetailService;

public class OrderDetailService implements IOrderDetailService {

	IOrderDetailDao dao = BeanFactory.getInstance("orderDetailDao", IOrderDetailDao.class);

	@Override
	public void add(OrderDetail od) {
		dao.add(od);
	}

	@Override
	public List<OrderDetail> query() {
		return dao.query();
	}

	@Override
	public List<OrderDetail> findByOrderId(int id) {
		return dao.findByOrderid(id);
	}
}
