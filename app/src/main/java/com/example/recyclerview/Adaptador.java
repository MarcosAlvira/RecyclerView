package com.example.recyclerview;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter implements View.OnLongClickListener, View.OnClickListener, View.OnTouchListener {
    Context context;
    Holder holder;
    View.OnLongClickListener longListener;
    View.OnClickListener listener;
    View.OnTouchListener listenerTouch;
    onImagenClickListener listenerBtImagen;

    private SparseBooleanArray selectedItems;

    public Adaptador(){
        selectedItems = new SparseBooleanArray();
    }
    boolean isSelected(int position){
        return getSelectedItems().contains(position);
    }
    boolean toggleSelection(int position){
        boolean seleccionado;
        if (selectedItems.get(position, false))
        {
            selectedItems.delete(position);
            seleccionado=false;
        } else {
            selectedItems.put(position, true);
            seleccionado=true;
        }
        notifyItemChanged(position);
        return  seleccionado;
    }

    void clearSelection() {
        List<Integer> selection = getSelectedItems();
        selectedItems.clear();
        for (Integer i : selection) {
            notifyItemChanged(i);
        }
    }

    int getSelectedItemCount() {
        return selectedItems.size();
    }

    List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); ++i) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public Adaptador(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler,parent,false);
        holder = new Holder(view, this.context);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        view.setOnTouchListener(this);
        holder.setClickBtImagen(new onImagenClickListener() {
            @Override
            public void onImagenClick(Usuario usuario) {
                listenerBtImagen.onImagenClick(usuario);
            }
        });
        return holder;
    }

    public void setClickBtImagen(onImagenClickListener listener)
    {
        if(listener!=null) listenerBtImagen=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((Holder)holder).bind(((MainActivity)context).datos[position], position);
    }

    @Override
    public int getItemCount() {
        return ((MainActivity)context).datos.length;
    }

    public void setOnClickListener(View.OnClickListener listener){
        if(listener!=null) this.listener=listener;
    }

    public void setOnLongClickListener(View.OnLongClickListener listener){
        if(listener!=null) this.longListener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null) listener.onClick(view);
    }

    @Override
    public boolean onLongClick(View view) {
        if(longListener!=null) longListener.onLongClick(view);
        return true;
    }

    public void setOnTouch(View.OnTouchListener listener) {this.listenerTouch = listener;}
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(listenerTouch != null){
            listenerTouch.onTouch(view, motionEvent);
        }
        return false;
    }

    public void eliminarItemsSeleccionados(List<Integer> items){
        Collections.sort(items);
        Collections.reverse(items);
        ArrayList<Usuario> aux =
                new ArrayList(Arrays.asList(((MainActivity)context).datos));
        for (int i : items) aux.remove(i);
        ((MainActivity)context).datos = new Usuario[aux.size()];
        ((MainActivity)context).datos = aux.toArray(((MainActivity)context).datos);
        for (int i :items)notifyItemRemoved(i);
    }

    public void desactivarSeleccion(){
        for(Usuario i : ((MainActivity)context).datos) i.setSeleccionado(false);
    }
}
