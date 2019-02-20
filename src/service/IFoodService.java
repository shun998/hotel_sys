package service;

/**
 * 菜品的业务逻辑接口
 */
import java.util.List;

import entity.Food;
import utils.PageBean;

public interface IFoodService {
	// 添加菜品

	void add(Food food);

	// 删除菜品
	void delete(int id);

	// 更新菜品
	void update(Food food);

	// 查询所有菜品
	List<Food> query();

	// 根据菜品id查询菜品
	Food findById(int id);

	// 根据关键字查询菜品
	List<Food> query(String keyword);

	// 分页查询
	public void getAll(PageBean<Food> pb);

	// 根据菜系查询菜品
	List<Food> findByType(int type);
}
