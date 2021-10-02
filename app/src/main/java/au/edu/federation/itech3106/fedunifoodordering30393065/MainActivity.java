package au.edu.federation.itech3106.fedunifoodordering30393065;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final int request = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickBuger (View view) {
        Log.e("1206", "1206");
        Intent intent = new Intent(MainActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","我是A");
        //startActivity(intent);
        startActivityForResult(intent,request);
    }
}
