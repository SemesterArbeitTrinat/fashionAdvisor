package floria.fashionadvisor.Photo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import	android.hardware.Camera.Parameters;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import floria.fashionadvisor.Galerie;
import floria.fashionadvisor.MainActivity;
import floria.fashionadvisor.R;

import static android.content.ContentValues.TAG;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

/**
 * Created by floria on 13/03/2018.
 */

public class NeuAufnehmen extends AppCompatActivity {


    private Camera mCamera;
    private CameraView mPreview;
    private  FileOutputStream stream;
    private FrameLayout  cSurface;
    // private Camera camera;
    //private SurfaceView cSurface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neuaufnehmen);

        cSurface = (FrameLayout) findViewById(R.id.photo_view);
                // Create an instance of Camera
        newPic(mCamera);

    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


   private void newPic(Camera camera) {

       mCamera=camera;
       mCamera = getCameraInstance();
       // Create our Preview view and set it as the content of our activity.
       mPreview = new CameraView(this, mCamera);
       cSurface.addView(mPreview);
       mPreview.setOnClickListener(new View.OnClickListener() {

           public void onClick(View v) {
               // Nous prenons une photo
             mCamera.takePicture(null, null, jpegCallback);




           }
       });


    }



    Camera.PictureCallback jpegCallback = new Camera.PictureCallback() {

        public void onPictureTaken(byte[] data, Camera camera) {


            if (data != null) {
                try {
                SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
                String fileName = "photo_" + timeStampFormat.format(new Date())+ ".jpg";

                // Metadata pour la photo
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
                values.put(MediaStore.Images.Media.DESCRIPTION, "Image prise par FormationCamera");
                values.put(MediaStore.Images.Media.DATE_TAKEN, new Date().getTime());
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

                // Support de stockage
                Uri taken =  getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        values);

                stream = (FileOutputStream) getContentResolver().openOutputStream(taken);


                // Enregistrement de votre image

                    if (stream != null) {
                        stream.write(data);
                        stream.flush();
                        stream.close();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
                mPreview.stopC();
                cSurface.removeView(mPreview);
                Intent callSetattribut = new Intent(NeuAufnehmen.this, SetAttribut.class);
                startActivity(callSetattribut);
                // Nous redémarrons la prévisualisation

            }
        }
    };


}
