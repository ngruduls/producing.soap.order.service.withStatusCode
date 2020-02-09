package hello;

import org.springframework.stereotype.Component;

@Component
public class OrderItem {
	public String identifier;
    public int price;
    public Boolean used;
    
    public OrderItem() {
    	this.identifier = "";
    	this.price = -1;
    	this.used = false;
    }
    
    public OrderItem(String identifier, int price, Boolean used) {
    	this.identifier = identifier;
    	this.price = price;
    	this.used = used;
    }
    
    String getWarehouse() {
		if (this.price < 100) {
			return "A";
		} else if (this.price >= 100 && this.price < 200) {
			return "B";
		} else {
			return "";
		}
    }
}
