package ua.kiev.prog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.kiev.prog.entity.Order;


public interface OrderService {

    void saveOrder(Order order);

    void deleteOrder(Order order);

    Page<Order> getAllOrders(Pageable pageable);

    Order findOrderById(Long orderId);

    Page<Order> findByOrderId(Long orderId, Pageable pageable);

    Page<Order> findByName(String searchName, Pageable pageable);

    Page<Order> findByEmail(String searchEmail, Pageable pageable);

    Page<Order> findByOrderIdAndName(Long searchId, String searchName, Pageable pageable);

    Page<Order> findByOrderIdAndEmail(Long searchId, String searchEmail, Pageable pageable);

    Page<Order> findByNameAndEmail(String searchName, String searchEmail, Pageable pageable);

    Page<Order> findByOrderIdAndNameAndEmail(Long searchId, String searchName, String searchEmail, Pageable pageable);

    Page<Order> findByUserId(Long userId, Pageable pageable);
}
