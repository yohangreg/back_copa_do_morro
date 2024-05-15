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

    public List<Dw> processExcel(MultipartFile file) throws IOException {
        List<Dw> dwList = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();

                // Pular o cabeçalho
                if (currentRow.getRowNum() == 0) {
                    continue;
                }

                Dw dw = new Dw();
                Iterator<Cell> cellIterator = currentRow.iterator();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();

                    switch (cellIndex) {
                        case 0:
                            dw.setNomeJogador(getStringValue(currentCell));
                            break;
                        case 1:
                            dw.setCpfJogador(getStringValue(currentCell));
                            break;
                        case 2:
                            dw.setDataNascimentoJogador(currentCell.getDateCellValue());
                            break;
                        case 3:
                            dw.setEstadoJogador(getStringValue(currentCell));
                            break;
                        case 4:
                            dw.setCidadeJogador(getStringValue(currentCell));
                            break;
                        case 5:
                            dw.setComunidadeJogador(getStringValue(currentCell));
                            break;
                        case 6:
                            dw.setNomeJogo(getStringValue(currentCell));
                            break;
                        case 7:
                            dw.setDataJogo(currentCell.getDateCellValue());
                            break;
                        case 8:
                            dw.setEstadoJogo(getStringValue(currentCell));
                            break;
                        case 9:
                            dw.setCidadeJogo(getStringValue(currentCell));
                            break;
                        case 10:
                            dw.setComunidadeJogo(getStringValue(currentCell));
                            break;
                        case 11:
                            dw.setNomeResponsavel(getStringValue(currentCell));
                            break;
                        case 12:
                            dw.setCpfResponsavel(getStringValue(currentCell));
                            break;
                        case 13:
                            dw.setEmailResponsavel(getStringValue(currentCell));
                            break;
                    }

                    cellIndex++;
                }

                if (verifyDwAttributes(dw)) {
                    dwList.add(dw);
                }
            }
        }

        return dwList;
    }

    private boolean verifyDwAttributes(Dw dw) {
        if (dw.getNomeJogador() == null ||
                dw.getCpfJogador() == null ||
                dw.getDataNascimentoJogador() == null ||
                dw.getEstadoJogador() == null ||
                dw.getCidadeJogador() == null ||
                dw.getNomeJogo() == null ||
                dw.getDataJogo() == null ||
                dw.getEstadoJogo() == null ||
                dw.getCidadeJogo() == null) {
            return false;
        }
        return true;
    }

    private String getStringValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return null;
        }
    }
}

