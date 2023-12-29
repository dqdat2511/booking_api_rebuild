package meu.booking_rebuild.response;

import lombok.Data;

@Data
public class CheckValidTripResponse {
    private String message;
    private boolean status;
}
