package ua.kiev.prog.entity.POJO;

import org.springframework.web.multipart.MultipartFile;

public class UploadModel {

    private String id;

    private MultipartFile file;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                "id='" + id + '\'' +
                ", file=" + file +
                '}';
    }
}