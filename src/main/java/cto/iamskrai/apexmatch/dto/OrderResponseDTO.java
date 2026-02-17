package cto.iamskrai.apexmatch.dto;

public class OrderResponseDTO {

    private int id;
    private String status;

    public OrderResponseDTO(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
