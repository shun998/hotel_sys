package entity;

import java.util.Date;

/**
 * 餐桌的实体类，封装餐桌的基本信息
 * 
 * @author ASUS
 *
 */
public class DinnerTable {

	private int id;// 餐桌id
	private String tableName;// 餐桌名称
	private int tableStatus;// 餐桌状态，0为空闲
	private Date orderDate;// 预定时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getTableStatus() {
		return tableStatus;
	}

	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "DinnerTable [id=" + id + ", tableName=" + tableName + ", tableStatus=" + tableStatus + ", orderDate="
				+ orderDate + "]";
	}
}
