package integrity;

public class FileInfo {

    private String filePath;
    private String hash;

    public FileInfo(String filePath, String hash) {
        this.filePath = filePath;
        this.hash = hash;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getHash() {
        return hash;
    }
}
