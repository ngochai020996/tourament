package com.axonactive.training.excel;

/*-
 * #%L
 * sheetfit
 * %%
 * Copyright (C) 2016 Adam Bien
 * %%
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author airhacks.com
 */
public class ExcelReader {

    /**
     * Loads a .xlsx file with the specified name from the
     * src/test/resources/[lower-test-class-name]/[file].xls and converts a Row
     * into a POJO using the mapper function.
     *
     * @param <T> the type of the converted row
     * @param mapper a function which maps a Row into a Pojo
     * @param hasHeader true - the header of the excel sheet is going to be
     * ignored.
     * @param tab the number of the tab in the excel sheet
     * @param testClass test class as naming convention for the test
     * @param folder the name of the folder comprising sheets from a single test
     * group e.g. SF203
     * @param file a name of the file without the .xlsx extension
     * @return a Stream of mapped POJOs.
     */
    public static <T> Stream<T> load(Function<Row, T> mapper, boolean hasHeader, int tab, String folder, String file) {
        int skipCount = 0;
        if (hasHeader) {
            skipCount = 1;
        }
        String fileName = computeFileName(folder, file);
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
    
    public static <T> String loadSheetName(int tab, String folder, String file) {
        String fileName = computeFileName(folder, file);
        try (InputStream inp = new BufferedInputStream(new FileInputStream(fileName));) {
            try (XSSFWorkbook wb = new XSSFWorkbook(inp)) {
                XSSFSheet sheet = wb.getSheetAt(tab);
                return sheet.getSheetName();
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Problems processing file: " + fileName, ex);
        }
    }


    public static <T> Stream<T> load(Function<Row, T> mapper, String folder, String file) {
        return load(mapper, true, 0, folder, file);
    }

    /**
     * Computes the location of the excel file
     *
     * @param clazz the lower name of the class is used as folder name
     * @param folder top level folder intended to group use cases
     * @param file the name of the excel sheet without the suffix
     * @return returns the relative name of the file
     */
    static String computeFileName(String folder, String file) {
        return folder + "/" + file + ".xlsx";
    }

    /**
     * Assumes the Cell content is numeric and converts that into a Java double
     *
     * @param cell HSF specific datacontainer
     * @return Content converted into double or 0 if blank
     */
    public static double asDouble(Cell cell) {
        int type = cell.getCellType();
        switch (type) {
            case Cell.CELL_TYPE_NUMERIC:
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_BLANK:
                return 0;
            case Cell.CELL_TYPE_FORMULA:
                return cell.getNumericCellValue();
            default:
                throw new IllegalArgumentException("Unknown this cell type: " + cell.getCellType());
        }
    }

    /**
     * Assumes the Cell content is text and converts it into a java.lang.String
     *
     * @param cell HSF specific datacontainer
     * @return Content converted into String or "" if blank
     */
    public static String asString(Cell cell) {
        int type = cell.getCellType();
        switch (type) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BLANK:
                return "";
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (IllegalStateException ise) {
                    return String.valueOf(Math.round(cell.getNumericCellValue()));
                }
            default:
                throw new IllegalArgumentException("Unknown cell type: " + cell.getCellType());
        }
    }

    /**
     * Assumes the Cell content is numeric and converts it into a java.lang.Long
     *
     * @param cell HSF specific datacontainer
     * @return Content converted into Long or 0 if blank
     */
    public static long asLong(Cell cell) {
        int type = cell.getCellType();
        switch (type) {
            case Cell.CELL_TYPE_NUMERIC:
                return Math.round(cell.getNumericCellValue());
            case Cell.CELL_TYPE_BLANK:
                return 0;
            case Cell.CELL_TYPE_FORMULA:
                return Math.round(cell.getNumericCellValue());
            default:
                throw new IllegalArgumentException("Unknown cell type: " + cell.getCellType());
        }
    }

}
