package floria.fashionadvisor.byteArrayPhoto;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by tomsc on 18.05.2018.
 */
/*
class SavePhotoTask extends AsyncTask<byte[], String, String> {
    @Override
    protected String doInBackground(byte[]... bmp) {
        File photo=new File(Environment.getExternalStorageDirectory(), "photo.bmp");

        if (photo.exists()) {
            photo.delete();
        }

        try {
            FileOutputStream fos=new FileOutputStream(photo.getPath());

            fos.write(bmp[0]);
            fos.close();
        }
        catch (java.io.IOException e) {
            Log.e("PictureDemo", "Exception in photoCallback", e);
        }

        return(null);
    }
}
*/

public class SavePhotoTask {
    //@Override
    public String saveImageFromDB(byte[]... bmp) {
        File photo=new File(Environment.getExternalStorageDirectory(), "photo.bmp");

        if (photo.exists()) {
            photo.delete();
        }

        try {
            FileOutputStream fos=new FileOutputStream(photo.getPath());
            String path = photo.getPath();

            fos.write(bmp[0]);
            fos.close();
            return path;
        }
        catch (java.io.IOException e) {
            Log.e("PictureDemo", "Exception in photoCallback", e);
        }

        return(null);
    }
}

