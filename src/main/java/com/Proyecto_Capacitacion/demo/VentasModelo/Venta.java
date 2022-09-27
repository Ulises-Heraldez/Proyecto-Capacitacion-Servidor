package com.Proyecto_Capacitacion.demo.VentasModelo;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

/**
 *
 * @author TESTER
 */
@Entity
@Table(name = "ventas") //Seleccionar el nombre de la tabla, si no lo ponemos se mandan automáticamente a la tabla del nombre de la clase, en este caso "venta"
@JsonInclude(JsonInclude.Include.NON_NULL) 	//  ignore all null fields
public class Venta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column
    private Long id;
    
//    @Column
//    private int id_partida;
    
    @Column
    private String folio;
    
    @Column
    private double total;
    
    @Column
    private String estado;

   
    //Getters y setters
    @Override
    public String toString() {
        return "Venta{idVenta=" + id + ", folio=" + folio + ", total=" + total + ", estado=" + estado + ", Partidas{" + "partida size=" + partida.size() + ", partida=" + partida +'}';
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public int getId_partida() {
//        return id_partida;
//    }
//
//    public void setId_partida(int id_partida) {
//        this.id_partida = id_partida;
//    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getEstado() {
    return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
        
    
    //Linkear las 2 tablas?
    @JsonManagedReference
//    @JsonIgnore //Se ignora para que no muestre 2 veces las partidas
    @OneToMany(mappedBy="venta", cascade = CascadeType.ALL)
    private List<Partida> partida = new ArrayList<>();
//    private List<PartidaModel> partida = new ArrayList<>();

//    @JsonIgnore
    public List<Partida> getPartida() {
        //Este es el otro que muestra partidas, pero este es necesario para ingresar datos, así que el @JsonIgnore se debe poner en el otro
        return partida; 
    }
//    @JsonIgnore
    public void setPartida(List<Partida> partida) {
        this.partida = partida;
    }

//    Venta get() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
}
