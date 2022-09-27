/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasController;

import com.Proyecto_Capacitacion.demo.VentasModelo.Partida;
import com.Proyecto_Capacitacion.demo.VentasService.PartidaService;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TESTER
 */
@RestController
public class PartidaController {

    @Autowired
    PartidaService partidaService;

    //Get lista partidas
    @GetMapping(value = "/partidas") //http://localhost:8080/partidas
    public ArrayList<Partida> obtenerPartida() {
        return partidaService.obtenerPartida();
    }

    //Eliminar partida
    @DeleteMapping(path = "/partidas/{id}") //http://localhost:8080/partidas/16
    public void eliminarPorId(@PathVariable("id") Long id) {
        System.out.println("id de partida a borrar - " + id);
        partidaService.deletePartida(id);
    }
    
    //Get partida específica
    @GetMapping(path = "/partidas/{id}") //http://localhost:8080/partidas/3
    public Optional<Partida> obtenerPartidaPorId(@PathVariable("id") Long id) {
        return this.partidaService.obtenerPorId(id);
    }
    
}
