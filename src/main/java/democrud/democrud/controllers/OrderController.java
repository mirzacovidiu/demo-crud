package democrud.democrud.controllers;

import democrud.democrud.model.Order;
import democrud.democrud.model.Product;
import democrud.democrud.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {this.orderService = orderService;}

    @GetMapping("/all")
    public ResponseEntity<List<Order>> readAllOrders(){
        List<Order> orderList = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
    }
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> readOrderById(@PathVariable Long id){
        Order orderToBeFoundById = orderService.findOrderById(id).orElseThrow(() -> new RuntimeException("Order not found!"));
        return ResponseEntity.ok(orderToBeFoundById);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {

        Order newOrder = orderService.createNewOrder(order);
        List<Product> productList = order.getProductList();
;
        newOrder.setProductList(productList);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(newOrder);

    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {

        Optional<Order> order=orderService.findOrderById(id);
//        String response = orderService.deleteorderById(id);
        if (order.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrderById(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderService.deleteOrderById(id));
        }
    }

}
