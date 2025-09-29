package com.tlp2.steam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlp2.steam.model.Jogador;
import com.tlp2.steam.service.JogadorService;



@Controller
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @GetMapping("/")
    public String cadastro() {
        return "/jogador";
    }

    @GetMapping("/teste")
    public String teste() {
        return "/index";
    }

    @GetMapping("/novo")
    public String novoJogador(Model model) {
        Jogador jogador = new Jogador();
        model.addAttribute("novoJogador", jogador);
        return "/index";
    }
    
    @PostMapping("/gravar")
    public String gravarEstudante(@ModelAttribute("novoJogador") Jogador jogador, RedirectAttributes attributes) {		
		jogadorService.criarJogador(jogador);
		attributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
        return "redirect:/novo";
    }
    
    /*
    @GetMapping("/qualquer coisa")
    public String teste() {
        return "/qualquer página";
    }
     */

}
