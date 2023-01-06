package org.example.Repository;

import org.example.model.McqModelClass;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelInputRepoImpl {
    public List<McqModelClass>  excelSaveInDb(List<McqModelClass> modelClassList);
}
