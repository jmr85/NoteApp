package com.jmruiz.NoteApp.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jmruiz.NoteApp.viewmodel.NewNoteDialogViewModel;
import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.NoteApp.R;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link NoteEntity}.
 */
public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context ctx;
    private NewNoteDialogViewModel viewModel;
    private FragmentManager fm;

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    public NotesAdapter(List<NoteEntity> items, Context ctx) {
        this.mValues = items;
        this.ctx = ctx;
        viewModel = new ViewModelProvider((AppCompatActivity)ctx).get(NewNoteDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.editTextTitle.setText(holder.mItem.getTitle());
        holder.editTextContent.setText(holder.mItem.getContent());

        if (holder.mItem.getImagePath() != null){
            Toast.makeText(ctx, "visible", Toast.LENGTH_LONG).show();
            holder.imageNote.setImageBitmap(BitmapFactory.decodeFile(holder.mItem.getImagePath()));
            holder.imageNote.setVisibility(View.VISIBLE);
        }else {
            Toast.makeText(ctx, "gone", Toast.LENGTH_LONG).show();
            holder.imageNote.setVisibility(View.GONE);
        }

        if(holder.mItem.isFavorite()) {
            holder.imageViewFavorite.setImageResource(R.drawable.ic_baseline_star_24);
        }

        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.mItem.isFavorite()) {
                    holder.mItem.setFavorite(false);
                    holder.imageViewFavorite.setImageResource(R.drawable.ic_baseline_star_border_24);

                } else {
                    holder.mItem.setFavorite(true);
                    holder.imageViewFavorite.setImageResource(R.drawable.ic_baseline_star_24);
                }
                viewModel.updateNote(holder.mItem);
            }
        });
        holder.imageViewSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aca deberia actualizar nota !!!

                holder.mItem.setTitle(holder.editTextTitle.getText().toString());
                holder.mItem.setContent(holder.editTextContent.getText().toString());

                viewModel.updateNote(holder.mItem);
            }
        });
        // !!! ESTOY VIENDO DEL TEMA DE COMO LLAMAR AL PERMISSION DESDE ACA EL RECYCLER PORQUE SE SUELE HACER DESDE ACTIVITY
        // !!! ESTO EN REALIDAD PRIMERO LO DEBERIA HACER DESDE EL NewNOteDialogFragment al agregar nueva foto en la nota
        // https://stackoverflow.com/questions/34410389/how-to-get-permission-result-callback-in-dialogfragment/34410596
        // !!! luego ver como hacer para editar la imagen desde aca el adapter
     /*   holder.imageNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if (ContextCompat.checkSelfPermission(
                        ctx, Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(
                            NotesAdapter.this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION
                    );
                } else {
                    selectImage();
                }
            }
        });*/
        holder.imageViewDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aca deberia elimina la nota !!!

                viewModel.deleteNoteById(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNewNotes(List<NoteEntity> newNotes) {
        this.mValues = newNotes;
        //Notifica a los observadores adjuntos que los datos subyacentes se han modificado y que cualquier Vista que refleje el conjunto de datos debe actualizarse.
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText editTextTitle;
        public final EditText editTextContent;
        public final ImageView imageViewFavorite;
        public final ImageView imageViewSaveNote;
        public final ImageView imageViewDeleteNote;
        public final ImageView imageNote;
        public NoteEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            editTextTitle = (EditText) view.findViewById(R.id.editTextTitle);
            editTextContent = (EditText) view.findViewById(R.id.editTextContent);
            imageViewFavorite = (ImageView) view.findViewById(R.id.imageViewFavorite);
            imageViewSaveNote = (ImageView) view.findViewById(R.id.imageViewSaveNote);
            imageViewDeleteNote = (ImageView) view.findViewById(R.id.imageViewDeleteNote);
            imageNote = (ImageView) view.findViewById(R.id.imageNotes);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + editTextContent.getText() + "'";
        }
    }

}