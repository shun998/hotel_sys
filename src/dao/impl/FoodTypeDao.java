package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import dao.IFoodTypeDao;
import entity.FoodType;
import utils.JdbcUtils;

public class FoodTypeDao implements IFoodTypeDao {
	private QueryRunner qr = JdbcUtils.getQuerrRunner();

//获取菜系
	@Override
	public Integer getFirstType() {
		try {
			String sql = "select * from foodtype";
			return qr.query(sql, new ScalarHandler<Integer>());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

//添加菜系
	@Override
	public void add(FoodType foodtype) {
		try {
			String sql = "INSERT INTO foodtype(typeName) VALUES(?);";
			qr.update(sql, foodtype.getTypeName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//根据id删除菜系
	@Override
	public void delete(int id) {
		try {
			String sql = "DELETE FROM foodtype WHERE id=?";
			qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//更新菜系
	@Override
	public void update(FoodType foodtype) {
		try {
			String sql = "UPDATE foodtype SET typeName=? WHERE id =?";
			qr.update(sql, foodtype.getTypeName(), foodtype.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

//查询所有的菜系
	@Override
	public List<FoodType> query() {
		try {
			String sql = "SELECT * FROM foodtype";
			return qr.query(sql, new BeanListHandler<FoodType>(FoodType.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//根据菜系id查询菜系
	@Override
	public FoodType findById(int id) {
		try {
			String sql = "SELECT * FROM foodtype where id =?";
			return qr.query(sql, new BeanHandler<FoodType>(FoodType.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//根据关键字查询菜系
	@Override
	public List<FoodType> query(String keyword) {
		try {
			String sql = "SELECT * FROM foodtype WHERE typeName LIKE ?";
			return qr.query(sql, new BeanListHandler<FoodType>(FoodType.class), "%" + keyword + "%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
