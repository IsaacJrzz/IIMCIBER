/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrity;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

/**
 *
 * @author MAXIM
 */
public class LoggerSDAS {

    private static final String logFile = "";

    public static void log(String module, String message) throws IOException {

        // Abrimos el archivo de log en modo añadir
        FileWriter writer = new FileWriter(logFile, true);

        // Obtenemos la hora actual
        String time = LocalTime.now().toString().substring(0, 8);

        // Escribimos el mensaje con formato unificado
        writer.write("[" + time + "] [" + module + "] " + message + "\n");

        writer.close();
    }
}
