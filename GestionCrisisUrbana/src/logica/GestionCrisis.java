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
    private Evento falloActivo; // Evento que se encuentra actualmente activo o en atención
    private int id; // Identificador único para cada evento generado
    private FrmPrincipal vista; // Referencia a la ventana principal del sistema (interfaz gráfica)
     // Colas auxiliares para organizar los eventos según su nivel de gravedad
    private ColaEventos despachoCriticos = new ColaEventos(); 
    private ColaEventos despachoMedios   = new ColaEventos();
    private ColaEventos despachoBajos    = new ColaEventos();


    public GestionCrisis() { // Inicializa las estructuras de datos y el contador de identificadores
        despacho = new ColaEventos(); 
        cascadasPendientes = new ColaEventos();
        registro = new PilaEventos();
        id = 1;
    }

    //GETTERS Y SETTERES---------------------
    public int generarId() { // Método que genera y retorna un identificador único para cada evento
        return id++;
    }

    public PilaEventos getRegistro() { // Retorna la pila que contiene el registro de eventos atendidos
        return registro;
    }

    public ColaEventos getDespacho() { // Retorna la cola principal de despacho de eventos
        return despacho;
    }

    public void setVista(FrmPrincipal vista) { // Asigna la referencia de la vista principal al gestor de crisis
        this.vista = vista;
    }
    
    // Método que retorna una cola con los eventos ordenados por prioridad
    // Primero críticos, luego medios y finalmente bajos
    public ColaEventos getDespachoOrdenado() {
        ColaEventos total = new ColaEventos(); 

        copiarCola(despachoCriticos, total); // Se copian los eventos críticos a la cola total
        copiarCola(despachoMedios, total); // Se copian los eventos de prioridad media
        copiarCola(despachoBajos, total); // Se copian los eventos de prioridad baja

        return total; // Se retorna la cola ordenada por nivel de gravedad
    }
    
    
    
    private void copiarCola(ColaEventos origen, ColaEventos destino) { // Método que copia los elementos de una cola origen a una cola destino
        int n = origen.getTamanio(); // Se obtiene el número de elementos de la cola origen
        for (int i = 0; i < n; i++) { // Se recorre la cola origen exactamente n veces
            Evento e = origen.eliminar(); // Se elimina temporalmente el evento del frente de la cola origen
            destino.insertar(e); // Se inserta el evento en la cola destino
            origen.insertar(e); // Se vuelve a insertar el evento en la cola origen para conservar su contenido y orden original
        }
    }

    //------------METODOS DE BOTONES-----------------
    
    // ------------BOTÓN FALLO ELÉCTRICO ------------
    public Evento generarFalloElectrico() { // Genera un evento de fallo eléctrico y lo gestiona dentro del sistema
        Evento e = FallosElectricos.generarFallo(generarId()); // Se genera un nuevo evento de fallo eléctrico con un ID único
        falloActivo = e; // Se establece el evento como el fallo activo actual
        insertarEnDespacho(e); // Se inserta el evento en la cola de despacho correspondiente
        if (vista != null) { // Si la vista está disponible, se registra el fallo y se actualiza la interfaz
            vista.registrarFallo(e);
            vista.refrescarDespacho();
        }
        iniciarCascadasAutomaticasElectrica(); // Se inician automáticamente los eventos en cascada
        return e; // Se retorna el evento generado
    }
    
    // ------------BOTÓN FALLO SEGURIDAD ------------
    public Evento generarFalloSeguridad(){ // Genera un evento de brecha de seguridad
        Evento p = BrechaSeguridad.generarFalloP(generarId());  // Se genera el evento principal de seguridad
        falloActivo = p; // Se asigna como el fallo activo
        insertarEnDespacho(p); // Se inserta el evento en el despacho
        if(vista != null){ // Se actualiza la interfaz gráfica
            vista.registrarFallo(p);
            vista.refrescarDespacho();
        }
        iniciarCascadasAutomaticasSEGURIDAD(); // Se inician los eventos en cascada asociados a la brecha de seguridad
        return p; // Se retorna el evento generado
    }

    // ------------ BOTÓN FALLO AGUA ------------
    public Evento generarFalloAgua(){ // Genera un evento relacionado con el suministro de agua
        Evento a = SuministroAgua.generarFallo(generarId()); // Se crea el evento principal de fallo de agua
        falloActivo=a; // Se establece como evento activo
        insertarEnDespacho(a); // Se inserta el evento en la cola de despacho
        if(vista != null){ // Se actualiza la interfaz gráfica
            vista.registrarFallo(a);
            vista.refrescarDespacho();
        }
        iniciarCascadasAutomaticasAGUA(); // Se generan los eventos en cascada del fallo de agua
        return a; // Se retorna el evento generado
    }
    
    // ------------ BOTÓN FALLO VIAL------------
    public Evento generarFalloVial(){ // Genera un evento de fallo vial
        Evento c = ColapsoVial.generarFalloC(generarId()); // Se genera el evento principal de fallo vial
        falloActivo=c; // Se asigna como el fallo activo
        insertarEnDespacho(c); // Se inserta el evento en el despacho
        if(vista != null){ // Se actualiza la interfaz gráfica
            vista.registrarFallo(c);
            vista.refrescarDespacho();
        }
        iniciarCascadaAutomaticasVial(); // Se inician los eventos en cascada asociados al fallo vial
        return c; // Se retorna el evento generado
    }
    
    // ------------ BOTÓN FALLO EMERGENCIA MEDICA ------------
    public Evento generarFalloMedico(){ // Genera un evento de fallo medico
        Evento m = EmergenciaMedica.generarFalloM(generarId()); // Se genera el evento principal de fallo medico
        falloActivo=m; // Se asigna como el fallo activo
        insertarEnDespacho(m); // Se inserta el evento en el despacho
        if(vista != null){ // Se actualiza la interfaz gráfica
            vista.registrarFallo(m);
            vista.refrescarDespacho();
        }
        iniciarCascadaAutomaticaMedica(); // Se inician los eventos en cascada asociados al fallo medico
        return m; // Se retorna el evento generado
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
