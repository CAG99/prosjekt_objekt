package project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BestillBil {
    private HashMap<String, Bil> bestillinger;

    // Konstruktør
    public BestillBil(){
        bestillinger = new HashMap<>();
    }

    // Metode som legger til bestillingen med epost og bil-objekt i bestillinger HashMap 
    public void leggTilBestilling(String epost, Bil bil){
        if (bestillinger.containsKey(epost)) {
            throw new IllegalStateException("Pga. begrenset produksjonskapasitet kan man kun bestille 1 bil per epost");
        } else {
             bestillinger.put(epost, bil);
        }
    }

    // Metode som henter bestilling fra HashMap
    public Bil hentBestilling(String epost){
        if (bestillinger.containsKey(epost)) {
            return bestillinger.get(epost);
        } else {
            throw new IllegalArgumentException("Ingen bestilling med den eposten");
        }
    }

    // Metode som skriver bestillingen til tekstfil 
    public void skrivBestillingTilFil(){
        try {
            PrintWriter skriver = new PrintWriter("Bestillinger.txt");
            for (Map.Entry<String, Bil> bil : bestillinger.entrySet()) {
                String epost = bil.getKey();
                String modell = bil.getValue().getModell();
                String farge = bil.getValue().getFarge();
                String felger = bil.getValue().getFelger();
                skriver.println(epost + "," + modell + "," + farge + "," + felger);
            }
            skriver.flush();
            skriver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metode som henter leser tekstfil og henter bestillingen 
    public Bil hentBestillinger(String epost) throws FileNotFoundException{
        Scanner leser = new Scanner(new File("Bestillinger.txt"));
        HashMap<String, Bil> bilBestillinger = new HashMap<>();

        while (leser.hasNextLine()) {
            String bestillString = leser.nextLine();
            String[] bestillingInfo = bestillString.split(",");

            System.out.println(bestillingInfo[0]);

            String eposter = bestillingInfo[0];
            String modell = bestillingInfo[1];
            String farge = bestillingInfo[2];
            String felger = bestillingInfo[3];

            Bil bil = new Bil();
            bil.setModell(modell);
            bil.setFarge(farge);
            bil.setFelger(felger); 

            bilBestillinger.put(eposter, bil);
        }
        leser.close();

        if (bilBestillinger.containsKey(epost)) {
            return bilBestillinger.get(epost);
        } else {
            return null;
        }
    }

    // Landskoder
    private String[] landskoder = {"ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw"};


    // Metode for validering av gyldig epost
    public boolean sjekkEpost(String epost) {
        if (!epost.contains("@")) {
            throw new IllegalArgumentException("Eposten må inneholde @ for å være gyldig!");
        }

        boolean gyldigLandskode = false;

        for (String kode : landskoder) {
            String nykode = "." + kode;
            if (epost.contains(nykode)) {
                gyldigLandskode = true;
                break;
            }
        }
        if (gyldigLandskode == true) {
            return true;
        }
        else {
            throw new IllegalArgumentException("Eposten må avslutte med en gyldig landskode!");
        }
    }


}
