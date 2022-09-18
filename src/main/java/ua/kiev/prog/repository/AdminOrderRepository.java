package ua.kiev.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.kiev.prog.entity.ShopOrder;

import java.util.List;

public interface AdminOrderRepository extends JpaRepository<ShopOrder, Long> {

    @Query("SELECT o FROM ShopOrder o ORDER BY o.id DESC")
    List<ShopOrder> getAllOrders();
}
