package com.kiarielinus.leaderboardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.kiarielinus.leaderboardapp.network.SubmitForms;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AreYouSure extends Activity {

    private static final String BASE_URL = "https://docs.google.com/forms/d/e/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.are_you_sure);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .6));

        //Get String extras from intent
        String fName, lName, eMail, gitLink;
        Intent intent = getIntent();
        fName = intent.getStringExtra("fName");
        lName = intent.getStringExtra("lName");
        eMail = intent.getStringExtra("eMail");
        gitLink = intent.getStringExtra("gitLink");

        //Initialize Retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //Start The SubmitWebservice
        final SubmitForms submitForms = retrofit.create(SubmitForms.class);
        Button submit = findViewById(R.id.submit_project);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Void> submitProject = submitForms.submitProject(fName, lName, gitLink, eMail);
                submitProject.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.isSuccessful()) {
                            startActivity(new Intent(AreYouSure.this, SubmissionSuccessful.class));
                        } else {
                            startActivity(new Intent(AreYouSure.this, SubmissionFailed.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        startActivity(new Intent(AreYouSure.this, SubmissionFailed.class));
                    }
                });
            }
        });

        ImageView cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}