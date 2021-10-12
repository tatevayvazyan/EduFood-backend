package am.mse.eduFood.rest;

import am.mse.eduFood.domain.Food;
import am.mse.eduFood.dto.AssetDto;
import am.mse.eduFood.service.AssetService;
import am.mse.eduFood.service.FoodService;
import javassist.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"https://edufood.mskh.am", "http://localhost:4200"})
@RestController
@RequestMapping(value = "/food")
public class FoodRestApi {

    private final AssetService assetService;
    private final FoodService foodService;

    public FoodRestApi(FoodService foodService, AssetService assetService) {
        this.foodService = foodService;
        this.assetService = assetService;
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
            Food newFood) throws NotFoundException {

        return foodService.updateFood(newFood);
    }

    @PostMapping("/asset/{id}/{name}")
    AssetDto uploadAsset(
        @RequestParam("file")
            MultipartFile file,
        @PathVariable
            Long id,
        @PathVariable
            String name) throws IOException, NotFoundException {

        return foodService.addAsset(id, file, name);
    }

    @GetMapping(value = "/get/asset/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    ResponseEntity getAsset(@PathVariable String name) throws NotFoundException {

        return ResponseEntity.ok(assetService.getImage(name));
    }

}
