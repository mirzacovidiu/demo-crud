package democrud.democrud.service;

import democrud.democrud.model.Order;
import democrud.democrud.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {return orderRepository.findAll(); }

    public Optional<Order> findOrderById(Long id) {return orderRepository.findOrderById(id);}


    public Order createNewOrder(Order order) {return orderRepository.save(order);}


    public String updateOrder(Order order){
        String response = "";
        Optional<Order> orderToUpdate = orderRepository.findOrderById(order.getId());
        if(orderToUpdate.isPresent()) {
            orderRepository.save(order);
            response = "Order was updated in DB";
        }else {
            response = "Order with id " + order.getId() + " was not found!";
        }
        return response;
    }


    public String deleteOrderById(Long id){
        String response = "";
        Optional<Order> orderToDelete = orderRepository.findOrderById(id);

        if(orderToDelete.isPresent()) {
            orderRepository.deleteOrderById(id);
            response = "Order with id " + id + " was deleted!";
        }else {
            response = "Order with id " + id + " was not found!";
        }
        return response;
    }
//    public String deleteProductFromOrder(Long idProduct, Long idOrder){
//
//    }
}



