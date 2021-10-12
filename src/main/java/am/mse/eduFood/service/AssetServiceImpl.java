package am.mse.eduFood.service;

import am.mse.eduFood.domain.Asset;
import am.mse.eduFood.repository.AssetRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {

        this.assetRepository = assetRepository;
    }

    public static File convert(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    // save uploaded file to new location
    private void writeToFile(InputStream uploadedInputStream, String filepath) {

        try {
            int read;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(filepath);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public Asset save(Asset asset) {

        return assetRepository.save(asset);
    }

    @Override
    public byte[] getImage(String uri) throws NotFoundException {

        Asset asset = assetRepository.findByUri("/food/get/asset/"+uri);
        if (asset != null) {
            return asset.getPhoto();

        }
       throw new NotFoundException("Image not found!");
    }


}
