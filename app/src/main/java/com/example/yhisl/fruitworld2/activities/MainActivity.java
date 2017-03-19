package com.example.yhisl.fruitworld2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.yhisl.fruitworld2.models.Fruit;
import com.example.yhisl.fruitworld2.adapter.MyAdapter;
import com.example.yhisl.fruitworld2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruits;

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = this.getAllFruits();
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mLayoutManager =  new LinearLayoutManager(this);
        mAdapter = new MyAdapter(fruits, R.layout.recycler_item, this, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruits, int position) {
               fruits.addQuantity(1);
                mAdapter.notifyItemChanged(position);
            }
        });

        //cuando sabemos que la cantidad de datos no variará
        mRecyclerView.setHasFixedSize(true);
        //para hacer una pequeña animación
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public List<Fruit> getAllFruits(){
        List<Fruit> lista = new ArrayList<Fruit>(){{
            add(new Fruit("Apple","Apple description",R.mipmap.ic_apple,R.drawable.apple_bg,0));
            add(new Fruit("Orange","Orange description",R.mipmap.ic_orange,R.drawable.orange_bg,0));
            add(new Fruit("Banana","Banana description",R.mipmap.ic_banana,R.drawable.banana_bg,0));
            add(new Fruit("Cherry","Cherry description",R.mipmap.ic_cherry,R.drawable.cherry_bg,0));
            add(new Fruit("Pear","Pear description",R.mipmap.ic_pear,R.drawable.pear_bg,0));
            add(new Fruit("Plum","Plum description",R.mipmap.ic_plum,R.drawable.plum_bg,0));
            add(new Fruit("Raspberry","Raspberry description",R.mipmap.ic_raspberry,R.drawable.raspberry_bg,0));
            add(new Fruit("Strawberry","Strawberry description",R.mipmap.ic_strawberry,R.drawable.strawberry_bg,0));
        }};
        return lista;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_fruit:
                int position = fruits.size();
                this.addFruit(position);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addFruit(int position){
        fruits.add(position,new Fruit("New Fruit "+(++counter),"fruit added by user",R.mipmap.ic_plum,R.drawable.plum_bg,0));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

}
