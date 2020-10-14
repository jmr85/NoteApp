package com.appjruiz.s05_responsive_design;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Note}.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private final List<Note> mValues;
    private final NotesInteractionListener mListener;

    public MyNoteRecyclerViewAdapter(List<Note> items, NotesInteractionListener mListener) {
        this.mValues = items;
        this.mListener = mListener;
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
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.favoriteNoteClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView textViewTitle;
        public final TextView textViewContent;
        public final ImageView imageViewFavorite;
        public Note mItem;

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