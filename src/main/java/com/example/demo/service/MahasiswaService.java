package com.example.demo.service;

import com.example.demo.model.Mahasiswa;

public interface MahasiswaService {
  Mahasiswa selectMahasiswa(String npm);
  boolean insert(Mahasiswa mahasiswa);
  boolean update(Mahasiswa mahasiswa);
}
