package db;

import com.google.inject.Inject;
import converters.Converter;
import excel.ExcelManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import properties.EnvProps;

import java.io.IOException;

public class MongoDbUtils implements DbUtils {
    protected static final Logger logger = (Logger) LogManager.getLogger();

    @Inject
    private DbInterface dbInterface;

    @Inject
    private ExcelManager excelManager;

    @Inject
    private Converter converter;

    @Inject
    private EnvProps envProps;

    @Override
    public void writeDataToExcel(String dataName, String excelSheetName) {
        try {
            excelManager.writeWorkBookToFile(envProps.props.getProperty(EnvProps.EXCEL_FILE_PATH),
                    excelManager.createWorkBook(excelSheetName, converter.convertList(dbInterface.getList(dataName))));
        } catch (IOException e) {
            logger.error("failed to write db data to workbook");
            e.printStackTrace();
        }
    }
}
