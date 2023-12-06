package meu.booking_rebuild.service;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

@Service
public class CodeService {
    public static String convertDateTimeToCode(Date date) {
        // Định dạng cho thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss");

        // Chuyển định dạng thời gian thành chuỗi
        String formattedDateTime = dateFormat.format(date);

        return formattedDateTime;
    }
}
