package me.phx.mybatis.mapper;

import me.phx.model.House;

/**
 * @author phoenix
 */
public interface ProductMapper {
    int insertHouse(House house);

    int deleteHouse(Integer id);
}
