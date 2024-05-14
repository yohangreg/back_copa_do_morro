package br.com.copadomorro.backend.utils;

import br.com.copadomorro.backend.entity.Dw;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelToModelUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public List<Dw> processExcel(MultipartFile file) {
        List<Dw> dwList = new ArrayList<>();
        String fileName = file.getOriginalFilename();
        if (!fileName.contains(".xlsx") || !fileName.contains(".xls"))
            throw new RuntimeException(" Formato de arquivo não suportado. Utilizar arquivos .xls ou .xlsx");

        try {
            Workbook workbook = null;
            if (file instanceof MultipartFile) {

                //Ler arquivo excel
                byte[] b = file.getBytes();
                InputStream inputStream = new ByteArrayInputStream(b);
                if (fileName.contains(".xlsx"))
                    workbook = new XSSFWorkbook(inputStream);
                else if (fileName.contains(".xls"))
                    workbook = new HSSFWorkbook(inputStream);
            }

            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                // Criar instância de DW
                Dw dw = new Dw();

                // Pular o cabeçalho, se necessário
                if (currentRow.getRowNum() == 0) {
                    continue;
                }

                // Verificar se a linha está vazia
                boolean isEmptyRow = true;
                Iterator<Cell> cellIterator = currentRow.iterator();
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() != CellType.BLANK) {
                        isEmptyRow = false;
                        break;
                    }
                }

                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    // Verificar o tipo de célula
                    if (currentCell.getCellType() == CellType.STRING) {
                        String cellValue = currentCell.getStringCellValue();
                        // Atribuir os valores dos campos do arquivo Excel aos campos correspondentes do objeto DW
                        switch (cellIndex) {
                            case 0:
                                dw.setNomeJogador(cellValue);
                                break;
                            case 1:
                                dw.setCpfJogador(cellValue);
                                break;
                            case 3:
                                dw.setEstadoJogador(cellValue);
                                break;
                            case 4:
                                dw.setCidadeJogador(cellValue);
                                break;
                            case 5:
                                dw.setComunidadeJogador(cellValue);
                                break;
                            case 6:
                                dw.setNomeJogo(cellValue);
                                break;
                            case 8:
                                dw.setEstadoJogo(cellValue);
                                break;
                            case 9:
                                dw.setCidadeJogo(cellValue);
                                break;
                            case 10:
                                dw.setComunidadeJogo(cellValue);
                                break;
                            case 11:
                                dw.setNomeResponsavel(cellValue);
                                break;
                            case 12:
                                dw.setCpfResponsavel(cellValue);
                                break;
                            case 13:
                                dw.setEmailResponsavel(cellValue);
                                break;
                        }
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        // Se for uma célula numérica, verificar se é uma data
                        if (DateUtil.isCellDateFormatted(currentCell)) {
                            Date cellValue = currentCell.getDateCellValue();
                            // Atribuir os valores dos campos do arquivo Excel aos campos correspondentes do objeto DW
                            switch (cellIndex) {
                                case 2:
                                    dw.setDataNascimentoJogador(cellValue);
                                    break;
                                case 7:
                                    dw.setDataJogo(cellValue);
                                    break;
                            }
                        }
                    }
                    cellIndex++;
                }

                // Adicionar objeto DW à lista
                dwList.add(dw);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Lida com a exceção
        }
        return dwList;
    }
}
