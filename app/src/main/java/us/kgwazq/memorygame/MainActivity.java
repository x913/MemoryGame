package us.kgwazq.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    List<ImageButton> buttons;
    List<Integer> cards;
    boolean isFirstPass;
    TextView title;
    TextView bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView)findViewById(R.id.headerText);
        bottom = (TextView)findViewById(R.id.bottomText);

        blinkAnimation(title, 1500, Animation.INFINITE);

        cards = new ArrayList<Integer>();
        cards.add(R.drawable.card_b_da);
        cards.add(R.drawable.card_b_dj);
        cards.add(R.drawable.card_b_dk);
        cards.add(R.drawable.card_b_dq);
        cards.add(R.drawable.card_b_ha);
        cards.add(R.drawable.card_b_ca);
        cards.add(R.drawable.card_b_hj);
        cards.add(R.drawable.card_b_sa);
        cards.add(R.drawable.card_b_sq);
        cards.add(R.drawable.card_b_ck);
        cards.add(R.drawable.card_b_hq);

        buttons = new ArrayList<ImageButton>();

        buttons.add((ImageButton)findViewById(R.id.card1));
        buttons.add((ImageButton)findViewById(R.id.card2));
        buttons.add((ImageButton)findViewById(R.id.card3));
        buttons.add((ImageButton)findViewById(R.id.card4));
        buttons.add((ImageButton)findViewById(R.id.card5));

        isFirstPass = false;
        Collections.shuffle(cards);
        showCards(0);

        for(ImageButton btn: buttons) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isFirstPass) {
                        isFirstPass = false;
                        Collections.shuffle(cards);
                        showCards(0);
                        bottom.setText(getString(R.string.card_guess));
                    } else {
                        isFirstPass = true;
                        showCards(buttons.size());
                        bottom.setText(getString(R.string.card_removed));
                    }
                }
            });
        }
    }


    void blinkAnimation(View view, int duration, int repeatCount) {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        //anim.setDuration(1500);
        anim.setDuration(duration);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(repeatCount);
        //anim.setRepeatCount(Animation.INFINITE);
        view.startAnimation(anim);
    }

    void showCards(int idx) {
        for(ImageButton btn: buttons) {
            Log.d("AAA SHUFFLE", "IDX " + idx);
            btn.setImageResource(cards.get(idx));
            blinkAnimation(btn, 500, 1);
            idx++;
        }
    }



}
