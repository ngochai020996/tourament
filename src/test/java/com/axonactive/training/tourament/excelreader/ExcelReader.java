package com.axonactive.training.tourament.excelreader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	
	public static <T> Stream<T> load(Function<Row, T> mapper, boolean hasHeader, int tab, Class testClass, String folder, String file) {
        int skipCount = 0;
        if (hasHeader) {
            skipCount = 1;
        }
        String fileName = computeFileName(testClass, folder, file);
        try (InputStream inp = new BufferedInputStream(new FileInputStream(fileName));) {
            try (XSSFWorkbook wb = new XSSFWorkbook(inp)) {
                XSSFSheet sheet = wb.getSheetAt(tab);
                Stream<Row> stream = StreamSupport.stream(sheet.spliterator(), false);
                return stream.skip(skipCount).map(mapper);

            }
        } catch (IOException ex) {
            throw new IllegalStateException("Problems processing file: " + fileName, ex);
        }
    }
	
	static String computeFileName(Class className, String folder, String file) {
        return "src/test/resources/" + folder + "/" + className.getSimpleName().toLowerCase() + "/" + file + ".xlsx";
    }
}
