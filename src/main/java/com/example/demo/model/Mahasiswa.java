package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.demo.service.ProgramStudiService;
import com.example.demo.service.ProgramStudiDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Slf4j
public class Mahasiswa
{

  private ProgramStudi prodi;

  private int id;
  private String npm;
  private String nama;
  private String tempat_lahir;
  private Date tanggal_lahir;
  private boolean jenis_kelamin;
  private String agama;
  private String golongan_darah;
  private String status;
  private int tahun_masuk;
  private String jalur_masuk;
  private int id_prodi;

  public String getJenisKelamin()
  {
    return jenis_kelamin ? "Perempuan" : "Laki-laki";
  }

  public String getNamaProdi()
  {
    return prodi.getNama_prodi();
  }

  public String getNamaFakultas()
  {
    log.info("Nama Fakultas {}", prodi.getFakultas());
    return prodi.getFakultas().getNama_fakultas();
  }

  public String getNamaUniversitas()
  {
    return prodi.getFakultas().getUniversitas().getNama_univ();
  }
}
