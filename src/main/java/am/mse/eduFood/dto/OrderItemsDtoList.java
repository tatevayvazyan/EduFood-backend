package am.mse.eduFood.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderItemsDtoList {

    private List<OrderItemDto> items;

    public OrderItemsDtoList() {

        this.items = new ArrayList<>();
    }
}
