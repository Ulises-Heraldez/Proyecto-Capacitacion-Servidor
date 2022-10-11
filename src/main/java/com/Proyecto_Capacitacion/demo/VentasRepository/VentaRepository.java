/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasRepository;

import com.Proyecto_Capacitacion.demo.VentasModelo.Venta;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ULISES
 */
@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    public abstract ArrayList<Venta> findByFolio(String Folio);
    
    public abstract ArrayList<Venta> findByTotal(Double Total);
    
    public abstract ArrayList<Venta> findByEstado(String Estado);

}
