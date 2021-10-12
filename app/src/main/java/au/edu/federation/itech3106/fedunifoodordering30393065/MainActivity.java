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
import android.widget.Toast;

import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final int requestCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO initile all sharedPreferences value.
        Intent intent = new Intent();
        if(intent.getStringExtra("back_code_string") == null) {
            SharedPreferences sharedPreferences= getSharedPreferences("data2", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //
            int his_order_count = 0;
            editor.putInt("his_order_count",his_order_count);
            //
            String order20 = "";
            editor.putString("order20",order20);
            //
            String str = "";
            editor.putString("order_all",str);
            //====================================================================
            editor.commit();
            Log.e("his_order_count", String.valueOf(his_order_count));
        }
        //-----------------------------------------------------
        //TODO 判断0

    }
/*
    @Override
    // 失败，不用这麽麻烦，直接判断返回的string存不存在。
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        //第一个参数，请求码，第二个结果码，第三个是数据
        int is_start = 1;
        switch(requestCode){
            case 1:
                //,,对应上边的requestCode设置的值1，代表请求是发往SecondeActivity
                if(resultCode==2){ //
                    // 获取secondactivity里的返回的数据结果
                    String shuju=data.getStringExtra("back_code_string");
                    Toast.makeText(this,shuju,Toast.LENGTH_SHORT).show();
                    //
                    is_start = 0;
                }else {
                    //finish();
                    int his_order_count = 0;
                    SharedPreferences sharedPreferences= getSharedPreferences("data2", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("his_order_count",his_order_count);
                    editor.commit();
                    Log.e("his_order_count", String.valueOf(his_order_count));
                }
                //Log.e("1230","1230");
        }
        //
    }
*/
    public void onClickBuger (View view) {
        Log.e("1206-1.1", "1206");
        Intent intent = new Intent(MainActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is mainactivity, ");
        //startActivity(intent);
        startActivityForResult(intent,requestCode);
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
        startActivityForResult(intent,requestCode);
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
        startActivityForResult(intent,requestCode);
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
        startActivityForResult(intent,requestCode);
    }


}
