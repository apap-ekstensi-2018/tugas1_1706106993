package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Fakultas;
import com.example.demo.model.Mahasiswa;
import com.example.demo.model.Universitas;
import com.example.demo.model.ProgramStudi;

import com.example.demo.service.FakultasService;
import com.example.demo.service.MahasiswaService;
import com.example.demo.service.UniversitasService;
import com.example.demo.service.ProgramStudiService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GraduationController {
  @Autowired
  MahasiswaService mahasiswaService;

  @Autowired
  FakultasService fakultasService;

  @Autowired
  UniversitasService universitasService;

  @Autowired
  ProgramStudiService programStudiService;

  @RequestMapping("/kelulusan")
  public String index (Model model, @RequestParam(value = "tahun", required = false) String tahun,
      @RequestParam(value = "prodi", required = false) String idProdi)
  {
    boolean outputStatus = tahun != null && idProdi != null;
    int totalMahasiswa = 0;
    int totalMahasiswaLulus = 0;
    float presentase = 0;

    ProgramStudi prodi = new ProgramStudi();
    Fakultas fakultas = new Fakultas();

    if (outputStatus) {
      int parseTahun = Integer.valueOf(tahun);
      int parseIdProdi = Integer.valueOf(idProdi);

      totalMahasiswa = mahasiswaService.getByTahunAndProdi(parseTahun, parseIdProdi).size();
      totalMahasiswaLulus = mahasiswaService.getGraduatedByTahunAndProdi(parseTahun, parseIdProdi).size();
      presentase = totalMahasiswaLulus / totalMahasiswa * 100;

      prodi = programStudiService.findById(parseIdProdi);
      fakultas = fakultasService.findById(prodi != null ? prodi.getIdFakultas() : null);
    }

    model.addAttribute("closeOutput", outputStatus);
    model.addAttribute("closeForm", !outputStatus);
    model.addAttribute("prodi", prodi);
    model.addAttribute("fakultas", fakultas);
    model.addAttribute("universitas", fakultas.getUniversitas());
    model.addAttribute("totalPresentase", presentase);
    model.addAttribute("infoPresentase", String.format("%s dari %s Mahasiswa Telas Lulus", totalMahasiswaLulus, totalMahasiswa));
    return "graduation/index";
  }
}
