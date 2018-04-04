package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Mahasiswa;

public interface MahasiswaService {
  Mahasiswa selectMahasiswa(String npm);
  boolean insert(Mahasiswa mahasiswa);
  boolean update(Mahasiswa mahasiswa);
  List<Mahasiswa> getByTahunAndProdi(int tahun, int prodi);
  List<Mahasiswa> getGraduatedByTahunAndProdi(int tahun, int prodi);
  List<Mahasiswa> searchByUnivAndFakultasAndProdi(int univ, int fakultas, int prodi);
}
