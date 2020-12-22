package com.example.esport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    Button resendCode;
    TextView fullName,email,phone,verifyMsg;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone=findViewById(R.id.profilePhone);
        fullName=findViewById(R.id.profileName);
        email=findViewById(R.id.profileEmail);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userId=fAuth.getCurrentUser().getUid();
        final DocumentReference documentReference=fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                phone.setText(documentSnapshot.getString("phone"));
                fullName.setText(documentSnapshot.getString("fName"));
                email.setText(documentSnapshot.getString("email"));
            }
        });
        resendCode=findViewById(R.id.resendCode);
        verifyMsg=findViewById(R.id.verifyMsg);

        final FirebaseUser user=fAuth.getCurrentUser();
        if(!user.isEmailVerified())
        {
           verifyMsg.setVisibility(View.VISIBLE);
           resendCode.setVisibility(View.VISIBLE);

           resendCode.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(final View v) {
                   FirebaseUser fuser=fAuth.getCurrentUser();
                   user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void aVoid) {
                           Toast.makeText(v.getContext(),"Verification Email Has been Sent Successfully",Toast.LENGTH_SHORT).show();
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Log.d("tag","onFailure: Email not sent"+e.getMessage());
                       }
                   });
               }
           });
        }

        Button foot_ball = (Button) findViewById(R.id.football);
        foot_ball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), FootballDeatilActivity.class);
                view.getContext().startActivity(intent);}
        });

        Button cricket = (Button) findViewById(R.id.cricket);
        cricket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CricketDetailActivity.class);
                view.getContext().startActivity(intent);}
        });
    }

    public void logout(View view) {

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Login.class));
          //  finish();
    }
}