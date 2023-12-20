package meu.booking_rebuild.service;

import com.spire.pdf.FileFormat;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import meu.booking_rebuild.response.TicketResponse;

@Service
public class ReportService {
    public void generateExcel(HttpServletResponse httpResponse, List<TicketResponse> responses) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(responses.get(0).getName_trip());

        // Set default cell style with borders
        CellStyle borderedCellStyle = workbook.createCellStyle();
        borderedCellStyle.setBorderTop(BorderStyle.THIN);
        borderedCellStyle.setBorderBottom(BorderStyle.THIN);
        borderedCellStyle.setBorderLeft(BorderStyle.THIN);
        borderedCellStyle.setBorderRight(BorderStyle.THIN);
        borderedCellStyle.setAlignment(HorizontalAlignment.CENTER);
        borderedCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font bodyFont = workbook.createFont();
        bodyFont.setFontName("Times New Roman");
        bodyFont.setFontHeightInPoints((short) 12);
        borderedCellStyle.setFont(bodyFont);

        // Create a CellStyle for bold and larger font
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontName("Times New Roman");
        headerFont.setFontHeightInPoints((short) 14);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);


        // Apply the bordered, bold font, and center alignment cell style to header row
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());


        // Loop through data to determine column widths and set bordered cell style
        for (int i = 0; i < responses.size(); i++) {
            TicketResponse response = responses.get(i);
            HSSFRow dataRow = sheet.createRow(i + 1);

            dataRow.createCell(0).setCellValue(i + 1);
            setCellValueAndAdjustWidth(dataRow.createCell(0), String.valueOf(i+1) , borderedCellStyle);
            setCellValueAndAdjustWidth(dataRow.createCell(1), response.getName_customer() , borderedCellStyle);
            setCellValueAndAdjustWidth(dataRow.createCell(2), response.getNumber_phone() , borderedCellStyle);
            setCellValueAndAdjustWidth(dataRow.createCell(3), response.getCode() , borderedCellStyle);
            setCellValueAndAdjustWidth(dataRow.createCell(4),    String.join(", ", response.getSeat()) , borderedCellStyle);
            setCellValueAndAdjustWidth(dataRow.createCell(5),    String.join(", ", response.getAddress()) , borderedCellStyle);
            setCellValueAndAdjustWidth(dataRow.createCell(6),  " " , borderedCellStyle);

        }

        HSSFRow headerRow = sheet.createRow(0);
        setCellValueAndAdjustWidth(headerRow.createCell(0), "STT" , borderedCellStyle);
        setCellValueAndAdjustWidth(headerRow.createCell(1), "Tên khách hàng" , borderedCellStyle);
        setCellValueAndAdjustWidth(headerRow.createCell(2), "Số điện thoại" , borderedCellStyle);
        setCellValueAndAdjustWidth(headerRow.createCell(3), "Mã vé đối soát" , borderedCellStyle);
        setCellValueAndAdjustWidth(headerRow.createCell(4), "Vị trí ghế  " , borderedCellStyle);
        setCellValueAndAdjustWidth(headerRow.createCell(5), "Địa chỉ rước " , borderedCellStyle);
        setCellValueAndAdjustWidth(headerRow.createCell(6), "Đã rước" , borderedCellStyle);
        for (int i = 0; i < headerRow.getPhysicalNumberOfCells(); i++) {
            headerRow.getCell(i).setCellStyle(headerCellStyle);
            sheet.setColumnWidth(i, 5500); // Set a specific width for columns (adjust as needed)
        }
        httpResponse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=danh_sach_hanh_khach.xls";
        httpResponse.setHeader(headerKey, headerValue);
        ServletOutputStream ops = httpResponse.getOutputStream();
//        convertExcelToPDF(workbook, ops);
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    private void setCellValueAndAdjustWidth(HSSFCell cell, String value, CellStyle cellStyle) {
        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);

        int length = value.length() * 256; // Approximate pixel width of the text in the cell
        int currentWidth = cell.getSheet().getColumnWidth(cell.getColumnIndex());
        if (length > currentWidth) {
            cell.getSheet().setColumnWidth(cell.getColumnIndex(), length);
        }
    }
    private void convertExcelToPDF(HSSFWorkbook workbook, ServletOutputStream outputStream) throws IOException {
        // Load the workbook from Apache POI into Spire.XLS
        com.spire.xls.Workbook spireWorkbook = new com.spire.xls.Workbook();
        spireWorkbook.loadFromStream(new ByteArrayInputStream(getBytesFromWorkbook(workbook)));

        // Get the first worksheet
        com.spire.xls.Worksheet sheet = spireWorkbook.getWorksheets().get(0);

        // Set the page orientation to landscape
        sheet.getPageSetup().setOrientation(com.spire.xls.PageOrientationType.Landscape);

        // Save the workbook as a PDF to the response output stream
        spireWorkbook.saveToStream(outputStream, com.spire.xls.FileFormat.PDF);
    }



    private static byte[] getBytesFromWorkbook(HSSFWorkbook workbook) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        return bos.toByteArray();
    }
}
