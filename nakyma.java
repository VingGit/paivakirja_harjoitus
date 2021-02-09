package DEMO1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class nakyma {





    protected void turvakysymyValitsin(ArrayList<String> kysymykset, int valittu) {

        nollaaKonsoli();
        System.out.println(Varit.GREEN_BACKGROUND+"valitse numeron perusteella kolme turvakysymstä, joiden avulla voit palauttaa salasanasi"+Varit.RESET);
        if(valittu==0) {
            for (int i = 1; i <kysymykset.size() ; i++) {

                System.out.println(kysymykset.get(i));
            }

        }
        else{
            for (int i = 1; i <kysymykset.size() ; i++) {
                if(valittu==i){
                    System.out.println(Varit.WHITE_UNDERLINED+kysymykset.get(i)+Varit.RESET);
                }
                else{
                    System.out.println(kysymykset.get(i));
                }
            }
        }


    }
    protected void suoritaLiittymaValitsin(ArrayList<String> kysymykset, int valittu) {


        System.out.println(Varit.GREEN_BACKGROUND+"Valitse haluamasi numero ja paina ENTER suorittaaksesi"+Varit.RESET);
        if(valittu==0) {
            for (int i = 1; i <kysymykset.size() ; i++) {

                System.out.println(kysymykset.get(i));


            }

        }
        else{
            for (int i = 1; i <kysymykset.size() ; i++) {
                if(valittu==i){
                    System.out.println(Varit.WHITE_UNDERLINED+kysymykset.get(i)+Varit.RESET);
                }
                else{
                    System.out.println(kysymykset.get(i));
                }
            }
        }


    }

    private static void nollaaKonsoli() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

}
