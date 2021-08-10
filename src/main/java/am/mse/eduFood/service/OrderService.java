package am.mse.eduFood.service;


import am.mse.eduFood.domain.Asset;
import am.mse.eduFood.domain.Food;
import am.mse.eduFood.domain.Order;

import java.io.IOException;
import java.util.List;

public interface OrderService {

    Order createOrder(List<Long> foodIds, Long userId);
    void delete(Long id);

}
