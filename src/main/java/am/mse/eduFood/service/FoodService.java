package am.mse.eduFood.service;


import am.mse.eduFood.domain.Food;
import am.mse.eduFood.dto.AssetDto;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FoodService {

    Food addFood(Food food);
    List<Food> getAllFoods();
    Food getFoodByName(String name);
    List<Food> getFoodByCategory(String category);
    Food getFoodById(Long id);
    Food updateFood(Food food) throws NotFoundException;
    void deleteFoodById(Long id);
    AssetDto addAsset(Long foodId, MultipartFile image, String name) throws IOException, NotFoundException;
}
