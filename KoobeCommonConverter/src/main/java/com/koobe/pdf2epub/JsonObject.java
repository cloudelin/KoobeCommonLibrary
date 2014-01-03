/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.pdf2epub;

import com.google.gson.Gson;
import java.util.Map;

/**
 *
 * @author arthur
 */
public abstract class JsonObject {
    public abstract Map toMap();
    public abstract void fromMap(Map map);

    public static <T> T fromJson(String json, Class<T> classOfT) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object t) {
        Gson gson = new Gson();
        return gson.toJson(t);
    }
}
