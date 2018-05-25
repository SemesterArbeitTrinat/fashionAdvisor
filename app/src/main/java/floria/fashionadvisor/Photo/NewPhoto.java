package floria.fashionadvisor.Photo;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import floria.fashionadvisor.R;
import floria.fashionadvisor.database.DBDataSource;

/**
 * Created by floria on 13/03/2018.
 */

public class NewPhoto extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Camera mCamera;
    private CameraView mPreview;
    private FileOutputStream stream;
    private FrameLayout  cSurface;
    private Uri file;
    private ContentValues values;
    public String mCurrentPhotoPath;
    public static Drawable DBphotoDrwb;
    public static Bitmap DBFotoBtm;
    public static String detectedColor;
    public static byte[] DBByteImage;
    public static String DBPhotoPath;



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

                       //Die Koordinaten speichern
                       int X = (int)event.getX();
                       int Y = (int)event.getY();
                       //Den rgb Code für den Pixel mit Koordinaten(X,Y) speichern
                       mPreview.rgbcolor(X,Y);
                       //Diese Werte wieder nehmen
                       int red = mPreview.getRed();
                       int green = mPreview.getGreen();
                       int blue = mPreview.getBlue();
                       //Die richtige Farbe zuordnen
                       String colo = colortxt(red,green,blue);
                       detectedColor=colo;
                       DBphotoDrwb = new BitmapDrawable(mPreview.getmBtm());
                       DBFotoBtm=mPreview.getmBtm();
                       mCamera.takePicture(null, null, jpegCallback);
                       //SavePicture();
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

            Bitmap bitmap = DBFotoBtm;

            File folder = Environment.getExternalStoragePublicDirectory("/storage/emulated/test");// the file path

            //if it doesn't exist the folder will be created
           if(!folder.exists())
           {folder.mkdir();}

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            byte[] data1 = stream.toByteArray();
         //   bitmap.saveBitmapToFile(bitmap, Environment.getInstance().getMyDir() + "/" + new Date().getTime() + ".jpg", Bitmap.CompressFormat.JPEG);
            FileOutputStream fos = null;
            try {
               File file = new File(folder.toString()+ new Date().getTime() + ".png");
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                fos = new FileOutputStream(file);
                fos.write(data1);
                DBPhotoPath = file.getPath();

            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

           // Bundle bundle = new Bundle();
           // bundle.putParcelable("bitmap", bitmap);
           // setResult(bundle);

           // cameraManager.restartPreview();*/
        /*   if (data != null) {
                try {
                // Enregistrement de votre image

                    if (stream != null) {
                        stream.write(data);
                        stream.flush();
                        stream.close();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }*/


                cSurface.removeView(mPreview);




                Intent callSetattribut = new Intent(NewPhoto.this, SetAttribut.class);
                startActivity(callSetattribut);
           // }
          }
        };


    private File getFile() {
        File folder = Environment.getExternalStoragePublicDirectory("/From_camera/imagens");// the file path

        //if it doesn't exist the folder will be created
        if(!folder.exists())
        {folder.mkdir();}

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+ timeStamp + "_";
        File image_file = null;

        try {
            image_file = File.createTempFile(imageFileName,".jpg",folder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mCurrentPhotoPath = image_file.getAbsolutePath();
        return image_file;
    }
private void SavePicture(){
   // mCurrentPhotoPath = image_file.getAbsolutePath();

    try {
        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                "yyyy-MM-dd-HH.mm.ss");
        String fileName = "photo_" + timeStampFormat.format(new Date())
                + ".jpg";

        // Metadata pour la photo
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, fileName);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image prise par FormationCamera");
        values.put(MediaStore.Images.Media.DATE_TAKEN, new Date().getTime());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        // Support de stockage
           Uri taken = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        // Ouverture du flux pour la sauvegarde
        stream = (FileOutputStream) getContentResolver().openOutputStream(taken);
        //DBPhotoPath=
       // DBPhotoPath = taken.getPath();
        Toast.makeText(getApplicationContext(), DBPhotoPath, Toast.LENGTH_LONG).show();
        mCamera.takePicture(null, null, jpegCallback);
    } catch (Exception e) {
        // TODO: handle exception
    }
}



    private String colortxt(int red, int green, int blue){

        String colorName;
        int colorId;
        colorId=getHumanColor(red, green, blue)[3];
        colorName=humanColors[colorId];

return colorName;
    }


        public static final int [] [] colors  = {
                {-1,-10,-360,-0,-100,-10,-80},
                {1,0,360,0,100,0,50},
                {2,0,360,0,15,50,130},
                {3,0,360,0,15,130,210},
                {4,-15,15,15,100,50,255},
                {5,15,45,15,100,50,255},
                {6,45,75,15,100,50,255},
                {7,75,105,15,100,50,255},
                {8,105,135,15,100,50,255},
                {9,135,165,15,100,50,255},
                {10,165,195,15,100,50,255},
                {11,195,225,15,100,50,255},
                {12,225,255,15,100,50,255},
                {13,255,285,15,100,50,255},
                {14,285,315,15,100,50,255},
                {15,315,345,15,100,50,255},
                {16,345,375,15,100,50,255},
                {17,0,360,0,15,210,255}
        };


    /**
     *
     *@author Tomasz Drobiszewski
     */
        /**
         * Color names.
         */
        public static final String [] humanColors = {
                "unbekannt",
                "schwarz", //black colors[1]
                "grau",
                "hell grau",
                "rot",
                "orange",
                "gelb",
                "grün",
                "grün",
                "grün",
                "turkis",
                "hell blau",
                "blau",
                "lila",
                "lila",
                "rosa",
                "rot",
                "weiss",
        };

        /**
         * Convert from  RGB do HSV
         *
         * @param r
         * @param g
         * @param b
         * @param hsv
         */
        public static void rgb2hsv(int r, int g, int b, int hsv[]) {

            int min; // Min. value of RGB
            int max; // Max. value of RGB
            int delMax; // Delta RGB value

            if (r > g) {
                min = g;
                max = r;
            } else {
                min = r;
                max = g;
            }
            if (b > max)
                max = b;
            if (b < min)
                min = b;

            delMax = max - min;

            float H = 0, S;
            float V = max;

            if (delMax == 0) {
                H = 0;
                S = 0;

            } else {
                S = delMax / (float) max;
                if (r == max)
                    H = ((g - b) / (float) delMax) * 60;
                else if (g == max)
                    H = (2 + (b - r) / (float) delMax) * 60;
                else if (b == max)
                    H = (4 + (r - g) / (float) delMax) * 60;

            }

            hsv[0] = (int) (H);
            hsv[1] = (int) (S * 100);
            hsv[2] = (int) V;
        }

        /**
         * Return human color index {@link //Color#colors}
         *
         * @param r
         * @param g
         * @param b
         * @return
         */
        public static int [] getHumanColor(int r, int g, int b) {
            int [] res = {0, 0, 0, 0};
            int[] hsv = new int[3];
            rgb2hsv(r, g, b, hsv);
            for (int i = 1; i < colors.length; i++) {
                if (hsv[0] <= colors[i][2] && hsv[0] >= colors[i][1]
                        && hsv[1] <= colors[i][4] && hsv[1] >= colors[i][3]
                        && hsv[2] <= colors[i][6] && hsv[2] >= colors[i][5]) {
                    res[3] = i;
                    res[0] = hsv[0];
                    res[1] = hsv[1];
                    res[2] = hsv[2];
                    return res;
                }

            }
            return res;
        }

    }

