package com.example.universityproject.backend;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DocumentBuilder {
    private Template template = null;
    private String output = null;
    private String input = null;

    private XWPFDocument doc = null;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Template getTemplate() {
        return template;
    }
    private void setXWPFDocument(String input){
        try {
            doc =  new XWPFDocument(Files.newInputStream(Paths.get(input)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public DocumentBuilder(Template template, String output, String input) {
        this.template = template;
        this.output = output;
        this.input = input;
        setXWPFDocument(this.input);


    }
    public void buildDoc(){
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
    }
    public void saveDoc(){
        try (FileOutputStream out = new FileOutputStream(output)) {
            doc.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
