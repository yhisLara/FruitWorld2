package com.example.yhisl.fruitworld2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private OnItemClickListener itemClickListener;

    private Context context;

    public MyAdapter(List<Fruit> fruits, int layout, OnItemClickListener itemClickListener){
        this.fruits = fruits;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.bind(fruits.get(position),itemClickListener);
    }

    @Override
    public int getItemCount(){return fruits.size();}


    public class ViewHolder extends RecyclerView.ViewHolder{
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
        }

        public void bind(final Fruit fruits, final OnItemClickListener listener){
            //procesar datos o renderizar
            TextName.setText(fruits.getName());
            //cambia tamaño de las imagenes para que ocupe el tamaño completo
            Picasso.with(context).load(fruits.getImageBack()).fit().into(imageViewPoster);
            imageViewPoster.setImageResource(fruits.getImageBack());

            //el itemView sale del parametro de la clase viewholder declarada mas arriba
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(fruits,getAdapterPosition());
                }
            });
        }

    }

    public interface OnItemClickListener{
        void onItemClick(Fruit fruits, int position);
    }
}
