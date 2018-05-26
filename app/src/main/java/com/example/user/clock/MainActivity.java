package com.example.user.clock;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button modeButton;
    private Button startButton;
    private Button stopButton;
    private Button setButton;
    private TextView resultTextView;

    private boolean resultFlag = false;

    private Clock clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        modeButton = findViewById(R.id.Mode);
        startButton = findViewById(R.id.Start);
        stopButton = findViewById(R.id.Stop);
        setButton = findViewById(R.id.Set);
        resultTextView = findViewById(R.id.Result);

        clock = Clock.getInstance();

        modeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock.changeMode();
                resultTextView.setText(clock.getResult());
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clock.start()) {
                    Toast.makeText(MainActivity.this, R.string.start_after_start,
                            Toast.LENGTH_LONG).show();
                    return;
                }
                resultTextView.setText(clock.getResult());
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clock.stop()) {
                    Toast.makeText(MainActivity.this, R.string.stop_after_stop,
                            Toast.LENGTH_LONG).show();
                    return;
                }
                resultTextView.setText(clock.getResult());
            }
        });

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clock.set();
                resultTextView.setText(clock.getResult());
            }
        });

        // show thew result
        resultFlag = true;
        resultLoop();
    }

    private void resultLoop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(resultFlag) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resultTextView.setText(clock.getResult());
                        }
                    });


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        resultFlag = false;
        super.onDestroy();
    }
}
