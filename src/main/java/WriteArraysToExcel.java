import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class WriteArraysToExcel {

    public void writeData() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Mnemonics");

        GetUserInputFilePaths filePath = new GetUserInputFilePaths();
        Read read = new Read(filePath.getUserInputFilePath());
        ArrayList<ArrayList> outerArray = read.getProcessedArrays();
        ListIterator<ArrayList> outerArrayIterator = outerArray.listIterator();
        ArrayList<Object> innerArray;

        //Object types in order: ArrayList -> ArrayList -> [Integer -> String]
        int rows = outerArray.size();

        for(int r = 0; r < rows; r++){
                innerArray = outerArrayIterator.next();
                XSSFRow row = sheet.createRow(r);
                //System.out.println("Iterations: " +r);
                //System.out.println(innerArray.get(0));
                //System.out.println(innerArray.get(1));
                XSSFCell cellNum = row.createCell(0);
                cellNum.setCellValue((Integer) innerArray.get(0));
                XSSFCell cellString = row.createCell(1);
                cellString.setCellValue((String) innerArray.get(1));

        }

        //create the excel file

        //String destination = ".\\ProcessedMnemonics.xlsx";
        FileOutputStream outstream = new FileOutputStream(filePath.getUserInputDestination());
        workbook.write(outstream);

        outstream.close();

        System.out.println("ProcessedMnemonics written successfully...");
    }
}

