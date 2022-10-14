/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasController;

import com.Proyecto_Capacitacion.demo.VentasModelo.Partida;
import com.Proyecto_Capacitacion.demo.VentasService.PartidaService;
import com.Proyecto_Capacitacion.demo.exception.ApiInvalidRequestException;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ULISES
 */
@RestController
@RequestMapping("/partida")
public class PartidaController {

    @Autowired
    PartidaService partidaService;

    //Get lista partidas
    @GetMapping(value = "") //http://localhost:8080/partida
    public ArrayList<Partida> getPartida() {
        return partidaService.getPartida();
    }

    //Eliminar partida
    @DeleteMapping(path = "/{id}") //http://localhost:8080/partida/16
    public void eliminarPorId(@PathVariable("id") Long id) {
        System.out.println("id de partida a borrar - " + id);
        partidaService.deletePartida(id);
    }

    //Get partida especí­fica
    @GetMapping(path = "/{id}") //http://localhost:8080/partida/3
    public Optional<Partida> getPartidaPorId(@PathVariable("id") String id) throws ApiInvalidRequestException {
        return this.partidaService.getForId(id);
    }

}
