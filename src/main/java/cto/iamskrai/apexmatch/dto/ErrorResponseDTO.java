package cto.iamskrai.apexmatch.dto;

import java.util.List;

public class ErrorResponseDTO {

    private String error;
    private List<String> details;

    public ErrorResponseDTO(String error, List<String> details) {
        this.error = error;
        this.details = details;
    }

    public String getError() {
        return error;
    }

    public List<String> getDetails() {
        return details;
    }

}
