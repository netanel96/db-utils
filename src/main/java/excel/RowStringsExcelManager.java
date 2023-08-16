package excel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import types.RowOfStrings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Excel manager that works with {@link RowOfStrings} as an input type.
 */
public class RowStringsExcelManager implements ExcelManager<RowOfStrings> {
    protected static final Logger logger = (Logger) LogManager.getLogger();

    private void createExcelRow(Sheet sheet, Integer numOfRow, RowOfStrings row) {
        Row execlRow = sheet.createRow(numOfRow);
        for (int columnIndex = 0; columnIndex < row.row().size(); columnIndex++) {
            Cell column = execlRow.createCell(columnIndex);
            column.setCellValue(row.row().get(columnIndex));
        }
    }

    private void setDefaultSheetProps(Sheet sheet) {
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
    }

    @Override
    public Workbook createWorkBook(String sheetName, List<RowOfStrings> listOfRows) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        setDefaultSheetProps(sheet);
        for (int rowIndex = 0; rowIndex < listOfRows.size(); rowIndex++) {
            createExcelRow(sheet, rowIndex, listOfRows.get(rowIndex));
        }
        logger.info("created sheet named: "+sheetName);
        return workbook;
    }

    @Override
    public void writeWorkBookToFile(String path, Workbook workbook) throws IOException {
        File currDir = new File(".");
        String absolutePath = currDir.getAbsolutePath();
        String defaultFilePath = absolutePath.substring(0, absolutePath.length() - 1) + "temp.xlsx";
        String excelFilePath= !path.equals("") ? path:defaultFilePath;
        logger.info("writing workbook to path: "+excelFilePath);
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        workbook.close();
    }
}
