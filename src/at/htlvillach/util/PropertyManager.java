package at.htlvillach.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private Properties props = new Properties();
    private String filename = "";
    private static PropertyManager instance;

    private PropertyManager(){

    }

    public static PropertyManager getInstance(){
        if(instance == null){
            instance = new PropertyManager();
        }
        return instance;
    }

    public void setFilename(String filename) {
        this.filename = filename;
        this.fillProperties();
    }

    private void fillProperties() {
        try(FileReader fileReader = new FileReader(this.filename)){
            this.props.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readProperty(String key, String defaultValue){
        return this.props.getProperty(key, defaultValue);
    }
}