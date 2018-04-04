package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Fakultas;

public interface FakultasService {
  List<Fakultas> findByUniv();
  List<Fakultas> findByUniv(int id_univ);
}
