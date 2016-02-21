package backEndApi.Api.Mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MangoConnection {

    private static ServerAddress address = new ServerAddress(
            "ds037283.mongolab.com", 37283);
    private MongoClient mClient;
    private MongoDatabase mDB;

    public MangoConnection(String database, String user, String pass) {
        List<MongoCredential> creds = new ArrayList<MongoCredential>();
        MongoCredential cred = MongoCredential.createCredential(user, database,
                pass.toCharArray());
        creds.add(cred);
        mClient = new MongoClient(address, creds);
        mDB = mClient.getDatabase(database);
    }

    public MongoClient getClient() {
        return mClient;
    }

    public MongoDatabase getDB() {
        return mDB;
    }

    public void closeCon(){
        mClient.close();
    }
}
