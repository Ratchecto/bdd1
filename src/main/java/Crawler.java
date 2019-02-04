import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Crawler {
    public static void main(String[] args){

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB database = mongoClient.getDB("myMongoDb");
        for ( String a : mongoClient.getDatabaseNames()){
            System.out.println();
        }

    }
}
