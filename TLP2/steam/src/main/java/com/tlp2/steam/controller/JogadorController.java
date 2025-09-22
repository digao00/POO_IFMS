package com.tlp2.steam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class JogadorController {

    @GetMapping("/")
    public String cadastro() {
        return "/lista";
    }

    @GetMapping("/teste")
    public String teste() {
        return "/index";
    }

    @GetMapping("/novo")
    public String novoJogador() {
        return "/novoJogador";
    } 
    /*
    @GetMapping("/qualquer coisa")
    public String teste() {
        return "/qualquer página";
    }
     */

}
