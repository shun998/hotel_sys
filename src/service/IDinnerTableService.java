package service;

/**
 * ����ҵ���߼��Ľӿ�
 */
import java.util.List;

import entity.DinnerTable;

public interface IDinnerTableService {
	// ��Ӳ���

	void add(DinnerTable dt);
	// ɾ������

	void delete(int id);
	// ���²���

	void update(DinnerTable dt);
	// ��ѯȫ��

	List<DinnerTable> query();
	// ���ݲ�����id��ѯ����

	DinnerTable findById(int id);
	// ���ݹؼ��ֲ�ѯ����

	List<DinnerTable> query(String keyword);

	DinnerTable changeState(int id);
	// �˳�����

	void quitTable(int id);
}
