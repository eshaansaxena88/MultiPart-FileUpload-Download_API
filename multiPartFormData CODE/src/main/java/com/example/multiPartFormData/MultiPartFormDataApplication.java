package com.example.multiPartFormData;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MultiPartFormDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiPartFormDataApplication.class, args);
	}
	
	 @Bean
	    public MultipartConfigElement multipartConfigElement() {
	        MultipartConfigFactory factory = new MultipartConfigFactory();
	        factory.setMaxFileSize(DataSize.ofMegabytes(150000));
	        factory.setMaxRequestSize(DataSize.ofMegabytes(150000));
	        return factory.createMultipartConfig();
	    }

}
