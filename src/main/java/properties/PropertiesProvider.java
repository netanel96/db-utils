package properties;

import java.util.Properties;

public interface PropertiesProvider {

    Properties getProperties(String propsName);
}
