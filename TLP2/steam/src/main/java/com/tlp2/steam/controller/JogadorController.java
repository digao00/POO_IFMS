package com.tlp2.steam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JogadorController {

    @GetMapping("/")
    public String cadastro() {
        return "/cadastro";
    }

}
