package am.mse.eduFood.service;


import am.mse.eduFood.domain.Food;
import am.mse.eduFood.domain.User;
import am.mse.eduFood.dto.UserDto;

import java.util.List;

public interface FoodService {

    void addFood(Food user);
    List<Food> getAllFoods();
    Food getFoodByName(String name);
    List<Food> getFoodByCategory(String category);
    Food getFoodById(Long id);
    Food updateFood(Food food);
    void deleteFoodById(Long id);
}
