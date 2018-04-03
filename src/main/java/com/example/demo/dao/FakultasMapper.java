package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.session.AutoMappingBehavior;


import com.example.demo.model.Fakultas;

@Mapper
public interface FakultasMapper {
  @Select("SELECT * FROM fakultas WHERE kode_fakultas = #{id_fakultas}")
  @Results(value = {
    @Result(property="id", column="id"),
    @Result(property="universitas", column="id_univ", one=@One(
      select = "com.example.demo.dao.UniversitasMapper.findById",
      fetchType = FetchType.LAZY))
  })
  Fakultas findById (@Param("id_fakultas") int id_fakultas);
}
