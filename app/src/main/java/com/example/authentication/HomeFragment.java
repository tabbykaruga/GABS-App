package com.example.authentication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;

import java.util.UUID;

public class HomeFragment extends Fragment {

    private ImageView imageView;
    private Button btn_save;
    public EditText et_name,et_location,et_phone;
    private RadioButton rd_company,rd_residence;
    UserDetailsHelperClass helperClass;
    private String model="";

    public FirebaseDatabase db;
    public DatabaseReference myRef;

    int i=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        final String[] value = new String[1];
        db=FirebaseDatabase.getInstance();
        myRef=db.getReference("UserDetails");

        et_name=view.findViewById(R.id.username);
        et_location=view.findViewById(R.id.Location);
        et_phone=view.findViewById(R.id.phone);

        helperClass=new UserDetailsHelperClass();

        rd_company=view.findViewById(R.id.radio_company);
        rd_residence=view.findViewById(R.id.radio_residence);
        btn_save=view.findViewById(R.id.btn_save);
        imageView=view.findViewById(R.id.imageview);

        AnimationDrawable animationDrawable =(AnimationDrawable) imageView.getDrawable();
        animationDrawable.start();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    i=(int) dataSnapshot.getChildrenCount();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "ERROR saving data", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String company= rd_company.getText().toString();
                String residence=rd_residence.getText().toString();
                String phone=et_phone.getText().toString();
                String name=et_name.getText().toString();
                String location=et_location.getText().toString();

                if (TextUtils.isEmpty(name)){
                    et_name.setError("Name is required.");
                    et_name.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    et_phone.setError("Phone is required.");
                    et_phone.requestFocus();
                    return;
                }
                if (phone.length()>10){
                    et_phone.setError("Phone number should be less than 10 digits.");
                    et_phone.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(location)){
                    et_location.setError("Location is required.");
                    et_location.requestFocus();
                    return;
                }

                helperClass.setName(et_name.getText().toString());
                helperClass.setLocation(et_location.getText().toString());
                helperClass.setPhone(et_phone.getText().toString());

                if (rd_company.isChecked()){
                    helperClass.setGroup(company);
                }else {
                    helperClass.setGroup(residence);
                }
                //myRef.child(String.valueOf(phone)).setValue(helperClass);

                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null){
                    String uid=user.getUid();
                    myRef.child(uid).setValue(helperClass);
                }else{
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Kindly Sing in", Toast.LENGTH_SHORT);
                    toast.show();
                }
                 Toast toast = Toast.makeText(getActivity().getApplicationContext(), "SAVED", Toast.LENGTH_SHORT);
                toast.show();

                //toast.makeText(getContext(), value[0], Toast.LENGTH_LONG).show();

            }
        });



        return  view;
  }

}
