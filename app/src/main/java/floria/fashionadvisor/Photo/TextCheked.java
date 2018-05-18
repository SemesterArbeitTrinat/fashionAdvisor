package floria.fashionadvisor.Photo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import floria.fashionadvisor.R;


/**
 * Created by floria on 08/04/2018.
 */

public class TextCheked extends BaseAdapter {

   private String[] style;
   private Context context;
   private LayoutInflater inflter;
   private String[] DBliste={"0","0","0","0","0"};
   private String DBtotalliste="";
   private int DBindex=0;

    public TextCheked(Context applicationContext, String[] style) {
        this.context = applicationContext;
        this.style=style;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return style.length;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

   @Override
    public View getView(int position, View view, ViewGroup parent) {

       view = inflter.inflate(R.layout.activity_gv, null);
       final CheckedTextView mTextV = (CheckedTextView) view.findViewById(R.id.mTextV);
       mTextV.setText(style[position]);

// perform on Click Event Listener on CheckedTextView
      mTextV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTextV.isChecked()) {
// set cheek mark drawable and set checked property to false
                    mTextV.setBackgroundColor(Color.TRANSPARENT);;
                    mTextV.setChecked(false);
                    for (int ii=0;ii<5;ii++) {
                        if (DBliste[ii].equals(mTextV.getText().toString()+"|")){
                            DBliste[ii]="0";
                        }
                    }

                }
                else {
// set cheek mark drawable and set checked property to true

                    mTextV.setBackgroundColor(Color.LTGRAY);
                    mTextV.setChecked(true);
                    DBliste[DBindex]=mTextV.getText().toString()+"|";
                    if (DBindex<5){
                        DBindex++;
                    }
                    else{
                        DBindex=0;
                    }
                }
              }
        });

        return view;
    }

    public void setDBtotalliste(String DBtotalliste) {
        this.DBtotalliste = DBtotalliste;
    }

    public String getDBtotalliste() {

        for (int ii=0;ii<5;ii++) {
            DBtotalliste = DBtotalliste+(DBliste[ii]);
        }

        return DBtotalliste;
    }
}
