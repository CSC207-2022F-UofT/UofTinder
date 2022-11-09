package com.group80.uoftinder.firebase;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class ucProfileImageUploader extends ucImageUploader {
    /**
     * Converts a bitmap image to a byte array, the quality is compressed to 1/4 of original, converted to JPEG format.
     *
     * @param bitmap The bitmap image to be converted
     * @return The converted byte array
     */
    protected byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
