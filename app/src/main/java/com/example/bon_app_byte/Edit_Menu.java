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
        private String price;

        dish()
        {
        }
        dish(String name,String price)
        {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getPrice() {
            return price;
        }
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
                String price = Price.getText().toString();
                dish d = new dish(name,price);

                MenuRef.child(d.getName()).setValue(d.price);
                Toast.makeText(getBaseContext(), "Dish added successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Edit_Menu.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }
}
