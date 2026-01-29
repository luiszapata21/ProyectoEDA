/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import modelo.FallosElectricos;
import Nodos.NodoServicio;
import estructuras.ColaEventos;
import estructuras.ListaServicios;
import estructuras.PilaEventos;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import modelo.Evento;
import modelo.Servicio;
import vista.FrmPrincipal;

/**
 *
 * @author ASUS
 */
public class GestionCrisis {
    private ColaEventos despacho;          // cola de problemas
    private ColaEventos cascadasPendientes;// cola de cascadas
    private PilaEventos registro;       // pila de atendidos
    private Evento falloActivo;
    private int id;

    public GestionCrisis() {
        despacho = new ColaEventos();
        cascadasPendientes = new ColaEventos();
        registro = new PilaEventos();
        id = 1;
    }

    public int generarId() {
        return id++;
    }

    public PilaEventos getRegistro() {
        return registro;
    }

    public ColaEventos getDespacho() {
        return despacho;
    }
    
    // ===== BOTÓN FALLO ELÉCTRICO =====
    public Evento generarFalloElectrico() {
        Evento e = FallosElectricos.generarFallo(generarId());
        despacho.insertar(e);
        iniciarCascadasAutomaticas();
        return e;
    }

    // ===== ATENDER (CRÍTICO → MEDIO → BAJO) =====
    public Evento atender() {
        Evento e = sacarPorNivel("CRÍTICO");
        if (e == null) e = sacarPorNivel("MEDIO");
        if (e == null) e = despacho.eliminar();

        if (e != null) {
            registro.apilar(e);
            liberarCascada();
        }
        return e;
    }

    // ===== SACAR POR NIVEL =====
    private Evento sacarPorNivel(String nivel) {
        Evento encontrado = null;
        int n = despacho.getTamanio();

        for (int i = 0; i < n; i++) {
            Evento e = despacho.eliminar();
            if (encontrado == null && e.getNivel().equals(nivel)) {
                encontrado = e;
            } else {
                despacho.insertar(e);
            }
        }
        return encontrado;
    }

    // ===== LIBERA UNA CASCADA =====
    private void liberarCascada() {
        if (Math.random() < 0.5) { // aleatorio
            Evento c = cascadasPendientes.eliminar();
            if (c != null) despacho.insertar(c);
        }
    }

 
    
    
    public void iniciarCascadasAutomaticas() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int generadas = 0;

            @Override
            public void run() {
                if (generadas == 3) {
                    timer.cancel();
                    return;
                }

                Evento c = FallosElectricos.generarCascada(generarId());
                despacho.insertar(c);
                JOptionPane.showMessageDialog(null, "⚠ NUEVA CASCADA GENERADA:\n" + c.toString(),
                "Cascada eléctrica",
                javax.swing.JOptionPane.WARNING_MESSAGE);
                generadas++;
            }
        },
        5000 + (int)(Math.random() * 5000),
        6000 + (int)(Math.random() * 6000));
    }
  }
