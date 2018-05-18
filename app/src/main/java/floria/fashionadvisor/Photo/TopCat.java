package floria.fashionadvisor.Photo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import floria.fashionadvisor.R;

/**
 * Created by flori on 30/04/2018.
 */

public class TopCat extends Fragment implements AdapterView.OnItemSelectedListener {


    private int index=0;
    private String categorie;
    private ArrayAdapter<CharSequence> adapter;

    public void setIndex(int index) {
        this.index = index;
    }
    private  View view;


    public String getCategorie() {
        return categorie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_topcat, container, false);
        Spinner spinner = (Spinner) view.findViewById(R.id.topcat);

        spinner.setOnItemSelectedListener(this);

        switch (index){
            case 1 :
                adapter = ArrayAdapter.createFromResource(getActivity(),R.array.down_cat, android.R.layout.simple_spinner_item);

                //categorie=adapter.toString();
                break;
            case 2 :
                adapter = ArrayAdapter.createFromResource(getActivity(),R.array.top_cat, android.R.layout.simple_spinner_item);

                //categorie=adapter.toString();

                break;

            default:

                break;

        }
        //categorie= spinner.getSelectedItem().toString();


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        return view;
    }

    public int getIndex() {
        return index;
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id) {
        categorie=adapter.getItem(pos).toString();

        // Toast.makeText(getApplicationContext(), pos, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }



}
