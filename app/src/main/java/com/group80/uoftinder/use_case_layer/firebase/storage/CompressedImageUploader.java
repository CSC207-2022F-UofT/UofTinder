package com.group80.uoftinder.use_case_layer.firebase.storage;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * A uploader to upload compressed bitmap images to the firebase cloud storage
 */
public class CompressedImageUploader extends ImageUploader {
    /**
     * The quality of the compressed image, from 1 (lowest quality) to 100 (original quality)
     */
    private int quality;

    /**
     * Creates a compressed image uploader with the given quality
     *
     * @param quality the quality of the image, scaled discretely from 1 to 100
     */
    public CompressedImageUploader(int quality) {
        if (quality < 1 || quality > 100)
            quality = 50;
        this.quality = quality;
    }

    /**
     * Creates a compressed image upload with the quality set to 25% of original
     */
    public CompressedImageUploader() {
        this.quality = 50;
    }

    /**
     * Sets the quality of future uploads
     *
     * @param quality the quality of the image, scaled discretely from 1 to 100
     */
    public void setQuality(int quality) {
        if (quality < 1 || quality > 100)
            quality = 50;
        this.quality = quality;
    }

    /**
     * Converts a bitmap image to a byte array, the quality is compressed to 1/4 of original, converted to JPEG format.
     *
     * @param bitmap The bitmap image to be converted
     * @return The converted byte array
     */
    protected byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, this.quality, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
