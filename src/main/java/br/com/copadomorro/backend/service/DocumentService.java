package br.com.copadomorro.backend.service;

import br.com.copadomorro.backend.dto.DocumentDTO;
import br.com.copadomorro.backend.entity.Document;
import br.com.copadomorro.backend.entity.User;
import br.com.copadomorro.backend.repository.DocumentRepository;
import br.com.copadomorro.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Service
public class DocumentService {

    private final Path rootLocation = Paths.get("C:/copadomorro/uploaded-files");

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserRepository userRepository;

    public Document saveDocument(MultipartFile file, DocumentDTO documentDTO, Long userId) throws IOException {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            String filename = file.getOriginalFilename();
            Path filePath = rootLocation.resolve(filename);

            Files.copy(file.getInputStream(), filePath);

            Document document = new Document();
            document.setTitle(documentDTO.getTitle());
            document.setDescription(documentDTO.getDescription());
            document.setPath(filePath.toString());
            document.setType(file.getContentType());
            document.setDate(new Date());

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            document.setUser(user);

            return documentRepository.save(document);
        } catch (IOException e) {
            throw new IOException("Failed to save document", e);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to find user", e);
        }
    }

    public Resource getDocument(Long id) throws IOException {
        try {
            Document document = documentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Document not found"));
            if (document == null) {
                return null;
            }

            Path filePath = Paths.get(document.getPath());
            byte[] fileBytes = Files.readAllBytes(filePath);
            ByteArrayResource resource = new ByteArrayResource(fileBytes);

            return resource;
        } catch (IOException e) {
            throw new IOException("Failed to download document", e);
        }
    }
}
