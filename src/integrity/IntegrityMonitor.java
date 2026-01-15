package integrity;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.ArrayList;

public class IntegrityMonitor {

    // Ruta de la carpeta a monitorizar
    private static final String folderPath = "C:\\Users\\MAXIM\\Desktop\\PruebaSha";

    // Estado previo
    private ArrayList<FileInfo> pState = new ArrayList<>();

    public void Monitoring() throws Exception {

        // Estado inicial
        pState = GetCState();

        while (true) {

            Thread.sleep(5000);

            ArrayList<FileInfo> cState = GetCState();

            CompareStates(pState, cState);

            pState = cState;
        }
    }

    // Obtiene el estado completo de la carpeta (recursivo)
    public ArrayList<FileInfo> GetCState() throws Exception {
        ArrayList<FileInfo> fileList = new ArrayList<>();
        scanFolder(new File(folderPath), fileList);
        return fileList;
    }

    // Escaneo recursivo
    private void scanFolder(File folder, ArrayList<FileInfo> fileList) throws Exception {

        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {

            if (file.isFile()) {
                String hash = CalculateHash(file);
                fileList.add(new FileInfo(file.getAbsolutePath(), hash));
            } else if (file.isDirectory()) {
                scanFolder(file, fileList);
            }
        }
    }

    // SHA-256 por streaming (seguro)
    private String CalculateHash(File file) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (InputStream is = Files.newInputStream(file.toPath())) {

            byte[] buffer = new byte[8192];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }

        byte[] hashBytes = digest.digest();

        StringBuilder hashString = new StringBuilder();
        for (byte b : hashBytes) {
            hashString.append(String.format("%02x", b));
        }

        return hashString.toString();
    }

    // Comparación de estados
    private void CompareStates(ArrayList<FileInfo> oldState,
                               ArrayList<FileInfo> newState) throws Exception {

        // Archivos eliminados o modificados
        for (FileInfo oldFile : oldState) {

            FileInfo newFile = findFile(oldFile.getFilePath(), newState);

            if (newFile == null) {
                LoggerSDAS.log("INTEGRITY",
                        "The file " + oldFile.getFilePath() + " has been deleted.");

            } else if (!oldFile.getHash().equals(newFile.getHash())) {
                LoggerSDAS.log("INTEGRITY",
                        "The file " + oldFile.getFilePath() + " has been modified.");
            }
        }

        // Archivos nuevos
        for (FileInfo newFile : newState) {

            if (findFile(newFile.getFilePath(), oldState) == null) {
                LoggerSDAS.log("INTEGRITY",
                        "The file " + newFile.getFilePath() + " has been created.");
            }
        }
    }

    // Busca archivo por ruta completa
    private FileInfo findFile(String filePath, ArrayList<FileInfo> fileList) {

        for (FileInfo file : fileList) {
            if (file.getFilePath().equals(filePath)) {
                return file;
            }
        }
        return null;
    }
}
