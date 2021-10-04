package am.mse.eduFood.rest;

import am.mse.eduFood.dto.OrderDto;
import am.mse.eduFood.dto.OrderItemsDtoList;
import am.mse.eduFood.service.OrderService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://edufood.mskh.am", "http://localhost:4200"})
@RestController
@RequestMapping(value = "/order")
public class OrderRestApi {

    private final OrderService orderService;

    public OrderRestApi(OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping("/create/{id}")
    OrderDto createOrder(@RequestBody
        OrderItemsDtoList items, @PathVariable Long id) throws  NotFoundException {

       return orderService.createOrder(id, items.getItems());
    }

    @DeleteMapping("/delete/{id}")
    void deleteOrder( @PathVariable Long id){

        orderService.deleteOrder(id);
    }

    @GetMapping("/daily")
    List<OrderDto> allForToday(){
        return orderService.getAllForToday();
    }

    @GetMapping("/myOrders/{userId}")
    List<OrderDto> allForUser(@PathVariable Long userId) throws NotFoundException {
        return orderService.getAllForUser(userId);
    }
}
