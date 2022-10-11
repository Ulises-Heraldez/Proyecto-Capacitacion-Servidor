/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasRepository;

import com.Proyecto_Capacitacion.demo.VentasModelo.Partida;
import com.Proyecto_Capacitacion.demo.VentasModelo.Venta;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ULISES
 */
public interface PartidaRepository extends JpaRepository<Partida, Long>{
    public abstract ArrayList<Partida> findByEstado(String Estado);
    
    public abstract ArrayList<Partida> findByVentaId(Long Venta);
    
    public Iterable<Partida> findByVenta(Venta venta);
}
