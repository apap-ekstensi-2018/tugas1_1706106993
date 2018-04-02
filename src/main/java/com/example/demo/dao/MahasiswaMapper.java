package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Mahasiswa;

@Mapper
public interface MahasiswaMapper {
  @Select("SELECT * FROM mahasiswa WHERE npm = #{npm}")
  Mahasiswa select (@Param("npm") String npm);

}
