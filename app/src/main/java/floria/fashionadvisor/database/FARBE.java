package floria.fashionadvisor.database;

/**
 * Created by Seehund on 17.05.2018.
 */

public class FARBE {

    private int Farbe_id;
    private String Farbe_farbe;

    public FARBE (int farbe_id, String farbe_farbe){
        this.Farbe_id=farbe_id;
        this.Farbe_farbe=farbe_farbe;
    }

    public String toStringfarbe(){
        String outputfarbe = "Farbe " + Farbe_farbe;
        return outputfarbe;
    }

    public long getId() {
        return Farbe_id;
    }

    public void setId(int farbe_id) {
        this.Farbe_id= farbe_id;
    }


}

