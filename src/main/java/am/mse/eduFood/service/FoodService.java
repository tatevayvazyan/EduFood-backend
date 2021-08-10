package am.mse.eduFood.service;


import am.mse.eduFood.domain.Food;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FoodService {

    void addFood(Food food);
    List<Food> getAllFoods();
    Food getFoodByName(String name);
    List<Food> getFoodByCategory(String category);
    Food getFoodById(Long id);
    Food updateFood(Food food);
    void deleteFoodById(Long id);
    Food addAsset(Long foodId, MultipartFile image, String name) throws IOException, NotFoundException;
}
