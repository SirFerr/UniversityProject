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

    private XWPFDocument getDoc() {
        return doc;
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
    private void stringSearch(XWPFDocument doc){
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    for (String oldReplacement : template.getReplaceableNames()) {
                        if (text != null && text.contains(oldReplacement)) {
                            text = text.replace(oldReplacement, template.getKeysAndValues().get(oldReplacement));
                            r.setText(text, 0);
                        }

                    }

                }
            }
        }
    }
    private void tableSearch(XWPFDocument doc){
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            for (String oldReplacement : template.getReplaceableNames()) {
                                if (text != null && text.contains(oldReplacement)) {
                                    text = text.replace(oldReplacement, template.getKeysAndValues().get(oldReplacement));
                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void buildDoc(){
        XWPFDocument doc = getDoc();
        stringSearch(doc);
        tableSearch(doc);
    }
    public void saveDoc(){
        try (FileOutputStream out = new FileOutputStream(output)) {
            doc.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void clearDoc(String path){
        String pathToVoidFile = "src/main/resources/wordAndExcelTemplates/VoidDoc.docx";
        try (FileOutputStream out = new FileOutputStream(path);
             XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(pathToVoidFile)))) {
            doc.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void deleteWM(String path, String WM) {
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(path)));
             FileOutputStream out = new FileOutputStream(path)) {
            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                List<XWPFRun> runs = paragraph.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains(WM)) {
                                text = text.replace(WM, "");
                                r.setText(text, 0);
                        }
                    }
                }
            }
            doc.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
