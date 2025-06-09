package com.sunhaj.slant;

import com.sunhaj.slant.config.SlantConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = SlantConfig.class)
public class SlantGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlantGameApplication.class, args);
	}

}
