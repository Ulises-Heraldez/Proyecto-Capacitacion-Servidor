/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasService;

import com.Proyecto_Capacitacion.demo.VentasModelo.Partida;
import com.Proyecto_Capacitacion.demo.VentasModelo.Venta;
import com.Proyecto_Capacitacion.demo.VentasRepository.PartidaRepository;
import com.Proyecto_Capacitacion.demo.VentasRepository.VentaRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TESTER
 */
@Service
public class PartidaService {

    @Autowired
    PartidaRepository partidaRepository;

    @Autowired
    VentaRepository ventaRepository;

    public ArrayList<Partida> obtenerPartida() {
        return (ArrayList<Partida>) partidaRepository.findAll();
    }

    public Partida guardarPartida(Partida partida) {
        return partidaRepository.save(partida);
    }

    public Optional<Partida> obtenerPorId(Long id) {
        return partidaRepository.findById(id);
    }

    public ArrayList<Partida> obtenerPorEstado(String Estado) {
        return partidaRepository.findByEstado(Estado);
    }

    public Iterable<Partida> obtenerPorId_Venta(Venta venta) {
        return partidaRepository.findByVenta(venta);
    }

    public String deletePartida(Long id) {
        try {
            partidaRepository.deleteById(id);
            return "Se eliminó la partida con id " + id;
        } catch (Exception e) {
            return "No pudo eliminar la partida con id " + id;
        }
    }
}
