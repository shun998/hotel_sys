package dao;

/**
 * �����Ĳ����ӿ�
 */
import java.util.List;

import entity.Orders;
import utils.PageBean;

public interface IOrdersDao {
//���¶���
	void update(Orders orders);

//��ѯ���еĶ���
	List<Orders> query();

//��Ӷ���
	void add(Orders orders);

//��ȡ��������
	int getCount();

//��ҳ��ѯ����
	void getAll(PageBean<Orders> pb);

//��ȡ�������ܼ�¼��
	int getTotalCount();
}
