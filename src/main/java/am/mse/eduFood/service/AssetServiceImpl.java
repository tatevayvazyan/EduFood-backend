package am.mse.eduFood.service;

import am.mse.eduFood.domain.Asset;
import am.mse.eduFood.repository.AssetRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class AssetServiceImpl implements AssetService{

    private final Cloudinary cloudinary;
    private final AssetRepository assetRepository;

    public AssetServiceImpl(Cloudinary cloudinary, AssetRepository assetRepository) {

        this.cloudinary = cloudinary;
        this.assetRepository = assetRepository;
    }

    @Override
    public String uploadAsset(Object image, String name) throws IOException {

        Map uploadResults = cloudinary.uploader().upload(image, ObjectUtils.asMap("public_id", name));

        return (String) uploadResults.get("secure_url");

    }

    @Override
    public Asset save(Asset asset) throws IOException {

        return assetRepository.save(asset);
    }
}
