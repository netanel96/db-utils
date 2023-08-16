import com.google.inject.AbstractModule;
import converters.Converter;
import converters.MongoDocsToRowOfStringsConverter;
import db.DbInterface;
import db.DbUtils;
import db.MongoDbInterface;
import db.MongoDbUtils;
import excel.ExcelManager;
import excel.RowStringsExcelManager;
import properties.EnvProps;
import properties.PropertiesProvider;
import properties.ResourcesPropsProvider;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AppManager.class).asEagerSingleton();
        bind(EnvProps.class).asEagerSingleton();

        //interfaces
        bind(DbUtils.class).to(MongoDbUtils.class).asEagerSingleton();
        bind(DbInterface.class).to(MongoDbInterface.class).asEagerSingleton();
        bind(ExcelManager.class).to(RowStringsExcelManager.class).asEagerSingleton();
        bind(Converter.class).to(MongoDocsToRowOfStringsConverter.class).asEagerSingleton();
        bind(PropertiesProvider.class).to(ResourcesPropsProvider.class).asEagerSingleton();

    }
}
