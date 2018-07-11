package ru.erus.nettr.data;

import java.io.*;
import java.util.HashMap;

public class DataBundle implements Serializable {

    HashMap<String, Object> map;

    public DataBundle() {
        map = new HashMap<>();
    }

    public DataBundle putInteger(String key, Integer value) {
        map.put(key, value);
        return this;
    }

    public DataBundle putString(String key, String value) {
        map.put(key, value);
        return this;
    }


    public Integer getInteger(String key, Integer defaultValue) {
        Object o = map.get(key);
        if (o != null && o instanceof Integer) {
            return (Integer) o;
        } else {
            return defaultValue;
        }
    }

    public String getString(String key, String defaultValue) {
        Object o = map.get(key);
        if (o != null && o instanceof String) {
            return (String) o;
        } else {
            return defaultValue;
        }
    }


    public void loadFromStream(InputStream inputStream) throws IOException, ClassNotFoundException {

        if (inputStream != null) {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            map = (HashMap<String, Object>) objectInputStream.readObject();
            objectInputStream.close();
        }

    }

    public void writeToStream(OutputStream outputStream) throws IOException {

        if (outputStream != null) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
        }

    }

}
