package ua.kiev.prog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.kiev.prog.entity.ShopOrder;


public interface OrderService {

    void saveOrder(ShopOrder shopOrder);

    void deleteOrder(ShopOrder shopOrder);

    Page<ShopOrder> getAllOrders(Pageable pageable);

    ShopOrder findOrderById(Long orderId);

    Page<ShopOrder> findByOrderId(Long orderId, Pageable pageable);

    Page<ShopOrder> findByName(String searchName, Pageable pageable);

    Page<ShopOrder> findByEmail(String searchEmail, Pageable pageable);

    Page<ShopOrder> findByOrderIdAndName(Long searchId, String searchName, Pageable pageable);

    Page<ShopOrder> findByOrderIdAndEmail(Long searchId, String searchEmail, Pageable pageable);

    Page<ShopOrder> findByNameAndEmail(String searchName, String searchEmail, Pageable pageable);

    Page<ShopOrder> findByOrderIdAndNameAndEmail(Long searchId, String searchName, String searchEmail, Pageable pageable);

    Page<ShopOrder> findByUserId(Long userId, Pageable pageable);
}
