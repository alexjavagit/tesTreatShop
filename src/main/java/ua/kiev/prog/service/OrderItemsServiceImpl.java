package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.kiev.prog.entity.OrderItems;
import ua.kiev.prog.repository.OrderItemsRepository;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {
    @Autowired
    OrderItemsRepository orderItemsRepository;

    public void saveOrder(OrderItems orderItems) {
        orderItemsRepository.save(orderItems);
    }
}
