/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasService;

import com.Proyecto_Capacitacion.demo.VentasModelo.Partida;
import com.Proyecto_Capacitacion.demo.VentasModelo.Venta;
import com.Proyecto_Capacitacion.demo.VentasRepository.PartidaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Proyecto_Capacitacion.demo.VentasRepository.VentaRepository;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;

/**
 *
 * @author TESTER
 */
@Service
public class VentaService { //implements IService {

    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    PartidaRepository partidaRepository;

    public ArrayList<Venta> obtenerVenta() {
        return (ArrayList<Venta>) ventaRepository.findAll();
    }

    public boolean guardarVenta(Venta venta) {

        try {
            venta.setTotal(calcularTotal(venta).doubleValue());
            ventaRepository.save(venta);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Venta> obtenerPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public ArrayList<Venta> obtenerPorFolio(String Folio) {
        return ventaRepository.findByFolio(Folio);
    }

    public ArrayList<Venta> obtenerPorEstado(String Estado) {
        return ventaRepository.findByEstado(Estado);
    }

    public ArrayList<Venta> obtenerPorTotal(Double Total) {
        return ventaRepository.findByTotal(Total);
    }

    public boolean folioDuplicado(String folio, Long id) {
        if (!(ventaRepository.findByFolio(folio).isEmpty())) {

            //Declaramos una nueva venta
            Venta venta = new Venta();

            //Le asignamos la venta obtenida con el folio repetido
            venta = ventaRepository.findByFolio(folio).get(0);

            //Si el id de la venta obtenida es el mismo de la nueva, entonces que si 
            //  permita dejar el folio igual, ya que no se repetirá de todas maneras
            if (id == venta.getId()) {
                return false;
            }

            return true;
        }
        return false;
    }

    public String deleteVenta(Long id) {
        try {
            ventaRepository.deleteById(id);
            return "Se eliminó la venta con id " + id;
        } catch (Exception e) {
            return "No pudo eliminar la venta con id" + id;
        }
    }

    public BigDecimal calcularTotal(Venta venta) { //, Partida partida
        BigDecimal total = BigDecimal.valueOf(0);

        try {

            //Lista de partidas nuevas en el JSON
            List<Partida> coll_partidas = venta.getPartida();

            System.out.println("partidas - " + coll_partidas);

            venta.getId();
            System.out.println("id_venta - " + venta.getId());

            Iterator itr = coll_partidas.iterator();
            while (itr.hasNext()) {

                String DetallesVenta = "" + itr.next();

                //Dividir el String con todos los valores mediante un separador
                String[] Detalles_Venta_Separados = DetallesVenta.split(",");

                //Asignar valor a la venta
                total = total.add(BigDecimal.valueOf(Double.parseDouble(Detalles_Venta_Separados[2])).multiply(BigDecimal.valueOf(Double.parseDouble(Detalles_Venta_Separados[3]))));

                System.out.println("total - " + total);
            }

            return total;

        } catch (Exception e) {
            System.out.println("error calcularTotal - " + e);
            e.printStackTrace();
        }

        return total;
    }

    public boolean eliminarVenta(Long id) {
        try {
            ventaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
