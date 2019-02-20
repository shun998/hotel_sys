package service.impl;

/**
 * 餐桌业务逻辑的实现类
 */
import java.util.Date;
import java.util.List;

import dao.IDinnerTableDao;
import entity.DinnerTable;
import factory.BeanFactory;
import service.IDinnerTableService;

public class DinnerTableService implements IDinnerTableService {

	IDinnerTableDao dao = BeanFactory.getInstance("dinnerTableDao", IDinnerTableDao.class);

	// 添加餐桌
	@Override
	public void add(DinnerTable dt) {
		dao.add(dt);
	}

	// 删除餐桌
	@Override
	public void delete(int id) {
		dao.delete(id);
	}
	// 更新餐桌

	@Override
	public void update(DinnerTable dt) {
		dao.update(dt);
	}

	// 查询全部
	@Override
	public List<DinnerTable> query(String keyword) {
		return dao.query(keyword);
	}

	// 查询全部的餐桌
	@Override
	public List<DinnerTable> query() {
		return dao.query();
	}
//改变餐桌状态

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

	// 根据餐桌的id查询餐桌
	@Override
	public DinnerTable findById(int id) {
		return dao.findById(id);
	}

	// 退出餐桌
	@Override
	public void quitTable(int id) {
		dao.quitTable(id);
	}
}
