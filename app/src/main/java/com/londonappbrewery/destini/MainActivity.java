package com.londonappbrewery.destini;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
    private TextView storyText;
    private Button upperButton;
    private  Button lowerButton;
    Map<Integer, Integer> upperButtonPaths;
    Map<Integer, Integer> lowerButtonPaths;
    Map<Integer, Integer[]> idsDictionary;
    int currentStation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            currentStation = savedInstanceState.getInt("currentStation");
        } else {
            currentStation = 1;
        }

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        storyText = (TextView) findViewById(R.id.storyTextView);
        upperButton = (Button) findViewById(R.id.buttonTop);
        lowerButton = (Button) findViewById(R.id.buttonBottom);
        upperButtonPaths = new HashMap<Integer, Integer>();
        lowerButtonPaths = new HashMap<Integer, Integer>();
        idsDictionary = new HashMap<Integer, Integer[]>();


        fillDictionaries();
        setContentToView(currentStation);

        Integer[] integers = idsDictionary.get(currentStation);
        if(integers[integers.length - 1] != 1)
            setGameButtonListers();
    }

    private void setGameButtonListers(){
        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        upperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStation = upperButtonPaths.get(currentStation);
                setContentToView(currentStation);

            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        lowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStation = lowerButtonPaths.get(currentStation);
                setContentToView(currentStation);
            }
        });
    }

    private void setContentToView(int stationNumber){

        Integer[] questionInfoArray = idsDictionary.get(stationNumber);
        storyText.setText(questionInfoArray[0]);

        if(questionInfoArray[questionInfoArray.length - 1] == 0){
            upperButton.setText(questionInfoArray[1]);
            lowerButton.setText(questionInfoArray[2]);
        }
        else{
            setRestartListener(upperButton);
            setExitListener(lowerButton);
        }
    }

    private void setRestartListener(Button restartButton){
        restartButton.setText(R.string.Restart);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStation = 1;
                setGameButtonListers();
                setContentToView(currentStation);
            }
        });
    }

    private void setExitListener(Button exitButton){
        exitButton.setText(R.string.Exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    private void fillDictionaries(){
        upperButtonPaths.put(1, 3);
        upperButtonPaths.put(3, 6);
        upperButtonPaths.put(2, 3);

        lowerButtonPaths.put(1, 2);
        lowerButtonPaths.put(3, 5);
        lowerButtonPaths.put(2, 4);

        idsDictionary.put(1, new Integer[] {R.string.T1_Story, R.string.T1_Ans1, R.string.T1_Ans2, 0});
        idsDictionary.put(2, new Integer[] {R.string.T2_Story, R.string.T2_Ans1, R.string.T2_Ans2, 0});
        idsDictionary.put(3, new Integer[] {R.string.T3_Story, R.string.T3_Ans1, R.string.T3_Ans2, 0});
        idsDictionary.put(4, new Integer[] {R.string.T4_End, 1});
        idsDictionary.put(5, new Integer[] {R.string.T5_End, 1});
        idsDictionary.put(6, new Integer[] {R.string.T6_End, 1});
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentStation", currentStation);
    }
}
