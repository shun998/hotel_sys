package service.impl;

/**
 *????????????????
 */
import dao.IFoodDao;
import entity.Food;
import factory.BeanFactory;
import service.IFoodService;
import utils.PageBean;

import java.util.List;

public class FoodService implements IFoodService {
	IFoodDao dao = BeanFactory.getInstance("foodDao", IFoodDao.class);

	// ?????
	@Override
	public void add(Food food) {
		dao.add(food);
	}

	// ??????
	@Override
	public void delete(int id) {
		dao.delete(id);

	}

	// ??????
	@Override
	public void update(Food food) {
		dao.update(food);

	}

	// ??????§Ó??
	@Override
	public List<Food> query() {
		return dao.query();
	}

	// ??????id??????
	@Override
	public Food findById(int id) {
		return dao.findById(id);
	}

	// ?????????????
	@Override
	public List<Food> query(String keyword) {
		return dao.query(keyword);
	}

	// ??????
	@Override
	public void getAll(PageBean<Food> pb) {
		try {
			dao.getAll(pb);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// ????????????
	@Override
	public List<Food> findByType(int type) {
		return dao.findByType(type);
	}
}
