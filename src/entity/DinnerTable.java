package entity;

import java.util.Date;

/**
 * ������ʵ���࣬��װ�����Ļ�����Ϣ
 * 
 * @author ASUS
 *
 */
public class DinnerTable {

	private int id;// ����id
	private String tableName;// ��������
	private int tableStatus;// ����״̬��0Ϊ����
	private Date orderDate;// Ԥ��ʱ��

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
