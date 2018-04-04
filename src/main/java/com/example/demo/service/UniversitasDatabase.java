package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UniversitasMapper;
import com.example.demo.model.Universitas;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UniversitasDatabase implements UniversitasService {
  @Autowired
  private UniversitasMapper universitasMapper;

  @Override
  public List<Universitas> all(){
    return universitasMapper.all()
  }
}
