package com.example.demo.controller;

import java.util.List;

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

    if(mahasiswa == null){
      model.addAttribute("status", "Gagal!");
      model.addAttribute("message", "Gagal menambahkan mahasiswa");
      return "mahasiswa/create";
    }

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
    return "mahasiswa/create";
  }

  @RequestMapping("/mahasiswa/ubah")
  public String edit (Model model, @RequestParam(value="npm", required=true) String npm)
  {
    Mahasiswa mahasiswa = mahasiswaService.selectMahasiswa(npm);
    model.addAttribute("title", "Ubah Mahasiswa");
    model.addAttribute("mahasiswa", new Mahasiswa());
    model.addAttribute("jenis_kelamin", Mahasiswa.JENIS_KELAMIN_OPTIONS);
    model.addAttribute("agama", Mahasiswa.AGAMA_OPTIONS);
    model.addAttribute("goldar", Mahasiswa.GOLONGAN_DARAH_OPTIONS);
    model.addAttribute("jalur_masuk", Mahasiswa.JALUR_MASUK_OPTIONS);
    model.addAttribute("linkSubmit", "/mahasiswa/" + npm + "/ubah");
    model.addAttribute("hideAlert", true);
    return "mahasiswa/edit";
  }

  @RequestMapping(value="/mahasiswa/{npm}/ubah", method=RequestMethod.PUT)
  public String update (Model model, @ModelAttribute Mahasiswa mahasiswa)
  {
    if (mahasiswaService.update(mahasiswa)){
      model.addAttribute("status","Sukses!");
      model.addAttribute("message", String.format("Mahasiswa dengan NPM %s berhasil ditambahkan.", mahasiswa.getNpm()));
    } else {
      model.addAttribute("status", "Gagal!");
      model.addAttribute("message", "Gagal menambahkan mahasiswa");
    }
    return "mahasiswa/create";
  }

  @RequestMapping(value="/mahasiswa/cari", method=RequestMethod.POST)
  public String search (Model model,
      @RequestParam(value = "univ", required = false) String idUniv,
      @RequestParam(value = "fakultas", required = false) String idFakultas,
      @RequestParam(value = "prodi", required = false) String idProdi)
  {
    List<Universitas> listUniv = universitasService.all();
    List<Fakultas> listFakultas = fakultasService.findByUniv(Integer.parseInt(idUniv));
    List<ProgramStudi> listProdi = programStudiService.findByFakultas(Integer.parseInt(idFakultas));

    model.addAttribute("title", "Cari Mahasiswa");

    if(listFakultas.size() != 0){
      model.addAttribute("fakultas-show", true);
      model.addAttribute("fakultas", listFakultas);
    }

    if(listProdi.size() != 0){
      model.addAttribute("prodi-show", true);
      model.addAttribute("prodi", listProdi);
      List<Mahasiswa> mahasiswa = mahasiswaService.searchByUnivAndFakultasAndProdi(Integer.parseInt(idUniv), Integer.parseInt(idFakultas), Integer.parseInt(idProdi));

      if(mahasiswa.size() != 0){
        model.addAttribute("mahasiswa", mahasiswa);
        return "mahasiswa/index";
      }
    }

    return "mahasiswa/search";
  }

  private String getAdditionalData(Mahasiswa mahasiswa)
  {
    ProgramStudi ps = programStudiService.findById(mahasiswa.getId_prodi());
    Fakultas fak = fakultasService.findById(ps.getId_fakultas());

    String tmpNPM = mahasiswa.generateNPM(
        String.valueOf(fak.getUniversitas().getKode_univ()), "001");

    int i = 1;
    while (mahasiswaService.selectMahasiswa(tmpNPM) != null) {
      tmpNPM = mahasiswa.generateNPM(
          String.valueOf(fak.getUniversitas().getKode_univ()),
          String.format("00%s", i++));
    }
    return tmpNPM;
  }
}
