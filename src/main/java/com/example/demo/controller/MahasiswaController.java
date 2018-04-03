package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Mahasiswa;
import com.example.demo.service.MahasiswaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MahasiswaController {

  @Autowired
  MahasiswaService wired;

  @RequestMapping(value="/mahasiswa", method=RequestMethod.GET)
  public String show (Model model, @RequestParam(value="npm", required=false) String npm)
  {
    Mahasiswa mahasiswa = wired.selectMahasiswa(npm);

    model.addAttribute("title", "Index");
    model.addAttribute("mahasiswa", mahasiswa);
    return "mahasiswa/view";
  }

  @RequestMapping("/mahasiswa/tambah")
  public String add (Model model)
  {
    model.addAttribute("title", "Index");
    return "home";
  }

  @RequestMapping(value="/mahasiswa/", method=RequestMethod.POST)
  public String create (Model model)
  {
    model.addAttribute("title", "Index");
    return "home";
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

}
