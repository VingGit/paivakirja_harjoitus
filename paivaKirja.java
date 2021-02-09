package DEMO1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import java.util.*;

class paivaKirja extends nakyma {
    private char[] Salis;
    private File tiedosto;
    private HashMap<Integer, String> turvakysymykset;
    private HashMap<LocalDate, ArrayList<String>> merkintaMappi;
    private ArrayList<String> kysymyksetArray;

    paivaKirja() {
        this.turvakysymykset = HashMapFromTextFile("turvakysymykset.txt");

    }
    //kato onk tiedosto tyhjä

    public void luoPaivakirja() {
        this.kysymyksetArray = new ArrayList<>();
        kysymyksetArray.add("");
        kysymyksetArray.add("1: Mikä on suosikki kirjasi?");
        kysymyksetArray.add("2: Mikä on pahin painajaisesi?");
        kysymyksetArray.add("3: Mikä oli ensimmäinen työpaikkasi");
        kysymyksetArray.add("4: Missä kävit ala-asteen?");
        kysymyksetArray.add("5: Mikä on lemmikkieläimesi nimi?");
        kysymyksetArray.add("6: Mikä on unelmiesi lomakohde?");
        kysymyksetArray.add("7: mikä on ensimmäinen asia, jonka ostaisit voitettuasi lotossa?");
        kysymyksetArray.add("8: Mikä on suosikkiruokasi?");
        nollaaKonsoli();
        Console konsoli = System.console();
        this.tiedosto = new File("salasana.txt");
        try {
            //creates a new file
            if (this.tiedosto.createNewFile())      // test if successfully created a new file
            {

                System.out.println("luotiin päiväkirja sijaintiin:  " + tiedosto); //returns the path string
                this.Salis = konsoli.readPassword("Anna päiväkirjallesi salasana: ");
                String str = String.valueOf(this.Salis);
                FileWriter myWriter = new FileWriter(this.tiedosto);
                myWriter.write(str);
                myWriter.close();
                turvakysymykset();
                suoritaliittyma();

            } else {

                System.out.println("Avattiin jo olemassa ollut päiväkirja sijainnista:  " + tiedosto);
                char[] vrtsalis = konsoli.readPassword("Anna jo olemassa olevan päiväkirjan salasana: ");
                String tarkistus = String.valueOf(vrtsalis);
                if (tarkistus.equals(salasanaTiedostosta("salasana.txt"))) {
                    System.out.println("Tervetuloa");
                    suoritaliittyma();
                } else {

                    tarkistaTurvakysymykset();
                    suoritaliittyma();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void uusiSalasana() {
        Console konsoli = System.console();

        try {
            //creates a new file
            if (this.tiedosto.exists() && !this.tiedosto.isDirectory())      // test if successfully created a new file
            {
                this.tiedosto.delete();
                this.tiedosto = new File("salasana.txt");
                this.Salis = konsoli.readPassword("Anna päiväkirjallesi salasana: ");
                System.out.println("Uusi salasana asetettu"); //returns the path string
                String str = String.valueOf(this.Salis);
                FileWriter myWriter = new FileWriter(this.tiedosto);
                myWriter.write(str);
                myWriter.close();

                suoritaliittyma();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tarkistaTurvakysymykset() {
        Scanner lukija = new Scanner(System.in);
        HashMap<Integer, String> tdsostaMap = new HashMap<>();
        HashMap<Integer, String> inputMap = new HashMap<>();
        boolean oikein = true;
        while (oikein) {
            System.out.println("Näyttää siltä, että unohdit salasanasi. Vastaa seuraaviin nollauskysymyksiin,");
            System.out.print("päästäksesi käsiksi päiväkirjaasi:");
            this.turvakysymykset = HashMapFromTextFile("turvakysymykset.txt");
            Set<Integer> keys = turvakysymykset.keySet();
            Integer[] array = keys.toArray(new Integer[keys.size()]);


            for (int i = 0; i < this.turvakysymykset.size(); i++) {
                tdsostaMap.put(array[i], this.turvakysymykset.get(array[i]));
            }
            System.out.println(kysymyksetArray.get(0));
            for (int i = 1; i < this.turvakysymykset.size() + 1; i++) {
                System.out.println(kysymyksetArray.get(i));
                String vastaus = lukija.next();
                lukija.nextLine();
                inputMap.put(array[i - 1], vastaus);
            }

            if (tdsostaMap.equals(inputMap)) {
                System.out.println("Turvakysymykset oikein, tervetuloa!");
                oikein = false;
            } else {
                System.out.println("Turvakysymykset  väärin");
            }
        }
    }

    private String salasanaTiedostosta(String tdsto) {


        try {

            return new String(Files.readAllBytes(Paths.get(tdsto)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void tarkasteleMerkintoja() {

    }

    private void lisaaMerkinta() {
        this.merkintaMappi = new HashMap<>();
        LocalDate lt = LocalDate.now();
        ArrayList<String> in = new ArrayList<>();
        Scanner s = new Scanner(System.in);


            while (s.hasNextLine()) {
                String line = s.nextLine();
                in.add(line);
                //System.out.println(s.hasNextLine()+"    hasnext");
                System.out.print(line != null && line.equalsIgnoreCase("END"));
                System.out.println("   ennen ifiä");
                if (line != null && line.equalsIgnoreCase("END") ) {
                    System.out.print(line != null && line.equalsIgnoreCase("END"+"    jälkeen ifin"));
                    System.out.println("    ifin jälkeen");
                    System.out.println("Output list : " + in);
                    break;
                }

        }
        merkintaMappi.put(lt, in);
        try (FileOutputStream fos = new FileOutputStream("merkinnat.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this.merkintaMappi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean salasanaOikein() {
        Scanner lukija = new Scanner(System.in);
        String koeta = lukija.next();
        if (koeta.equals(salasanaTiedostosta("salasana.txt"))) {
            return true;
        }
        return false;
    }

    private void turvakysymykset() {


        Scanner lukija = new Scanner(System.in);


        turvakysymyValitsin(kysymyksetArray, 0);

        int i = 0;
        while (i < 3) {
            if (!lukija.hasNextInt()) {
                lukija.nextLine();
                turvakysymyValitsin(kysymyksetArray, 0);
                System.out.println("Anna numeroita, älä kirjaimia.:");

            } else {
                int valinta = lukija.nextInt();
                if (valinta < 1 || valinta > 9) {
                    turvakysymyValitsin(kysymyksetArray, 0);
                    System.out.println("Anna luku väliltä 1-9:");

                } else {
                    turvakysymyValitsin(kysymyksetArray, valinta);
                    i++;
                    System.out.print("Kysymyksen " + i + " vastaus: ");
                    String vastaus = lukija.next();
                    lukija.nextLine();
                    turvakysymykset.put(valinta, vastaus);
                    turvakysymyValitsin(kysymyksetArray, 0);
                    System.out.println("Kiitos, valitse nyt jokin toinen kysymys");

                }
            }

        }
        System.out.println("Turvakysymykset asettettu!");
        System.out.println(turvakysymykset.get(1));
        System.out.println(turvakysymykset.get(2));
        System.out.println(turvakysymykset.get(3));
        tallanneaHasmap(turvakysymykset);

    }

    protected void suoritaliittyma() {


        Scanner lukija = new Scanner(System.in);

        ArrayList<String> kysymykset = new ArrayList<>();
        kysymykset.add("");
        kysymykset.add("1: lisää merkintä");
        kysymykset.add("2: poista merkintä");
        kysymykset.add("3: Näytä merkinnät");
        kysymykset.add("4: Uusi salasana");
        kysymykset.add("5: Sulje");

        suoritaLiittymaValitsin(kysymykset, 0);


        while (true) {
            if (!lukija.hasNextInt()) {
                lukija.nextLine();
                suoritaLiittymaValitsin(kysymykset, 0);
                System.out.println("Anna numeroita, älä kirjaimia.:");

            } else {
                int valinta = lukija.nextInt();
                if (valinta < 1 || valinta > 5) {
                    suoritaLiittymaValitsin(kysymykset, 0);
                    System.out.println("Anna luku väliltä 1-5:");

                } else {
                    lukija.nextLine();
                    suoritaLiittymaValitsin(kysymykset, valinta);
                    kasitteleLiittymaInput(valinta);
                    if (!lukija.hasNextLine()) {
                        lukija.nextLine();
                    }

                }
            }

        }


    }

    protected void kasitteleLiittymaInput(int x) {

        switch (x) {
            case 1:
                lisaaMerkinta();
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                uusiSalasana();
                System.out.println("Salasana vaihdettu!");
                break;
            case 5:
                nollaaKonsoli();

                System.exit(0);
                break;
            default:
                System.out.println("tapahtui virhe!");
        }
    }

    public static void nollaaKonsoli() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    private void tallanneaHasmap(HashMap<Integer, String> mappi) {


        // create new HashMap

        // new file object
        File file = new File("turvakysymykset.txt");

        BufferedWriter bf = null;

        try {

            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));

            // iterate map entries
            for (Map.Entry<Integer, String> entry :
                    mappi.entrySet()) {

                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                        + entry.getValue());

                // new line
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                // always close the writer
                bf.close();
            } catch (Exception e) {

            }
        }

    }

    private static HashMap<Integer, String> HashMapFromTextFile(String tdstonimi) {

        HashMap<Integer, String> map
                = new HashMap<>();
        BufferedReader br = null;

        try {

            // create file object
            File file = new File(tdstonimi);

            // create BufferedReader object from the File
            br = new BufferedReader(new FileReader(file));

            String line = null;

            // read file line by line
            while ((line = br.readLine()) != null) {

                // split the line by :
                String[] parts = line.split(":");

                // first part is name, second is number
                int valinta = Integer.parseInt(parts[0].trim());
                String number = parts[1].trim();

                // put name, number in HashMap if they are
                // not empty
                if (valinta > 0 && !number.equals(""))
                    map.put(valinta, number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                } catch (Exception ignored) {
                }
                ;
            }
        }

        return map;
    }

}

