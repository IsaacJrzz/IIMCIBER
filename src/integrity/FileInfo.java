/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrity;

/**
 *
 * @author MAXIM
 */
public class FileInfo {

    //Variables
    private String fileName, hash;

    //Constructor
    public FileInfo(String fileName, String hash) {
        this.fileName = fileName;
        this.hash = hash;
    }

    //Getters
    public String getFileName() {
        return fileName;
    }

    public String getHash() {
        return hash;
    }

}
