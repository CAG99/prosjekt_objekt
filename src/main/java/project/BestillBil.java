package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;

public class BestillBil {
    private HashMap<String, Bil> bestillinger;

    public BestillBil(){
        bestillinger = new HashMap<>();
    }

    public void leggTilBestilling(String epost, Bil bil){
        bestillinger.put(epost, bil);
        System.out.println(bestillinger.get(epost));
    }

    public void skrivBestillingTilFil(String epost, Bil bil){
        try {
            PrintWriter skriver = new PrintWriter("Bestillinger.txt");
            skriver.println(epost + "," + bil.getModell() + "," + bil.getFarge() + "," + bil.getFelger());

            skriver.flush();
            skriver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Landskoder
    private String[] landskoder = {"ad", "ae", "af", "ag", "ai", "al", "am", "ao", "aq", "ar", "as", "at", "au", "aw", "ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz", "ca", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj", "dk", "dm", "do", "dz", "ec", "ee", "eg", "eh", "er", "es", "et", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf", "gg", "gh", "gi", "gl", "gm", "gn", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il", "im", "in", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw", "ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mk", "ml", "mm", "mn", "mo", "mp", "mq", "mr", "ms", "mt", "mu", "mv", "mw", "mx", "my", "mz", "na", "nc", "ne", "nf", "ng", "ni", "nl", "no", "np", "nr", "nu", "nz", "om", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "ps", "pt", "pw", "py", "qa", "re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "ss", "st", "sv", "sx", "sy", "sz", "tc", "td", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tr", "tt", "tv", "tw", "tz", "ua", "ug", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw"};


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


    /* 
    // Epost
    public String getEmail() {
        return epost;
    }

    public void setEmail(String epost) {

        // Sjekker om navnet er med i eposten
        String navnEpost = navn.toLowerCase().replace(" ", ".");
        
        if (!epost.contains(navnEpost) || !epost.contains("@")) {
            throw new IllegalArgumentException("Eposten må være på formen: fornavn.etternavn@domene.landskode");
        }

        // Sjekker om landskoden er med i listen over gyldige landskoder
        String[] deltEpost = epost.split("\\.", 3);
        String siste = deltEpost[deltEpost.length - 1];

        boolean gyldigLandskode = false;
        for (String kode : landskoder) {
            if (kode.equals(siste)) {
                gyldigLandskode = true;
                break;
            }
        }
        if (!gyldigLandskode) {
            throw new IllegalArgumentException("Ikke en gyldig landskode!");
        }

        this.epost = epost;    
    }
    */

}
