package com.jmruiz.NoteApp.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jmruiz.NoteApp.R;
import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.NoteApp.viewmodel.NewNoteDialogViewModel;

import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class NewNoteDialogFragment  extends DialogFragment {

    private NewNoteDialogViewModel mViewModel;
    //crear setter and getter
    private String title;
    private String content;
    private boolean isFavorite;
    private String imagePath;
    private String selectedImagePath;
    private View view;
    private EditText editTextTitle, editTextContent;
    private SwitchCompat switchNoteFavorite;
    private ImageView imageViewAddImage;
    private Context ctx;

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    public void setAdInfo(String title, String content, boolean isFavorite, String imagePath)
    {
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.isFavorite = isFavorite;
    }

    public static NewNoteDialogFragment newInstance() {
        return new NewNoteDialogFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        ctx = getContext();// lo agregue 22-11-2020 21:30 habria que ver si despues funciona bien

        builder.setTitle("New note");
        builder.setMessage("Enter the details of the new note")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //title = editTextTitle.getText().toString();
                        //content = editTextContent.getText().toString();

                        //isFavorite = switchNoteFavorite.isChecked();

                        //selectedImagePath = "";

                        final NoteEntity note = new NoteEntity();
                        note.setTitle(editTextTitle.getText().toString());
                        note.setContent(editTextContent.getText().toString());
                        note.setFavorite(switchNoteFavorite.isChecked());
                        note.setImagePath(selectedImagePath);

                        // Comunicar al ViewModel el nuevo dato.
                        mViewModel = new ViewModelProvider(getActivity())
                                .get(NewNoteDialogViewModel.class);
                        //mViewModel.insertNote(new NoteEntity(title, content, isFavorite, imagePath));
                        mViewModel.insertNote(note);
                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.new_note_dialog_fragment, null);

        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextContent = view.findViewById(R.id.editTextContent);
        switchNoteFavorite = view.findViewById(R.id.switchNoteFavorite);

        imageViewAddImage = view.findViewById(R.id.imageViewAddImage);

        imageViewAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "clickkkk", Toast.LENGTH_LONG).show();

                if (ContextCompat.checkSelfPermission(
                        getActivity().getApplication(), Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                } else {
                    selectImage();
                }
            }
        });

        builder.setView(view);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults.length > 0){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                selectImage();
            }else {
                Toast.makeText(getActivity(), "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK){
            if (data != null){
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null){
                    try {
                        InputStream inputStream = ctx.getContentResolver().openInputStream(selectedImageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageViewAddImage.setImageBitmap(bitmap);
                        imageViewAddImage.setVisibility(View.VISIBLE);
                        //findViewById(R.id.imageRemoveImage).setVisibility(View.VISIBLE);

                        selectedImagePath = getPathFromUri(selectedImageUri);
                    }catch (Exception e){
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    private String getPathFromUri(Uri contentUri){
        String filePath;
        Cursor cursor = ctx.getContentResolver()
                .query(contentUri,null,null,null,null);
        if (cursor == null){
            filePath = contentUri.getPath();
        }else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex("_data");
            filePath = cursor.getString(index);
            cursor.close();
        }
        return filePath;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
