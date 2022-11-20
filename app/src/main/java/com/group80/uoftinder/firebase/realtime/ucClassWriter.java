package com.group80.uoftinder.firebase.realtime;


import com.google.firebase.database.DatabaseReference;

/**
 * A realtime database writer class that can upload data classes to the database. The data class
 * must have getters and setters for the fields wish to upload.
 *
 * @param <T> the type of the objects to be uploaded
 */
public class ucClassWriter<T> extends ucWriter<T> {
    /**
     * Writes the data to the given structural location in the realtime database
     *
     * @param structure the file structure of the upload
     * @param data      the data to be uploaded
     */
    @Override
    public void write(String[] structure, T data) {
        DatabaseReference reference = this.databaseReference;
        for (String dir : structure)
            reference = reference.child(dir);
        reference.setValue(data).addOnSuccessListener(this::notifySuccess).addOnFailureListener(this::notifyFailure);
    }
}
