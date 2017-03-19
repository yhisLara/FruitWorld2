package com.example.yhisl.fruitworld2.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yhisl.fruitworld2.R;
import com.example.yhisl.fruitworld2.models.Fruit;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by yhisl on 17/03/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Fruit> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener itemClickListener;


    public MyAdapter(List<Fruit> fruits, int layout,Activity activity, OnItemClickListener itemClickListener){
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.bind(fruits.get(position),itemClickListener);
    }

    @Override
    public int getItemCount(){return fruits.size();}


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener , MenuItem.OnMenuItemClickListener{
        public TextView TextName;
        public TextView TextDescription;
        public TextView TextCounter;
        public ImageView imageViewPoster;

        //constructor
        public ViewHolder(View itemView) {
            super(itemView);
            TextName = (TextView) itemView.findViewById(R.id.TextName);
            TextDescription = (TextView) itemView.findViewById(R.id.TextDescription);
            TextCounter = (TextView) itemView.findViewById(R.id.TextCounter);
            imageViewPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            //añadimos al view el listener para el context menu, en vez de hacerlo en
            //el activity mediante el metodo registerForContextMenu
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Fruit fruits, final OnItemClickListener listener){
            //procesar datos o renderizar
            TextName.setText(fruits.getName());
            TextDescription.setText(fruits.getDescription());
            //casteando el numero a String
            TextCounter.setText(fruits.getCounter() + "");
            //logica aplicada para la limitacion de la cantidad  en cada elemento fruta
            if(fruits.getCounter() == fruits.MAX_CANTIDAD){
                TextCounter.setTextColor(ContextCompat.getColor(activity,R.color.colorAlert));
                TextCounter.setTypeface(null, Typeface.BOLD);
            }else{
                TextCounter.setTextColor(ContextCompat.getColor(activity,R.color.defaultTextColor));
                TextCounter.setTypeface(null, Typeface.NORMAL);
            }
            //cambia tamaño de las imagenes para que ocupe el tamaño completo
            Picasso.with(activity).load(fruits.getImageBack()).fit().into(imageViewPoster);

            //añadirmos el evento click solo paa cuando pinchen las imagenes
            this.imageViewPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruits,getAdapterPosition());
                }
            });
        }

        //Sobreescribimos el OncreateContextMenu, dentro del ViewHolder, en vez de hacerlo en el activity


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //recogemos la posicion con el metodo getAdapterPosition
            Fruit fruitSelected = fruits.get(this.getAdapterPosition());
            // se establecen las propiedades para el menu
            menu.setHeaderTitle(fruitSelected.getName());
            menu.setHeaderIcon(fruitSelected.getIcon());
            //se infla el menu
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.menu_long,menu);
            //por ultimo, añadimos uno por uno, el listener onMenuItemClick para
            //controlar las acciones  en el contextMenu, anteriormente lo manejabamos
            //con el metodo onContextItemSelected en el activity
            //le agrega a cada opcion del menu el onMenuItemClick
            for(int i=0; i < menu.size(); i++)
                menu.getItem(i).setOnMenuItemClickListener(this);
        }

        //Sobreeescribimos onMenuItemClick, dentro del ViewHolder,
        //en vez de hacerlo en el activity bajo el nombre onContextItemSelected


        @Override
        public boolean onMenuItemClick(MenuItem item) {
            //no obtenemos nuestro objeto info
            //porque la posicion la podemos rescatar desde getAdapterPosition
            switch(item.getItemId()){
                case R.id.delete_option:
                    //observa que como estamos dentro del adaptador,podemos acceder
                    //a los metodos  propios de él como notifyItemRemoved o notifyItemChanged
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;
                case R.id.reset_option:
                    fruits.get(getAdapterPosition()).ResetCounter();
                    notifyItemChanged(getAdapterPosition());
                    return true;
                default:
                    return false;
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Fruit fruits, int position);
    }
}
