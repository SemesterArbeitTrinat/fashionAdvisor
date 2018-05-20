package floria.fashionadvisor.GaleriePhoto;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
        GalerieItem fav=new GalerieItem();
        fav.setIndex(position);
        fav.setNameCat("Fav");
        switch (position){
            case 1:
                fav.setNameCat("Fav");
                break;
            case 2:
                fav.setNameCat("Alle");
                break;
            case 3:
                fav.setNameCat("Top");
                break;
            case 4:
                fav.setNameCat("Tshirt");
                break;
            case 5:
                fav.setNameCat("Hose");
                break;
            case 6:
                fav.setNameCat("Pullover");
                break;

            default:
                break;


        }

        return fav;
     }

}
