package au.edu.federation.itech3106.fedunifoodordering30393065;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        //-----------------------------------------------------
    }
    public void onClickBuger (View view) {
        Log.e("1206-1.1", "1206");
        Intent intent = new Intent(MainActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        //startActivity(intent);
        startActivityForResult(intent,request);
        //-------------------------------------------------------
        //步骤1：创建一个SharedPreferences对象
        SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
        //步骤2： 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //步骤3：将获取过来的值放入文件
        editor.putString("ca","burger");
        editor.putInt("ids", 0);
        editor.putBoolean("isComfirm",false);
        //步骤4：提交
        editor.commit();

    }

    public void onClickPizza (View view) {
        Log.e("1206-1.2", "1206");
        Intent intent = new Intent(MainActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        //startActivity(intent);
        startActivityForResult(intent,request);
        //-------------------------------------------------------
        //步骤1：创建一个SharedPreferences对象
        SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
        //步骤2： 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //步骤3：将获取过来的值放入文件
        editor.putString("ca","pizza");
        editor.putInt("ids", 0);
        editor.putBoolean("isComfirm",false);
        //步骤4：提交
        editor.commit();

    }

    public void onClickSundaer (View view) {
        Log.e("1206-1.3", "1206");
        Intent intent = new Intent(MainActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        //startActivity(intent);
        startActivityForResult(intent,request);
        //-------------------------------------------------------
        //步骤1：创建一个SharedPreferences对象
        SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
        //步骤2： 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //步骤3：将获取过来的值放入文件
        editor.putString("ca","sundae");
        editor.putInt("ids", 0);
        editor.putBoolean("isComfirm",false);
        //步骤4：提交
        editor.commit();

    }

    public void onClickHistory (View view) {
        Log.e("1206-1.4", "1206");
        Intent intent = new Intent(MainActivity.this,OrderHistoryActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        //startActivity(intent);
        startActivityForResult(intent,request);
    }
}
