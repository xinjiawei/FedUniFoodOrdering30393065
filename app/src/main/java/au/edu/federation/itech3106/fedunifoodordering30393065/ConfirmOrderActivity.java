package au.edu.federation.itech3106.fedunifoodordering30393065;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.RadioGroup.OnCheckedChangeListener;

import java.io.IOException;
import java.text.NumberFormat;

public class ConfirmOrderActivity<price> extends AppCompatActivity {
    private static final int request = 100;
    private RadioGroup rg;
    private RadioButton Meal_Big, Meal_Small, No_Meal;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmorder);
        //-----------------------------------------------
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String msg2 = sharedPreferences.getString("1214-2.1", "");
        float price = sharedPreferences.getFloat("1214-2.2", 0);
        //price = price_float;
        int count = sharedPreferences.getInt("1214-2.3", 1);
        this.setTitle("Confirm Order");
        //-----------------------------------------------
        String msg = "Extras:" + msg2 + " (" + count + " total)";
        TextView food = this.findViewById(R.id.textView3);
        food.setTextSize(20);
        if (count > 4 && count < 7) {
            food.setTextSize(15);
            food.setText(msg);
        } else if (count >= 7) {
            food.setTextSize(12);
            food.setText(msg);
        } else {
            food.setText(msg);
        }
        Log.e("1212-2.1", msg2);
        Log.e("1212-2.2", String.valueOf(price));
        Log.e("1214-2", String.valueOf(count));
        //-----------------------------------------------
        //-----------------------------------------------
        Button button = (Button) findViewById(R.id.place_order);
        button.setText("PLACE ORDER($" + price + ")");
        //-----------------------------------------------
        rg = (RadioGroup) findViewById(R.id.extra_meal);
        Meal_Big = (RadioButton) findViewById(R.id.meal_big);
        Meal_Small = (RadioButton) findViewById(R.id.meal_small);
        No_Meal = (RadioButton) findViewById(R.id.no_meal);
        //注意是给RadioGroup绑定监视器
        rg.setOnCheckedChangeListener(new MyRadioButtonListener());
    }

    class MyRadioButtonListener implements OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            /*
            //-------------------------------------------------------
            //步骤1：创建一个SharedPreferences对象
            SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
            //步骤2： 实例化SharedPreferences.Editor对象
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //步骤3：将获取过来的值放入文件

            //editor.putString("1214-2.1",msg20);
            //editor.putFloat("1214-2.2", (float) price);
            //editor.putInt("1214-2.3",count_num);


            //步骤4：提交
            editor.commit();
            //Log.e("1214-2.1",msg);
            //Log.e("1214-2.2",msg20);
            //-------------------------------------------------------
            */
            //-----------------------------------------------
            SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
            float price = sharedPreferences.getFloat("1214-2.2", 0);
            //-----------------------------------------------
            //-------------------------------------------------------
            //步骤1：创建一个SharedPreferences对象
            SharedPreferences sharedPreferences_setpri = getSharedPreferences("data2", Context.MODE_PRIVATE);
            //步骤2： 实例化SharedPreferences.Editor对象
            SharedPreferences.Editor editor = sharedPreferences_setpri.edit();
            // 选中状态改变时被触发
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(2);
            double extra_price;
            switch (checkedId) {
                case R.id.meal_big:
                    extra_price = Double.parseDouble(nf.format(price + 4.99));
                    Log.e("1216", "当前用户选择" + Meal_Big.getText().toString() + ":" + extra_price);
                    break;
                case R.id.meal_small:
                    extra_price = Double.parseDouble(nf.format(price + 2.99));
                    Log.e("1216", "当前用户选择" + Meal_Small.getText().toString() + ":" + extra_price);
                    break;
                case R.id.no_meal:
                    extra_price = Double.parseDouble(nf.format(price + 0));
                    Log.e("1216", "当前用户选择" + No_Meal.getText().toString() + ":" + extra_price);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + checkedId);
            }
            //步骤4：提交
            Button button = (Button) findViewById(R.id.place_order);
            button.setText("PLACE ORDER($" + extra_price + ")");
            editor.putFloat("1214-2.2.0", (float) extra_price);
            editor.commit();
        }
    }

    //TODO can not exec, but it worked???
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("1211-3", "1211-3");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request) {
            if (data != null) {
                String sData = data.getStringExtra("data").toString();
                //sData: A，我是B,已经收到
                Log.e("1211-3", sData);
            }
        }
    }

    public void onClickCancel(View view) {
        Log.e("1215", "1215");
        finish();
    }
}