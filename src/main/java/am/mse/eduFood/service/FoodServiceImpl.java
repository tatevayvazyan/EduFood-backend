package am.mse.eduFood.service;

import am.mse.eduFood.domain.Asset;
import am.mse.eduFood.domain.Food;
import am.mse.eduFood.dto.AssetDto;
import am.mse.eduFood.repository.FoodRepository;
import javassist.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    private final AssetService assetService;

    public FoodServiceImpl(FoodRepository foodRepository, AssetService assetService) {

        this.foodRepository = foodRepository;
        this.assetService = assetService;
    }


    @Override
    public Food addFood(Food food) {

        return foodRepository.save(food);
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
    public Food updateFood(Food food) throws NotFoundException {

        Food updateFood = foodRepository.findById(food.getId()).orElseThrow(() -> new NotFoundException("Food not found"));
        if (food.getPrice() != 0.0) {
            updateFood.setPrice(food.getPrice());
        }
        if (!StringUtils.isBlank(food.getCategory())) {
            updateFood.setCategory(food.getCategory());
        }
        if (!StringUtils.isBlank(food.getDescription())) {
            updateFood.setDescription(food.getDescription());
        }
        if (!StringUtils.isBlank(food.getName())) {
            updateFood.setName(food.getName());
        }

        return foodRepository.save(updateFood);
    }

    @Override
    public void deleteFoodById(Long id) {

        foodRepository.findById(id).ifPresent(foodRepository::delete);
    }

    @Override
    public AssetDto addAsset(Long foodId, MultipartFile image, String name) throws IOException, NotFoundException {

        Food food = foodRepository.findById(foodId).orElseThrow(() -> new NotFoundException("Food not found"));
        System.out.println("food found for uploading image" + food.getName());

        Asset asset = new Asset();
        asset.setName(name);
        asset.setUri("/food/get/asset/"+image.getOriginalFilename());
        asset.setFood(food);
        asset.setPhoto(image.getBytes());
        assetService.save(asset);

        return new AssetDto(asset.getId(),asset.getUri(),asset.getName());
    }



    private static byte[] readBytesFromFile(String filePath) throws IOException {
        File inputFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(inputFile);

        byte[] fileBytes = new byte[(int) inputFile.length()];
        inputStream.read(fileBytes);
        inputStream.close();

        return fileBytes;
    }
}
