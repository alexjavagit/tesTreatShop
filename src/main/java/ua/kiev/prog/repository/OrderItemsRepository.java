package ua.kiev.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kiev.prog.entity.OrderItems;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
    @Query("SELECT o FROM OrderItems o WHERE o.shopOrder.id=:id")
    List<OrderItems> findByOrderId(Long id);
}
