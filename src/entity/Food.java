package entity;

/**
 * ��װ��Ʒ����Ϣ
 * 
 * @author ASUS
 *
 */
public class Food {

	private int id;// ��Ʒ��id
	private String foodName;// ��Ʒ������
	private int foodType_id;// ��Ʒ������ϵ��id
	private double price;// ��Ʒ�ļ۸�
	private double mprice;// ��Ա��
	private String remark;// ��Ʒ�ļ��
	private String img;// ��Ʒ����

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
