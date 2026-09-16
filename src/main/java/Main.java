import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //Read readTest = new Read(".\\Test.xlsx");
        //readTest.printArrays();

        //Final Code:
        WriteArraysToExcel test = new WriteArraysToExcel();
        test.writeData();

        /*
        String word = "aBlackbeard";
        StringConvertLa test = new StringConvertLa(word);
        System.out.println(test.is1WordOnly());
        System.out.println(test.getFirstName());
        System.out.println(test.getLastName());

        MajorSystemConverter testLvl2 = new MajorSystemConverter(word);
        System.out.println(testLvl2.getFirstMajorNum());
        System.out.println(testLvl2.finalAns());
        */
    }
    /*
    public void testing(){
        String name1 = "       Urek EeeMazino   ";
        String name2 = "WhyxCalibur AeiouCalibur";


        MajorSystemConverter nameA = new MajorSystemConverter(name1);
        MajorSystemConverter nameB = new MajorSystemConverter(name2);

        System.out.println(nameA.finalAns());
        System.out.println(nameB.finalAns());
        System.out.println(nameB.getFirstMajorNum());
        System.out.println(nameB.getLastMajorNum());
    }

     */

}
