package sponsorship.model.vo;

public class TotalOrder {
	
	private int price;
	private int count;
	public TotalOrder() {
		super();
	}
	public TotalOrder(int price, int count) {
		super();
		this.price = price;
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
