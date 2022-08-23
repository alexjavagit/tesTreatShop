package ua.kiev.prog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.entity.Order;
import ua.kiev.prog.repository.AdminOrderRepository;

import java.util.List;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    private final AdminOrderRepository adminOrderRepository;

    public AdminOrderServiceImpl(AdminOrderRepository adminOrderRepository) {
        this.adminOrderRepository = adminOrderRepository;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return adminOrderRepository.getAllOrders();
    }
}
