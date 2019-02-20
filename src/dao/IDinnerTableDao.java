package dao;

/**
 * �����Ĳ����ӿ�
 */
import java.util.List;

import entity.DinnerTable;

public interface IDinnerTableDao {
//��Ӳ���
	void add(DinnerTable dt);

//ɾ������
	void delete(int id);

//���²���
	void update(DinnerTable dt);

//��ѯȫ��
	List<DinnerTable> query();

//���ݲ�����id��ѯ����
	DinnerTable findById(int id);

//���ݹؼ��ֲ�ѯ����
	List<DinnerTable> query(String keyword);

//�˳�����
	void quitTable(int id);
}
