package am.mse.eduFood.service;


import am.mse.eduFood.dto.OrderDto;
import am.mse.eduFood.dto.OrderItemDto;
import javassist.NotFoundException;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(Long userId, List<OrderItemDto> items) throws NotFoundException;
    void deleteOrder(Long orderId);
    List<OrderDto> getAllForToday();

    List<OrderDto> getAllForUser(Long userId) throws NotFoundException;
}
