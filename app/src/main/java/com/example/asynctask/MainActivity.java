package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView percent_text;
    private TextView textView_text;

    private Button start_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        percent_text = (TextView) findViewById(R.id.percent);
        textView_text = (TextView) findViewById(R.id.textView);


        start_button = (Button) findViewById(R.id.start_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExampleAsyncTask exampleAsyncTask = new ExampleAsyncTask();
                exampleAsyncTask.execute(60);
            }
        });

    }


    private class ExampleAsyncTask extends AsyncTask<Integer, Integer, String> {


//        Params
        @Override
        protected String doInBackground(Integer... integers) {
            for (int i = 0; i < integers[0]; i++){
                publishProgress(i*100 / integers[0]);
                try{
                    Thread.sleep(1000);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            return "Finished";
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);

            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            textView_text.setText("Working...");
            percent_text.setText("100%");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textView_text.setText("Napping...");
            progressBar.setVisibility(View.VISIBLE);
        }

//        Progress
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            percent_text.setText(Integer.toString(values[0]) + "%");
            progressBar.setProgress(values[0]);
        }
    }
}