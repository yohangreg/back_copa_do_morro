package br.com.copadomorro.backend.controller;

import br.com.copadomorro.backend.dto.DocumentDTO;
import br.com.copadomorro.backend.dto.DwDTO;
import br.com.copadomorro.backend.dto.UserViewDTO;
import br.com.copadomorro.backend.entity.Document;
import br.com.copadomorro.backend.entity.Dw;
import br.com.copadomorro.backend.service.DocumentService;
import br.com.copadomorro.backend.service.DwService;
import br.com.copadomorro.backend.utils.ExcelToModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(value = "/api/document")
@CrossOrigin
public class DocumentController {

    @Autowired
    private DocumentService documentService;

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

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("userId") Long userId) {
        try {
            DocumentDTO documentDTO = new DocumentDTO();
            documentDTO.setTitle(title);
            documentDTO.setDescription(description);

            documentService.saveDocument(file, documentDTO, userId);
            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadDocument(@PathVariable Long id) {
        try {
            Resource resource = documentService.getDocument(id);
            if (resource == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }
}
