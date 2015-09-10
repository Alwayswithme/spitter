package me.phx.mybatis.mapper;

import me.phx.model.AbstractEntity;

import java.util.List;

/**
 * @author phoenix
 */
public interface BaseMapper<T extends AbstractEntity> {
    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    T selectByPrimaryKey(Integer id);

    List<T> selectAll();

    int updateByPrimaryKey(T record);
}
