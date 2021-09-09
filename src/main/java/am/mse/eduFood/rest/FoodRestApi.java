package am.mse.eduFood.rest;

import am.mse.eduFood.domain.Food;
import am.mse.eduFood.service.FoodService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "https://edufood.mskh.am")
@RestController
@RequestMapping(value = "/food")
public class FoodRestApi {

    private final FoodService foodService;

    public FoodRestApi(FoodService foodService) {

        this.foodService = foodService;
    }

    @GetMapping("/all")
    List<Food> all() {

        return foodService.getAllFoods();
    }

    @GetMapping("/category")
    List<Food> allInCategory(
        @RequestParam
            String category) {

        return foodService.getFoodByCategory(category);
    }

    @PostMapping("/create")
    Food newFood(
        @RequestBody
            Food newFood) {

        return foodService.addFood(newFood);
    }

    @GetMapping("/{id}")
    Food one(
        @PathVariable
            Long id) {

        return foodService.getFoodById(id);
    }

    @GetMapping("/name")
    Food oneByName(
        @RequestParam
            String name) {

        return foodService.getFoodByName(name);
    }

    @DeleteMapping("/{id}")
    void deleteFood(
        @PathVariable
            Long id) {

        foodService.deleteFoodById(id);
    }


    @PutMapping("/")
    Food update(
        @RequestBody
            Food newFood) {

        return foodService.updateFood(newFood);
    }

    @PostMapping("/asset/{id}/{name}")
    Food uploadAsset(
        @RequestParam("file")
            MultipartFile file,
        @PathVariable
            Long id,
        @PathVariable
            String name) throws IOException, NotFoundException {

        return foodService.addAsset(id, file, name);
    }
}
