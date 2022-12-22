package com.vodafone.deviceconfserv.repository;

import com.vodafone.deviceconfserv.entity.DeviceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Device repository.
 */
@Repository
public interface DeviceRepository extends MongoRepository<DeviceEntity, String> {
}
