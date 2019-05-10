package Java;

import java.util.ArrayList;

/*
public class Main2 {
    public static void main(String[] args) {
        Logic logic = new Logic();
    }
}*/

class Logic{
    private int a ; // lines
    private int b ; // columns
    private String[][] grid ;
    private int numbOfBomb ;
    private String bombSymb = "*" ;
    private int muxСellsNumb;
    private int[] randomNumbers; //for  positions of bombs
    private ArrayList<Integer> setBomb ;

    private ArrayList<Integer> checkWhereWeSetBombsList ; // = ArrayList<Integer> setBomb
    private ArrayList<Integer> freeCellsList ;
    private ArrayList<Integer> valueNearBombListPosition;
    private ArrayList<Integer> valueNearBombListValue;


    public Logic(){
        a = 9 ;
        b = 9 ;
        numbOfBomb = 10 ;
        grid = new String [a][b];
        muxСellsNumb = a * b; // = 81
        randomNumbers = new int[numbOfBomb]; // = 10
        setBomb = new ArrayList<>();
        setBombsRec();
        checkListAndMas(); //for checking
        setBomb();
        checkMatrix();
        setBorders() ;
        checkMatrix();

        checkWhereWeSetBombsList = new ArrayList<>();
        freeCellsList = new ArrayList<>();
        valueNearBombListPosition = new ArrayList<>();
        valueNearBombListValue = new ArrayList<>();
        createLists();
        checkWhereWeSetBombsListSize(); // for checking!

    }

    private void setBombsRec(){
        int temp = (int)(Math.random()*muxСellsNumb)+1 ;
        System.out.println("temp =" + temp);
        if (setBomb.contains(temp) && setBomb.size() < numbOfBomb){
            setBombsRec();
        };
        if (!setBomb.contains(temp) && setBomb.size() != numbOfBomb ){
            setBomb.add(temp) ;
            System.out.println("setBomb.size() = " + setBomb.size());
            randomNumbers[setBomb.size()-1] = temp;
            if (setBomb.size() < numbOfBomb){
                setBombsRec();
            };
        };
    }


    private void checkListAndMas(){
        System.out.println("================================" );
        for (int i = 0 ; i<setBomb.size(); i++){
            System.out.println("setBomb.get ("+i+") =" + setBomb.get(i));
            System.out.println("randomNumbers ("+i+") =" + randomNumbers[i]);
        }
    }

    private void setBomb(){
        System.out.println("================================" );
        for(int i =0 ; i<randomNumbers.length ;  i++){
            int temp = randomNumbers[i]-1 ;
            int a1 =  temp/a ;
            System.out.println("lines  - a"+i+" = " + a1);
            int b1 =  temp - (a*a1) ;
            System.out.println("columns - b"+i+" = " + b1);
            grid[a1][b1] = bombSymb ;

        }
    }

    private void checkMatrix(){
        System.out.println(" ");
        System.out.println("================================" );
        System.out.println(" ");
        for(int i =0 ; i<a ;  i++){
            for(int c =0 ; c<b ;  c++){
                if (grid[i][c] == null) {grid[i][c] = "-" ;}
                System.out.print( grid[i][c] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void setBorders(){
        int numbOfBomb = 0 ;
        for(int i =0 ; i<a ;  i++){
            for(int c =0 ; c<b ;  c++){
                //------------------------------------------
                if (grid[i][c].equals(bombSymb)) {
                    if ((i-1) >= 0){
                        if(!grid[i-1][c].equals("-")&& !grid[i-1][c].equals(bombSymb)) {
                            int tempParam = Integer.parseInt(grid[i-1][c])   ;
                            int tempParam2 = tempParam + 1 ;
                            grid[i-1][c] = Integer.toString(tempParam2) ;
                        }
                    }
                    if ((i-1) >= 0){
                        if(grid[i-1][c].equals("-")) {
                            grid[i-1][c] = Integer.toString(1) ;
                        }
                    }
                }
                //------------------------------------------
                if (grid[i][c].equals(bombSymb)) {
                    if ((i+1)<= 8){
                        if(!grid[i+1][c].equals("-")&& !grid[i+1][c].equals(bombSymb)) {
                            int tempParam = Integer.parseInt(grid[i+1][c])   ;
                            int tempParam2 = tempParam + 1 ;
                            grid[i+1][c] = Integer.toString(tempParam2) ;
                        }
                    }
                    if ((i+1)<= 8){
                        if(grid[i+1][c].equals("-")) {
                            grid[i+1][c] = Integer.toString(1) ;
                        }
                    }
                }
                //------------------------------------------
                if (grid[i][c].equals(bombSymb)) {
                    if ((c+1)<= 8){
                        if(!grid[i][c+1].equals("-")&& !grid[i][c+1].equals(bombSymb)) {
                            int tempParam = Integer.parseInt(grid[i][c+1])   ;
                            int tempParam2 = tempParam + 1 ;
                            grid[i][c+1] = Integer.toString(tempParam2) ;
                        }
                    }
                    if ((c+1)<= 8){
                        if(grid[i][c+1].equals("-")) {
                            grid[i][c+1] = Integer.toString(1) ;
                        }
                    }
                }
                //------------------------------------------
                if (grid[i][c].equals(bombSymb)) {
                    if ((c-1) >= 0){
                        if(!grid[i][c-1].equals("-")&& !grid[i][c-1].equals(bombSymb)) {
                            int tempParam = Integer.parseInt(grid[i][c-1])   ;
                            int tempParam2 = tempParam + 1 ;
                            grid[i][c-1] = Integer.toString(tempParam2) ;
                        }
                    }
                    if ((c-1)>= 0){
                        if(grid[i][c-1].equals("-")) {
                            grid[i][c-1] = Integer.toString(1) ;
                        }
                    }
                }
                //--------------- upper left -----------------------
                if (grid[i][c].equals(bombSymb)) {
                    if ((i-1) >= 0 && (c-1) >= 0){
                        if(!grid[i-1][c-1].equals("-")&& !grid[i-1][c-1].equals(bombSymb)) {
                            int tempParam = Integer.parseInt(grid[i-1][c-1])   ;
                            int tempParam2 = tempParam + 1 ;
                            grid[i-1][c-1] = Integer.toString(tempParam2) ;
                        }
                    }
                    if ((i-1) >= 0 && (c-1) >= 0 ){
                        if(grid[i-1][c-1].equals("-")) {
                            grid[i-1][c-1] = Integer.toString(1) ;
                        }
                    }
                }
                //--------------- upper right -----------------------
                if (grid[i][c].equals(bombSymb)) {
                    if ((i-1) >= 0 && (c+1)<= 8){
                        if(!grid[i-1][c+1].equals("-")&& !grid[i-1][c+1].equals(bombSymb)) {
                            int tempParam = Integer.parseInt(grid[i-1][c+1])   ;
                            int tempParam2 = tempParam + 1 ;
                            grid[i-1][c+1] = Integer.toString(tempParam2) ;
                        }
                    }
                    if ((i-1) >= 0 && (c+1)<= 8){
                        if(grid[i-1][c+1].equals("-")) {
                            grid[i-1][c+1] = Integer.toString(1) ;
                        }
                    }
                }
                //--------------- left lower  -----------------------
                if (grid[i][c].equals(bombSymb)) {
                    if ((i+1)<= 8 && (c-1) >= 0){
                        if(!grid[i+1][c-1].equals("-")&& !grid[i+1][c-1].equals(bombSymb)) {
                            int tempParam = Integer.parseInt(grid[i+1][c-1])   ;
                            int tempParam2 = tempParam + 1 ;
                            grid[i+1][c-1] = Integer.toString(tempParam2) ;
                        }
                    }
                    if ((i+1)<= 8 && (c-1) >= 0 ){
                        if(grid[i+1][c-1].equals("-")) {
                            grid[i+1][c-1] = Integer.toString(1) ;
                        }
                    }
                }
                //--------------- right lower  -----------------------
                if (grid[i][c].equals(bombSymb)) {
                    if ((i+1)<= 8 && (c+1)<= 8){
                        if(!grid[i+1][c+1].equals("-")&& !grid[i+1][c+1].equals(bombSymb)) {
                            int tempParam = Integer.parseInt(grid[i+1][c+1])   ;
                            int tempParam2 = tempParam + 1 ;
                            grid[i+1][c+1] = Integer.toString(tempParam2) ;
                        }
                    }
                    if ((i+1)<= 8 && (c+1)<= 8 ){
                        if(grid[i+1][c+1].equals("-")) {
                            grid[i+1][c+1] = Integer.toString(1) ;
                        }
                    }
                }

                //-----------------------------------------------

            }
        }
    }

    private void createLists(){
        int tempNumb = 1;
        for(int i =0 ; i<a ;  i++){
            for(int c =0 ; c<b ;  c++){
                if (grid[i][c].equals("*")){
                    checkWhereWeSetBombsList.add(tempNumb);
                }
                if (grid[i][c].equals("-")){
                    freeCellsList.add(tempNumb);
                }
                if (!grid[i][c].equals("-") && !grid[i][c].equals("*")){
                    valueNearBombListPosition.add(tempNumb);
                    valueNearBombListValue.add(Integer.parseInt(grid[i][c]));
                }
                tempNumb = tempNumb + 1 ;
            }
        }
       printLists();   // - for checking
    }

    private void printLists(){
        System.out.println("====================================");
        System.out.println("checkWhereWeSetBombsList");
        for (int i = 0 ; i <checkWhereWeSetBombsList.size(); i++ ){
            System.out.print(checkWhereWeSetBombsList.get(i)+ " // ");
        }
        System.out.println("");
        System.out.println("freeCellsList");
        for (int i = 0 ; i <freeCellsList.size(); i++ ){
            System.out.print(freeCellsList.get(i) + " // ");
        }
        System.out.println("");
        System.out.println("valueNearBombList");
        for (int i = 0 ; i <valueNearBombListPosition.size(); i++ ){
            System.out.print(valueNearBombListPosition.get(i)+" = "+ valueNearBombListValue.get(i)+ " // ");
        }
    }

    private void checkWhereWeSetBombsListSize() {
        System.out.println("====================================");
        System.out.println("");
        System.out.println("checkWhereWeSetBombsList.size() = "+ checkWhereWeSetBombsList.size());
    }


    public ArrayList<Integer> getCheckWhereWeSetBombsList() {
        return checkWhereWeSetBombsList;
    }

    public ArrayList<Integer> getFreeCellsList() {
        return freeCellsList;
    }

    public ArrayList<Integer> getValueNearBombListPosition() {
        return valueNearBombListPosition;
    }

    public ArrayList<Integer> getValueNearBombListValue() {
        return valueNearBombListValue;
    }


}
