package dao;

/**
 * ��������Ĳ����ӿ�
 */
import java.util.List;

import entity.OrderDetail;

public interface IOrderDetailDao {
//��Ӷ�������
	void add(OrderDetail od);

//��ѯ���еĶ�������
	List<OrderDetail> query();

//���ݶ���id��ѯ��������
	List<OrderDetail> findByOrderid(int id);
}
