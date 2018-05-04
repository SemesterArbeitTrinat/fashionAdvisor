package floria.fashionadvisor.Photo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * Created by floria on 29/03/2018.
 */

public class CameraView extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback {

    private SurfaceHolder mHolder;
    private Camera mCamera;
    //This variable is responsible for getting and setting the camera settings
    private Camera.Parameters parameters;
    //this variable stores the camera preview size
    private Camera.Size previewSize;
    //this array stores the pixels as hexadecimal pairs

    private int pixels[];

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private Bitmap mBmp;

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }




    public Bitmap getmBtm() {
        return mBmp;
    }

    public CameraView(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mCamera.setDisplayOrientation(90);


        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


    }

    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.

        try {

            mCamera.setPreviewDisplay(holder);
            mCamera.setPreviewCallback(this);

            ///initialize the variables
            parameters = mCamera.getParameters();
            previewSize = parameters.getPreviewSize();
            pixels = new int[previewSize.width * previewSize.height];
            mCamera.startPreview();

        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }


    }

    public void surfaceDestroyed(SurfaceHolder holder) {

        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }
        // empty. Take care of releasing the Camera preview in your activity.
    }


    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.
        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }
        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }
        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }


    @Override
    public boolean performClick() {
// TODO Auto-generated method stub
        return super.performClick();
    }


    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

        YuvImage image = new YuvImage(data, parameters.getPreviewFormat(), previewSize.width, previewSize.height, null);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        image.compressToJpeg(new Rect(0, 0, previewSize.width, previewSize.height), 100, out);
        byte[] imageBytes = out.toByteArray();
        mBmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    public void rgbcolor( int X, int Y) {


        red = 0;
        green = 0;
        blue = 0;

        for (int ii = -2; ii < 3; ii++) {
            for (int jj = -2; jj < 3; jj++) {
                int pixel = mBmp.getPixel(X - ii, Y - jj);
                red = red + Color.red(pixel);
                green = green + Color.green(pixel);
                blue = blue + Color.blue(pixel);

            }
        }

        red = red / 25;
        green = green / 25;
        blue = blue / 25;


    }
}