/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto_Capacitacion.demo.VentasController;

import com.Proyecto_Capacitacion.demo.VentasModelo.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.Proyecto_Capacitacion.demo.VentasService.VentaService;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author TESTER
 */
@RestController
//@RequestMapping("/venta")
public class VentaController {

    @Autowired
    VentaService ventaService;

    //Agregar venta
    //si se le indica un ID se actualiza en vez de agregar
    @PostMapping(value = "/ventas")
    public boolean guardarVenta(@RequestBody Venta venta) {

        if (ventaService.folioDuplicado(venta.getFolio(), venta.getId()) == true) {
            System.out.println("Folio duplicado");
            return false;
        }
        
        return this.ventaService.guardarVenta(venta);
    }

    //Get venta específica
    @GetMapping(path = "/ventas/{id}") //http://localhost:8080/ventas/3
    public Optional<Venta> obtenerVentaPorId(@PathVariable("id") Long id) {
        return this.ventaService.obtenerPorId(id);
    }

    //Get listado de ventas
    @GetMapping(path = "/ventas")
    public ArrayList<Venta> obtenerVentaPorValor(
            @RequestParam(value = "folio", required = false) String Folio, //http://localhost:8080/ventas?folio=1234
            @RequestParam(value = "total", required = false) Double Total, //http://localhost:8080/ventas?total=5020
            @RequestParam(value = "estado", required = false) String Estado) { //http://localhost:8080/ventas?estado=Pagada

        if (Folio != null && Folio != "") {
            return this.ventaService.obtenerPorFolio(Folio);
        }
        if (Total != null && Total != 0) {
            return this.ventaService.obtenerPorTotal(Total);
        }
        if (Estado != null && Estado != "") {
            return this.ventaService.obtenerPorEstado(Estado);
        }

        return ventaService.obtenerVenta();
    }

    //Eliminar venta
    @DeleteMapping(path = "/ventas/{id}") //http://localhost:8080/ventas/7
    public String eliminarPorId(@PathVariable("id") Long id) {
        return this.ventaService.deleteVenta(id);
    }

}
