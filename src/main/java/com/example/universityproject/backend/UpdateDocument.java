package com.example.universityproject.backend;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//class updates word file
//input - base word file
//output - file after replacement
//oldReplacement - variable with key-words(like ${name}, ${id} etc.)
//newReplacement - variable with words to replace
public class UpdateDocument {
    public void updateDocument(String input, String output, String oldReplacement ,String newReplacement) throws IOException {
        //text paragraphs parser
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(input)))) {
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        // if statement to find word to replace
                        if (text != null && text.contains(oldReplacement)) {
                            text = text.replace(oldReplacement, newReplacement);
                            r.setText(text, 0);
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
                                // if statement to find word to replace
                                if (text != null && text.contains(oldReplacement)) {
                                    text = text.replace(oldReplacement, newReplacement);
                                    r.setText(text,0);
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
