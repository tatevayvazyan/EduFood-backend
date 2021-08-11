package am.mse.eduFood.service;

import am.mse.eduFood.domain.Food;
import am.mse.eduFood.domain.Order;
import am.mse.eduFood.domain.OrderItem;
import am.mse.eduFood.domain.User;
import am.mse.eduFood.dto.OrderDto;
import am.mse.eduFood.dto.OrderItemDto;
import am.mse.eduFood.repository.OrderItemsRepository;
import am.mse.eduFood.repository.OrderRepository;
import am.mse.eduFood.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemsRepository orderItemsRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    private final FoodService foodService;


    public OrderServiceImpl(OrderRepository orderRepository, OrderItemsRepository orderItemsRepository,
        UserRepository userRepository, UserService userService, FoodService foodService) {

        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.foodService = foodService;
    }

    @Override
    public OrderDto createOrder(Long userId, List<OrderItemDto> items) throws NotFoundException {

        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Order order = new Order();
        order.setUser(user);
        //        orderRepository.save(order);

        double totalPrice = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto itemDto : items) {
            Food food = foodService.getFoodById(itemDto.getItem());
            if(food == null){
                throw new NotFoundException("Food not found: "+itemDto.getItem());
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setFood(food);
            orderItem.setQuantity(itemDto.getQuantity());
            //            orderItemsRepository.save(orderItem);
            orderItems.add(orderItem);
            totalPrice += food.getPrice() * itemDto.getQuantity();
        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        orderRepository.save(order);



        return new OrderDto(order.getId(), order.getTotalPrice(), userService.getUserDto(order.getUser()), items);
    }
}
