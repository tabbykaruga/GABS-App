package com.example.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PrivateFragment extends Fragment {

    private Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6;
    private TextView tv_takataka,tv_flash,tv_nesco,tv_bins,tv_zoataka,tv_garbage;
    private Spinner sp_takataka,sp_nesco,sp_zoataka,sp_bins,sp_garbage,sp_flash;


    private FirebaseDatabase db;
    private DatabaseReference myRef;
    NotificationHelperClass helperClass;
    UserDetailsHelperClass helperClass2;

    int i=0;


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_private, container, false);

        db=FirebaseDatabase.getInstance();
        myRef=db.getReference("Notifications");

        helperClass=new NotificationHelperClass();
        helperClass2=new UserDetailsHelperClass();

        btn_1=view.findViewById(R.id.btn_takataka);
        btn_2=view.findViewById(R.id.btn_zoataka);
        btn_3=view.findViewById(R.id.btn_nescokenya);
        btn_4=view.findViewById(R.id.btn_bins);
        btn_5=view.findViewById(R.id.btn_garbagecom);
        btn_6=view.findViewById(R.id.btn_flashservices);

        tv_takataka=view.findViewById(R.id.tv_takataka);
        tv_garbage=view.findViewById(R.id.tv_garbage_com);
        tv_flash=view.findViewById(R.id.tv_flash);
        tv_bins=view.findViewById(R.id.tv_bins);
        tv_nesco=view.findViewById(R.id.tv_nesco);
        tv_zoataka=view.findViewById(R.id.tv_zoataka);

        sp_takataka=view.findViewById(R.id.takataka_hours);
        sp_bins=view.findViewById(R.id.binsservices_hours);
        sp_flash=view.findViewById(R.id.flashservices_hours);
        sp_nesco=view.findViewById(R.id.nescoservices_hours);
        sp_zoataka=view.findViewById(R.id.zoataka_hours);
        sp_garbage=view.findViewById(R.id.garbagecom_hours);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    i=(int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "ERROR sending notification", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                String hours;

                helperClass.setServiceProvider(tv_takataka.getText().toString());
                helperClass.setTime(sp_takataka.getSelectedItem().toString());

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    String uid=user.getUid();
                    helperClass.setClient(uid);
                }else{
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Kindly Sing in", Toast.LENGTH_SHORT);
                    toast.show();
                }
                myRef.child(String.valueOf(i++)).setValue(helperClass);

                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helperClass.setServiceProvider(tv_zoataka.getText().toString());
                helperClass.setTime(sp_zoataka.getSelectedItem().toString());

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    String uid=user.getUid();
                    helperClass.setClient(uid);
                }else{
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Kindly Sing in", Toast.LENGTH_SHORT);
                    toast.show();
                }

                myRef.child(String.valueOf(i++)).setValue(helperClass);
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helperClass.setServiceProvider(tv_nesco.getText().toString());
                helperClass.setTime(sp_nesco.getSelectedItem().toString());

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    String uid=user.getUid();
                    helperClass.setClient(uid);
                }else{
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Kindly Sing in", Toast.LENGTH_SHORT);
                    toast.show();
                }

                myRef.child(String.valueOf(i++)).setValue(helperClass);
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helperClass.setServiceProvider(tv_bins.getText().toString());
                helperClass.setTime(sp_bins.getSelectedItem().toString());
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    String uid=user.getUid();
                    helperClass.setClient(uid);
                }else{
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Kindly Sing in", Toast.LENGTH_SHORT);
                    toast.show();
                }
                myRef.child(String.valueOf(i++)).setValue(helperClass);
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helperClass.setServiceProvider(tv_garbage.getText().toString());
                helperClass.setTime(sp_garbage.getSelectedItem().toString());

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    String uid=user.getUid();
                    helperClass.setClient(uid);
                }else{
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Kindly Sing in", Toast.LENGTH_SHORT);
                    toast.show();
                }

                myRef.child(String.valueOf(i++)).setValue(helperClass);
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                helperClass.setServiceProvider(tv_flash.getText().toString());
                helperClass.setTime(sp_flash.getSelectedItem().toString());

                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    String uid=user.getUid();
                    helperClass.setClient(uid);
                }else{
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Kindly Sing in", Toast.LENGTH_SHORT);
                    toast.show();
                }

                myRef.child(String.valueOf(i++)).setValue(helperClass);
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Notification Sent", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        return view;

    }
}