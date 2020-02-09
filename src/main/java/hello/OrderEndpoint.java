package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import io.spring.guides.gs_producing_web_service.GetOrderRequest;
import io.spring.guides.gs_producing_web_service.GetOrderResponse;
import io.spring.guides.gs_producing_web_service.Order;
import io.spring.guides.gs_producing_web_service.Response;

@Endpoint
public class OrderEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
	
	private OrderItem orderItem;

	@Autowired
	public OrderEndpoint(OrderItem order) {
		this.orderItem = order;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
	@ResponsePayload
	public GetOrderResponse getCountry(@RequestPayload GetOrderRequest request) {
		
		Order order = request.getOrder();
		String name = order.getName();
		int price = order.getPrice();
		Boolean used = order.isUsed();
		if (used == null) {
			used = false;
		}
		orderItem = new OrderItem(name, price, used);
		
		GetOrderResponse response = new GetOrderResponse();
		Response responseData = new Response();
		
		String warehouse = orderItem.getWarehouse();
		if  (warehouse.equals("A") || warehouse.equals("B")) {
			responseData.setCode(1);
			responseData.setWarehouse(warehouse);
		} else {
			responseData.setCode(0);
		}
		response.setResponse(responseData);
		return response;
	}
}