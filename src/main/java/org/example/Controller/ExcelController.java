package org.example.Controller;

import java.util.List;
import org.example.Service.ExcelInputServiceImpl;
import org.example.model.CodeModelClass;
import org.example.model.McqModelClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ExcelController {
    @Autowired
    private ExcelInputServiceImpl excelInputService;

    @PostMapping("/InsertExcelSheet")
    @ResponseBody
    public List<McqModelClass> excelReader(@RequestParam("file") List<MultipartFile> excel) {
        String[] contentType=new String[excel.size()];
        boolean result=false;
        for (int i=0;i<excel.size();i++) {
            contentType[i] = excel.get(i).getContentType();
            if(contentType[i].equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
                result = true;
            }
            else {
                result = false;
            }

        }
        if (result){
            return excelInputService.excelReader(excel);
        }else {
            return null;
        }

    }
    @GetMapping("/AddMoreInDb")
    @ResponseBody
    public List<McqModelClass> saveInDb(@RequestBody List<McqModelClass> modelClassList){
       return excelInputService.saveInDb(modelClassList);

    }
    @PostMapping("/InsertCodeExcelSheet")
    @ResponseBody
    public List<CodeModelClass> excelCodeReader(@RequestParam("file") List<MultipartFile> excelcode){
        String[] contentType=new String[excelcode.size()];
        boolean result=false;
        for (int i=0;i<excelcode.size();i++) {
            contentType[i] = excelcode.get(i).getContentType();
            if(contentType[i].equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
                result = true;
            }
            else {
                result = false;
            }
        }
        if (result){
            return excelInputService.excelCodeReader(excelcode);
        }else {
            return null;
        }
    }
}