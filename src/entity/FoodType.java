package entity;

/**
 * ��ϵ��ʵ����
 * 
 * @author ASUS
 *
 */
public class FoodType {
	private int id;// ��ϵid
	private String typeName;// ��ϵ����

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public String toString() {
		return "FoodType [id=" + id + ", typeName=" + typeName + "]";
	}

}
