package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.kiev.prog.entity.ShopOrder;
import ua.kiev.prog.repository.OrderRepository;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    public ShopOrder findOrderById(Long orderId) {
        ShopOrder shopOrder = orderRepository.getOne(orderId);
        return shopOrder;
    }

    public void saveOrder(ShopOrder shopOrder) {
        orderRepository.save(shopOrder);
    }

    public void deleteOrder(ShopOrder shopOrder) {
        orderRepository.delete(shopOrder);
    }

    @Override
    public Page<ShopOrder> getAllOrders(Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findAllBy(pageable);
        return list;
    }

    public Page<ShopOrder> findByOrderId(Long orderId, Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findByOrderId(orderId, pageable);
        return list;
    }

    public Page<ShopOrder> findByName(String searchName, Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findByName(searchName, pageable);
        return list;
    }

    public Page<ShopOrder> findByEmail(String searchEmail, Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findByEmail(searchEmail, pageable);
        return list;
    }

    public Page<ShopOrder> findByOrderIdAndName(Long searchId, String searchName, Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findByOrderIdAndName(searchId, searchName, pageable);
        return list;
    }

    public Page<ShopOrder> findByOrderIdAndEmail(Long searchId, String searchEmail, Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findByOrderIdAndEmail(searchId, searchEmail, pageable);
        return list;
    }

    public Page<ShopOrder> findByNameAndEmail(String searchName, String searchEmail, Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findByNameAndEmail(searchName, searchEmail, pageable);
        return list;
    }

    public Page<ShopOrder> findByOrderIdAndNameAndEmail(Long searchId, String searchName, String searchEmail, Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findByOrderIdAndNameAndEmail(searchId, searchName, searchEmail, pageable);
        return list;
    }

    public Page<ShopOrder> findByUserId(Long userId, Pageable pageable) {
        Page<ShopOrder> list = orderRepository.findByUserId(userId, pageable);
        return list;
    }
}
