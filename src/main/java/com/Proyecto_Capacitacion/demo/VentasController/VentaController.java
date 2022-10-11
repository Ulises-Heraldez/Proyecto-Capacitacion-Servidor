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
import com.Proyecto_Capacitacion.demo.exception.ApiDuplicatedEntryException;
import com.Proyecto_Capacitacion.demo.exception.ApiInvalidRequestException;
import com.Proyecto_Capacitacion.demo.exception.ApiEmptyRequestException;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ULISES
 */
@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    VentaService ventaService;

    //Agregar venta
    //si se le indica un ID se actualiza en vez de agregar
    @PostMapping
    public boolean guardarVenta(@RequestBody Venta venta) throws ApiDuplicatedEntryException {
        return this.ventaService.guardarVenta(venta);
    }

    //Get venta específica
    @GetMapping("/{id}")
    public Optional<Venta> getVentaForId(@PathVariable("id") String id)
            throws ApiInvalidRequestException, ApiEmptyRequestException {

        return this.ventaService.getForId(id);
    }
    
    //Get listado de ventas
    @GetMapping
    public ArrayList<Venta> getVentaPorValor(
            @RequestParam(value = "folio", required = false) String Folio, //http://localhost:8080/venta?folio=1234
            @RequestParam(value = "total", required = false) Double Total, //http://localhost:8080/venta?total=5020
            @RequestParam(value = "estado", required = false) String Estado) { //http://localhost:8080/venta?estado=Pagada

        if (Folio != null && Folio != "") {
            return this.ventaService.getForFolio(Folio);
        }
        if (Total != null && Total != 0) {
            return this.ventaService.getPorTotal(Total);
        }
        if (Estado != null && Estado != "") {
            return this.ventaService.getForEstado(Estado);
        }

        return ventaService.getVenta();
    }

    //Eliminar venta
    @DeleteMapping("/{id}") //http://localhost:8080/venta/7
    public void eliminarPorId(@PathVariable("id") String id) throws ApiInvalidRequestException, ApiEmptyRequestException {
        this.ventaService.deleteVenta(id);
    }

}
