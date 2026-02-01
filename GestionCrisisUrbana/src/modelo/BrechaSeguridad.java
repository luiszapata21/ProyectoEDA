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
public class BrechaSeguridad {
    private static Random s = new Random();

    // ===== FALLOS PRINCIPALES =====
    private static String[][] fallosP = {
        {"Disturbios masivos", "CRÍTICO", "Perdida de control de las personas"},
        {"Robo organizado", "MEDIO", "Miedo en la población"},
        {"Incidente aislado", "BAJO", "Ingresos delictivos de gente"}      
    };
    
    // ===== cascadas =====
    private static String[][] cascadasP = {
          {"Centros comerciales llenos", "CRÍTICO", "Aglomeración de personas"},
          {"Robo a mano armada", "CRÍTICO", "Estado peligroso en la ciudad"},
          {"Miedo Ciudadano", "MEDIO", "Ciudad inestable"},
          {"Negocios desprotegidos", "MEDIO", "Vacunadores"}
    };
    
    public static Evento generarFalloP(int id){
        int i = s.nextInt(fallosP.length);
        return new Evento(
        id,
        fallosP[i][0],
        fallosP[i][1],
        fallosP[i][2]
        );
    }
    
    public static Evento generarCascadaP(int id, String nivelFallo){
         int i;
        while (true) {
            i = s.nextInt(cascadasP.length);
            String nivel = cascadasP[i][1];
            if (nivelFallo.equals("CRÍTICO")) break;
            if (nivelFallo.equals("MEDIO") && nivel.equals("MEDIO")) break;
            if (nivelFallo.equals("BAJO") && nivel.equals("BAJO")) break;
        }
        return new Evento(
            id,
            cascadasP[i][0],
            cascadasP[i][1],
            "Problema derivado de la brecha de seguridad"
        );
    }
}
