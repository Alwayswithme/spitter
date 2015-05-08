package com.springapp.mvc.repository;

import com.springapp.mvc.model.Device;

import java.util.List;

/**
 * @author phoenix
 */
public interface DeviceRepository {

    List<Device> getByOwnerId(Integer id);
}
