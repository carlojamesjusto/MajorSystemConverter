import java.util.ArrayList;
import java.util.Objects;

public class MajorSystemConverter extends StringConvertLa {

    MajorSystemConverter(String name){
        super(name);
    }
    private int iterationTracker;

    public int getFirstMajorNum() {
        ArrayList firstName = super.getFirstName();
        for (int i = 0; i < firstName.size(); i++) {
            char charot = (char) firstName.get(i);
            int numConverted = numNotComplicated(charot);
            setIterationTracker(i);

            if( numConverted != -1){
                return numConverted;
            }
            else if(charot == 'c'){
                return numC(charot, firstName);
            }
            else if(charot == 'g'){
                return numG(charot, firstName);
            }
            else if((charot == 'z') || (charot == 's')){
                return numSorZ(charot, firstName);
            }
            else if(charot == 'x') {
                return numX(charot, firstName);
            }
        }

        return -1;
    }
    //Get the second number if it's a word,applicable for first and last name in separate process

    public int secondNumber(){
        ArrayList firstName = super.getFirstName();

        if(getIterationTracker() == firstName.size() -1){
            return -1;
        }

        for (int i = getIterationTracker()+1; i < firstName.size(); i++) {
            char charot = (char) firstName.get(i);
            int numConverted = numNotComplicated(charot);

            if( numConverted != -1){
                return numConverted;
            }
            else if(charot == 'c'){
                return numC(charot, firstName);
            }
            else if(charot == 'g'){
                return numG(charot, firstName);
            }
            else if((charot == 'z') || (charot == 's')){
                return numSorZ(charot, firstName);
            }
            else if(charot == 'x') {
                return numX(charot, firstName);
            }
        }

        return -1;
    }
    public void setIterationTracker(int x){
        this.iterationTracker = x;
    }
    public int getIterationTracker(){
        return this.iterationTracker;
    }

    public int getLastMajorNum() {
        ArrayList lastName = super.getLastName();
        if(Objects.isNull(lastName)){
            return -99;
        }

        for (int i = 0; i < lastName.size(); i++) {
            char charot = (char) lastName.get(i);
            int numConverted = numNotComplicated(charot);
            setIterationTracker(i);

            if( numConverted != -1){
                return numConverted;
            }

            else if(charot == 'c'){
                return numC(charot, lastName);
            }
            else if(charot == 'g'){
                return numG(charot, lastName);
            }
            else if((charot == 'z') || (charot == 's')){
                return numSorZ(charot, lastName);
            }
            else if(charot == 'x') {
                return numX(charot, lastName);
            }
        }

        return -1;
    }

    public int numC(char x, ArrayList arrayName){
        int xNum = arrayName.indexOf(x);
        if(xNum != arrayName.size() - 1){
            char nextChar = (char) arrayName.get(xNum + 1);

            if((nextChar == 'e') || (nextChar == 'i') || (nextChar == 'y')){
                return 130;//soft c
            }

            else if(nextChar == 'k'){
                return 990;//ck
            }
            //ch
            else if(nextChar == 'h' ){
                if(arrayName.lastIndexOf(nextChar) == arrayName.size() -1){
                    return 280;
                }
                else{
                    char secondNextChar = (char) arrayName.get(xNum +2);
                    if((secondNextChar == 'e') || (secondNextChar == 'i') || (secondNextChar == 'y')){
                        return 180;//soft ch
                    }
                }
                return 280;//hard ch
            }
        }

        //assume it's hard c if any of the above conditions is not true
        return 230;//hard c
    }

    public int numSorZ(char x, ArrayList arrayName){
        int xNum = arrayName.indexOf(x);
        if(xNum != arrayName.size() - 1){
            char nextChar = (char) arrayName.get(xNum + 1);
            if(nextChar == 'h'){
                return 6;//if zh or sh
            }
        }
        return 0;
    }

    public int numG(char x, ArrayList arrayName){
        int xNum = arrayName.indexOf(x);
        if(xNum != arrayName.size() - 1) {
            char nextChar = (char) arrayName.get(xNum +1);

            if((nextChar == 'e') || (nextChar == 'i') || (nextChar == 'y')){
                return 170;//soft g
            }
        }
        return 270;//hard g
    }

    public int numX(char x, ArrayList arrayName) {
        int xNum = arrayName.indexOf(x);

        if(xNum != arrayName.size() - 1) {
            char nextChar = (char) arrayName.get(xNum + 1);
            if (xNum == 0) {
                return 250;//hard x
            } else {
                if (arrayName.indexOf(xNum - 1) == 'e') {
                    //check if 'ex' is followed by a vowel
                    if ((nextChar == 'a') || (nextChar == 'e') || (nextChar == 'i')
                            || (nextChar == 'o') || (nextChar == 'u')) {
                        return 150;//this is a soft x
                    }
                }
            }
        }
        return 250;
    }

    private int numNotComplicated(char x) {
        x = Character.toLowerCase(x);

        if ((x == 't') || (x == 'd')) {
            return 1;
        } else if ((x == 'n')) {
            return 2;
        } else if (x == 'm') {
            return 3;
        } else if (x == 'r') {
            return 4;
        } else if (x == 'l') {
            return 5;
        } else if (x == 'j') {
            return 6;
        } else if ((x == 'k') || (x == 'q')) {
            return 7;
        } else if ((x == 'f') || (x == 'v')) {
            return 8;
        } else if ((x == 'p') || (x == 'b')) {
            return 9;
        }

        //-1 if there's none of these chars in an array
        return -1;
    }


    public int finalAns(){
        int first = getFirstMajorNum();
        int last = getLastMajorNum();

        //if it's 1 word only
        if(last == -99){
            if(secondNumber() == -1){
                return first;
            }
            else if((first > 9)) {
                return (first * 100) + last;
            }
            return (first*10) + secondNumber();
        }



        if((first == -1) || (last == -1)){
            return -1;
        }
        else if((first > 9) || (last > 9)){
            return (first*100) + last;

        }

        return (first * 10) + (last);
    }
}
