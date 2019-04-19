//package ProjektBus.Server;
//
//import com.mongodb.MongoClient;
////import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.io.IOException;
//
//@ActiveProfiles("test")
//@TestConfiguration
//public class ComponentTestConfig {
//
//    @Value("${spring.data.mongodb.host}")
//    private String MONGO_DB_URL;
//
//    @Value("${spring.data.mongodb.database}")
//    private String MONGO_DB_NAME;
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws IOException {
//        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
//        mongo.setBindIp("localhost");
//        MongoClient mongoClient = mongo.getObject();
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, "a");
//        return mongoTemplate;
//    }
//}
