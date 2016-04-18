package org.cmpe281.cloud.dbhandler;

import org.cmpe281.cloud.model.BarometerSensor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Naks on 17-Apr-16.
 * MongoDB Repository for Barometer Sensor - Provided by Spring Community
 */
public interface IBarometerRepository extends CrudRepository<BarometerSensor, String> {
    BarometerSensor findByLatitude(String latitude);
    BarometerSensor findByLongitude(String longitude);
}
