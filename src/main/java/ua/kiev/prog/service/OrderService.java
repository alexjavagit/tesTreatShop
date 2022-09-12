package ua.kiev.prog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.kiev.prog.entity.Order;


public interface OrderService {

    void saveOrder(Order order);

    Page<Order> getAllOrders(Pageable pageable);

    Page<Order> findByOrderId(String orderId, Pageable pageable);

    Page<Order> findByName(String searchName, Pageable pageable);

    Page<Order> findByEmail(String searchEmail, Pageable pageable);

    Page<Order> findByOrderIdAndName(String searchId, String searchName, Pageable pageable);

    Page<Order> findByOrderIdAndEmail(String searchId, String searchEmail, Pageable pageable);

    Page<Order> findByNameAndEmail(String searchName, String searchEmail, Pageable pageable);

    Page<Order> findByOrderIdAndNameAndEmail(String searchId, String searchName, String searchEmail, Pageable pageable);
}
