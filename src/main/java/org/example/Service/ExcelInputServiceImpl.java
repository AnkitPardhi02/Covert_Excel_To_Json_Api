package org.example.Service;

import org.example.model.CodeModelClass;
import org.example.model.McqModelClass;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelInputServiceImpl {
    public List<McqModelClass> excelReader( List<MultipartFile> excel);
    public List<McqModelClass> saveInDb(List<McqModelClass> modelClassList);
    public List<CodeModelClass> excelCodeReader(List<MultipartFile> excelcode);
}
