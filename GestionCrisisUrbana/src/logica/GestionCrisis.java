/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import Nodos.NodoServicio;
import estructuras.ColaEventos;
import estructuras.ColaFIFO;
import estructuras.ListaServicios;
import estructuras.PilaEventos;
import estructuras.PilaLIFO;
import modelo.Evento;
import modelo.Servicio;

/**
 *
 * @author ASUS
 */
public class GestionCrisis {
    //------ATRIBUTOS--------
    private ColaFIFO despacho;      // FIFO → problemas activos
    private PilaEventos registro;   // LIFO → atendidos (deshacer)
    private ColaFIFO historial;     // FIFO → todo lo que pasa
    private ColaFIFO cascadasPendientes;
    private int contadorId;

    //CONSTRUCTOR
    public GestionCrisis() {
        despacho = new ColaFIFO();
        registro = new PilaEventos();
        historial = new ColaFIFO();
        cascadasPendientes = new ColaFIFO();
        contadorId = 1;
    }

    // ===== ID =====
    public int generarId() {
        return contadorId++;
    }

    
    

    // ===== ATENDER =====
    public Evento atender() {
        Evento e = despacho.eliminar();
        if (e != null) {
            registro.apilar(e);
            historial.insertar(e);
        }
        return e;
    }

    // ===== DESHACER =====
    public Evento deshacer() {
        Evento e = registro.desapilar();
        if (e != null) {
            despacho.insertar(e);
            historial.insertar(e);
        }
        return e;
    }

    // ===== GETTERS =====
    public ColaFIFO getDespacho() {
        return despacho;
    }

    public PilaEventos getRegistro() {
        return registro;
    }

    public ColaFIFO getHistorial() {
        return historial;
    }
    
    
    
    //----------------GENERACIÓN DE FALLOS------------------
    public Evento generarFalloAleatorioELECTRICO() {
        int r = (int)(Math.random() * 3); // 0,1,2
        Evento e = null;
        switch (r) {

            case 0: // APAGÓN TOTAL
                e = new Evento(
                    generarId(),
                    "Apagón total",
                    "CRÍTICO", "Apagón total en la ciudad de QUITO"
                );
                despacho.insertar(e);
                historial.insertar(e);

                // Cascada automática
                generarCascadaApagon();

                break;

            case 1: // CORTE PARCIAL
                e = new Evento(
                    generarId(),
                    "Corte parcial de energía",
                    "MEDIO", "Corte de energía en varios sectores"
                );
                despacho.insertar(e);
                historial.insertar(e);

                generarCascadaCorteParcial();
                break;

            case 2: // SOBRECARGA
                e = new Evento(
                    generarId(),
                    "Sobrecarga eléctrica",
                    "BAJO", "Fallo en cableado"
                );
                despacho.insertar(e);
                historial.insertar(e);
                break;
        }
        return e;
    }

    private void generarCascadaApagon() {
         generarEventoCascada("Salud", "CRÍTICO", "Hospitales sin energía");
        generarEventoCascada("Seguridad", "CRÍTICO", "Semáforos fuera de servicio");
        generarEventoCascada("Transporte", "MEDIO", "Metro detenido");
        generarEventoCascada("Agua", "MEDIO", "Bombeo de agua interrumpido");
    }

    private void generarCascadaCorteParcial() {
        generarEventoCascada("Retrasos en Transporte", "MEDIO", "Retrasos en la vía pública");
        generarEventoCascada("Disminución de Seguridad", "MEDIO", "Reducción de vigilancia nocturna");
    }
    
    
    
    
    
    
    
    private void generarEventoCascada(String tipo, String nivel,String descripcion) {
        Evento e = new Evento(generarId(),tipo,nivel, descripcion );
        cascadasPendientes.insertar(e);
        historial.insertar(e);
    }
    
    private void liberarUnaCascada() {
      Evento e = cascadasPendientes.eliminar();
      if (e != null) {
        despacho.insertar(e);
      }
    }
    
    //-------ATENDER FALLOS---------------
    public Evento atenderMasCritico() {
        if (despacho.estaVacia()) return null;

        Evento e = sacarPorNivel("CRÍTICO");
        if (e == null) e = sacarPorNivel("MEDIO");
        if (e == null) e = despacho.eliminar(); // BAJO

        if (e != null) {
            registro.apilar(e);
            historial.insertar(e);

            liberarUnaCascada();
        }

        return e;
    }
    
    private Evento sacarPorNivel(String nivel) {
        Evento encontrado = null;
        int n = despacho.getTamanio();
        for (int i = 0; i < n; i++) {
            Evento e = despacho.eliminar();
            if (encontrado == null && e.getNivel().equals(nivel)) {
                encontrado = e; // lo saco
            } else {
                despacho.insertar(e); // lo devuelvo
            }
        }
        return encontrado;
    }

  }
