package ProjektBus.Server.mapper;

import ProjektBus.Server.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonMapper {

    public static String userMapper(User user){
        String jsonUser=null;
        ObjectMapper mapper=new ObjectMapper();
        try {
            jsonUser=mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonUser;
    }

    public static String listMapper(List<User> userList){
        String jsonUser=null;
        ObjectMapper mapper=new ObjectMapper();
        try {
            jsonUser=mapper.writeValueAsString(userList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonUser;
    }
}
