package entity;

/**
 * 菜系的实体类
 * 
 * @author ASUS
 *
 */
public class FoodType {
	private int id;// 菜系id
	private String typeName;// 菜系名称

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
