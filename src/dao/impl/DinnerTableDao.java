package dao.impl;

/**
 * �����Ĳ���ʵ����
 */
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import dao.IDinnerTableDao;
import entity.DinnerTable;
import utils.JdbcUtils;

public class DinnerTableDao implements IDinnerTableDao {
	private QueryRunner qr = JdbcUtils.getQuerrRunner();

//��Ӳ���
	@Override
	public void add(DinnerTable dt) {
		try {
			String sql = "INSERT dinnertable(tableName) VALUES(?)";
			qr.update(sql, dt.getTableName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

//���ݲ���id��ѯ����
	@Override
	public DinnerTable findById(int id) {
		String sql = "select * from dinnertable where id=?";
		try {
			return qr.query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//���ݲ�����idɾ������
	@Override
	public void delete(int id) {
		String sql = "delete from dinnertable where id=?";
		try {
			qr.update(sql, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//���²���
	@Override
	public void update(DinnerTable dt) {
		String sql = "UPDATE dinnertable SET tableStatus=?,orderDate=? WHERE id=?";
		Date date = dt.getOrderDate();
		try {
			JdbcUtils.getQuerrRunner().update(sql, dt.getTableStatus(), date, dt.getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//���ݹؼ��ֲ�ѯ����
	@Override
	public List<DinnerTable> query(String keyword) {
		String sql = "SELECT * FROM dinnertable WHERE tableName LIKE ?";
		try {
			return qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), "%" + keyword + "%");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//��ѯȫ������
	@Override
	public List<DinnerTable> query() {
		String sql = "select * from dinnertable";
		try {
			return qr.query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//�˳�����
	@Override
	public void quitTable(int id) {
		String sql = "UPDATE dinnertable SET tableStatus=?,orderDate=? WHERE id=?";

		try {
			JdbcUtils.getQuerrRunner().update(sql, 0, null, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
