package com.example.universityproject;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UpdateDocument {
    public void updateDocument(String input, String output, String oldReplacement ,String newReplacement) throws IOException {
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(Paths.get(input)))) {
            for (XWPFParagraph p : doc.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (text != null && text.contains(oldReplacement)) {
                            text = text.replace(oldReplacement, newReplacement);
                            r.setText(text, 0);
                        }
                    }
                }
            }
            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null && text.contains(oldReplacement)) {
                                    text = text.replace(oldReplacement, newReplacement);
                                    r.setText(text,0);
                                }
                            }
                        }
                    }
                }
            }
            try (FileOutputStream out = new FileOutputStream(output)) { // save the docs
                doc.write(out);
            }
        }
    }
}
