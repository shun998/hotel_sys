package service;

/**
 * ���������ҵ���߼��ӿ�
 */
import java.util.List;

import entity.OrderDetail;

public interface IOrderDetailService {
	// ��Ӷ�������
	void add(OrderDetail od);

	// ��ѯ���еĶ�������
	List<OrderDetail> query();

	// ���ݶ���id��ѯ��������
	List<OrderDetail> findByOrderId(int id);
}
