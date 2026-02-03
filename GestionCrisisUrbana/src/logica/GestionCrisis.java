/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import modelo.FallosElectricos;
import estructuras.ColaEventos;
import estructuras.PilaEventos;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import modelo.BrechaSeguridad;
import modelo.ColapsoVial;
import modelo.EmergenciaMedica;
import modelo.Evento;
import modelo.SuministroAgua;
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
    private FrmPrincipal vista;
    private ColaEventos despachoCriticos = new ColaEventos();
    private ColaEventos despachoMedios   = new ColaEventos();
    private ColaEventos despachoBajos    = new ColaEventos();


    public GestionCrisis() {
        despacho = new ColaEventos();
        cascadasPendientes = new ColaEventos();
        registro = new PilaEventos();
        id = 1;
    }

    //GETTERS Y SETTERES---------------------
    public int generarId() {
        return id++;
    }

    public PilaEventos getRegistro() {
        return registro;
    }

    public ColaEventos getDespacho() {
        return despacho;
    }

    public void setVista(FrmPrincipal vista) {
        this.vista = vista;
    }
    
    public ColaEventos getDespachoOrdenado() {
        ColaEventos total = new ColaEventos();

        copiarCola(despachoCriticos, total);
        copiarCola(despachoMedios, total);
        copiarCola(despachoBajos, total);

        return total;
    }
    
    
    
    private void copiarCola(ColaEventos origen, ColaEventos destino) {
        int n = origen.getTamanio();
        for (int i = 0; i < n; i++) {
            Evento e = origen.eliminar();
            destino.insertar(e);
            origen.insertar(e);
        }
    }

    //------------METODOS DE BOTONES-----------------
    
    // ------------BOTÓN FALLO ELÉCTRICO ------------
    public Evento generarFalloElectrico() {
        Evento e = FallosElectricos.generarFallo(generarId());
        falloActivo = e;
        insertarEnDespacho(e);
        if (vista != null) {
            vista.registrarFallo(e);
            vista.refrescarDespacho();
        }
        iniciarCascadasAutomaticasElectrica();
        return e;
    }
    
    // ------------BOTÓN FALLO SEGURIDAD ------------
    public Evento generarFalloSeguridad(){
        Evento p = BrechaSeguridad.generarFalloP(generarId());
        falloActivo = p;
        insertarEnDespacho(p);
        if(vista != null){
            vista.registrarFallo(p);
            vista.refrescarDespacho();
        }
        iniciarCascadasAutomaticasSEGURIDAD();
        return p;
    }

    // ------------ BOTÓN FALLO AGUA ------------
    public Evento generarFalloAgua(){
        Evento a = SuministroAgua.generarFallo(generarId());
        falloActivo=a;
        insertarEnDespacho(a);
        if(vista != null){
            vista.registrarFallo(a);
            vista.refrescarDespacho();
        }
        iniciarCascadasAutomaticasAGUA(); 
        return a;
    }
    
    // ------------ BOTÓN FALLO VIAL------------
    public Evento generarFalloVial(){
        Evento c = ColapsoVial.generarFalloC(generarId());
        falloActivo=c;
        insertarEnDespacho(c);
        if(vista != null){
            vista.registrarFallo(c);
            vista.refrescarDespacho();
        }
        iniciarCascadaAutomaticasVial();
        return c;
    }
    
    // ------------ BOTÓN FALLO EMERGENCIA MEDICA ------------
    public Evento generarFalloMedico(){
        Evento m = EmergenciaMedica.generarFalloM(generarId());
        falloActivo=m;
        insertarEnDespacho(m);
        if(vista != null){
            vista.registrarFallo(m);
            vista.refrescarDespacho();
        }
        iniciarCascadaAutomaticaMedica();
        return m;
    }
    
    
    
    //-------LISTAS DE PRIORIDADES-----------
    
    
    // ------------ ATENDER (CRÍTICO → MEDIO → BAJO) ------------
    public Evento atender() {
        Evento e = null;

        // Primero los CRITICOS
        if (!despachoCriticos.estaVacia()) {
            e = despachoCriticos.eliminar();
        }
        // Segundo los MEDIOS
        else if (!despachoMedios.estaVacia()) {
            e = despachoMedios.eliminar();
        }
        // Tercero los BAJOS
        else if (!despachoBajos.estaVacia()) {
            e = despachoBajos.eliminar();
        }

        if (e != null) {
            registro.apilar(e); // pila de atendidos

            if (vista != null) {
                vista.registrarAtendido(e);
                vista.refrescarDespacho();
                vista.refrescarRegistro();
            }
        }
        return e;
        
    }

    //------------ DESHACCER ULTIMO ------------
    public Evento deshacerUltimo() {
       Evento e = registro.desapilar(); // saca el último
       if (e != null) {
           insertarEnDespacho(e); // vuelve al despacho
                if (vista != null) {
                    vista.registrarDeshecho(e);
                    vista.refrescarDespacho();
                    vista.refrescarRegistro();
                }
       }
       return e;
   }

    
    private void insertarEnDespacho(Evento e) {
        String nivel = e.getNivel().toUpperCase();
        switch (nivel) {
            case "CRÍTICO":
                despachoCriticos.insertar(e);
                break;
            case "MEDIO":
                despachoMedios.insertar(e);
                break;
            case "BAJO":
                despachoBajos.insertar(e);
                break;
        }
    }
    
    
    
    //------------ INICIAR CASCADA ------------
    
    //CASCADAS ELECTRICAS
    public void iniciarCascadasAutomaticasElectrica() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int generadas = 0;
            @Override
            public void run() {
                if (generadas == 3) {
                    timer.cancel();
                    return;
                }
                Evento c = FallosElectricos.generarCascada(generarId(),falloActivo.getNivel());
                insertarEnDespacho(c);
                if (vista != null) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        vista.registrarCascada(c);
                        vista.refrescarDespacho();
                    });
                }
                 // 👇 MENSAJE SE MANTIENE
                JOptionPane.showMessageDialog(null,"⚠ NUEVA CASCADA GENERADA:\n" + c.toString(),"Cascada eléctrica",JOptionPane.WARNING_MESSAGE);
                generadas++;
            }
        }, 7000, 6000);
    }
    
    //CASCADAS DE SEGURIDAD
    public void iniciarCascadasAutomaticasSEGURIDAD() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int generadas = 0;

            @Override
            public void run() {
                if (generadas == 3) {
                    timer.cancel();
                    return;
                }
                Evento c = BrechaSeguridad.generarCascadaP(generarId() , falloActivo.getNivel());
                insertarEnDespacho(c);
                if (vista != null) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        vista.registrarCascada(c);
                        vista.refrescarDespacho(); });
                }

                JOptionPane.showMessageDialog(
                    null,
                    "⚠ NUEVA CASCADA GENERADA:\n" + c.toString(),
                    "Cascada seguridad",
                    JOptionPane.WARNING_MESSAGE);

                generadas++;
            }
        },7000, 6000);
    }
    
    //CASCADAS DE AGUA 
    public void iniciarCascadasAutomaticasAGUA() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int generadas = 0;

            @Override
            public void run() {
                if (generadas == 3) {
                    timer.cancel();
                    return;
                }
                // Generar cascada de agua
                Evento c = modelo.SuministroAgua.generarCascada(generarId(),  falloActivo.getNivel());
                insertarEnDespacho(c);
                if (vista != null) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        vista.registrarCascada(c);
                        vista.refrescarDespacho(); 
                    });
                }

                JOptionPane.showMessageDialog(
                    null,
                    "⚠ NUEVA CASCADA DE AGUA:\n" + c.toString(),
                    "Crisis Hídrica",
                    JOptionPane.WARNING_MESSAGE);

                generadas++;
            }
        }, 7000, 6000); // Mismos tiempos que los otros
    }

    //CASCADAS VIAL
    private void iniciarCascadaAutomaticasVial() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int generadas = 0;

            @Override
            public void run() {
                if (generadas == 3) {
                    timer.cancel();
                    return;
                }
                Evento c = ColapsoVial.generarCascadaP(generarId(), falloActivo.getNivel());
                insertarEnDespacho(c);
                if (vista != null) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        vista.registrarCascada(c);
                        vista.refrescarDespacho(); });
                }

                JOptionPane.showMessageDialog(
                    null,
                    "⚠ NUEVA CASCADA GENERADA:\n" + c.toString(),
                    "Cascada Vial",
                    JOptionPane.WARNING_MESSAGE);

                generadas++;
            }
        },7000, 6000);
    }
    
    //Cascada de Emergencia Médica
    private void iniciarCascadaAutomaticaMedica(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int generadas = 0;

            @Override
            public void run() {
                if (generadas == 3) {
                    timer.cancel();
                    return;
                }
                Evento c = EmergenciaMedica.generarCascadaM(generarId(), falloActivo.getNivel());
                insertarEnDespacho(c);
                if (vista != null) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        vista.registrarCascada(c);
                        vista.refrescarDespacho(); });
                }

                JOptionPane.showMessageDialog(
                    null,
                    "⚠ NUEVA CASCADA GENERADA:\n" + c.toString(),
                    "Emergencia Médica",
                    JOptionPane.WARNING_MESSAGE);

                generadas++;
            }
        },7000, 6000);
    }
    
    /*
    // ------------ MODO AUTOMÁTICO ------------
    public void iniciarSimulacionAutomatica() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                int opcion = (int) (Math.random() * 4);

                Evento e = null;

                switch (opcion) {
                    case 0:
                        e = generarFalloElectrico();
                        break;

                    case 1:
                        e = generarFalloAgua();
                        break;

                    case 2:
                        e = generarFalloSeguridad();
                        break;

                    case 3:
                        e = generarFalloVial();
                        break;
                }

                if (vista != null && e != null) {
                    javax.swing.SwingUtilities.invokeLater(() -> {
                        vista.refrescarDespacho();
                    });
                }
            }
        }, 3000, 10000); // empieza en 3s, luego cada 10s
    }
    
    
    private boolean esCascada(Evento e) {
        String tipo = e.getTipo().toUpperCase();

        // CASCADAS ELÉCTRICAS
        if (tipo.contains("HOSPITALES") ||
            tipo.contains("METRO") ||
            tipo.contains("SEMÁFOROS") ||
            tipo.contains("AGUA INTERRUMPIDA")) {
            return true;
        }

        // CASCADAS DE AGUA
        if (tipo.contains("SALUD") ||
            tipo.contains("SED") ||
            tipo.contains("LIMPIEZA")) {
            return true;
        }

        // CASCADAS VIALES
        if (tipo.contains("ACCIDENTES") ||
            tipo.contains("SISTEMA") ||
            tipo.contains("MALESTAR")) {
            return true;
        }

        // CASCADAS DE SEGURIDAD
        if (tipo.contains("MIEDO") ||
            tipo.contains("CENTROS") ||
            tipo.contains("NEGOCIOS")) {
            return true;
        }

        return false; // si no es cascada → es fallo
    }
*/
    
  }
