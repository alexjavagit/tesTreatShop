package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.kiev.prog.entity.Order;
import ua.kiev.prog.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        Page<Order> list = orderRepository.findAllBy(pageable);
        return list;
    }

    public Page<Order> findByOrderId(String orderId, Pageable pageable) {
        Page<Order> list = orderRepository.findByOrderId(orderId, pageable);
        return list;
    }

    public Page<Order> findByName(String searchName, Pageable pageable) {
        Page<Order> list = orderRepository.findByName(searchName, pageable);
        return list;
    }

    public Page<Order> findByEmail(String searchEmail, Pageable pageable) {
        Page<Order> list = orderRepository.findByEmail(searchEmail, pageable);
        return list;
    }

    public Page<Order> findByOrderIdAndName(String searchId, String searchName, Pageable pageable) {
        Page<Order> list = orderRepository.findByOrderIdAndName(searchId, searchName, pageable);
        return list;
    }

    public Page<Order> findByOrderIdAndEmail(String searchId, String searchEmail, Pageable pageable) {
        Page<Order> list = orderRepository.findByOrderIdAndEmail(searchId, searchEmail, pageable);
        return list;
    }

    public Page<Order> findByNameAndEmail(String searchName, String searchEmail, Pageable pageable) {
        Page<Order> list = orderRepository.findByNameAndEmail(searchName, searchEmail, pageable);
        return list;
    }

    public Page<Order> findByOrderIdAndNameAndEmail(String searchId, String searchName, String searchEmail, Pageable pageable) {
        Page<Order> list = orderRepository.findByOrderIdAndNameAndEmail(searchId, searchName, searchEmail, pageable);
        return list;
    }
}
