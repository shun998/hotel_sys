package entity;

/**
 * 封装订单信息
 */
import java.util.Date;

public class Orders {
	private int id; // -- 主键
	private int table_id;// -- 外键： 餐桌编号
	private Date orderDate;// -- 下单日期
	private double totalPrice;// -- 订单所有菜需要的总金额
	private int orderStatus;// 订单状态，结账和未结账

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTable_id() {
		return table_id;
	}

	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", table_id=" + table_id + ", orderDate=" + orderDate + ", totalPrice=" + totalPrice
				+ ", orderStatus=" + orderStatus + "]";
	}

}