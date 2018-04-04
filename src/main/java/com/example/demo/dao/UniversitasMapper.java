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

import com.example.demo.model.Universitas;

@Mapper
public interface UniversitasMapper {
  @Select("SELECT DISTINCT * FROM universitas WHERE kode_univ = #{id_univ} LIMIT 1")
  Universitas findById (@Param("id_univ") int id_univ);

  @Select("SELECT * FROM universitas")
  List<Universitas> all();
}
