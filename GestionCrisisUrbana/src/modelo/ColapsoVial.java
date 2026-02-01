/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Random;

/**
 *
 * @author lagui
 */
public class ColapsoVial {
    private static Random c = new Random();
    
    // ===== FALLOS PRINCIPALES =====
    private static String[][] fallosC = {
        {"Colapso vial", "CRÍTICO", "Congestión masiva"},
        {"Semáforos Dañados", "MEDIO", "Cierre de vías"},
        {"Congestión leve", "BAJO", "Tráfico concurrente"}      
    };
    
    // ===== cascadas =====
    private static String[][] cascadasC = {
          {"Robo a mano armada", "CRÍTICO", "Gente en la calle"},
          {"Múltiples accidentes", "CRÍTICO", "Personas heridas"},
          {"Sistema de semáforos dañado", "MEDIO", "Inmobilidad de carros"},
          {"Malestar social", "MEDIO", "Accidentes concurrentes"}
    };
    
    public static Evento generarFalloC(int id){
        int i = c.nextInt(fallosC.length);
        return new Evento(
        id,
        fallosC[i][0],
        fallosC[i][1],
        fallosC[i][2]
        );
    }
    
    public static Evento generarCascadaP(int id, String nivelFallo){
        int i;
        while (true) {
            i = c.nextInt(cascadasC.length);
            String nivel = cascadasC[i][1];
            if (nivelFallo.equals("CRÍTICO")) break;
            if (nivelFallo.equals("MEDIO") && nivel.equals("MEDIO")) break;
            if (nivelFallo.equals("BAJO") && nivel.equals("BAJO")) break;
        }
        return new Evento(
            id,
            cascadasC[i][0],
            cascadasC[i][1],
            "Problema derivado del colapso vial"
        );
    }
}
