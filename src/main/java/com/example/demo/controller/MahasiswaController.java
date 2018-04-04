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
public class MahasiswaController {

  @Autowired
  MahasiswaService mahasiswaService;

  @Autowired
  FakultasService fakultasService;

  @Autowired
  UniversitasService universitasService;

  @Autowired
  ProgramStudiService programStudiService;

  @RequestMapping(value="/mahasiswa", method=RequestMethod.GET)
  public String show (Model model, @RequestParam(value="npm", required=false) String npm)
  {
    Mahasiswa mahasiswa = mahasiswaService.selectMahasiswa(npm);

    model.addAttribute("title", "Index");
    model.addAttribute("mahasiswa", mahasiswa);
    return "mahasiswa/view";
  }

  @RequestMapping("/mahasiswa/tambah")
  public String add (Model model)
  {
    model.addAttribute("title", "Tambah Mahasiswa");
    model.addAttribute("mahasiswa", new Mahasiswa());
    model.addAttribute("jenis_kelamin", Mahasiswa.JENIS_KELAMIN_OPTIONS);
    model.addAttribute("agama", Mahasiswa.AGAMA_OPTIONS);
    model.addAttribute("goldar", Mahasiswa.GOLONGAN_DARAH_OPTIONS);
    model.addAttribute("jalur_masuk", Mahasiswa.JALUR_MASUK_OPTIONS);
    model.addAttribute("linkSubmit", "/mahasiswa/tambah");
    model.addAttribute("hideAlert", true);
    return "mahasiswa/new";
  }

  @RequestMapping(value="/mahasiswa/tambah", method=RequestMethod.POST)
  public String create (Model model, @ModelAttribute Mahasiswa mahasiswa)
  {
    model.addAttribute("title", "Index");
    mahasiswa.setNpm(getAdditionalData(mahasiswa));
    mahasiswa.setStatus("Aktif");

    if (mahasiswaService.insert(mahasiswa)){
      model.addAttribute("status","Sukses!");
      model.addAttribute("message", String.format("Mahasiswa dengan NPM %s berhasil ditambahkan.", mahasiswa.getNpm()));
    } else {
      model.addAttribute("status", "Gagal!");
      model.addAttribute("message", "Gagal menambahkan mahasiswa");
    }
    return "create";
  }

  @RequestMapping("/mahasiswa/ubah")
  public String edit (Model model)
  {
    model.addAttribute("title", "Index");
    return "home";
  }

  @RequestMapping(value="/mahasiswa/{npm}/ubah", method=RequestMethod.PUT)
  public String update (Model model)
  {
    model.addAttribute("title", "Index");
    return "home";
  }

  @RequestMapping(value="/mahasiswa/cari", method=RequestMethod.POST)
  public String search (Model model)
  {
    model.addAttribute("title", "Index");
    return "home";
  }

  private getAdditionalData(mahasiswa)
  {
    ProgramStudi ps = programStudiService.findById(mahasiswa.getId_prodi());
    Fakultas fak = fakultasService.findById(ps.getId_fakultas());

    String tmpNPM = mahasiswa.generateNPM(
        String.valueOf(faku.getUniversitas().getKode_univ()),
        String.valueOf(mahasiswa.getIdProdi()),
        mode.equals("insert") ? "001" : mahasiswa.getNpm().substring(9, 12));

    int i = 1;
    while (mahasiswaDAO.select(tmpNPM) != null) {
      tmpNPM = mahasiswa.generateNPM(
          mahasiswa.getTahunMasuk(),
          String.valueOf(um.getKodeUniversitas()),
          String.valueOf(mahasiswa.getIdProdi()),
          mahasiswa.getJalurMasuk(),
          String.format("00%s", i++));
    }
    return tmpNPM;
  }
