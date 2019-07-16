package com.statuestore.yours.data.helpers;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileIO {

    public static String saveImg(Context context, Bitmap img, String nome) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath = new File(directory,nome + ".png");

        try {
            FileOutputStream fos = new FileOutputStream(mypath);
            img.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mypath.getPath();
    }

    public static Bitmap getImageFrom(String path) {
        Bitmap b = null;
        try {
            File f = new File(path);
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return b;
    }

    public static void saveImg(String nome, Bitmap img) {
        File directory = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        int random = (int)(Math.random() * 10000);
        File mypath = new File(directory, nome + random + ".png");

        try {
            FileOutputStream fos = new FileOutputStream(mypath);
            img.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
