/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrity;

/**
 *
 * @author MAXIM
 */
public class Main {

    public static void main(String[] args) {

        try {
            // Iniciamos el monitor de integridad
            IntegrityMonitor monitor = new IntegrityMonitor();
            monitor.Monitoring();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
