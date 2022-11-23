package com.accountingservices.lisa.excel;

import com.accountingservices.lisa.models.UserRequest;
import com.accountingservices.lisa.repository.OrganizationTypeRepository;
import com.accountingservices.lisa.repository.UserRequestRepository;
import lombok.SneakyThrows;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;


@Service
public class ExcelService {
    public ExcelService() {
    }

    private static List<UserRequest> allRequests;

    public void setAllRequests(List<UserRequest> allRequests) {
        ExcelService.allRequests = allRequests;
    }

    @SneakyThrows
    public void saveUserRequestsToExcel() {


        FileInputStream fis = new FileInputStream(new File("requests.xlsx"));       //беру файл с незаполненной табличкой
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        CreationHelper creationHelper = wb.getCreationHelper();

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));// по другому, вроде,
        // нормально дату не сохранить в экселе

        Sheet sheet = wb.getSheetAt(0);

        for (UserRequest userRequest : allRequests) {
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
                    row.createCell(5).setCellValue(userRequest.getPlacedAt());
                    Cell cell = row.createCell(5);
                    cell.setCellValue(userRequest.getPlacedAt());
                    cell.setCellStyle(cellStyle);
                    break;
                }
            }
        }

        OutputStream fos = new FileOutputStream("requestsFinal.xlsx"); // сохраняю в новый эксель файл,
        wb.write(fos);                                                                  // который в будущем заменится
        fis.close();                                                                     // на свежий и полностью перепишется
    }

    @SneakyThrows
    public void deleteInfoFromFinalFile(){                                      //удаляю файл какой к верху
        OutputStream fos = new FileOutputStream("requestsFinal.xlsx");
    }


//    @SneakyThrows
//    private void addUserRequestExcel(UserRequest userRequest) {
//        OPCPackage pkg = OPCPackage.open(new File("requests.xlsx"));
//        XSSFWorkbook wb = new XSSFWorkbook(pkg);
//        CreationHelper creationHelper = wb.getCreationHelper();
//
//        CellStyle cellStyle = wb.createCellStyle();
//        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("m/d/yy h:mm"));
//
//        Sheet sheet = wb.getSheetAt(0);
//
//        for (Row row :
//                sheet) {
//            System.out.println(row.toString());
//            if (row.getCell(0).getCellType() != CellType.BLANK) continue;
//            else {
//                row.createCell(0).setCellValue(userRequest.getName());
//                row.createCell(1).setCellValue(userRequest.getEmail());
//                row.createCell(2).setCellValue(userRequest.getPhoneNumber());
//                row.createCell(3).setCellValue(userRequest.getOrganizationType());
//                row.createCell(4).setCellValue(userRequest.getProblem());
//                row.createCell(5).setCellValue(userRequest.getPlacedAt());
//
//                Cell cell = row.createCell(5);
//                cell.setCellValue(userRequest.getPlacedAt());
//                cell.setCellStyle(cellStyle);
//
////                Cell cell = row.createCell(5);
////                cell.setCellValue(Calendar.getInstance());
////                cell.setCellStyle(cellStyle);
//                break;
//            }
//
//
//        }
//        OutputStream fos = new FileOutputStream("requests.xlsx", true);
//        wb.write(fos);
//        pkg.close();
//    }
//
//    public void saveUserRequestsToExcel() {
//        for (UserRequest request : (List<UserRequest>) userRequestRepository.findAll()) {
//            addUserRequestExcel(request);
//        }
//    }
}
