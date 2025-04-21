package com.chatop.chaTop.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is a configuration class that configure the cloudinary server access according to the env url/secret key.
 */
@Configuration
public class CloudinaryConfig {

    @Value("${CLOUDINARY_URL}")
    private String cloudinaryURL;

    /**
     * Creates a Cloudinary bean.
     *
     * @return the Cloudinary bean
     */
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(this.cloudinaryURL);
    }
}
