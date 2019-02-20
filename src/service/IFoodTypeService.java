package service;

/**
 * 菜系的业务逻辑接口
 */
import java.util.List;

import entity.FoodType;

public interface IFoodTypeService {
	// 添加菜系
	void add(FoodType foodtype);

	// 删除菜系
	void delete(int id);

	// 更新菜系
	void update(FoodType foodtype);

	// 查询所有菜系
	List<FoodType> query();

	// 根据菜系id查询菜系
	FoodType findById(int id);

	// 根据关键字查询菜系
	List<FoodType> query(String keyword);

	// 获取菜系类型
	Integer getFirstType();
}
