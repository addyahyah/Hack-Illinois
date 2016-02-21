package backEndApi.Api.Mongo;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static ServerAddress address = new ServerAddress(
            "ds011168.mongolab.com", 11168);
    private MongoClient mClient;
    private MongoDatabase mDB;

    public MongoConnection(String database, String user, String pass) {
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
