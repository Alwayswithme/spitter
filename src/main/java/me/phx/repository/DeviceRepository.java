package me.phx.repository;

import me.phx.model.Device;

import java.util.List;

/**
 * @author phoenix
 */
public interface DeviceRepository {

    List<Device> getByOwnerId(Integer id);
}
