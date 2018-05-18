package floria.fashionadvisor.database;

/**
 * Created by Seehund on 24.04.2018.
 */

public class DB {
    private static int id;
    private String Stil;
    private String Bezeichnung;
    private static String Farbe;
    private String Schnitt;
    private int Rank;
    private int Favorit;
    private int Haeufigkeit;
    private String Photo;
    private String Kategorie;


   public DB (int id, String Stil, String Bezeichnung, String Farbe, String Schnitt, int Rank, int Favorit, int Haeufigkeit, String Photo, String Kategorie){
       this.id = id;
       this.Stil = Stil;
       this.Bezeichnung = Bezeichnung;
       this.Farbe = Farbe;
       this.Schnitt= Schnitt;
       this.Rank= Rank;
       this.Favorit=Favorit;
       this.Haeufigkeit=Haeufigkeit;
       this.Photo=Photo;
       this.Kategorie=Kategorie;

   }


    public static long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Override
    public String toString (){
       String output = "Bezeichnung: "+Bezeichnung + " Farbe:  "+ Farbe+ " Stil "+Stil;
       return output;
    }



}
