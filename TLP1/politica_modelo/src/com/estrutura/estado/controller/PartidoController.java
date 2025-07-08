package com.estrutura.estado.controller;

import com.estrutura.estado.model.dao.PartidoDAO;
import com.estrutura.estado.view.PoliticaView;

public class PartidoController {
    private PoliticaView view;
    private PartidoDAO dao = new PartidoDAO();

    public PartidoController(PoliticaView view) {
        this.view = view;
    }

    
}
