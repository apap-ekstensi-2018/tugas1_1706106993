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

import com.example.demo.model.ProgramStudi;


@Mapper
public interface ProgramStudiMapper {
  @Select("SELECT DISTINCT * FROM program_studi WHERE id = #{id_prodi} LIMIT 1")
  @Results(value = {
    @Result(property="id", column="id"),
    @Result(property="fakultas", column="id_fakultas", one=@One(
      select = "com.example.demo.dao.FakultasMapper.findById",
      fetchType = FetchType.LAZY))
  })
  ProgramStudi findById (@Param("id_prodi") int id_prodi);

  @Select("SELECT * FROM program_studi WHERE id_fakultas = #{id_fakultas}")
  List<ProgramStudi> findByFakultas (@Param("id_fakultas") int id_fakultas);
}
