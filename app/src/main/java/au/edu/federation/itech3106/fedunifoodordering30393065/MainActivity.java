package au.edu.federation.itech3106.fedunifoodordering30393065;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int requestCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //0TODO initile all sharedPreferences value.
        SharedPreferences sharedPreferences= getSharedPreferences("data2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int his_order_count = 0;
        int num = 0;
        String str = "";
        Intent intent = new Intent();
        if(intent.getStringExtra("back_code_string") == null) {
            //
            editor.putInt("his_order_count",his_order_count);
            editor.putString("order20",str);
            editor.putString("order_all",str);
            editor.putInt("reorder",num);
            //editor.putString("reorder_list",str);
            //====================================================================
            editor.commit();
            Log.e("his_order_count", String.valueOf(his_order_count));
        }

        editor.putString("reorder_list",str);

    }

    public void onClickBuger (View view) {
        Log.e("1206-1.1", "1206");
        Intent intent = new Intent(MainActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        startActivityForResult(intent,requestCode);

        SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ca","burger");
        editor.commit();

    }

    public void onClickPizza (View view) {
        Log.e("1206-1.2", "1206");
        Intent intent = new Intent(MainActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        startActivityForResult(intent,requestCode);
        SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ca","pizza");
        editor.commit();

    }

    public void onClickSundaer (View view) {
        Log.e("1206-1.3", "1206");
        Intent intent = new Intent(MainActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        startActivityForResult(intent,requestCode);
        SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ca","sundae");
        editor.commit();

    }

    public void onClickHistory (View view) {
        Log.e("1206-1.4", "1206");
        Intent intent = new Intent(MainActivity.this,OrderHistoryActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        startActivityForResult(intent,requestCode);
    }


}
