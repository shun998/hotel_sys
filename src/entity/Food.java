package entity;

/**
 * 封装菜品的信息
 * 
 * @author ASUS
 *
 */
public class Food {

	private int id;// 菜品的id
	private String foodName;// 菜品的名称
	private int foodType_id;// 菜品所属菜系的id
	private double price;// 菜品的价格
	private double mprice;// 会员价
	private String remark;// 菜品的简介
	private String img;// 菜品样例

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodType_id() {
		return foodType_id;
	}

	public void setFoodType_id(int foodType_id) {
		this.foodType_id = foodType_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMprice() {
		return mprice;
	}

	public void setMprice(double mprice) {
		this.mprice = mprice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", foodName=" + foodName + ", foodType_id=" + foodType_id + ", price=" + price
				+ ", mprice=" + mprice + ", remark=" + remark + ", img=" + img + "]";
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		Food f = (Food) obj;
		return f.getId() == this.id;
	}
}
