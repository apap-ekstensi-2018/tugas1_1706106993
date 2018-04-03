package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProgramStudiMapper;
import com.example.demo.model.ProgramStudi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramStudiDatabase implements ProgramStudiService {
  @Autowired
  private ProgramStudiMapper mapper;

  @Override
  public ProgramStudi findProdi(int id) {
    log.info("Find program studi with id {}", id);
    return mapper.findById(id);
  }
}
