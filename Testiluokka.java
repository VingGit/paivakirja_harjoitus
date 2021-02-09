package DEMO1;

public class Testiluokka {
    public static void main(String[] args) {
        Muistikirja kirja = new Muistikirja("Mikko Muistavainen");
        kirja.lisaaMuistiinpano("Muista käydä kaupassa");
        kirja.lisaaMuistiinpano("Muista lukea tenttiin");
        kirja.lisaaMuistiinpano("Muista katsoa uutiset");

        System.out.println(kirja.kaikkiMuistiinpanot());
    }
}
