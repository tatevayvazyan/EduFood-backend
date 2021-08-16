package am.mse.eduFood.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;

    private LocalDateTime createdDate;

    private double totalPrice;

    private UserDto user;

    private List<OrderItemDto> orderItems;

}
