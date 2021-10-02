package au.edu.federation.itech3106.fedunifoodordering30393065;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;



public class MakeOrderActivity extends AppCompatActivity {
    //String sData = getIntent().getStringExtra("data").toString();
    //sData: 我是A
    private static final int request = 100;
    private static final int result = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeorder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.e("1207-2","1207-2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //Log.e("1209", "0");
        //Log.e("1209", String.valueOf(item));
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) {
            Log.e("1208-2","1208-2");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //TODO can not exec, but it worked???
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("1211","1211");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request) {
            if (data != null) {
                String sData = data.getStringExtra("data").toString();
                //sData: A，我是B,已经收到
                Log.e("1211",sData);
            }
        }
    }
}
