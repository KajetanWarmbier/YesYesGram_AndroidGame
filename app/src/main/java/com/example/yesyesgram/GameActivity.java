package com.example.yesyesgram;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.shape.ShapePath;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    int fieldColor = 1;
    int hearts = 3;
    ImageView pictureImg;
    Resources res;
    String packageName;
    String pictureNumber;
    AlertDialog.Builder alertLose;
    AlertDialog.Builder alertWin;

    int[][] picture;


    int[][] gameField = {
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
            {0,0,0,0,0},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        pictureImg = findViewById(R.id.catImg);
        res = getResources();
        packageName = getPackageName();

        picture = (int[][])getIntent().getSerializableExtra("picture");
        pictureNumber = getIntent().getExtras().getString("pictureNumber");

        alertLose = new AlertDialog.Builder(GameActivity.this);
        alertLose.setTitle("Przegrana");
        alertLose.setMessage("Przegrano rozgrywkę.");
        alertLose.setNeutralButton("Wyjdź", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertWin = new AlertDialog.Builder(GameActivity.this);
        alertWin.setTitle("Wygrano!");
        alertWin.setMessage("Wygrano rozgrywkę.");
        alertWin.setNeutralButton("Wyjdź", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        iterateHintY();
        iterateHintX();
    }

    public void changeColor(View view) {
        Button clickedBtn = (Button) view;


        if (fieldColor == 1) {
            String btnTag = clickedBtn.getTag().toString();
            String[] position = btnTag.split(";");
            int Y = Integer.parseInt(position[0]);
            int X = Integer.parseInt(position[1]);


            if (picture[Y][X] == 1) {
                clickedBtn.setBackgroundColor(Color.BLACK);
                clickedBtn.animate().alpha(1).setDuration(300);
                clickedBtn.setClickable(false);

                gameField[Y][X] = 1;
                checkWin();
            } else {
                clickedBtn.setBackgroundColor(Color.LTGRAY);
                clickedBtn.animate().alpha(1).setDuration(300);
                clickedBtn.setClickable(false);

                hearthCheck();
                Log.i("Hearts:", String.valueOf(hearts));

                gameField[Y][X] = 2;
                checkWin();


            }

        } else if (fieldColor == 2) {
            String btnTag = clickedBtn.getTag().toString();
            String[] position = btnTag.split(";");
            int Y = Integer.parseInt(position[0]);
            int X = Integer.parseInt(position[1]);

            if (picture[Y][X] == 2) {
                clickedBtn.setBackgroundColor(Color.LTGRAY);
                clickedBtn.animate().alpha(1).setDuration(300);
                clickedBtn.setClickable(false);

                gameField[Y][X] = 2;
                checkWin();
            } else {
                clickedBtn.setBackgroundColor(Color.BLACK);
                clickedBtn.animate().alpha(1).setDuration(300);
                clickedBtn.setClickable(false);

                hearthCheck();
                Log.i("Hearts:", String.valueOf(hearts));

                gameField[Y][X] = 1;
                checkWin();

            }
        }


    }

    public void changeFieldColor(View view) {

        Button clickedBtn = (Button) view;

        if (fieldColor == 1) {
            fieldColor = 2;
            clickedBtn.setText("GREY");
            clickedBtn.setBackgroundColor(Color.LTGRAY);
            clickedBtn.setTextColor(Color.BLACK);
        } else {
            fieldColor = 1;
            clickedBtn.setText("BLACK");
            clickedBtn.setBackgroundColor(Color.BLACK);
            clickedBtn.setTextColor(Color.WHITE);
        }

    }

    public void hearthCheck() {
        int hearthChecker = res.getIdentifier("hearth" + hearts, "id", packageName);
        ImageView hearthImg = findViewById(hearthChecker);
        hearthImg.setAlpha(0.3f);
        hearts--;

        if (hearts == 0) {
            alertLose.show();
        }

    }

    public void checkWin() {
        if (Arrays.deepEquals(gameField, picture)) {

            if (pictureNumber.equals("pictureOne")) {
                pictureImg.setBackgroundResource(R.drawable.picture_1);
            } else if (pictureNumber.equals("pictureTwo")) {
                pictureImg.setBackgroundResource(R.drawable.picture_2);
            } else if (pictureNumber.equals("pictureThree")) {
                pictureImg.setBackgroundResource(R.drawable.picture_3);
            }

            pictureImg.setVisibility(View.VISIBLE);
            pictureImg.animate().alpha(1.0f).setDuration(1000);
            new android.os.Handler(Looper.getMainLooper()).postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            alertWin.show();
                        }
                    },
                    2000
            );
        } else {
            Log.i("status", "Jeszcze nie wygrano!");
        }
    }

    public  void iterateHintY() {
        for (int j=0; j<5;j++){

            int hint = res.getIdentifier("hint_0_" + j, "id", packageName);

            TextView hintLoop = findViewById(hint);

            int[] textValue = new int[5];
            int textValueIter = 0;

            for (int i = 0; i < 5; i++) {
                int cellValue = picture[j][i];
                if (i == 0 && cellValue == 1) {
                    textValue[textValueIter] = 1;
                } else if (i > 0 && cellValue == 1) {
                    textValue[textValueIter]++;
                } else {
                    textValueIter++;
                }

            }

            String hintText = "\n ";

            for (int i = 0; i < 5; i++) {
                if (textValue[i] != 0) {
                    hintText = hintText + String.valueOf(textValue[i]) + " ";
                }
            }

            hintLoop.setText(hintText);
        }
    }

    public  void iterateHintX() {
        for (int j=0; j<5;j++){

            int hint = res.getIdentifier("hint_1_" + j, "id", packageName);

            TextView hintLoop = findViewById(hint);

            int[] textValue = new int[5];
            int textValueIter = 0;

            for (int i = 0; i < 5; i++) {
                int cellValue = picture[i][j];
                if (i == 0 && cellValue == 1) {
                    textValue[textValueIter] = 1;
                } else if (i > 0 && cellValue == 1) {
                    textValue[textValueIter]++;
                } else {
                    textValueIter++;
                }

            }

            String hintText = "";

            for (int i = 0; i < 5; i++) {
                if (textValue[i] != 0) {
                    hintText = hintText + String.valueOf(textValue[i]) + "\n";
                }
            }

            hintLoop.setText(hintText);
        }
    }

    public void btnBack(View view) {
        finish();
    }
}