package com.example.demo.service;

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
}
