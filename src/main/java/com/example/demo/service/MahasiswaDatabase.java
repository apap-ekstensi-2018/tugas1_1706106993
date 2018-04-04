package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MahasiswaMapper;
import com.example.demo.model.Mahasiswa;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaDatabase implements MahasiswaService {
  @Autowired
  private MahasiswaMapper mahasiswaMapper;

  @Override
  public Mahasiswa selectMahasiswa(String npm) {
    log.info ("select mahasiswa with npm {}", npm);
    return mahasiswaMapper.select(npm);
  }

  @Override
  public boolean insert(Mahasiswa mahasiswa){
    log.info("insert mahasiswa to database");
    mahasiswaMapper.insert(mahasiswa);

    return mahasiswaMapper.select(mahasiswa.npm) != null;
  }

  @Override
  public boolean update(Mahasiswa mahasiswa){
    log.info("update mahasiswa dengan npm {}", mahasiswa.getNpm());
    mahasiswaMapper.update(mahasiswa);

    return mahasiswaMapper.select(mahasiswa.npm) != null;
  }

  @Override
  public List<Mahasiswa> getByTahunAndProdi(int tahun, int prodi){
    log.info("select mahasiswa with prodi {} and year {}", prodi, tahun);
    return mahasiswaMapper.getByTahunAndProdi(tahun, prodi);
  }

  @Override
  public List<Mahasiswa> getGraduatedByTahunAndProdi(int tahun, int prodi){
    log.info("select mahasiswa with prodi {} and year {}", prodi, tahun);
    return mahasiswaMapper.getGraduatedByTahunAndProdi(tahun, prodi);
  }
}
