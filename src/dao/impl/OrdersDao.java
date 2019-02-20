package dao.impl;

/**
 * 订单的操作实现类
 */
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.IOrdersDao;
import entity.Orders;
import utils.JdbcUtils;
import utils.PageBean;

public class OrdersDao implements IOrdersDao {

	private QueryRunner qr = JdbcUtils.getQuerrRunner();

//添加订单
	@Override
	public void add(Orders orders) {
		String sql = " INSERT orders(table_id,orderDate,totalPrice) VALUES(?,?,?)";
		try {
			qr.update(sql, orders.getTable_id(), orders.getOrderDate(), orders.getTotalPrice());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//获取订单数
	public int getCount() {
		String sql = "select count(*) from orders";
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//更新订单
	@Override
	public void update(Orders orders) {
		String sql = "UPDATE orders SET orderStatus =? WHERE id=?";
		try {
			qr.update(sql, orders.getOrderStatus(), orders.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

//查询所有的订单
	@Override
	public List<Orders> query() {
		String sql = "SELECT * FROM orders";
		try {
			return qr.query(sql, new BeanListHandler<Orders>(Orders.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//获取全部订单数
	@Override
	public void getAll(PageBean<Orders> pb) {

		// 2. 查询总记录数; 设置到pb对象中
		int totalCount = this.getTotalCount();
		pb.setTotalCount(totalCount);

		/*
		 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！ 如果当前页为末页，再点下一页显示有问题！ 解决： 1. 如果当前页 <= 0;
		 * 当前页设置当前页为1; 2. 如果当前页 > 最大页数； 当前页设置为最大页数
		 */
		// 判断
		if (pb.getCurrentPage() <= 0) {
			pb.setCurrentPage(1); // 把当前页设置为1
		} else if (pb.getCurrentPage() > pb.getTotalPage()) {
			pb.setCurrentPage(pb.getTotalPage()); // 把当前页设置为最大页数
		}

		// 1. 获取当前页： 计算查询的起始行、返回的行数
		int currentPage = pb.getCurrentPage();
		int index = (currentPage - 1) * pb.getPageCount(); // 查询的起始行
		int count = pb.getPageCount(); // 查询返回的行数

		// 3. 分页查询数据; 把查询到的数据设置到pb对象中
		String sql = "select * from orders limit ?,?";

		try {
			// 根据当前页，查询当前页数据(一页数据)
			List<Orders> pageData = qr.query(sql, new BeanListHandler<Orders>(Orders.class), index, count);
			// 设置到pb对象中
			pb.setPageData(pageData);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

//获取查询的订单数
	@Override
	public int getTotalCount() {
		String sql = "select count(*) from orders";
		try {
			// 执行查询， 返回结果的第一行的第一列
			Long count = qr.query(sql, new ScalarHandler<Long>());
			return count.intValue();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
