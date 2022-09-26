package com.example.universityproject.backend;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

//class updates word file
//input - base word file
//output - file after replacement
//oldReplacement - variable with key-words(like ${name}, ${id} etc.)
//newReplacement - variable with words to replace
public class UpdateDocument {
    public UpdateDocument(String input, String output){

    }
    private XWPFDocument doc = null;
    public  void setDoc(String input){
        try{
            doc = new XWPFDocument(Files.newInputStream(Paths.get(input)));
            doc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveDoc(String output){
        try (FileOutputStream fileOutputStream = new FileOutputStream(output)){
            doc.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateDocument(String input, String output, ArrayList<Template> templates/*String oldReplacement ,String newReplacement*/) throws IOException {
        //text paragraphs parser
        // Получение документа
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(input)))) {

            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        for (String oldReplacement : template.getReplaceableNames()) {
                            // if statement to find word to replace
                            if (text != null && text.contains(oldReplacement)) {
                                text = text.replace(oldReplacement, template.getCorrectValue(oldReplacement));
                                r.setText(text, 0);
                            }

                        }

                    }
                }
            }
            //word tables parser
            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                for (String oldReplacement : template.getReplaceableNames()) {
                                    // if statement to find word to replace
                                    if (text != null && text.contains(oldReplacement)) {
                                        text = text.replace(oldReplacement, template.getCorrectValue(oldReplacement));
                                        r.setText(text, 0);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // save the docs
            try (FileOutputStream out = new FileOutputStream(output)) {
                doc.write(out);
            }
        }
    }

}
