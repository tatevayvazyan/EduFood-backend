package am.mse.eduFood.service;


import am.mse.eduFood.domain.Asset;
import am.mse.eduFood.domain.Food;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface AssetService {

    Asset save(Asset asset) throws IOException;
    byte[] getImage(String uri) throws NotFoundException;

}
