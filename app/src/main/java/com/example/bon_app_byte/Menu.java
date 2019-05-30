package com.example.bon_app_byte;

import android.app.ActionBar;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import  android.widget.TextView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Menu extends AppCompatActivity {

        FirebaseDatabase menuDatabase;
        DatabaseReference myRef;
        List <Dishes> menu = new ArrayList<>();
        TableLayout table;
        TextView ed;
        int i=1;

        public class Dishes {
            public Dishes(boolean available, int price, String name) {
                this.available = available;
                this.price = price;
                this.name = name;
            }

            private boolean available;
            private int price;
            private String name;

            public Dishes(){
            }


            public void setName(String name) {
                this.name = name;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public void setAvailable(boolean available) {
                this.available = available;
            }



            public String getName() {
                return name;
            }

            public int getPrice(){
                return price;
            }

            public boolean getAvailable(){return available;}
        }

    public TableRow getTableRowHeading(){
            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.span = 4;
            TextView tableHeading = new TextView(this);
            tableHeading.setText("Menu");
            tableHeading.setTextSize(20);
            tableHeading.setGravity(Gravity.CENTER);
            tableRow.addView(tableHeading,layoutParams);
            return tableRow;
    }

    public TableRow getTableRow(Dishes d)
    {
        TableRow tableRow = new TableRow(this);
        TextView name = new TextView(this);
        name.setText(d.getName());
        name.setTextSize(18);
        tableRow.addView(name);

        TextView price = new TextView(this);
        price.setText(d.getName());
        price.setTextSize(18);
        tableRow.addView(price);

        CheckBox avail = new CheckBox(this);
        avail.setActivated(true);
        tableRow.addView(avail);

        return tableRow;

    }

    public void checkAddition(Dishes d,Context context)
    {
        for(Dishes dish:menu){
            if(dish.getName().contains(d.getName())){
                return;
            }
        }


       menu.add(d);
     /* TableRow row= new TableRow(context);
        //TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        //row.setLayoutParams(lp);
        TextView idx = new TextView(context);
        idx.setText(i);
        row.addView(idx,0);

        TextView n = new TextView(context);
        n.setText(d.getName());
        row.addView(n,1);

        TextView P = new TextView(context);
        P.setText(d.getPrice());
        row.addView(P,2);

        CheckBox checkBox = new CheckBox(context);
        row.addView(checkBox, 3);


        table.addView(row);
        setContentView(table);
    */
    }

        @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
       final Context context = getApplicationContext();
       table = findViewById(R.id.Tablelayout);
       TableRow tableHeading = getTableRowHeading();
       table.addView(tableHeading);
        menuDatabase = FirebaseDatabase.getInstance();
       myRef = menuDatabase.getReference().child("Dish");
       RelativeLayout relativeLayout = new RelativeLayout(this);





      /*      myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Dish d = new Dish();
                    d =  dataSnapshot.getValue(Dish.class);
                    TableRow tableRow = getTableRow(d);
                    table.addView(tableRow);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.i("MyActivity","ERROR!!");

                }
            });*/
            RelativeLayout.LayoutParams centerTableParameters = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            centerTableParameters.addRule(RelativeLayout.CENTER_IN_PARENT);
            ScrollView scroll = findViewById(R.id.scroll);
            scroll.removeView(table);

            relativeLayout.addView(table,centerTableParameters);
           setContentView(relativeLayout);

            myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot ds : dataSnapshot.getChildren()){

                   Dishes d = ds.getValue(Dishes.class);
                    //ed.setText(d.getName());
                    checkAddition(d,context);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ;
    }
}
