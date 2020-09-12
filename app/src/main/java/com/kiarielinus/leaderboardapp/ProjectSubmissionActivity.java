package com.kiarielinus.leaderboardapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ProjectSubmissionActivity extends AppCompatActivity {

    EditText firstName,lastName,emailAddress,githubLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_submission);

        //Make status bar transparent
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Link Android XML to java class
        Button submitProject;
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        emailAddress = findViewById(R.id.email_address);
        githubLink = findViewById(R.id.github_link);
        submitProject = findViewById(R.id.submit_project);
        submitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Convert EditText Values to string
                String fName,lName,eMail,gitLink;
                fName =firstName.getText().toString();
                lName = lastName.getText().toString();
                eMail = emailAddress.getText().toString();
                gitLink = githubLink.getText().toString();
                //Send String data into the PopUp activity
                Intent intent = new Intent(ProjectSubmissionActivity.this,AreYouSure.class);
                intent.putExtra("fname",fName);
                intent.putExtra("lName",lName);
                intent.putExtra("eMail",eMail);
                intent.putExtra("gitLink",gitLink);
                startActivity(intent);
            }
        });


    }
}