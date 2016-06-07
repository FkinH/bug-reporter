package com.fkinh.bugreporter.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Author: jinghao
 * Email: jinghao@meizu.com
 * Date: 2016-06-07
 */
public class FileUtil {

    public static boolean copyFileByStream(File src, File dst) {
        InputStream in;
        OutputStream out;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        byte[] buf = new byte[1024];
        int len;
        try {
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
