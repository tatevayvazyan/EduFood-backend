package am.mse.eduFood.rest;

import am.mse.eduFood.domain.Food;
import am.mse.eduFood.service.FoodService;
import javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/food")
public class OrderRestApi {

    private final FoodService foodService;

    public OrderRestApi(FoodService foodService) {

        this.foodService = foodService;
    }

    @GetMapping("/all")
    List<Food> all() {
        return foodService.getAllFoods();
    }

    @GetMapping("/all/{category}")
    List<Food> allInCategory(@PathVariable String  category) {
        return foodService.getFoodByCategory(category);
    }

    @PostMapping("/")
    void newFood(@RequestBody
        Food newFood) {
         foodService.addFood(newFood);
    }

    @GetMapping("/{id}")
    Food one(@PathVariable
        Long id) {

        return foodService.getFoodById(id);
    }

    @GetMapping("/name/{name}")
    Food oneByName(@PathVariable
        String name) {

        return foodService.getFoodByName(name);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        foodService.deleteFoodById(id);
    }


    @PutMapping("/")
    Food update(@RequestBody
        Food newFood) {
       return foodService.updateFood(newFood);
    }
    @PostMapping("/{id}/{name}")
    Food uploadAsset(@RequestParam("file")
        MultipartFile file, @PathVariable Long id, @PathVariable String name) throws IOException, NotFoundException {
       return foodService.addAsset(id, file, name);
    }
}
