package db;

import com.google.inject.Inject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bson.Document;
import properties.EnvProps;

import java.util.ArrayList;
import java.util.List;

public class MongoDbInterface implements DbInterface<Document> {
    protected static final Logger logger = (Logger) LogManager.getLogger();
    private final MongoDatabase database;

    @Inject
    public MongoDbInterface(EnvProps envProps){
        String connectionString = envProps.props.getProperty(EnvProps.CONNECTION_STRING);
        String dataBaseName = envProps.props.getProperty(EnvProps.DATABASE_NAME);

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        logger.info("successfully connected to mongo db");
        database = mongoClient.getDatabase(dataBaseName);
        String logInfo = database.listCollectionNames().first()==null ? "database Named:"+dataBaseName+" is empty." :
                "successfully connected to database named: "+dataBaseName;
        logger.info(logInfo);
    }

    private List<Document> getDocsFromCollection(String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        List<Document> docs = new ArrayList<>();
        collection.find().iterator().forEachRemaining((doc) -> docs.add(doc));
        String logInfo = docs.isEmpty() ? "collection: "+collectionName+" is empty." :
                "successfully read collection named: "+collectionName;
        logger.info(logInfo);
        logger.debug("received from collection:"+collectionName+" docs:" + docs);
        return docs;
    }

    @Override
    public List<Document> getList(String name) {
        return getDocsFromCollection(name);
    }
}
