package com.example.mz;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class CountSaver {

    private static final String fileName = "counts";

    public static void saveCount(Context context, short count) throws IOException {
        String fileContent = String.valueOf(count);
        FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        fos.write(fileContent.getBytes());
        System.out.println("SUCCESSFULL saved: " + fileContent + " == " + readCount(context));
    }

    public static short readCount(Context context) throws IOException {
        FileInputStream fis = context.openFileInput(fileName);

        BufferedReader br= new BufferedReader(new InputStreamReader(fis));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((s= br.readLine())!= null)  {
            sb.append(s);
        }

        short count;
        try {
            count = Short.parseShort(sb.toString());
            System.out.println("FOUND: " + count);
        } catch (NumberFormatException e) {
            count = 0;
            System.out.println("FOUND NOTHING!");
            System.out.println("BUT THIS : ???" + sb.toString() + "???");
        }

        return count;
    }
}
