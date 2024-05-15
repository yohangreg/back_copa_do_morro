package br.com.copadomorro.backend.controller;

import br.com.copadomorro.backend.dto.DwDTO;
import br.com.copadomorro.backend.dto.UserViewDTO;
import br.com.copadomorro.backend.entity.Dw;
import br.com.copadomorro.backend.service.DwService;
import br.com.copadomorro.backend.utils.ExcelToModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/document")
@CrossOrigin
public class DocumentController {

    @Autowired
    private DwService dwService;

    @Autowired
    private ExcelToModelUtils excelToModelUtils;

    @PostMapping("/upload/dw")
    public ResponseEntity<?> uploadDw(@RequestParam("file") MultipartFile file) {
        try {
            List<Dw> dwList = excelToModelUtils.processExcel(file);
            List<DwDTO> dwSavedList = dwService.saveDwList(dwList);
            return ResponseEntity.status(HttpStatus.CREATED).body(dwSavedList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + " " + e.getCause());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage() + " " + e.getCause());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
