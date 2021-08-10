package am.mse.eduFood.service;


import am.mse.eduFood.domain.Asset;
import am.mse.eduFood.domain.Food;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface AssetService {

    String uploadAsset(Object image, String name) throws IOException;
    Asset save(Asset asset) throws IOException;

}
