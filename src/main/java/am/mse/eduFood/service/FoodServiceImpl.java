package am.mse.eduFood.service;

import am.mse.eduFood.domain.Asset;
import am.mse.eduFood.domain.Food;
import am.mse.eduFood.repository.FoodRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    @Override
    public Food addAsset(Long foodId, MultipartFile image, String name) throws IOException, NotFoundException {

        Food food = foodRepository.findById(foodId).orElseThrow(() -> new NotFoundException("Food not found"));

        String uri = assetService.uploadAsset(convert(image), name);

        Asset asset = new Asset();
        asset.setName(name);
        asset.setUri(uri);
//        assetService.save(asset);

        List<Asset> assets = food.getAssets();
        if (assets == null) {
            assets = new ArrayList<>();
        }
        assets.add(asset);
        food.setAssets(assets);


        return foodRepository.save(food);
    }

    public static File convert(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
