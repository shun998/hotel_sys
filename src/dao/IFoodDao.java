package dao;

/**
 * ��Ʒ�Ĳ����ӿ�
 */
import java.util.List;

import entity.Food;
import utils.PageBean;

public interface IFoodDao {
//��Ӳ�Ʒ
	void add(Food food);

//ɾ����Ʒ
	void delete(int id);

//���²�Ʒ
	void update(Food food);

//��ѯ���в�Ʒ
	List<Food> query();

//���ݲ�Ʒid��ѯ��Ʒ
	Food findById(int id);

//���ݹؼ��ֲ�ѯ��Ʒ
	List<Food> query(String keyword);

//���ݲ�ϵ��ѯ��Ʒ
	List<Food> findByType(int type);

//��ҳ��ѯ
	void getAll(PageBean<Food> pb);

//�õ��ܼ�¼��
	int getTotalCount(PageBean<Food> pb);
}
