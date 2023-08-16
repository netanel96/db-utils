package properties;

import com.google.inject.Inject;
import db.DbInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import java.util.Properties;

/**
 * Manages env.properties data.
 */
public class EnvProps {
    protected static final Logger logger = (Logger) LogManager.getLogger();

    //properties file name
    private final String ENV_FILE_NAME = "env";

    //properties fields names
    public static final String COLLECTION_NAME = "COLLECTION_NAME";
    public static final String DATABASE_NAME = "DATABASE_NAME";
    public static final String CONNECTION_STRING = "CONNECTION_STRING";
    public static final String EXCEL_FILE_PATH = "EXCEL_FILE_PATH";

    public Properties props;

    @Inject
    public EnvProps(PropertiesProvider propertiesProvider){
        props = propertiesProvider.getProperties(ENV_FILE_NAME);
        logger.info("successfully loaded "+ENV_FILE_NAME+" properties");
        logger.debug("loaded props:"+props);
    }
}
