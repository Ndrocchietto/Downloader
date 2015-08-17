package ivano.android.com.downloader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
TextView tv;
    String line;
    Button connect;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         connect = (Button) findViewById(R.id.button);

        connect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "cliccato", Toast.LENGTH_LONG).show();
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... params) {

                        try {

                            return downloadHTML();
                        } catch (Exception e) {
                            Log.d("IVO", e.toString());
                        }
                        return "what?";
                    }

                    @Override
                    protected void onPostExecute(String result) {

                        tv = (TextView) findViewById(R.id.textView);
                        tv.setText(result);

                    }
                }.execute();
            }





        });
    }



            public String downloadHTML() throws Exception {
                URL url = new URL("http://www.repubblica.it/");
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is);

                BufferedReader br = new BufferedReader(isr);

                line = null;
                // Use a StringBuilder to collect the lines of
                // data we retrieve.
                StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                return sb.toString();

            }


            @Override
            public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                return true;
            }


            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();

                //noinspection SimplifiableIfStatement
                if (id == R.id.action_settings) {
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

        }

