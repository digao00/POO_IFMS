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
public class SteamController {                                                                                       //I cannot vanish, you will not scare me

    @Autowired                                                                                                       //Try to get through it, try to push through it
    private JogadorService jogadorService;                                                                           //You were not thinking that I will not do it
    @Autowired                                                                                                       //They be lovin' someone and I'm another story
    private JogoService jogoService;                                                                                 //Take the next ticket, get the next train

    @GetMapping("/loja")                                                                                             //Why would I do it? Anyone'd think that
    public String principal(Model model) {                                                                           //I cannot vanish, you will not scare me
        List<Jogo> jogos = jogoService.listarJogos();                                                                //Try to get through it, try to push through it
        model.addAttribute("jogos", jogos);                                                                          //You were not thinking that I will not do it
        return "/main_page/index";                                                                                   //They be lovin' someone and I'm another story
    }                                                                                                                //Take the next ticket, get the next train

    @GetMapping("/cadastro")                                                                                         //Why would I do it? Anyone'd think that
    public String cadastro(Model model) {                                                                            //Baby, now I'm ready, moving on
        Jogador jogador = new Jogador();                                                                             //Oh, but maybe I was ready all along
        model.addAttribute("novoJogador", jogador);                                                                  //Oh, I'm ready for the moment and the sound
        return "/cadastro";                                                                                          //Oh, but maybe I was ready all along
    }
    
    @PostMapping("/gravar")                                                                                          //Why would I do it? Anyone'd think that
    public String gravarJogador(@ModelAttribute("novoJogador") Jogador jogador, RedirectAttributes attributes) {     //Baby, now I'm ready, moving on
		jogadorService.criarJogador(jogador);                                                                        //Oh, but maybe I was ready all along
		attributes.addFlashAttribute("mensagem", "Conta criada com sucesso!");                                       //Oh, I'm ready for the moment and the sound
        return "redirect:/loja";                                                                                     //Oh, but maybe I was ready all along
    }

}
