package com.vodafone.deviceconfserv;

import com.vodafone.deviceconfserv.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * The type Vodafone application.
 */
@SpringBootApplication
@EnableMongoRepositories
public class DeviceConfigurationApplication {

	/**
	 * The Device repository.
	 */
	@Autowired
	DeviceRepository deviceRepository;

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DeviceConfigurationApplication.class, args);
	}

}
