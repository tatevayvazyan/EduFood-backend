package am.mse.eduFood.rest;

import am.mse.eduFood.dto.OrderDto;
import am.mse.eduFood.dto.OrderItemsDtoList;
import am.mse.eduFood.service.OrderService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderRestApi {

    private final OrderService orderService;

    public OrderRestApi(OrderService orderService) {

        this.orderService = orderService;
    }

    @PostMapping("/create/{id}")
    OrderDto uploadAsset(@RequestBody
        OrderItemsDtoList items, @PathVariable Long id) throws  NotFoundException {

       return orderService.createOrder(id, items.getItems());
    }
}
