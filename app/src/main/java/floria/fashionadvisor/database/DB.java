package floria.fashionadvisor.database;

/**
 * Created by Seehund on 24.04.2018.
 */

public class DB {
    private int id;
    private int Stil;
    private int Bezeichnung;
    private int Farbe;
    private int Schnitt;
    private int Rank;
    private int Favorit;
    private int Haeufigkeit;
    private String Photo;


   public DB (int id, int Stil, int Bezeichnung, int Farbe, int Schnitt, int Rank, int Favorit, int Haeufigkeit, String Photo){
       this.id = id;
       this.Stil = Stil;
       this.Bezeichnung = Bezeichnung;
       this.Farbe = Farbe;
       this.Schnitt= Schnitt;
       this.Rank= Rank;
       this.Favorit=Favorit;
       this.Haeufigkeit=Haeufigkeit;
       this.Photo=Photo;

   }





    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString (){
       String output = "Bezeichnung: "+Bezeichnung + " Farbe:  "+ Farbe;
       return output;
    }



}
