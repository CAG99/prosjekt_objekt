package project;

import java.util.ArrayList;

public class BestillBil {
    private ArrayList<Bil> bestillinger;

    public BestillBil(){
        bestillinger = new ArrayList<Bil>();
    }

    public void leggTilBestilling(Bil bil){
        bestillinger.add(bil);
        System.out.println(bestillinger.get(0));
    }


}