package properties;

import types.RowOfStrings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Manages properties files Provided from resources folder.
 */
public class ResourcesPropsProvider implements PropertiesProvider{
    private static final String FILE_TYPE = ".properties";

    public Properties getProperties(String name){
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propsPath = rootPath +name+ FILE_TYPE;
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(propsPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props;
    }
}
