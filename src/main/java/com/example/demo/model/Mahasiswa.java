package com.example.demo.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.demo.service.ProgramStudiService;
import com.example.demo.service.ProgramStudiDatabase;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Slf4j
public class Mahasiswa
{

  public static final String[] JENIS_KELAMIN_OPTIONS  = {"Laki-laki", "Perempuan"};
  public static final String[] GOLONGAN_DARAH_OPTIONS = {"A+", "A-", "AB+", "AB-", "B+", "B-", "O+", "O-"};
  public static final String[] AGAMA_OPTIONS          = {"Islam", "Budha", "Hindu", "Katolik", "Konghucu", "Protestan"};
  public static final String[] JALUR_MASUK_OPTIONS    = {"Ujian Tulis Bersama/SBMPTN", "Ujian Tulis Mandiri", "Undangan Olimpiade", "Undangan Paralel/PPKB", "Undangan Reguler/SNMPTN"};

  public static final int TAG_JK_PRIA   = 1;
  public static final int TAG_JK_WANITA = 0;

  private ProgramStudi prodi;

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

  public String getJenisKelamin()
  {
    return jenis_kelamin ? "Perempuan" : "Laki-laki";
  }

  public String getNamaProdi()
  {
    return prodi.getNama_prodi();
  }

  public String generateNPM(String kodeUniv, String kodeProdi, String kodeInputData){
    String kode = "";
    switch (jalurMasuk){
      case JALUR_MASUK_OPTIONS[0]: kode = "57";
                                   break;
      case JALUR_MASUK_OPTIONS[1]: kode = "62";
                                   break;
      case JALUR_MASUK_OPTIONS[2]: kode = "53";
                                   break;
      case JALUR_MASUK_OPTIONS[3]: kode = "55";
                                   break;
      case JALUR_MASUK_OPTIONS[4]: kode = "54";
                                   break;
    }

    return Integer.toString(tahunMasuk.substring(2,4)) + kodeUniv + kodeProdi + kode + kodeInputData;
  }
}
