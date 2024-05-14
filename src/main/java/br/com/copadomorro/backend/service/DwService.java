package br.com.copadomorro.backend.service;

import br.com.copadomorro.backend.dto.DwDTO;
import br.com.copadomorro.backend.entity.Dw;
import br.com.copadomorro.backend.repository.DwRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DwService {

    @Autowired
    private DwRepository dwRepository;

    public List<DwDTO> saveDwList(List<Dw> dwList) {
        if (dwList.isEmpty()) {
            throw new IllegalArgumentException("O documento de dw está vazio.");
        }

        List<Dw> savedDwList = dwList.stream()
                .map(dwRepository::save) // Salva cada objeto DW e retorna o objeto salvo
                .collect(Collectors.toList());

        // Mapeia os DWs salvos para DTOs
        List<DwDTO> savedDwDtoList = savedDwList.stream()
                .map(DwDTO::new) // Supondo que existe um construtor de cópia ou método de conversão em DwDto
                .collect(Collectors.toList());

        return savedDwDtoList;
    }
}
