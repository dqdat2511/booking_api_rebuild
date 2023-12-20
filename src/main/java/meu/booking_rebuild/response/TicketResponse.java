package meu.booking_rebuild.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
//@ResponseBody
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketResponse {
    private String name_trip;
    private String name_customer;
    private String number_phone;
    private String code;
    private int number_tickets;
    private String address;
    private List<String> seat;
}
