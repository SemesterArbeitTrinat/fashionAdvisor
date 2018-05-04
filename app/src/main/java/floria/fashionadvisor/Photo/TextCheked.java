package floria.fashionadvisor.Photo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import floria.fashionadvisor.R;



/**
 * Created by floria on 08/04/2018.
 */

public class TextCheked extends BaseAdapter {

   private String[] style;
   private Context context;
   private LayoutInflater inflter;

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
                }
                else {
// set cheek mark drawable and set checked property to true

                    mTextV.setBackgroundColor(Color.LTGRAY);
                    mTextV.setChecked(true);
                }
              }
        });

        return view;
    }
}
