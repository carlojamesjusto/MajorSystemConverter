import java.util.ArrayList;
import java.util.Locale;


public class StringConvertLa {
    protected String name;
    StringConvertLa(String name){
        String name2 = removeBirthDate(name);
        String name1 = name2.trim();
        this.name = name1.toLowerCase();
    }

    public static String removeBirthDate(String name){
        while(name.contains("(") || (name.contains(")"))){
            int foo = name.lastIndexOf('(') -1;
            name = name.substring(0,foo);
        }
        //System.out.println('#' +name +'#');
        name = removeDash(name);
        return name;
    }
    public static String removeDash(String name){
        name = name.replaceAll("-", " ");
        return name;
    }

    //methods
    public ArrayList getFirstName() {
        ArrayList<Character> arrayFirstName = new ArrayList<Character>();
        if(is1WordOnly()){
            for(int i = 0; i<getName().length(); i++){
                arrayFirstName.add(getName().charAt(i));
            }
        }
        else{
            for (int i = 0; i < getSeparator(); i++) {
                arrayFirstName.add(getName().charAt(i));
            }
        }

        return arrayFirstName;
    }

    public ArrayList getLastName() {
        ArrayList<Character> arrayLastName = new ArrayList<Character>();

        //return null if it's 1 word only
        if(is1WordOnly()){
            return null;
        }

        for (int i = getSeparator() + 1; i < getName().length(); i++) {
            arrayLastName.add(getName().charAt(i));
        }
        return arrayLastName;
    }

    protected int getSeparator() {
        int separator = getName().lastIndexOf(' ');
        return separator;
    }

    public boolean is1WordOnly(){
        if(getName().indexOf(' ') == -1){
            return true;
        }

        return false;
    }

    public String getName(){
        return this.name;
    }

}



