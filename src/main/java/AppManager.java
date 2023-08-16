import com.google.inject.Inject;
import db.DbUtils;
import properties.EnvProps;

public class AppManager {
    @Inject
    private DbUtils dbUtils;

    @Inject
    private EnvProps envProps;

    public void run() {
        String dataName = envProps.props.getProperty(EnvProps.COLLECTION_NAME);
        dbUtils.writeDataToExcel(dataName, dataName);
    }
}
