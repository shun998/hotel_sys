package service.impl;
/**
 * ������ҵ���߼��ӿ�
 */
import java.util.List;

import dao.IOrdersDao;
import entity.Orders;
import factory.BeanFactory;
import service.IOrdersService;
import utils.PageBean;

public class OrdersService implements IOrdersService {

	IOrdersDao dao = BeanFactory.getInstance("ordersDao", IOrdersDao.class);
	//���¶���

	@Override
	public void update(Orders orders) {
		dao.update(orders);
	}
	//��ѯ���еĶ���
	@Override
	public List<Orders> query() {
		return dao.query();
	}
	//��Ӷ���
	@Override
	public void add(Orders orders) {
		dao.add(orders);
	}
	//��ȡ��������
	@Override
	public int getCount() {
		return dao.getCount();
	}
	//��ҳ��ѯ����
	@Override
	public void getAll(PageBean<Orders> pb) {
		try {
			dao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
