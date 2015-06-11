package com.example.saisandeep.asynctaskex;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends ActionBarActivity {


    ListView lv;
    String[] texts={"hello","oq","ow","sa","se","df","er","fg","cv","fg","hg","fd","sd","as","er","vc","xz"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_PROGRESS);//TO generate a progress bar inbuild at the top.

        setContentView(R.layout.activity_my);
        lv= (ListView) findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,new ArrayList<String>()));
        new MyTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    class MyTask extends AsyncTask<Void,String,Void>
    {

        ArrayAdapter<String> adapter;
        private int count=0;

        @Override
        protected void onPreExecute() {

            adapter= (ArrayAdapter<String>) lv.getAdapter();
            setProgressBarIndeterminate(false);//set to be false because we have definite number of values
            setProgressBarVisibility(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(String item:texts)
            {
                publishProgress(item);

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {

            adapter.add(values[0]);
            count++;
            setProgress((int)(((double)count/texts.length)*10000));
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            setProgressBarVisibility(false);
            Toast.makeText(MyActivity.this,"All items were added successfully",Toast.LENGTH_SHORT).show();
        }
    }
}
