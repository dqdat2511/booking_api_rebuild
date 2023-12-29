package meu.booking_rebuild.response;

import lombok.Data;

import java.util.UUID;

@Data
public class PassangerTripResponse {
    private String message;
    private UUID id;
    private boolean status;
    public PassangerTripResponse() {
    }
    public PassangerTripResponse success(String message, UUID id) {
        this.message = message;
        this.id = id;
        this.status = true;
        return new PassangerTripResponse(this.message, this.id, this.status);
    }
    public PassangerTripResponse(String message, UUID id, boolean status) {
        this.message = message;
        this.id = id;
        this.status = status;
    }
    public PassangerTripResponse fail(String message) {
        this.message = message;
        this.id = null;
        this.status = false;
        return new PassangerTripResponse(this.message, this.id, this.status);
    }


}
