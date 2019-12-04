package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Usuario[] datos = new Usuario[] {new Usuario("Marcos","Alvira Romero", "marcosalvira@iesdoctorbalmis.com", false),
            new Usuario("Mario","Martínez Sánchez", "mariomartinez@iesdoctorbalmis.com", false),
            new Usuario("Aitor","Soto Troll", "aitroll@iesdoctorbalmis.com", false)
    ,new Usuario("Cristiano","Ronaldo Dos Santos", "thebest@iesdoctorbalmis.com",false)};

    RecyclerView recycler;
    Adaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler=(RecyclerView)findViewById(R.id.recycler);
        adaptador=new Adaptador(this);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recycler.getChildAdapterPosition(v);
                Toast.makeText(MainActivity.this, datos[pos].getCorreo(), Toast.LENGTH_SHORT).show();
            }
        });
        adaptador.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = recycler.getChildAdapterPosition(v);
                Toast.makeText(MainActivity.this, datos[pos].getApellidos(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        adaptador.setClickBtImagen(new onImagenClickListener() {
            @Override
            public void onImagenClick(Usuario usuario) {
                Intent intento = new Intent(Intent.ACTION_SEND);
                intento.setType("text/html");
                intento.setData(Uri.parse("mailto:"));
                intento.putExtra(Intent.EXTRA_EMAIL, usuario.getCorreo());
                startActivity(Intent.createChooser(intento, "E-mail"));
                Toast.makeText(MainActivity.this, "Enviado E-mail a " + usuario.getCorreo(), Toast.LENGTH_SHORT).show();
            }
        });
        recycler.setAdapter(adaptador);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    public class ActionModeCallback implements ActionMode.Callback{
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.selected_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.remove:
                    adaptador.eliminarItemsSeleccionados(adaptador.getSelectedItems());
                    actionMode.finish();
                    return true;
                    default: return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            adaptador.clearSelection();
            adaptador.desactivarSeleccion();
            actionMode = null;
        }
    }
}


