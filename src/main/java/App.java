import com.google.inject.Guice;
import com.google.inject.Injector;

public class App {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppModule());
        AppManager appManager = injector.getInstance(AppManager.class);
        appManager.run();
    }
}
