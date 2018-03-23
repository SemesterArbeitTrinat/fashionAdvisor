package floria.fashionadvisor.GaleriePhoto;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import floria.fashionadvisor.R;

/**
 * Created by flori on 22/03/2018.
 */

public class Favoris extends Fragment {

    int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Favoris() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        switch (index){
            case 0:
                return inflater.inflate(R.layout.favoris_fragment, container, false);
            case 1:
                return inflater.inflate(R.layout.tee_shirt_frag, container, false);
            case 2:
                return inflater.inflate(R.layout.favoris_fragment, container, false);
            case 3:
                return inflater.inflate(R.layout.favoris_fragment, container, false);
            case 4:
                return inflater.inflate(R.layout.tee_shirt_frag, container, false);
            case 5:
                return inflater.inflate(R.layout.favoris_fragment, container, false);
            default:
                return null;
        }
       // return inflater.inflate(R.layout.favoris_fragment, container, false);
    }
}
