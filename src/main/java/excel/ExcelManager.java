package excel;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

public interface ExcelManager<T> {

    Workbook createWorkBook(String sheetName, List<T> list);

    void writeWorkBookToFile(String path, Workbook workbook) throws IOException;
}
