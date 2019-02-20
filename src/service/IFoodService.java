package service;

/**
 * ��Ʒ��ҵ���߼��ӿ�
 */
import java.util.List;

import entity.Food;
import utils.PageBean;

public interface IFoodService {
	// ��Ӳ�Ʒ

	void add(Food food);

	// ɾ����Ʒ
	void delete(int id);

	// ���²�Ʒ
	void update(Food food);

	// ��ѯ���в�Ʒ
	List<Food> query();

	// ���ݲ�Ʒid��ѯ��Ʒ
	Food findById(int id);

	// ���ݹؼ��ֲ�ѯ��Ʒ
	List<Food> query(String keyword);

	// ��ҳ��ѯ
	public void getAll(PageBean<Food> pb);

	// ���ݲ�ϵ��ѯ��Ʒ
	List<Food> findByType(int type);
}
