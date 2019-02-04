import com.mongodb.*;

import java.util.Arrays;

public class Crawler {
    public static void main(String[] args){

        MongoCredential credential = MongoCredential.createCredential("user", "labdd", "password1".toCharArray());

        MongoClientURI uri = new MongoClientURI("mongodb://user:password1@ds247698.mlab.com:47698/labdd");
        MongoClient mongoClient = new MongoClient(uri);
        DB database = mongoClient.getDB("labdd");

       System.out.println(database);

    }
}
