package Utilities;

import com.google.gson.Gson;

import java.util.HashMap;

public class Response {
    HashMap<String, String> main;
    public Response(){
        main = new HashMap<>();
    }
    public Response add(String key, String value){
        main.put(key,value);
        return this;
    }

    public String json(){
        return new Gson().toJson(main);
    }
}
