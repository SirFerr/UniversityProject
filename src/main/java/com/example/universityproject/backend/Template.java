package com.example.universityproject.backend;

import java.util.ArrayList;
import java.util.Objects;

public class Template {
    private String studentFullName;
    private String studentShortName = null;
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
        setField("${studentFullName}",this.studentFullName);
        setField("${studentFN}",this.studentShortName );
    }
    public  ArrayList<String> getReplaceableNames(){
        return replaceableNames;
    }

   public ArrayList<String> getNewValues(){
        return valuesForKeys;
    }
    public String getCorrectValue(String replaceableField){
        for (String field : valuesForKeys){
            if(Objects.equals(field, replaceableField))
                return field;
        }
        return null;
    }

   public void setField(String field, String value){
        for (String f : replaceableNames){
            if(Objects.equals(f, field)){
                valuesForKeys.add(value);
                break;
            }

        }

    }
}
