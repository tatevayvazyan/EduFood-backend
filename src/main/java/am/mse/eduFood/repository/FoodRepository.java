package am.mse.eduFood.repository;

import am.mse.eduFood.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByCategory(String category);
    Food findByName(String name);
}


