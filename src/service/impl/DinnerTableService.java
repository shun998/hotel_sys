package service.impl;

/**
 * ����ҵ���߼���ʵ����
 */
import java.util.Date;
import java.util.List;

import dao.IDinnerTableDao;
import entity.DinnerTable;
import factory.BeanFactory;
import service.IDinnerTableService;

public class DinnerTableService implements IDinnerTableService {

	IDinnerTableDao dao = BeanFactory.getInstance("dinnerTableDao", IDinnerTableDao.class);

	// ��Ӳ���
	@Override
	public void add(DinnerTable dt) {
		dao.add(dt);
	}

	// ɾ������
	@Override
	public void delete(int id) {
		dao.delete(id);
	}
	// ���²���

	@Override
	public void update(DinnerTable dt) {
		dao.update(dt);
	}

	// ��ѯȫ��
	@Override
	public List<DinnerTable> query(String keyword) {
		return dao.query(keyword);
	}

	// ��ѯȫ���Ĳ���
	@Override
	public List<DinnerTable> query() {
		return dao.query();
	}
//�ı����״̬

	@Override
	public DinnerTable changeState(int id) {
		DinnerTable table = dao.findById(id);

		int status = table.getTableStatus();
		if (status == 0) {
			status = 1;
			Date date = new Date();
			table.setOrderDate(date);
		} else if (status == 1) {
			status = 0;
			table.setOrderDate(null);
		}
		table.setTableStatus(status);
		dao.update(table);
		return table;
	}

	// ���ݲ�����id��ѯ����
	@Override
	public DinnerTable findById(int id) {
		return dao.findById(id);
	}

	// �˳�����
	@Override
	public void quitTable(int id) {
		dao.quitTable(id);
	}
}
