package DEMO1;


import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Test {
    //Represent a node of the singly linked list


    public static void main(String[] args) throws IOException {
        Scanner s= new Scanner(System.in);
        ArrayList<String> lista = new ArrayList<>();
        while(s.hasNextLine()){
                String lause = s.nextLine();
                lista.add(lause);


            if(lause != null && lause.equalsIgnoreCase("end") ){
                System.out.println("toimii!");
                break;
            }
        }
    }
}