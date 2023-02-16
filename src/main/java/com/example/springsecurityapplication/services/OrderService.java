package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.Order;
import com.example.springsecurityapplication.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    ///////////
    // удалению заказа по id
    @Transactional
    public void deleteOrder(int id){
        orderRepository.deleteById(id);
    }

    //получение заказа по id
    public Order getOrderId(int id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElse(null);
    }

    // изменение статуса заказа админом
    @Transactional
    public void updateStatus1(Order order) {
        order.setStatus(Status.Processing);
        orderRepository.save(order);
    }
    @Transactional
    public void updateStatus2(Order order) {
        order.setStatus(Status.In_progress);
        orderRepository.save(order);
    }
    @Transactional
    public void updateStatus3(Order order) {
        order.setStatus(Status.Completed);
        orderRepository.save(order);
    }
    @Transactional
    public void updateStatus4(Order order) {
        order.setStatus(Status.Canceled);
        orderRepository.save(order);
    }

    // отмена заказа пользователем
    @Transactional
    public void editStatusCanceled(Order order) {
        order.setStatus(Status.Canceled);
        orderRepository.save(order);
    }

    // изменение статуса заказа продавцом

    public Object geAllOrders() {
        return null;
    }
    //////////

    // поиск по 4 цифрам
    @Transactional
    public List<Order> findOrderNumber (String string){
        List<Order> orderNumber = orderRepository.findOrderNumber(string);
        return orderNumber;
    }

}















