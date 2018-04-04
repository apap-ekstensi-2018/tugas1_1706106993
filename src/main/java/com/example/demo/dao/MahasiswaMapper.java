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

  @Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) VALUES ("
    + "#{mahasiswa.npm},"
    + "#{mahasiswa.nama},"
    + "#{mahasiswa.tempatLahir},"
    + "#{mahasiswa.tanggalLahir},"
    + "#{mahasiswa.jenisKelamin},"
    + "#{mahasiswa.agama},"
    + "#{mahasiswa.golonganDarah},"
    + "#{mahasiswa.status},"
    + "#{mahasiswa.tahunMasuk},"
    + "#{mahasiswa.jalurMasuk},"
    + "#{mahasiswa.idProdi})")
  void insert(@Param("mahasiswa") Mahasiswa mahasiswa);

  @Update("UPDATE mahasiswa SET "
    + "npm=#{mahasiswa.npm},"
    + "nama=#{mahasiswa.nama},"
    + "tempat_lahir=#{mahasiswa.tempatLahir},"
    + "tanggal_lahir=#{mahasiswa.tanggalLahir},"
    + "jenis_kelamin=#{mahasiswa.jenisKelamin},"
    + "agama=#{mahasiswa.agama},"
    + "golongan_darah=#{mahasiswa.golonganDarah},"
    + "status=#{mahasiswa.status},"
    + "tahun_masuk=#{mahasiswa.tahunMasuk},"
    + "jalur_masuk=#{mahasiswa.jalurMasuk},"
    + "id_prodi=#{mahasiswa.idProdi}"
    + " WHERE id=#{mahasiswa.id}")
  void update(@Param("mahasiswa") Mahasiswa mahasiswa);
}
