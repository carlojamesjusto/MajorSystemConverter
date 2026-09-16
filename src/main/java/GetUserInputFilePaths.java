import java.util.Scanner;

public class GetUserInputFilePaths {


    public String getUserInputFilePath(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Put double slash instead of single slash ");
        System.out.print("Enter filePath: ");
        String filePath = sc.nextLine();

        return filePath;
    }
    public String getUserInputDestination(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file destination and file name: ");
        String destination = sc.nextLine();

        return destination;
    }

}
