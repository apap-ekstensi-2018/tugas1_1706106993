package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Mahasiswa
{
    private int id;
    private String npm;
    private String nama;
    private String tempat_lahir;
    private Date tanggal_lahir;
    private boolean jenis_kelamin;
    private String agama;
    private String golongan_darah;
    private String status;
    private int tahun_masuk;
    private String jalur_masuk;
    private int id_prodi;
}
