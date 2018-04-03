package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProgramStudi
{
  private Fakultas fakultas;

  private int id;
  private int kode_prodi;
  private String nama_prodi;
  private int id_fakultas;
}
