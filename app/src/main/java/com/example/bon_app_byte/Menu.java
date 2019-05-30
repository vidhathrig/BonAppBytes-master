package com.example.bon_app_byte;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import  android.widget.TextView;


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
        List <Dish> menu = new ArrayList<>();
        TableLayout table;
        TextView ed;
        int i=1;

        public class Dish {

            private String Name;
            private String Price;


            Dish(String name, String price) {
                Name = name;
                Price = price;
            }
            Dish()
            {

            }

            public String getName() {
                return Name;
            }

            public String getPrice(){
                return Price;
            }
        }

    public void checkAddition(Dish d,Context context)
    {
        for(Dish dish:menu){
            if(dish.getName().contains(d.getName())){
                return;
            }
        }


       menu.add(d);
      /*  TableRow row= new TableRow(context);
        //TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        //row.setLayoutParams(lp);
        TextView idx = new TextView(context);
        idx.setText(i);
        row.addView(idx,0);

       /* TextView n = new TextView(context);
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
      // table = (TableLayout)findViewById(R.id.Tablelayout);
        menuDatabase = FirebaseDatabase.getInstance();
       myRef = menuDatabase.getReference().child("dish");

            Query queryRef = myRef.orderByChild("key").equalTo("Dosa");


            ed = (TextView) findViewById(R.id.name);






        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String name = ds.getKey();
                    String price =ds.getValue(String.class);
                    Dish d = new Dish(name,price);
                   // Log.i(d.getName());
                    //ed.setText(d.getName());
                    checkAddition(d,context);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
