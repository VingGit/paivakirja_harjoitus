package DEMO1;

import java.util.ArrayList;

class Muistikirja {
    private String omistaja;
    private ArrayList<String> muistiinpanot;

    public Muistikirja(String kasapaskaa) {
        this.omistaja = kasapaskaa;
        // ei parametrista, vaan alustetaan aina tyhjä lista
        this.muistiinpanot = new ArrayList<>();
    }

    public String getOmistaja() {
        return this.omistaja;
    }

    public void lisaaMuistiinpano(String muistiinpano) {
        this.muistiinpanot.add(muistiinpano);
    }

    // Palauttaa kaikki muistiinpanot yhdessä merkkijonossa
    public String kaikkiMuistiinpanot() {
        // join-metodi yhdistää listan alkiot annetulla välimerkillä
        // se on siis tavallaan split-metodin vastakohta
        String mp = String.join("\n", muistiinpanot);
        return mp;
    }
}