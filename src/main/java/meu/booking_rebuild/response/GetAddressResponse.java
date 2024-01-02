package meu.booking_rebuild.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class GetAddressResponse {
    private String address;
    private String phone;
    private String name;
}
