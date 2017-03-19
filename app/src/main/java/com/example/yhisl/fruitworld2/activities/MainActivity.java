package com.example.yhisl.fruitworld2.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
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
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fruits = this.getAllFruits();
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mLayoutManager =  new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new MyAdapter(fruits, R.layout.recycler_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Fruit fruits, int position) {
                MoreFruit(position);
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
            add(new Fruit("Apple","Apple description",R.drawable.apple_ic,R.drawable.apple_bg));
            add(new Fruit("Orange","Orange description",R.drawable.orange_ic,R.drawable.orange_bg));
            add(new Fruit("Banana","Banana description",R.drawable.banana_ic,R.drawable.banana_bg));
            add(new Fruit("Cherry","Cherry description",R.drawable.cherry_ic,R.drawable.cherry_bg));
            add(new Fruit("Pear","Pear description",R.drawable.pear_ic,R.drawable.pear_bg));
            add(new Fruit("Plum","Plum description",R.drawable.plum_ic,R.drawable.plum_bg));
            add(new Fruit("Raspberry","Raspberry description",R.drawable.raspberry_ic,R.drawable.raspberry_bg));
            add(new Fruit("Strawberry","Strawberry description",R.drawable.strawberry_ic,R.drawable.strawberry_bg));
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
                this.addFruit(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderIcon(this.fruits.get(info.position).getIcon());
        menu.setHeaderTitle(this.fruits.get(info.position).getName());
        inflater.inflate(R.menu.menu_long,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_option:
                this.RemovedFruit(info.position);
                return true;
            case R.id.reset_option:
                this.ResetFruit(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void ResetFruit(int position) {

    }

    public void addFruit(int position){
        fruits.add(position,new Fruit("New Fruit "+(++counter),"fruit added by user",R.drawable.plum_bg,R.drawable.plum_ic));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    public void RemovedFruit(int position){
        fruits.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void MoreFruit(int position){

    }
}
