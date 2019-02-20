package dao.impl;

/**
 * 订单详情的操作实现类
 */
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.IOrderDetailDao;
import entity.OrderDetail;
import utils.JdbcUtils;

public class OrderDetailDao implements IOrderDetailDao {

	private QueryRunner qr = JdbcUtils.getQuerrRunner();

//添加订单详情
	@Override
	public void add(OrderDetail od) {
		String sql = " INSERT orderdetail(orderId,food_id,foodCount) VALUES(?,?,?)";
		try {
			qr.update(sql, od.getOrderId(), od.getFood_id(), od.getFoodCount());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

//查询全部订单详情
	@Override
	public List<OrderDetail> query() {
		try {
			String sql = "SELECT * FROM orderdetail";
			return qr.query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//根据订单id查询订单详情
	@Override
	public List<OrderDetail> findByOrderid(int id) {
		try {
			String sql = "SELECT * FROM orderdetail where orderId=?";
			return qr.query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
