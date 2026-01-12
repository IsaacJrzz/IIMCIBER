/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrity;

import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.ArrayList;

/**
 *
 * @author MAXIM
 */
public class IntegrityMonitor {

    //Ruta de la carpeta que vamos a analizar
    private static final String folderPath = "";

    //ArrayList que almacená el estado anterior de los archivos
    private ArrayList<FileInfo> pState = new ArrayList<>();

    public void Monitoring() throws Exception {
        // Obtenemos el estado inicial de la carpeta
        pState = GetCState();

        // Bucle infinito para monitorizar constantemente
        while (true) {

            // Esperamos 5 segundos entre comprobaciones
            Thread.sleep(5000);

            // Leemos el estado actual de la carpeta
            ArrayList<FileInfo> CState = GetCState();

            // Comparamos el estado anterior con el actual
            CompareStates(pState, CState);

            // Actualizamos el estado anterior
            pState = CState;
        }

    }

    public ArrayList<FileInfo> GetCState() throws Exception {
        ArrayList<FileInfo> fileList = new ArrayList<>();
        File folder = new File(folderPath);

        // Recorremos todos los archivos de la carpeta
        for (File file : folder.listFiles()) {

            // Solo nos interesan archivos, no subcarpetas
            if (file.isFile()) {

                // Calculamos el hash del archivo
                String hash = CalculateHash(file);

                // Guardamos la información en la lista
                fileList.add(new FileInfo(file.getName(), hash));
            }
        }
        return fileList;
    }

    private String CalculateHash(File file) throws Exception {

        // Usamos SHA-256 para garantizar la integridad del archivo
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        // Leemos todo el contenido del archivo
        byte[] fileBytes = Files.readAllBytes(file.toPath());

        // Calculamos el hash
        byte[] hashBytes = digest.digest(fileBytes);

        // Convertimos el hash a formato hexadecimal
        StringBuilder hashString = new StringBuilder();
        for (byte b : hashBytes) {
            hashString.append(String.format("%02x", b));
        }

        return hashString.toString();
    }

    private void CompareStates(ArrayList<FileInfo> oldState,
            ArrayList<FileInfo> newState) throws Exception {

        // Comprobamos archivos modificados o eliminados
        for (FileInfo oldFile : oldState) {

            FileInfo newFile = findFile(oldFile.getFileName(), newState);

            // Si no existe en el estado nuevo, ha sido eliminado
            if (newFile == null) {
                LoggerSDAS.log("INTEGRITY",
                        "The file " + oldFile.getFileName() + " has been deleted.");

                // Si existe pero el hash es distinto, ha sido modificado
            } else if (!oldFile.getHash().equals(newFile.getHash())) {
                LoggerSDAS.log("INTEGRITY",
                        "The file " + oldFile.getFileName() + " has been modified.");
            }
        }

        // Comprobamos archivos nuevos
        for (FileInfo newFile : newState) {

            if (findFile(newFile.getFileName(), oldState) == null) {
                LoggerSDAS.log("INTEGRITY",
                        "The file " + newFile.getFileName() + " has been created.");
            }
        }
    }

    private FileInfo findFile(String fileName, ArrayList<FileInfo> fileList) {

        // Buscamos un archivo concreto dentro de la lista
        for (FileInfo file : fileList) {
            if (file.getFileName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }
}
