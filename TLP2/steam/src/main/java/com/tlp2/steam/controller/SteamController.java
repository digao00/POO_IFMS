package com.tlp2.steam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlp2.steam.exception.JogadorNotFoundException;
import com.tlp2.steam.model.Jogador;
import com.tlp2.steam.model.Jogo;
import com.tlp2.steam.service.JogadorService;
import com.tlp2.steam.service.JogoService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
public class SteamController {

    @Autowired
    private JogadorService jogadorService;
    @Autowired
    private JogoService jogoService;

    @GetMapping("/loja")
    public String principal(Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("jogadorLogado");
        List<Jogo> jogos = jogoService.listarJogos();
        model.addAttribute("jogos", jogos);
        model.addAttribute("jogadorID", id);
        return "/main_page/index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Jogador jogador = new Jogador();
        model.addAttribute("novoJogador", jogador);
        return "/cadastro";
    }
    
    @PostMapping("/gravar")
    public String gravarJogador(@ModelAttribute("novoJogador") @Valid Jogador jogador, BindingResult error, RedirectAttributes attributes, HttpSession session) {
        if (error.hasErrors()) {
            return "/cadastro";
        }
		jogadorService.criarJogador(jogador);
		attributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
        session.setAttribute("jogadorLogado", jogador.getId());
        return "redirect:/loja";
    }

    @GetMapping("/apagar/{id}")
    public String apagarJogador(@PathVariable("id") Long id, RedirectAttributes attributes) {
        try {
            jogadorService.apagarJogador(id);
        } catch (JogadorNotFoundException e) {
            attributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "/cadastro";
    }

}
