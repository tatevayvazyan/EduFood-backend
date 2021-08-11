package am.mse.eduFood.dto;

import am.mse.eduFood.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    private double totalPrice;

    private UserDto user;

    private List<OrderItemDto> orderItems;

}
