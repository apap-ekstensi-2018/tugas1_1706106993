package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ProgramStudi;

public interface ProgramStudiService {
  ProgramStudi findById(int id);
  List<ProgramStudi> findByFakultas(int id);
}
