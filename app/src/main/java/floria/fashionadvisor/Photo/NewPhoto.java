package floria.fashionadvisor.Photo;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import floria.fashionadvisor.R;

/**
 * Created by floria on 13/03/2018.
 */

public class NewPhoto extends AppCompatActivity {


    private Camera mCamera;
    private CameraView mPreview;
    private FileOutputStream stream;
    private FrameLayout  cSurface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neuaufnehmen);
        cSurface = (FrameLayout) findViewById(R.id.photo_view);
        newPic();

    }


    public Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


    private void newPic() {

       mCamera = getCameraInstance();
       // Create our Preview view and set it as the content of our activity.
       mPreview = new CameraView(this, mCamera);
       cSurface.addView(mPreview);
       mPreview.setOnTouchListener(new View.OnTouchListener() {

         //  public void onClick(View v) {
               // Nous prenons une photo

           //}
           @Override
               public boolean onTouch(View myView, MotionEvent event) {

               switch (event.getAction()) {
                   case MotionEvent.ACTION_DOWN:

                       int X = (int)event.getX();
                       int Y = (int)event.getY();

                       mPreview.getmBtm();
                       mPreview.rgbcolor(X,Y);
                       int red = mPreview.getRed();
                       int green = mPreview.getGreen();
                       int blue = mPreview.getBlue();

                       Toast.makeText(getApplicationContext(), "R:"+red+" G:"+green+" B:"+blue,
                               Toast.LENGTH_LONG).show();
                       mCamera.takePicture(null, null, jpegCallback);
                       break;
                   case MotionEvent.ACTION_UP:
                       myView.performClick();
                       break;
                   default:
                       break;
               }

               return true;
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
             /*   ContentValues values = new ContentValues();
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
                    }*/
                } catch (Exception e) {
                    // TODO: handle exception
                }
                cSurface.removeView(mPreview);
                Intent callSetattribut = new Intent(NewPhoto.this, SetAttribut.class);
                startActivity(callSetattribut);
            }
        }
        };
}
