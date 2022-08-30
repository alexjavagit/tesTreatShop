package ua.kiev.prog.entity.POJO;

public class DeleteProductModel {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeleteProductModel{" +
                "id='" + id + '\'' +
                '}';
    }
}
