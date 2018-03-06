package com.example.jaime.keeper;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jaime.keeper.NotaFragment.OnListFragmentInteractionListener;
import com.example.jaime.keeper.model.Nota;

import java.util.List;

public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private final List<Nota> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyNotaRecyclerViewAdapter(List<Nota> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
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
        public final EditText titulo;
        public final EditText descripcion;
        public final TextView categoria;
        public Nota mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            titulo = (EditText) view.findViewById(R.id.editTextTitulo);
            descripcion = (EditText) view.findViewById(R.id.editTextDescripcion);
            categoria = (TextView) view.findViewById(R.id.textViewCategoriaReal);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO aqui se hace algo cuando se pincha en la nota
                    Intent i = new Intent();
                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + titulo.getText() + "'" + descripcion.getText() + "'" + categoria.getText();
        }
    }
}
