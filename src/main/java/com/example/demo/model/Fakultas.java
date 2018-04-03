package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Fakultas
{
  private Universitas universitas;

  private int id;
  private int kode_fakultas;
  private String nama_fakultas;
  private int id_univ;
}
