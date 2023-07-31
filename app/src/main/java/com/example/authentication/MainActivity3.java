package com.example.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


    }
}
    public static int MathChallenge(int num) {
        // code goes here
        if (num <= 1){
            System.out.print("false");
        }

        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0){
                System.out.print("false");
                break;
            }else{
                System.out.print("true");
                break;
            }
        }
            return num;
    }
}