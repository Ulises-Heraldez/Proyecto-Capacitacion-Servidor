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
import org.springframework.stereotype.Service;
import com.Proyecto_Capacitacion.demo.VentasRepository.VentaRepository;
import com.Proyecto_Capacitacion.demo.exception.ApiDuplicatedEntryException;
import com.Proyecto_Capacitacion.demo.exception.ApiInvalidRequestException;
import com.Proyecto_Capacitacion.demo.exception.ApiEmptyRequestException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 *
 * @author ULISES
 */
@Service
public class VentaService { //implements IService {

    private final VentaRepository ventaRepository;
    private final PartidaRepository partidaRepository;

    public VentaService(VentaRepository ventaRepository, PartidaRepository partidaRepository) {
        this.ventaRepository = ventaRepository;
        this.partidaRepository = partidaRepository;
    }

    public ArrayList<Venta> getVenta() {
        return (ArrayList<Venta>) ventaRepository.findAll();
    }

    public boolean guardarVenta(Venta venta) throws ApiDuplicatedEntryException {

        if (isFolioDuplicado(venta) == true) {
            System.out.println("Folio duplicado");
            throw new ApiDuplicatedEntryException("Folio duplicado");
//            throw new ApiDuplicatedEntryException("Folio duplicado", HttpStatus.FORBIDDEN);
        }

        try {
            venta.setTotal(calcularTotal(venta).doubleValue());
            ventaRepository.save(venta);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

//    public Optional<Venta> getForId(String idS) {
    public Optional<Venta> getForId(String idS) throws ApiInvalidRequestException, ApiEmptyRequestException {

        try {
            Long id = Long.parseLong(idS);
            if (("" + this.ventaRepository.findById(id)).equals("Optional.empty")) {
                System.out.println("Ese id no existe");
                throw new ApiEmptyRequestException("Ese id no existe");
//                throw new ApiInvalidRequestException("Ese id no existe", HttpStatus.NOT_FOUND);
            }
            return this.ventaRepository.findById(id);
        } catch (NumberFormatException nfe) {
            System.out.println("Ingrese un número válido");
            throw new ApiInvalidRequestException("Ingrese un número válido");
//            throw new ApiInvalidRequestException("Ingrese un número válido", HttpStatus.BAD_REQUEST);
        }
//        return ventaRepository.findById(id);
//        return ventaRepository.findById(id);
    }

//    @ExceptionHandler
//    public String handleInvalidIdException(InvalidIdException iie){
//        return iie.getMessage();
//    }
    public ArrayList<Venta> getForFolio(String Folio) {
        return ventaRepository.findByFolio(Folio);
    }

    public ArrayList<Venta> getForEstado(String Estado) {
        return ventaRepository.findByEstado(Estado);
    }

    public ArrayList<Venta> getPorTotal(Double Total) {
        return ventaRepository.findByTotal(Total);
    }

//    public boolean isFolioDuplicado(String folio, Long id) {
    public boolean isFolioDuplicado(Venta venta) {
        if (!(ventaRepository.findByFolio(venta.getFolio()).isEmpty())) {

            Long idVenta = venta.getId();

            //Le asignamos la venta obtenida con el folio repetido
            venta = ventaRepository.findByFolio(venta.getFolio()).get(0);
            //Si el id de la venta obtenida es el mismo de la nueva, entonces que si 
            //  permita dejar el folio igual, ya que no se repetirá de todas maneras

            if (idVenta == venta.getId()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void deleteVenta(String idS) throws ApiInvalidRequestException, ApiEmptyRequestException {
//        try {
//            ventaRepository.deleteById(id);
//            return "Se eliminó la venta con id " + id;
//        } catch (Exception e) {
//            return "No pudo eliminar la venta con id" + id;
//        }

        try {
            Long id = Long.parseLong(idS);
            this.ventaRepository.deleteById(id);
        } catch (NumberFormatException nfe) {
            throw new ApiInvalidRequestException("Ingrese un número válido");
        } catch (EmptyResultDataAccessException erdae) {
            throw new ApiEmptyRequestException("Ese id no existe");
        } catch (Exception e) {
            System.out.println("Error al eliminar venta");
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
                String[] detallesVentaSeparados = DetallesVenta.split(",");

                //Asignar valor a la venta
                String cantidadStr = detallesVentaSeparados[2];
                double cantidadDou = Double.parseDouble(cantidadStr);
                BigDecimal cantidad = BigDecimal.valueOf(cantidadDou);

                String precioStr = detallesVentaSeparados[3];
                double precioDou = Double.parseDouble(precioStr);
                BigDecimal precio = BigDecimal.valueOf(precioDou);

                total = total.add(cantidad.multiply(precio));

                System.out.println("total - " + total);
            }

            return total;

        } catch (Exception e) {
            System.out.println("error calcularTotal - " + e);
            e.printStackTrace();
        }

        return total;
    }

}
