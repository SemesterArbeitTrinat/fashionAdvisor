package floria.fashionadvisor.GaleriePhoto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import floria.fashionadvisor.R;

/**
 * Created by flori on 16/03/2018.
 */

public class ViewGalerie extends FragmentPagerAdapter {

    int onglet;

    @Override
    public int getCount() {
        return onglet;
    }

    public ViewGalerie(FragmentManager fm, int onglet) {
        super(fm);
        this.onglet = onglet;
    }

    @Override
    public Fragment getItem(int position) {
        Favoris fav=new Favoris();
        fav.setIndex(position);
        return fav;
     }

}
