package com.accountingservices.lisa.excel;

import com.accountingservices.lisa.models.UserRequest;
import lombok.SneakyThrows;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class ExcelService {
    @SneakyThrows
    public void addUserRequestExcel(UserRequest userRequest) {
        OPCPackage pkg = OPCPackage.open(new File("requests.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        CreationHelper creationHelper = wb.getCreationHelper();

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));

        Sheet sheet = wb.getSheetAt(0);

        for (Row row :
                sheet) {
            System.out.println(row.toString());
            if (row.getCell(0).getCellType() != CellType.BLANK) continue;
            else {
                row.createCell(0).setCellValue(userRequest.getName());
                row.createCell(1).setCellValue(userRequest.getEmail());
                row.createCell(2).setCellValue(userRequest.getPhoneNumber());
                row.createCell(3).setCellValue(userRequest.getOrganizationType());
                row.createCell(4).setCellValue(userRequest.getProblem());
                Cell cell = row.createCell(5);
                cell.setCellValue(Calendar.getInstance());
                cell.setCellStyle(cellStyle);
                break;
            }


        }
        OutputStream fos = new FileOutputStream("requests.xlsx", true);
        wb.write(fos);
        pkg.close();
    }
}
