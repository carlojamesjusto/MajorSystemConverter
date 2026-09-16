import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;

//this class reads then writes the data from excel to a list of arrayLists. 1 row = 1 arrayList
public class Read {
    private String filePath;
    private ArrayList<ArrayList> rowsArray;


    Read(String filePath) throws IOException {
        this.filePath = filePath;
        rowsArray = new ArrayList<ArrayList>();
    }

    public void getData() throws IOException {
        FileInputStream input = new FileInputStream(this.filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(input);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(1).getLastCellNum();
        //System.out.println("num rows: " + rows);
        //System.out.println("num cols: " + cols);

        forLoop(sheet, rows, cols);

    }

    public void forLoop(XSSFSheet sheet, int rows, int cols) {
        for (int r = 0; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);

            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.getCell(c);

                if (cell.getCellType() == CellType.STRING) {
                    inputDataToArrayList(cell.getStringCellValue());
                }
            }
        }
    }

    public void inputDataToArrayList(String toAdd) {
        ArrayList<Object> tempArray = new ArrayList<>();
        tempArray.add(toAdd);
        getrowsArray().add(tempArray);

    }
    public void getMajorSystemNumbers(ArrayList rowsArray){
        ListIterator<ArrayList> iter = rowsArray.listIterator();

        while(iter.hasNext()){
            ArrayList<Object> tempArray = new ArrayList<>();
            tempArray = iter.next();

            ListIterator<Object> innerIter = tempArray.listIterator();
            while(innerIter.hasNext()){
                Object obj = innerIter.next();
                if(obj instanceof String){
                    MajorSystemConverter convert = new MajorSystemConverter((String) obj);
                    innerIter.add(convert.finalAns());
                    switchNameAndNumberColumn(tempArray);
                }
                else{
                    System.out.println("The name in Column 1 of excel is taken by Java as not a String.");
                }

            }
        }


    }
    public void switchNameAndNumberColumn(ArrayList swap){
        int length = swap.size();
        for(int i = 0; i < length; i++){
            Object temp = swap.get(i);
            swap.remove(i);
            swap.add(temp);
        }
    }
    public ArrayList getrowsArray() {
        return this.rowsArray;
    }
    public void printArrays() throws IOException {
        processFinalArrays();
        int length = getrowsArray().size();

        for(int i = 0; i<length; i++){
            System.out.println("Row " +i+1 +": ->" +getrowsArray().get(i));
        }
    }
    public void processFinalArrays() throws IOException{
        getData();
        getMajorSystemNumbers(getrowsArray());
    }
    public ArrayList getProcessedArrays() throws IOException{
        processFinalArrays();
        return getrowsArray();
    }

}

