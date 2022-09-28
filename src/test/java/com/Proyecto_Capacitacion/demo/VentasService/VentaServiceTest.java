package com.Proyecto_Capacitacion.demo.VentasService;

import com.Proyecto_Capacitacion.demo.VentasModelo.Partida;
import com.Proyecto_Capacitacion.demo.VentasModelo.Venta;
import com.Proyecto_Capacitacion.demo.VentasRepository.PartidaRepository;
import com.Proyecto_Capacitacion.demo.VentasRepository.VentaRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

/**
 *
 * @author TESTER
 */
public class VentaServiceTest {

    private final VentaRepository ventaRepository = mock(VentaRepository.class);
    private final PartidaRepository partidaRepository = mock(PartidaRepository.class);

    @Test
    public void guardarVenta_Normal_TotalEsCorrecto() {
        VentaService service = new VentaService(ventaRepository, partidaRepository); //mock

        Venta venta = new Venta();

        venta.setFolio("123123");
        venta.setEstado("Pagada");

        Partida partida = new Partida();
        partida.setArticulo("Pepino");
        partida.setCantidad(10);
        partida.setPrecio(20);
        partida.setEstado("Activa");

        List<Partida> listaPartidas = new ArrayList<>();
        listaPartidas.add(partida);
        
        Partida partida2 = new Partida();
        partida2.setArticulo("Aguacate");
        partida2.setCantidad(30);
        partida2.setPrecio(15);
        partida2.setEstado("Activa");

        listaPartidas.add(partida2);
        
        

        venta.setPartida(listaPartidas);

        service.guardarVenta(venta);

        assertEquals(650, venta.getTotal());
    }

    @Test
    public void isFolioDuplicado_Normal_FolioNoDuplicado() {
        
        
        
    }

}
