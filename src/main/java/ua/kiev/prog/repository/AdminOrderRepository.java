package ua.kiev.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kiev.prog.entity.Order;

import java.util.List;

public interface AdminOrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o ORDER BY o.id DESC")
    List<Order> getAllOrders();
}
