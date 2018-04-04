package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.FakultasMapper;
import com.example.demo.model.Fakultas;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasDatabase implements FakultasService {
  @Autowired
  private FakultasMapper fakultasMapper;

  @Override
  public List<Fakultas> findByUniv(int id_univ){
    return fakultasMapper.findByUniv(id_univ);
  }

  @Override
  public List<Fakultas> findByUniv(){
    return new List<Fakultas>();
  }

  @Override
  public Fakultas findById(int id){
    return fakultasMapper.findById(id);
  }
}
