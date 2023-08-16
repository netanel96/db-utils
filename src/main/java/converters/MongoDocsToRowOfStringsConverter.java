package converters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bson.Document;
import types.RowOfStrings;

import java.util.ArrayList;
import java.util.List;

public class MongoDocsToRowOfStringsConverter implements Converter<Document, RowOfStrings> {
    protected static final Logger logger = (Logger) LogManager.getLogger();

    @Override
    public List<RowOfStrings> convertList(List<Document> docs) {
        List<RowOfStrings> listOfRows = new ArrayList<>();
        docs.forEach((doc) -> {
            List<String> row = new ArrayList<>();
            doc.values().forEach((cell) -> row.add(cell.toString()));
            listOfRows.add(new RowOfStrings(row));
        });
        logger.info("converted docs to list of rows");
        return listOfRows;
    }
}
