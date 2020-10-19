package com.jmruiz.NoteApp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jmruiz.NoteApp.db.entity.NoteEntity;
import com.jmruiz.NoteApp.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link NoteEntity}.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private List<NoteEntity> mValues;
    private Context ctx;

    public MyNoteRecyclerViewAdapter(List<NoteEntity> items, Context ctx) {
        this.mValues = items;
        this.ctx = ctx;
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
        holder.textViewTitle.setText(holder.mItem.getTitle());
        holder.textViewContent.setText(holder.mItem.getContent());
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);
        if(holder.mItem.isFavorite()) {
            holder.imageViewFavorite.setImageResource(R.drawable.ic_baseline_star_24);
        }
        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        public final TextView textViewTitle;
        public final TextView textViewContent;
        public final ImageView imageViewFavorite;
        public NoteEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            textViewTitle = (TextView) view.findViewById(R.id.textViewTitle);
            textViewContent = (TextView) view.findViewById(R.id.textViewContent);
            imageViewFavorite = (ImageView) view.findViewById(R.id.imageViewFavorite);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewContent.getText() + "'";
        }
    }
}