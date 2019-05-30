package com.example.bon_app_byte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Order_Queue extends AppCompatActivity {

    FirebaseDatabase menuDatabase;
    DatabaseReference myRef;



    public class Order
    {

    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__queue);
    }
}
