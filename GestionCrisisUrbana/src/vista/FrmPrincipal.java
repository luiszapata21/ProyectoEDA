/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import Nodos.NodoEvento;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import logica.GestionCrisis;
import modelo.Evento;
import modelo.TipoProblema;


/**
 *
 * @author ASUS
 */
public class FrmPrincipal extends javax.swing.JFrame {
    // PUNTOS
    private PanelPuntos panelPuntos;
    
    //ATRIBUTO GLOBAL
    private GestionCrisis gestion;
    
    //HISTORIAL
    private JLabel[] pilaHistorial;
    private int topeHistorial;
    private final int MAX_HISTORIAL = 100;

    
    //BARRAS
    private int eventosActivos = 0;
    private final int MAX_EVENTOS = 100;
    

       // ----------- COLORES -----------
    private final Color BG_PRINCIPAL = new Color(10, 15, 30);
    private final Color BG_PANEL     = new Color(20, 30, 55);
    private final Color ROJO_ALERTA  = new Color(220, 60, 60);
    private final Color VERDE_OK     = new Color(40, 200, 120);
    private final Color AMARILLO     = new Color(255, 180, 60);
    private final Color AZUL         = new Color(80, 160, 255);
    private final Color MORADO       = new Color(170, 90, 255);

    // ----------- PANEL -----------
    private void estilizarPanel(JDesktopPane p) {
        p.setBackground(BG_PANEL);
        p.setBorder(
            javax.swing.BorderFactory.createLineBorder(
                new Color(80, 120, 200, 80), 1
            )
        );
        p.setOpaque(true);
    }

    // ----------- BARRA -----------
    private void estilizarBarra(JProgressBar bar, Color color) {
        bar.setForeground(color);
        bar.setBackground(new Color(30, 40, 70));
        bar.setBorder(null);
        bar.setPreferredSize(new Dimension(200, 6));
        bar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI());
        bar.setStringPainted(false);
    }

    // ----------- BOTÓN -----------
    private void estilizarBoton(JButton btn, Color fondo) {
        btn.setBackground(fondo.darker());
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(
            javax.swing.BorderFactory.createLineBorder(
                fondo.brighter(), 1
            )
        );
        btn.setOpaque(true);
    }


    
    public FrmPrincipal() {
        initComponents();
        gestion = new GestionCrisis(); 
            
        
        //PUNTOS
        panelPuntos = new PanelPuntos();
        jLabelFoto.add(panelPuntos);
        panelPuntos.setBounds(0, 0, jLabelFoto.getWidth(), jLabelFoto.getHeight());   
        
        

        //PANELES DE REGISTRO Y DESPACHO
        jPanelDespacho.setLayout( new javax.swing.BoxLayout(jPanelDespacho,javax.swing.BoxLayout.Y_AXIS) );
        jPanelRegsitro.setLayout(new javax.swing.BoxLayout(jPanelRegsitro, javax.swing.BoxLayout.Y_AXIS));
       
        
        gestion= new GestionCrisis();
        gestion.setVista(this);
         
        pilaHistorial = new JLabel[MAX_HISTORIAL];
        topeHistorial = -1;
        
        // Fondo del JFrame
        getContentPane().setBackground(BG_PRINCIPAL);

        // ====== PANELES ======
        estilizarPanel(jDesktopPaneErnegia);
        estilizarPanel(jDesktopPaneSalud);
        estilizarPanel(jDesktopPaneTransporte);
        estilizarPanel(jDesktopPaneAgua);
        estilizarPanel(jDesktopPaneSeguridad);
        estilizarPanel(jDesktopPaneBotones);
        estilizarPanel(jDesktopPane1);
        jPanelDespacho.setBorder( javax.swing.BorderFactory.createLineBorder(new Color(80, 160, 255), 2));
        jPanelContendorConsola.setBackground(new Color(16, 50, 90)); 
        jPanelContenedorDespachos.setBackground(new Color(16, 50, 90)); 
        jPanelDespacho.setBackground(new Color(200, 220, 235));
        jPanelRegsitro.setBackground(new Color(200, 220, 235));
        jPanelHistorial.setBackground(Color.BLACK);

        

        // ====== BARRAS ======
        estilizarBarra(jProgressEnergia, ROJO_ALERTA);
        estilizarBarra(jProgressSalud, VERDE_OK);
        estilizarBarra(jProgressTransporte, AMARILLO);
        estilizarBarra(jProgressAgua, AZUL);
        estilizarBarra(jProgressSeguridad1, MORADO);
        estilizarBarra(jProgressSEventos, AZUL);
        
        // ====== BOTONES ======
        estilizarBoton(jBFalloElectrico, ROJO_ALERTA);
        estilizarBoton(jBFalloAgua, AZUL);
        estilizarBoton(jBFalloVial, AMARILLO);
        estilizarBoton(jBFalloSalud, VERDE_OK);
        estilizarBoton(jBFalloSeguridad, MORADO);
        jBAtender.setBackground(new Color(173, 216, 230));
        jBDeshacer.setBackground(new Color(173, 216, 230));

        // ====== FUENTES ======
        Font titulo = new Font("Segoe UI", Font.BOLD, 30);
        Font valor  = new Font("Segoe UI", Font.BOLD, 16);
        Font valorServicios = new Font("Segoe UI", Font.BOLD, 20);
        

        jLabelTitulo.setFont(titulo);
        jLabelFilaDespacho.setFont(new Font("Segoe UI", Font.BOLD, 25) { });
        jLabelRegistro.setFont(new Font("Segoe UI", Font.BOLD, 25) {});
        jLabelSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 20) {});
        jLabelEnergiaValor.setFont(valor);
        jLabelSaludValor.setFont(valor);
        jLabelTransporteValor.setFont(valor);
        jLabelAguaValor.setFont(valor);
        jLabelSeguridadValor.setFont(valor);
        jLabelEventosValor.setFont(valor);
        jLabelEventosValor.setFont(new Font("Segoe UI", Font.BOLD, 40) { });
        jLabelErnegia.setFont(valorServicios);
        jLabelSalud.setFont(valorServicios);
        jLabelTransporte.setFont(valorServicios);
        jLabelAgua.setFont(valorServicios);
        jLabelSeguridad.setFont(valorServicios);
        jLabelEnergiaValor.setForeground(new Color(220, 60, 60));
        jLabelSaludValor.setForeground(new Color(40, 200, 120));
        jLabelTransporteValor.setForeground(new Color(255, 180, 60));
        jLabelAguaValor.setForeground(new Color(80, 160, 255));
        jLabelSeguridadValor.setForeground(new Color(170, 90, 255));
        jLabelEventosValor.setForeground(new Color(220, 230, 255));
        jLabelSubtitulo.setForeground(Color.WHITE);
        jLabelFilaDespacho.setForeground(Color.WHITE);
        jLabelRegistro.setForeground(Color.WHITE);
    }
    
    //------PUNTOS---------
    public class PanelPuntos extends JPanel {
        private ArrayList<Punto> puntos = new ArrayList<>();
        private boolean fondoGris = false;

        public PanelPuntos() {
            setOpaque(false);
            // Timer para animación (palpitado)
            Timer timer = new Timer(80, e -> repaint());
            timer.start();
        }
        
       public void agregarPunto(Evento e, Color color) {
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());
            puntos.add(new Punto(e, x, y, color));
            fondoGris = true;
            repaint();
        }
        
        public void quitarPunto(Evento e) {
            puntos.removeIf(p -> p.evento.equals(e));
            if (puntos.isEmpty()) fondoGris = false;
            repaint();
        }

        public void restaurarPunto(Evento e) {
            // evitar duplicados
            for (Punto p : puntos) {
                if (p.evento.equals(e)) {
                    return;
                }
            }
            Color color = colorPorTipo(e);
            agregarPunto(e, color);
        }
        

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // Fondo gris semitransparente
            if (fondoGris) {
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            // Dibujar puntos
            for (Punto p : puntos) {
                p.dibujar(g2);
            }
        }
    }
       
    public class Punto {
        Evento evento;
        int x, y;
        Color color;
        int radio = 13;
        boolean crecer = true;

        public Punto(Evento evento, int x, int y, Color color) {
            this.evento = evento;
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public void dibujar(Graphics2D g) {
            if (crecer) radio++;
            else radio--;
            if (radio >= 25) crecer = false;
            if (radio <= 20) crecer = true;
            g.setColor(color);
            g.fillOval(x - radio / 2, y - radio / 2, radio, radio);
        }
    }   
    
    private Color colorPorTipo(Evento e) {

        String tipo = e.getTipo().toLowerCase();

        if (tipo.contains("apagón")) {
            return ROJO_ALERTA;
        }
        if (tipo.contains("agua")) {
            return AZUL;
        }
        if (tipo.contains("metro") || tipo.contains("semáforo") || tipo.contains("transporte")) {
            return AMARILLO;
        }
        if (tipo.contains("hospitales")) {
            return VERDE_OK;
        }
        if (tipo.contains("disturbios") || tipo.contains("robo") || tipo.contains("incidente") ) {
            return MORADO;
        }
        return Color.GRAY;
    }

    
    // --------- BARRAS DE PORCENTAJE -----
    private void actualizarEventos(int delta) {
        eventosActivos += delta;

        if (eventosActivos < 0) eventosActivos = 0;
        if (eventosActivos > MAX_EVENTOS) eventosActivos = MAX_EVENTOS;

        jProgressSEventos.setValue(eventosActivos);
        jLabelEventosValor.setText(String.valueOf(eventosActivos));
    }
    
    private int impactoPorNivel(String nivel) {
        switch (nivel) {
            case "CRÍTICO": return 30;
            case "MEDIO":   return 15;
            case "BAJO":    return 5;
            default:        return 0;
        }
    }
    
    private void modificarBarra(JProgressBar barra, JLabel label,int delta) {
        int valor = barra.getValue() + delta;
        if (valor < 0) valor = 0;
        if (valor > 100) valor = 100;
        barra.setValue(valor);
        label.setText(valor + " %");
    }
    
    private void aplicarImpacto(Evento e, boolean subir) {
        int impacto = impactoPorNivel(e.getNivel());
        if (!subir) impacto = -impacto;
        String tipo = e.getTipo().toUpperCase();
        if (tipo.contains("APAGÓN") ||  tipo.contains("CORTE") || tipo.contains("SOBRECARGA") ) {
            modificarBarra(jProgressEnergia, jLabelEnergiaValor, impacto);
        } else if (tipo.contains("AGUA")|| tipo.contains("PRESION") || tipo.contains("CONTAMINACION")) {
            modificarBarra(jProgressAgua, jLabelAguaValor, impacto);
        } else if (tipo.contains("TRANSPORTE") || tipo.contains("METRO") || tipo.contains("SEMÁFOROS")) {
            modificarBarra(jProgressTransporte, jLabelTransporteValor, impacto);
        } else if (tipo.contains("HOSPITALES") || tipo.contains("SALUD")) {
            modificarBarra(jProgressSalud, jLabelSaludValor, impacto);
        } else if (tipo.contains("DISTURBIOS") || tipo.contains("ROBO") || tipo.contains("INCIDENTE") || tipo.contains("CENTROS")
                || tipo.contains("ARMADA") || tipo.contains("MEDIOS") || tipo.contains("NEGOCIOS")) {
            modificarBarra(jProgressSeguridad1, jLabelSeguridadValor, impacto);
        }
    }
    
    
    
    //-------DESPACHO Y RGISTRO
    
    private JPanel crearProblema(String texto) {
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("  " + texto);
        label.setForeground(Color.BLACK);

        panel.add(label, BorderLayout.CENTER);
        return panel;
    }
    
    private void agregarProblemaAlDespacho(String texto) {
        JPanel problema = crearProblema(texto);
        jPanelDespacho.add(problema);
        jPanelDespacho.revalidate();
        jPanelDespacho.repaint();
    }
    
     private void agregarProblemaAlRegistro(String texto) {
        JPanel problema = crearProblema(texto);
        jPanelRegsitro.add(problema);
        jPanelRegsitro.revalidate();
        jPanelRegsitro.repaint();
    }
    
    public void refrescarDespacho() {
         jPanelDespacho.removeAll();
        NodoEvento aux = gestion.getDespacho().getFrente();
        while (aux != null) {
            agregarProblemaAlDespacho(aux.info.toString());
            aux = aux.liga;
        }
        jPanelDespacho.revalidate();
        jPanelDespacho.repaint();
    }
    
    public void refrescarRegistro() {
        jPanelRegsitro.removeAll();

        NodoEvento aux = gestion.getRegistro().getCima(); // pila (último arriba)
        while (aux != null) {
            agregarProblemaAlRegistro(aux.info.toString());
            aux = aux.liga;
        }

        jPanelRegsitro.revalidate();
        jPanelRegsitro.repaint();
        
    }

    
    
    // ------ HISTORISAL ------
    
    public void agregarAlHistorial(String texto) {
        if (topeHistorial == MAX_HISTORIAL - 1) return; // lleno
         JLabel lbl = new JLabel(texto);
         lbl.setForeground(Color.RED);
         lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));

         // subir tope
         topeHistorial++;

         // mover todo hacia abajo (efecto pila)
         for (int i = topeHistorial; i > 0; i--) {
             pilaHistorial[i] = pilaHistorial[i - 1];
         }

         // nuevo arriba
         pilaHistorial[0] = lbl;
         refrescarHistorial();
        
    }
    
    
    
    
    public void refrescarHistorial() {
        jPanelHistorial.removeAll();
        int y = 10;
        for (int i = 0; i <= topeHistorial; i++) {
            JLabel lbl = pilaHistorial[i];
            lbl.setBounds(
                10,
                y,
                500,
                25
            );
            jPanelHistorial.add(lbl);
            y += 28;
        }
        jPanelHistorial.revalidate();
        jPanelHistorial.repaint();
    }
    
    private String horaActual() {
        return java.time.LocalTime.now()
                .withNano(0)
                .toString();
    }
    
    
    //--------- METODOS DE REGISTROS
    
    public void registrarFallo(Evento e) {
        agregarAlHistorial(
            "[" + horaActual() + "] ❌ FALLO: " + e.getNivel() + " | " + e.getTipo()
        ); 
        actualizarEventos(+1);
    }

    public void registrarCascada(Evento e) {
        agregarAlHistorial(
            "[" + horaActual() + "] ⚠ CASCADA: " + e.getNivel() + " | " + e.getTipo()
        );
        Color color = colorPorTipo(e);
        panelPuntos.agregarPunto(e, colorPorTipo(e));
        actualizarEventos(+1);
        aplicarImpacto(e, false); 
    }

    public void registrarAtendido(Evento e) {
        agregarAlHistorial(
            "[" + horaActual() + "] ✅ ATENDIDO: " + e.getNivel() + " | " + e.getTipo()
        );
        actualizarEventos(-1);
        panelPuntos.quitarPunto(e);
    }

    public void registrarDeshecho(Evento e) {
        agregarAlHistorial(
            "[" + horaActual() + "] 🔁 DESHECHO: " + e.getNivel() + " | " + e.getTipo()
        );
        actualizarEventos(+1);
        panelPuntos.restaurarPunto(e);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jDesktopPaneErnegia1 = new javax.swing.JDesktopPane();
        jProgressEnergia1 = new javax.swing.JProgressBar();
        jLabelErnegia1 = new javax.swing.JLabel();
        jLabelEnergiaValor1 = new javax.swing.JLabel();
        jDesktopPaneErnegia = new javax.swing.JDesktopPane();
        jProgressEnergia = new javax.swing.JProgressBar();
        jLabelErnegia = new javax.swing.JLabel();
        jLabelEnergiaValor = new javax.swing.JLabel();
        jDesktopPaneSalud = new javax.swing.JDesktopPane();
        jLabelSalud = new javax.swing.JLabel();
        jLabelSaludValor = new javax.swing.JLabel();
        jProgressSalud = new javax.swing.JProgressBar();
        jDesktopPaneTransporte = new javax.swing.JDesktopPane();
        jLabelTransporte = new javax.swing.JLabel();
        jLabelTransporteValor = new javax.swing.JLabel();
        jProgressTransporte = new javax.swing.JProgressBar();
        jDesktopPaneAgua = new javax.swing.JDesktopPane();
        jLabelAgua = new javax.swing.JLabel();
        jLabelAguaValor = new javax.swing.JLabel();
        jProgressAgua = new javax.swing.JProgressBar();
        jDesktopPaneSeguridad = new javax.swing.JDesktopPane();
        jLabelSeguridad = new javax.swing.JLabel();
        jLabelSeguridadValor = new javax.swing.JLabel();
        jProgressSeguridad1 = new javax.swing.JProgressBar();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabelTitulo = new javax.swing.JLabel();
        jDesktopPaneBotones = new javax.swing.JDesktopPane();
        jBFalloSeguridad = new javax.swing.JButton();
        jBFalloSalud = new javax.swing.JButton();
        jBFalloVial = new javax.swing.JButton();
        jBFalloAgua = new javax.swing.JButton();
        jBFalloElectrico = new javax.swing.JButton();
        jPanelContenedorDespachos = new javax.swing.JPanel();
        jLabelFilaDespacho = new javax.swing.JLabel();
        jProgressSEventos = new javax.swing.JProgressBar();
        jLabelEventosValor = new javax.swing.JLabel();
        jBAtender = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanelDespacho = new javax.swing.JList<>();
        jPanelContendorConsola = new javax.swing.JPanel();
        jLabelRegistro = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanelRegsitro = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanelHistorial = new javax.swing.JList<>();
        jBDeshacer = new javax.swing.JButton();
        jLabelSubtitulo = new javax.swing.JLabel();
        jLabelFoto = new javax.swing.JLabel();

        jCheckBox1.setText("jCheckBox1");

        jProgressEnergia1.setValue(100);

        jLabelErnegia1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelErnegia1.setText("⚡ Energía");

        jLabelEnergiaValor1.setText("100 %");

        jDesktopPaneErnegia1.setLayer(jProgressEnergia1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneErnegia1.setLayer(jLabelErnegia1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneErnegia1.setLayer(jLabelEnergiaValor1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneErnegia1Layout = new javax.swing.GroupLayout(jDesktopPaneErnegia1);
        jDesktopPaneErnegia1.setLayout(jDesktopPaneErnegia1Layout);
        jDesktopPaneErnegia1Layout.setHorizontalGroup(
            jDesktopPaneErnegia1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneErnegia1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneErnegia1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneErnegia1Layout.createSequentialGroup()
                        .addComponent(jProgressEnergia1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDesktopPaneErnegia1Layout.createSequentialGroup()
                        .addComponent(jLabelErnegia1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelEnergiaValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jDesktopPaneErnegia1Layout.setVerticalGroup(
            jDesktopPaneErnegia1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneErnegia1Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(jDesktopPaneErnegia1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelErnegia1)
                    .addComponent(jLabelEnergiaValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressEnergia1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jProgressEnergia.setValue(100);

        jLabelErnegia.setForeground(new java.awt.Color(255, 255, 255));
        jLabelErnegia.setText("⚡ Energía");

        jLabelEnergiaValor.setText("100 %");

        jDesktopPaneErnegia.setLayer(jProgressEnergia, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneErnegia.setLayer(jLabelErnegia, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneErnegia.setLayer(jLabelEnergiaValor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneErnegiaLayout = new javax.swing.GroupLayout(jDesktopPaneErnegia);
        jDesktopPaneErnegia.setLayout(jDesktopPaneErnegiaLayout);
        jDesktopPaneErnegiaLayout.setHorizontalGroup(
            jDesktopPaneErnegiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneErnegiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneErnegiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneErnegiaLayout.createSequentialGroup()
                        .addComponent(jProgressEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jDesktopPaneErnegiaLayout.createSequentialGroup()
                        .addComponent(jLabelErnegia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelEnergiaValor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );
        jDesktopPaneErnegiaLayout.setVerticalGroup(
            jDesktopPaneErnegiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneErnegiaLayout.createSequentialGroup()
                .addGap(0, 34, Short.MAX_VALUE)
                .addGroup(jDesktopPaneErnegiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelErnegia)
                    .addComponent(jLabelEnergiaValor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelSalud.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSalud.setText("❤️‍ Salud");

        jLabelSaludValor.setText("100 %");

        jProgressSalud.setValue(100);

        jDesktopPaneSalud.setLayer(jLabelSalud, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSalud.setLayer(jLabelSaludValor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSalud.setLayer(jProgressSalud, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneSaludLayout = new javax.swing.GroupLayout(jDesktopPaneSalud);
        jDesktopPaneSalud.setLayout(jDesktopPaneSaludLayout);
        jDesktopPaneSaludLayout.setHorizontalGroup(
            jDesktopPaneSaludLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneSaludLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneSaludLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneSaludLayout.createSequentialGroup()
                        .addComponent(jLabelSalud)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelSaludValor, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jDesktopPaneSaludLayout.createSequentialGroup()
                        .addComponent(jProgressSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jDesktopPaneSaludLayout.setVerticalGroup(
            jDesktopPaneSaludLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneSaludLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneSaludLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneSaludLayout.createSequentialGroup()
                        .addGap(0, 22, Short.MAX_VALUE)
                        .addComponent(jLabelSalud))
                    .addComponent(jLabelSaludValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jProgressSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jLabelTransporte.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTransporte.setText("🚥 Transporte");

        jLabelTransporteValor.setText("100 %");

        jProgressTransporte.setValue(100);

        jDesktopPaneTransporte.setLayer(jLabelTransporte, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneTransporte.setLayer(jLabelTransporteValor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneTransporte.setLayer(jProgressTransporte, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneTransporteLayout = new javax.swing.GroupLayout(jDesktopPaneTransporte);
        jDesktopPaneTransporte.setLayout(jDesktopPaneTransporteLayout);
        jDesktopPaneTransporteLayout.setHorizontalGroup(
            jDesktopPaneTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneTransporteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneTransporteLayout.createSequentialGroup()
                        .addComponent(jLabelTransporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelTransporteValor, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(jDesktopPaneTransporteLayout.createSequentialGroup()
                        .addComponent(jProgressTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jDesktopPaneTransporteLayout.setVerticalGroup(
            jDesktopPaneTransporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneTransporteLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabelTransporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jDesktopPaneTransporteLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelTransporteValor, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        jLabelAgua.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgua.setText("💧 Agua");

        jLabelAguaValor.setText("100 %");

        jProgressAgua.setValue(100);

        jDesktopPaneAgua.setLayer(jLabelAgua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneAgua.setLayer(jLabelAguaValor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneAgua.setLayer(jProgressAgua, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneAguaLayout = new javax.swing.GroupLayout(jDesktopPaneAgua);
        jDesktopPaneAgua.setLayout(jDesktopPaneAguaLayout);
        jDesktopPaneAguaLayout.setHorizontalGroup(
            jDesktopPaneAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneAguaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPaneAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneAguaLayout.createSequentialGroup()
                        .addComponent(jLabelAgua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelAguaValor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPaneAguaLayout.createSequentialGroup()
                        .addComponent(jProgressAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jDesktopPaneAguaLayout.setVerticalGroup(
            jDesktopPaneAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneAguaLayout.createSequentialGroup()
                .addGroup(jDesktopPaneAguaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPaneAguaLayout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addComponent(jLabelAgua))
                    .addGroup(jDesktopPaneAguaLayout.createSequentialGroup()
                        .addComponent(jLabelAguaValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGap(24, 24, 24)
                .addComponent(jProgressAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelSeguridad.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSeguridad.setText("🛡️ SEGURIDAD");
        jLabelSeguridad.setToolTipText("");

        jLabelSeguridadValor.setText("100 %");

        jProgressSeguridad1.setValue(100);

        jDesktopPaneSeguridad.setLayer(jLabelSeguridad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSeguridad.setLayer(jLabelSeguridadValor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneSeguridad.setLayer(jProgressSeguridad1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneSeguridadLayout = new javax.swing.GroupLayout(jDesktopPaneSeguridad);
        jDesktopPaneSeguridad.setLayout(jDesktopPaneSeguridadLayout);
        jDesktopPaneSeguridadLayout.setHorizontalGroup(
            jDesktopPaneSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPaneSeguridadLayout.createSequentialGroup()
                .addComponent(jLabelSeguridad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSeguridadValor, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jDesktopPaneSeguridadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressSeguridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jDesktopPaneSeguridadLayout.setVerticalGroup(
            jDesktopPaneSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneSeguridadLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jDesktopPaneSeguridadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSeguridad)
                    .addComponent(jLabelSeguridadValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jProgressSeguridad1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Plataforma de Gestión de Crisis Urbanas ");

        jDesktopPane1.setLayer(jLabelTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo)
                .addContainerGap(1333, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBFalloSeguridad.setText("BRECHA SEGURIDAD");
        jBFalloSeguridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFalloSeguridadActionPerformed(evt);
            }
        });

        jBFalloSalud.setText("EMERGENCIA MÉDICA");
        jBFalloSalud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFalloSaludActionPerformed(evt);
            }
        });

        jBFalloVial.setText("COLAPSO VIAL");
        jBFalloVial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFalloVialActionPerformed(evt);
            }
        });

        jBFalloAgua.setText("SUMINISTRO AGUA");
        jBFalloAgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFalloAguaActionPerformed(evt);
            }
        });

        jBFalloElectrico.setText("FALLO ELÉCTRICO");
        jBFalloElectrico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBFalloElectricoActionPerformed(evt);
            }
        });

        jDesktopPaneBotones.setLayer(jBFalloSeguridad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneBotones.setLayer(jBFalloSalud, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneBotones.setLayer(jBFalloVial, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneBotones.setLayer(jBFalloAgua, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPaneBotones.setLayer(jBFalloElectrico, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPaneBotonesLayout = new javax.swing.GroupLayout(jDesktopPaneBotones);
        jDesktopPaneBotones.setLayout(jDesktopPaneBotonesLayout);
        jDesktopPaneBotonesLayout.setHorizontalGroup(
            jDesktopPaneBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneBotonesLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jDesktopPaneBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBFalloSeguridad, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFalloSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFalloVial, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFalloAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBFalloElectrico, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        jDesktopPaneBotonesLayout.setVerticalGroup(
            jDesktopPaneBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPaneBotonesLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBFalloElectrico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBFalloAgua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBFalloVial, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBFalloSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBFalloSeguridad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabelFilaDespacho.setText("FILA DE DESPACHO");

        jProgressSEventos.setValue(100);

        jLabelEventosValor.setText("0");

        jBAtender.setText("ATENDER");
        jBAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAtenderActionPerformed(evt);
            }
        });

        jPanelDespacho.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jPanelDespacho);

        javax.swing.GroupLayout jPanelContenedorDespachosLayout = new javax.swing.GroupLayout(jPanelContenedorDespachos);
        jPanelContenedorDespachos.setLayout(jPanelContenedorDespachosLayout);
        jPanelContenedorDespachosLayout.setHorizontalGroup(
            jPanelContenedorDespachosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorDespachosLayout.createSequentialGroup()
                .addGroup(jPanelContenedorDespachosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelContenedorDespachosLayout.createSequentialGroup()
                        .addGroup(jPanelContenedorDespachosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelContenedorDespachosLayout.createSequentialGroup()
                                .addGroup(jPanelContenedorDespachosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelContenedorDespachosLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabelFilaDespacho))
                                    .addComponent(jProgressSEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addComponent(jLabelEventosValor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelContenedorDespachosLayout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jBAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelContenedorDespachosLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelContenedorDespachosLayout.setVerticalGroup(
            jPanelContenedorDespachosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContenedorDespachosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelContenedorDespachosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelEventosValor, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelContenedorDespachosLayout.createSequentialGroup()
                        .addComponent(jLabelFilaDespacho)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jProgressSEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBAtender, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabelRegistro.setText("CONSOLA");

        jPanelRegsitro.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane7.setViewportView(jPanelRegsitro);

        jTabbedPane1.addTab("tab2", jScrollPane7);

        jPanelHistorial.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jPanelHistorial);

        jTabbedPane1.addTab("tab2", jScrollPane6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jBDeshacer.setText("DESHACER");
        jBDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDeshacerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelContendorConsolaLayout = new javax.swing.GroupLayout(jPanelContendorConsola);
        jPanelContendorConsola.setLayout(jPanelContendorConsolaLayout);
        jPanelContendorConsolaLayout.setHorizontalGroup(
            jPanelContendorConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContendorConsolaLayout.createSequentialGroup()
                .addGroup(jPanelContendorConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelContendorConsolaLayout.createSequentialGroup()
                        .addGroup(jPanelContendorConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelContendorConsolaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelRegistro))
                            .addGroup(jPanelContendorConsolaLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jBDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 128, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelContendorConsolaLayout.setVerticalGroup(
            jPanelContendorConsolaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContendorConsolaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelRegistro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(jBDeshacer, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelSubtitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSubtitulo.setText("INTEGRIDAD DE SISTEMAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSubtitulo)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jDesktopPaneSeguridad, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDesktopPaneAgua, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDesktopPaneTransporte, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDesktopPaneSalud, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jDesktopPaneBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jDesktopPaneErnegia, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelContenedorDespachos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelContendorConsola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelSubtitulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDesktopPaneErnegia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDesktopPaneSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDesktopPaneTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDesktopPaneAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDesktopPaneSeguridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDesktopPaneBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelContenedorDespachos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jPanelContendorConsola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    
    private void jBFalloElectricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFalloElectricoActionPerformed
        
       Evento e = gestion.generarFalloElectrico();
       panelPuntos.agregarPunto(e, ROJO_ALERTA);
        JOptionPane.showMessageDialog( this, "Nuevo evento generado:\n" + e.toString(),"Fallo Eléctrico",JOptionPane.WARNING_MESSAGE);
        aplicarImpacto(e, false);
        refrescarDespacho();
        
    
    }//GEN-LAST:event_jBFalloElectricoActionPerformed

    private void jBFalloAguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFalloAguaActionPerformed
        Evento a = gestion.generarFalloAgua();
        panelPuntos.agregarPunto(a, AZUL);
        
        // 3. Mostrar mensaje al usuario
        JOptionPane.showMessageDialog(this,
            "Nuevo evento generado:\n" + a.toString(),
            "Fallo de Suministro de Agua",
            JOptionPane.WARNING_MESSAGE
        );
        aplicarImpacto(a, false);
        refrescarDespacho();
    }//GEN-LAST:event_jBFalloAguaActionPerformed

    private void jBFalloVialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFalloVialActionPerformed
  
    }//GEN-LAST:event_jBFalloVialActionPerformed

    private void jBFalloSaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFalloSaludActionPerformed

        

    }//GEN-LAST:event_jBFalloSaludActionPerformed

    private void jBFalloSeguridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBFalloSeguridadActionPerformed
          Evento b = gestion.generarFalloSeguridad();
          panelPuntos.agregarPunto(b, MORADO);
          JOptionPane.showMessageDialog(this,"Nuevo evento generado:\n" + b.toString(),"Fallo de Seguridad",JOptionPane.WARNING_MESSAGE);
          aplicarImpacto(b, false);
          refrescarDespacho();
    }//GEN-LAST:event_jBFalloSeguridadActionPerformed

    private void jBAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAtenderActionPerformed

        Evento e = gestion.atender();
        if (e != null) {
            JOptionPane.showMessageDialog(this,
                "Atendido:\n" + e.toString(),
                "Atención",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
        refrescarDespacho();
        refrescarRegistro();
        aplicarImpacto(e, true);
    }//GEN-LAST:event_jBAtenderActionPerformed

    private void jBDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDeshacerActionPerformed
        Evento e = gestion.deshacerUltimo();
        if (e != null) {
            refrescarDespacho();
            refrescarRegistro();
            aplicarImpacto(e, false);
        }
        

    }//GEN-LAST:event_jBDeshacerActionPerformed

    
    
    
    
    
 
   
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAtender;
    private javax.swing.JButton jBDeshacer;
    private javax.swing.JButton jBFalloAgua;
    private javax.swing.JButton jBFalloElectrico;
    private javax.swing.JButton jBFalloSalud;
    private javax.swing.JButton jBFalloSeguridad;
    private javax.swing.JButton jBFalloVial;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPaneAgua;
    private javax.swing.JDesktopPane jDesktopPaneBotones;
    private javax.swing.JDesktopPane jDesktopPaneErnegia;
    private javax.swing.JDesktopPane jDesktopPaneErnegia1;
    private javax.swing.JDesktopPane jDesktopPaneSalud;
    private javax.swing.JDesktopPane jDesktopPaneSeguridad;
    private javax.swing.JDesktopPane jDesktopPaneTransporte;
    private javax.swing.JLabel jLabelAgua;
    private javax.swing.JLabel jLabelAguaValor;
    private javax.swing.JLabel jLabelEnergiaValor;
    private javax.swing.JLabel jLabelEnergiaValor1;
    private javax.swing.JLabel jLabelErnegia;
    private javax.swing.JLabel jLabelErnegia1;
    private javax.swing.JLabel jLabelEventosValor;
    private javax.swing.JLabel jLabelFilaDespacho;
    private javax.swing.JLabel jLabelFoto;
    private javax.swing.JLabel jLabelRegistro;
    private javax.swing.JLabel jLabelSalud;
    private javax.swing.JLabel jLabelSaludValor;
    private javax.swing.JLabel jLabelSeguridad;
    private javax.swing.JLabel jLabelSeguridadValor;
    private javax.swing.JLabel jLabelSubtitulo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTransporte;
    private javax.swing.JLabel jLabelTransporteValor;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelContendorConsola;
    private javax.swing.JPanel jPanelContenedorDespachos;
    private javax.swing.JList<String> jPanelDespacho;
    private javax.swing.JList<String> jPanelHistorial;
    private javax.swing.JList<String> jPanelRegsitro;
    private javax.swing.JProgressBar jProgressAgua;
    private javax.swing.JProgressBar jProgressEnergia;
    private javax.swing.JProgressBar jProgressEnergia1;
    private javax.swing.JProgressBar jProgressSEventos;
    private javax.swing.JProgressBar jProgressSalud;
    private javax.swing.JProgressBar jProgressSeguridad1;
    private javax.swing.JProgressBar jProgressTransporte;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
