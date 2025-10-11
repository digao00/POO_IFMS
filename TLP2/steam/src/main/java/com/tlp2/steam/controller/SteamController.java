package com.tlp2.steam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tlp2.steam.model.Jogador;
import com.tlp2.steam.model.Jogo;
import com.tlp2.steam.service.JogadorService;
import com.tlp2.steam.service.JogoService;



@Controller
public class SteamController {

    @Autowired
    private JogadorService jogadorService;
    private JogoService jogoService;

    //pagina inicial "/" leva pra login ou cadastro
    //pagina de login "/login"
    //pagina de biblioteca "/biblioteca" th:href="/biblioteca"

    @GetMapping("/loja")
    public String principal(Model model) {
        List<Jogo> jogos = jogoService.listarJogos();
        model.addAttribute("jogos", jogos);
        return "/main_page/index";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {
        Jogador jogador = new Jogador();
        model.addAttribute("novoJogador", jogador);
        return "/cadastro";
    }
    
    @PostMapping("/gravar")
    public String gravarJogador(@ModelAttribute("novoJogador") Jogador jogador, RedirectAttributes attributes) {		
		jogadorService.criarJogador(jogador);
		attributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");
        return "redirect:/loja";
    }

}
