package com.example.enesimona30.licenta1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RouletteActivity extends AppCompatActivity {

    ImageView ivRouletteArrow;
    Button btnRouletteGo;
    Random r;
    int angle;
    TextView tvRouletteProcent;
    Boolean clicked=false;
    Float procent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);

        ivRouletteArrow=(ImageView) findViewById(R.id.iv_roulette_arrow);

        btnRouletteGo=(Button) findViewById(R.id.btn_roulette_go);
        r=new Random();



        btnRouletteGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(clicked==false) {

                    angle = r.nextInt(3600 - 1440) + 1440;
                    RotateAnimation rotate = new RotateAnimation(0, angle,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setFillAfter(true);
                    rotate.setDuration(3600);
                    rotate.setInterpolator(new AccelerateDecelerateInterpolator());

                    ivRouletteArrow.startAnimation(rotate);
                   // Toast.makeText(getApplicationContext(), angle + " ", Toast.LENGTH_LONG).show();
                    int rest = angle % 360;
                    if (rest >= 0 && rest <= 8) {
                        procent= Float.valueOf(6);
                    } else if (rest >= 9 && rest <= 50) {
                        procent= Float.valueOf(10);
                    } else if (rest >= 51 && rest <= 105) {
                        procent= Float.valueOf(9);
                    } else if (rest >= 106 && rest <= 150) {
                        procent= Float.valueOf(7);
                    } else if (rest >= 151 && rest <= 213) {
                        procent= Float.valueOf(5);
                    } else if (rest >= 214 && rest <= 274) {
                        procent= Float.valueOf(8);
                    } else if (rest >= 275 && rest <= 300) {
                        procent= Float.valueOf(12);
                    } else if (rest >= 301 && rest <= 325) {
                        procent= Float.valueOf(11);
                    } else if (rest >= 326 && rest <= 360) {
                        procent= Float.valueOf(6);
                    }

                    btnRouletteGo.setText("URMATORUL");
                    clicked=true;
                    ResultActivity.procent=Float.parseFloat(procent.toString());
                }else{
                    Intent intent=new Intent(getApplicationContext(), ResultActivity.class);
                    startActivity(intent);
                }

            }
        });


    }
}
