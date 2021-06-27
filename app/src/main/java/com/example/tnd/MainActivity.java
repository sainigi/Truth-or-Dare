package com.example.tnd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView bottle;
    private Button spinButton;
    private Random random = new Random();
    private int lastDirection;
    private Drawable img;
    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottle = findViewById(R.id.bottle);
        spinButton = findViewById(R.id.spinbutton);
        img = getApplicationContext().getDrawable(R.drawable.disable_button);



        spinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int newDirection = random.nextInt(3600);
                float pivotX = bottle.getWidth()/2;
                float pivotY = bottle.getHeight()/2;

                Animation rotate = new RotateAnimation(lastDirection,newDirection,pivotX,pivotY);
                rotate.setDuration(2000);
                rotate.setFillAfter(true);

                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        spinButton.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        spinButton.setEnabled(true);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                lastDirection = newDirection;

                bottle.startAnimation(rotate);

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishAffinity();
            super.onBackPressed();
            return;
        }
        else{
            Toast.makeText(this,"Press Back again to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.three_dots,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.threeDotHome){
//            startActivity(new Intent(MainActivity.this,MainActivity.class));
            Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
        }

        if(id==R.id.threeDotAbout){
            startActivity(new Intent(MainActivity.this,AboutActivity.class));
//            Toast.makeText(this,"About Clicked",Toast.LENGTH_SHORT).show();
        }

        if(id==R.id.threeDotContactUs){
            startActivity(new Intent(MainActivity.this,ContactUs.class));
//            Toast.makeText(this,"Contact Us Clicked",Toast.LENGTH_SHORT).show();
        }

        if(id==R.id.threeDotShare){

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "https://sainigi.github.io/mPortfolio/";
            String shareSubject = "Subject";

            sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);

            startActivity(Intent.createChooser(sharingIntent,"Share Using"));

//            startActivity(new Intent(MainActivity.this,ShareActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}