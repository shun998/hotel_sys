package service;

import java.util.List;

import entity.Orders;
import utils.PageBean;

public interface IOrdersService {

	void update(Orders orders);

	List<Orders> query();

	void add(Orders orders);

	int getCount();

	public void getAll(PageBean<Orders> pb);
}
