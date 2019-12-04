package com.example.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView txtNombre, txtApellido;
    ImageButton imgBtn;
    View.OnClickListener listener;
    onImagenClickListener listenerImagen;
    Context context;
    Usuario usuario;

    public Holder(View itemView, Context context){
        super(itemView);
        this.context = context;
        txtNombre=(TextView)itemView.findViewById(R.id.textoNombre);
        txtApellido=(TextView)itemView.findViewById(R.id.textoApellido);
        imgBtn = (ImageButton) itemView.findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(this);
    }

    public void bind(Usuario usuario, int pos){
        if(pos%2==0) itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        else itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));

        if (usuario.getNombre().contains("s")) imgBtn.setVisibility(View.VISIBLE);
        else imgBtn.setVisibility(View.INVISIBLE);
        txtNombre.setText(usuario.getNombre());
        txtApellido.setText(usuario.getApellidos());
        this.usuario = usuario;
    }

    public void setClickBtImagen(onImagenClickListener listener){
        if(listener!=null) this.listenerImagen=listener;
    }

    @Override
    public void onClick(View view) {
        if(listenerImagen!=null) listenerImagen.onImagenClick(usuario);
    }
}
