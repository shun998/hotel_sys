package service;

/**
 * 餐桌业务逻辑的接口
 */
import java.util.List;

import entity.DinnerTable;

public interface IDinnerTableService {
	// 添加餐桌

	void add(DinnerTable dt);
	// 删除餐桌

	void delete(int id);
	// 更新餐桌

	void update(DinnerTable dt);
	// 查询全部

	List<DinnerTable> query();
	// 根据餐桌的id查询餐桌

	DinnerTable findById(int id);
	// 根据关键字查询餐桌

	List<DinnerTable> query(String keyword);

	DinnerTable changeState(int id);
	// 退出餐桌

	void quitTable(int id);
}
