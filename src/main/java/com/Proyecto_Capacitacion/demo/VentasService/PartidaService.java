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

    public ArrayList<Partida> obtenerPorId_Venta(Long Venta) {
        return partidaRepository.findByVentaId(Venta);
    }

    public String deletePartida(Long id) {
        try {

            //Re-calcular el total de la venta
//            Optional<Partida> partida;
//            Partida partida = new Partida();
            Partida partida = obtenerPorId(id).get();
            System.out.println("ps - partida - " + partida);
            Venta venta = partida.getVenta();
            partidaRepository.deleteById(id);

            System.out.println("ps - venta - " + venta);
//            Long idVenta = venta.getId();
//            System.out.println("ps - id venta - " + idVenta);

            System.out.println("ps - test");
            VentaService ventaService = new VentaService();

//            ventaRepository.save(venta);

//            System.out.println("venta con partida a borrar - " + venta);
//            ventaService.guardarVenta(venta);

//            BigDecimal calcularTotal = VentaService.calcularTotal(venta);
//            System.out.println("a " + calcularTotal);
            return "Se eliminó la partida con id " + id;
        } catch (Exception e) {
            return "No pudo eliminar la partida con id " + id;
        }
    }
}
