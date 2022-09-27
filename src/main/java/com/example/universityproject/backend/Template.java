package com.example.universityproject.backend;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Template {
    private String studentFullName;
    private String studentShortName;
    private Map<String,String> keysAndValues;
    public void setStudentFullName(String studentFullName){
        this.studentFullName = studentFullName;
        studentShortName = StudentsFIOConverter.cutStud(this.studentFullName);
    }
    private final ArrayList<String> replaceableNames;
    private final ArrayList<String> valuesForKeys = new ArrayList<>();
    public Template(ArrayList<String> replaceableNames, String studentFullName){
        this.replaceableNames = replaceableNames;
        this.studentFullName = studentFullName;
        this.studentShortName = StudentsFIOConverter.cutStud(this.studentFullName);
        setField("studentFullName",this.studentFullName);
        setField("studentFN",this.studentShortName );

    }
    public  ArrayList<String> getReplaceableNames(){
        return replaceableNames;
    }

   public ArrayList<String> getNewValues(){
        return valuesForKeys;
    }
   public void setField(String field, String value){
        for (String f : replaceableNames){
            if(Objects.equals(f, field)){
                valuesForKeys.add(value);
                break;
            }
        }

    }
    public void createMap(){
        keysAndValues = IntStream.range(0,replaceableNames.size()).boxed().collect(Collectors.toMap(replaceableNames::get,valuesForKeys::get));
    }

    public Map<String, String> getKeysAndValues() {
        return keysAndValues;
    }
}
