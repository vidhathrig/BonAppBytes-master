package com.example.bon_app_byte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Edit_Menu extends AppCompatActivity {


    EditText Name;
    EditText Price;
    Button Add;
    DatabaseReference rootRef,MenuRef;
    public class dish
    {
        private String name;
        private int price;
        private boolean available;

        dish()
        {
        }
        dish(String name,int price,boolean available)
        {
            this.name = name;
            this.price = price;
            this.available = available;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public boolean getAvailable(){return  available;}
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__menu);
        Name = findViewById(R.id.name);
        Price = findViewById(R.id.price);
        Add = findViewById(R.id.add);
        rootRef = FirebaseDatabase.getInstance().getReference();
        MenuRef = rootRef.child("Dish");

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                name = name.replace(' ','-');
                name = name.toLowerCase();
                int price =Integer.parseInt(Price.getText().toString()) ;
                dish d = new dish(name,price,true);

                MenuRef.child(d.getName()).setValue(d);
                Toast.makeText(getBaseContext(), "Dish added successfully!", Toast.LENGTH_LONG).show();
                Name.setText("");
                Price.setText("");
                //Intent intent = new Intent(Edit_Menu.this,MainActivity.class);
               // startActivity(intent);

            }
        });


    }
}
