package br.com.copadomorro.backend.dto;

import br.com.copadomorro.backend.entity.Document;
import org.springframework.beans.BeanUtils;

public class DocumentDTO {

    private String title;
    private String description;

    public DocumentDTO(Document document) {
        BeanUtils.copyProperties(document, this);
    }

    public DocumentDTO(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}