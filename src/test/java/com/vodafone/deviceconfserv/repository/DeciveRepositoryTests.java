package com.vodafone.deviceconfserv.repository;


import com.vodafone.deviceconfserv.entity.DeviceEntity;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The type Decive repository tests.
 */
@Testcontainers
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class DeciveRepositoryTests {
    /**
     * The constant mongoDBContainer.
     */
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    /**
     * Sets properties.
     *
     * @param registry the registry
     */
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Clean up.
     */
    @AfterClass
    void cleanUp() {
        this.deviceRepository.deleteAll();
    }

    @Test
    void duplicateKeyExceptionWithUniquePinCode() {
        DeviceEntity device1 = new DeviceEntity("5", "READY", -1, "0000001");
        deviceRepository.save(device1);

        DeviceEntity device2 = new DeviceEntity("99", "ACTIVE", 10, "0000001");
        assertThrows(DuplicateKeyException.class, () -> {
            deviceRepository.save(device2);
        });
    }
    @Test
    void shouldUpdateADevice() {
        Optional<DeviceEntity> optionalDevice = deviceRepository.findById("5");

        if (optionalDevice.isPresent()) {
            DeviceEntity deviceEntity = optionalDevice.get();
            Assert.assertEquals("READY",deviceEntity.getStatus());
            deviceEntity.setStatus("ACTIVE");
            DeviceEntity updatedEntity = deviceRepository.save(deviceEntity);
            assertEquals(deviceEntity.getId(), updatedEntity.getId());
            assertEquals("ACTIVE", updatedEntity.getStatus());
        }


    }
}
