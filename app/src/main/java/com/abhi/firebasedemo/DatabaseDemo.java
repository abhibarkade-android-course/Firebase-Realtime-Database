package com.abhi.firebasedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DatabaseDemo extends AppCompatActivity {

    EditText unm, fnm, prof;

    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_demo);

        unm = findViewById(R.id.ed_unm);
        fnm = findViewById(R.id.ed_fnm);
        prof = findViewById(R.id.ed_prof);

        db = FirebaseDatabase.getInstance("https://fir-demo-e6510-default-rtdb.firebaseio.com/").getReference();
    }

    public void addUser(View view) {
        if (unm.getText().toString().length() > 0
                && fnm.getText().toString().length() > 0
                && prof.getText().toString().length() > 0) {

            CustomUserData userData = new CustomUserData(
                    unm.getText().toString(),
                    fnm.getText().toString(),
                    prof.getText().toString()
            );

            db.child("UsersData")
                    .child(unm.getText().toString())
                    .setValue(userData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(DatabaseDemo.this, "User Added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(DatabaseDemo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else
            Toast.makeText(this, "Invalid Data", Toast.LENGTH_SHORT).show();
    }

    public void getUserData(View view) {
        try {
            if(unm.getText().toString().length()>0){
                db.child("UsersData")
                        .child(unm.getText().toString())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                CustomUserData userData=snapshot.getValue(CustomUserData.class);
                                Toast.makeText(DatabaseDemo.this, userData.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(DatabaseDemo.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }else
                Toast.makeText(this, "Invalid Data", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}














