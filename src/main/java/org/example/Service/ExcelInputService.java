package org.example.Service;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Repository.ExcelInputRepoImpl;
import org.example.model.CodeModelClass;
import org.example.model.McqModelClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelInputService implements ExcelInputServiceImpl{
    @Autowired
    private ExcelInputRepoImpl excelInputRepo;
    @Override
    public List<McqModelClass> excelReader(List<MultipartFile> excel) {
        List<McqModelClass> excelList = new ArrayList<>();

        try {
            for (int j=0;j<excel.size();j++){
                XSSFWorkbook workbook = new XSSFWorkbook(excel.get(j).getInputStream());
                XSSFSheet sheet = workbook.getSheetAt(0);
                for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
                    int sr = (int) Float.parseFloat(sheet.getRow(i).getCell(0).toString());
                    McqModelClass mcqModelClass = new McqModelClass();
                    mcqModelClass.setSr(sr);
                    mcqModelClass.setLevel((int)Float.parseFloat(sheet.getRow(i).getCell(1).toString()));
                    mcqModelClass.setQue(sheet.getRow(i).getCell(2).toString());
                    mcqModelClass.setOpn1(sheet.getRow(i).getCell(3).toString());
                    mcqModelClass.setOpn2(sheet.getRow(i).getCell(4).toString());
                    mcqModelClass.setOpn3(sheet.getRow(i).getCell(5).toString());
                    mcqModelClass.setOpn4(sheet.getRow(i).getCell(6).toString());
                    mcqModelClass.setWopn(sheet.getRow(i).getCell(7).toString());
                    excelList.add(mcqModelClass);
                }
            }



//            for(int i=0; i<sheet.getPhysicalNumberOfRows();i++) {
//                XSSFRow row = sheet.getRow(i);
//
//                for(int j=0;j<row.getPhysicalNumberOfCells();j++) {
//
//                    System.out.print(row.getCell(j) +" ");
//                }
//                System.out.println("");
//            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return excelInputRepo.excelSaveInDb(excelList);
    }

    @Override
    public List<McqModelClass> saveInDb(List<McqModelClass> modelClassList) {
        return excelInputRepo.excelSaveInDb(modelClassList);
    }

    @Override
    public List<CodeModelClass> excelCodeReader(List<MultipartFile> excelcode) {
        List<CodeModelClass> excelCodeList = new ArrayList<>();

        try {
            for (int j=0;j<excelcode.size();j++){
                XSSFWorkbook workbook = new XSSFWorkbook(excelcode.get(j).getInputStream());
                XSSFSheet sheet = workbook.getSheetAt(0);
                for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
//                    int sr = (int) Float.parseFloat(sheet.getRow(i).getCell(0).toString());
                    CodeModelClass codeModelClass=new CodeModelClass();
//                    codeModelClass.setSr(sr);
                    codeModelClass.setLevel((int)Float.parseFloat(sheet.getRow(i).getCell(0).toString()));
                    codeModelClass.setCode(sheet.getRow(i).getCell(1).toString());
                    codeModelClass.setSinput(sheet.getRow(i).getCell(2).toString());
                    codeModelClass.setSoutput(sheet.getRow(i).getCell(3).toString());
                    codeModelClass.settCase1(sheet.getRow(i).getCell(4).toString());
                    codeModelClass.settCase2(sheet.getRow(i).getCell(5).toString());
                    codeModelClass.settCase3(sheet.getRow(i).getCell(6).toString());
                    codeModelClass.settCase4(sheet.getRow(i).getCell(7).toString());
                    excelCodeList.add(codeModelClass);
                }
            }



//            for(int i=0; i<sheet.getPhysicalNumberOfRows();i++) {
//                XSSFRow row = sheet.getRow(i);
//
//                for(int j=0;j<row.getPhysicalNumberOfCells();j++) {
//
//                    System.out.print(row.getCell(j) +" ");
//                }
//                System.out.println("");
//            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

//        return excelInputRepo.excelSaveInDb(excelList);
          return excelCodeList;
    }
}

