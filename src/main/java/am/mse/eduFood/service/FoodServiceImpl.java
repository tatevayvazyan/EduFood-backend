package am.mse.eduFood.service;

import am.mse.eduFood.domain.Food;
import am.mse.eduFood.domain.User;
import am.mse.eduFood.dto.UserDto;
import am.mse.eduFood.repository.FoodRepository;
import am.mse.eduFood.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {

        this.foodRepository = foodRepository;
    }


    @Override
    public void addFood(Food user) {

    }

    @Override
    public List<Food> getAllFoods() {

        return foodRepository.findAll();
    }

    @Override
    public Food getFoodByName(String name) {

        return foodRepository.findByName(name);
    }

    @Override
    public List<Food> getFoodByCategory(String category) {

        return foodRepository.findByCategory(category);
    }

    @Override
    public Food getFoodById(Long id) {

        return foodRepository.findById(id).orElse(null);
    }

    @Override
    public Food updateFood(Food food) {

        return null;
    }

    @Override
    public void deleteFoodById(Long id) {

        Food food = foodRepository.findById(id).orElse(null);
        if (food != null) {
            foodRepository.delete(food);
        }
    }
}
