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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        order.setCreatedDate(LocalDateTime.now());
        //        orderRepository.save(order);

        double totalPrice = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto itemDto : items) {
            Food food = foodService.getFoodById(itemDto.getItem());
            if (food == null) {
                throw new NotFoundException("Food not found: " + itemDto.getItem());
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



        return new OrderDto(order.getId(), order.getCreatedDate(), order.getTotalPrice(), userService.getUserDto(order.getUser()),
            items);
    }

    @Override
    public List<OrderDto> getAllForToday() {

        List<Order> orders = orderRepository.findByCreatedDateBetween(LocalDate.now().atTime(0, 0), LocalDate.now().atTime(23, 59));
        return orders.stream().map(this::getOrderDto).collect(Collectors.toList());
    }

    public OrderDto getOrderDto(Order order) {

        List<OrderItemDto> orderItemDtos =
            order.getOrderItems().stream().map(oi -> new OrderItemDto(oi.getFood().getId(), oi.getQuantity(), oi.getFood().getName())).collect(Collectors.toList());

        return new OrderDto(order.getId(), order.getCreatedDate(), order.getTotalPrice(), userService.getUserDto(order.getUser()),
            orderItemDtos);
    }


}
