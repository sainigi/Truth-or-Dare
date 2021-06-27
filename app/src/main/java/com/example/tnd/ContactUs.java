package com.example.tnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {

    private TextView mportfolio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        mportfolio = findViewById(R.id.mportfolio);
        mportfolio.setMovementMethod(LinkMovementMethod.getInstance());
//        mportfolio.setText(Html.fromHtml(getResources().getString(R.string.)));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ContactUs.this,MainActivity.class));
    }
}