package am.mse.eduFood.config.assets;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import com.cloudinary.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils
        .asMap("cloud_name", "dag99kb8j", "api_key", "142164263725753", "api_secret", "Iu6ZZUNDzvp2-lVlIcOiIY83L_0"));

    @Bean
    public Cloudinary cloudinary() {
        return cloudinary;
    }
}
