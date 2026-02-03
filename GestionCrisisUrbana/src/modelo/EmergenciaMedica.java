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
public class EmergenciaMedica {
    private static Random m = new Random();
    
    private static String[][] fallosM = {
        {"Saturación hospitalaria", "CRÍTICO", "Muerte de pacientes"},
        {"Falta de ambulancias", "MEDIO", "Mayor perdida de victimas"},
        {"Retrasos administrativos", "BAJO", "Molestias en la población"}      
    };
    
     // ===== cascadas =====
    private static String[][] cascadasM = {
          {"Atención retrasada", "CRÍTICO", "Mayor colapso hospitalario"},
          {"Camas no disponibles", "CRÍTICO", "Muerte de pacientes"},
          {"Dificultad de traslado", "MEDIO", "Ambulancias en mal estado"},
          {"Propagación de la crisis", "MEDIO", "Mayor cantidad de enfermos"}
    };
    
    public static Evento generarFalloM(int id){
        int i = m.nextInt(fallosM.length);
        return new Evento(
        id,
        fallosM[i][0],
        fallosM[i][1],
        fallosM[i][2]
        );
    }
    
    public static Evento generarCascadaM(int id, String nivelFallo){
         int i;
        while (true) {
            i = m.nextInt(cascadasM.length);
            String nivel = cascadasM[i][1];
            if (nivelFallo.equals("CRÍTICO")) break;
            if (nivelFallo.equals("MEDIO") && nivel.equals("MEDIO")) break;
            if (nivelFallo.equals("BAJO") && nivel.equals("BAJO")) break;
        }
        return new Evento(
            id,
            cascadasM[i][0],
            cascadasM[i][1],
            "Problema derivado de la emergencia médica"
        );
    }
}
