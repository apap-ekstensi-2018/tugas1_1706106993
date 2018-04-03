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

import com.example.demo.model.Mahasiswa;
import com.example.demo.model.ProgramStudi;

@Mapper
public interface MahasiswaMapper {
  @Select("SELECT DISTINCT * FROM mahasiswa WHERE npm = #{npm} LIMIT 1")
  @Results(value = {
    @Result(property="id", column="id"),
    @Result(property="prodi", column="id_prodi", one=@One(
      select = "com.example.demo.dao.ProgramStudiMapper.findById",
      fetchType = FetchType.LAZY))
  })
  Mahasiswa select (@Param("npm") String npm);
}