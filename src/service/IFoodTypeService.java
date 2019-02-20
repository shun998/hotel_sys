package service;

/**
 * ��ϵ��ҵ���߼��ӿ�
 */
import java.util.List;

import entity.FoodType;

public interface IFoodTypeService {
	// ��Ӳ�ϵ
	void add(FoodType foodtype);

	// ɾ����ϵ
	void delete(int id);

	// ���²�ϵ
	void update(FoodType foodtype);

	// ��ѯ���в�ϵ
	List<FoodType> query();

	// ���ݲ�ϵid��ѯ��ϵ
	FoodType findById(int id);

	// ���ݹؼ��ֲ�ѯ��ϵ
	List<FoodType> query(String keyword);

	// ��ȡ��ϵ����
	Integer getFirstType();
}
