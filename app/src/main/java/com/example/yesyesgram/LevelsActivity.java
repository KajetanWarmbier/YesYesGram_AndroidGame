package com.example.yesyesgram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class LevelsActivity extends AppCompatActivity {

    Button backBtn;

    String pictureOne = "pictureOne";
    int[][] picture_1 = {
            {1,2,1,2,1},
            {1,1,1,2,1},
            {1,1,1,1,1},
            {1,1,1,1,1},
            {1,2,1,2,1},
    };

    String pictureTwo = "pictureTwo";
    int[][] picture_2 = {
            {1,2,2,2,1},
            {1,1,1,1,1},
            {2,1,1,1,2},
            {1,1,1,1,1},
            {2,1,2,1,2}
    };

    String pictureThree = "pictureThree";
    int[][] picture_3 = {
            {1,1,1,1,1},
            {2,1,1,1,2},
            {1,1,1,1,1},
            {1,1,1,1,1},
            {2,1,2,1,2}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        backBtn = (Button)findViewById(R.id.backBtn);
        backBtn.setOnClickListener((view) -> {
            finish();
        });

    }

    public void playLevel1(View view) {
        Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
        intent.putExtra("picture", picture_1);
        intent.putExtra("pictureNumber", pictureOne);
        startActivity(intent);
    }

    public void playLevel2(View view) {

        Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
        intent.putExtra("picture", picture_2);
        intent.putExtra("pictureNumber", pictureTwo);
        startActivity(intent);
    }

    public void playLevel3(View view) {

        Intent intent = new Intent(LevelsActivity.this, GameActivity.class);
        intent.putExtra("picture", picture_3);
        intent.putExtra("pictureNumber", pictureThree);
        startActivity(intent);
    }
}