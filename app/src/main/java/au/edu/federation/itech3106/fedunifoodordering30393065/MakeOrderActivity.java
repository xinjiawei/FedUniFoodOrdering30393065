package au.edu.federation.itech3106.fedunifoodordering30393065;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.text.NumberFormat;
import android.widget.CompoundButton.OnCheckedChangeListener;


import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    //String sData = getIntent().getStringExtra("data").toString();
    //sData: 我是A
    ArrayList<CompoundButton> selected = new ArrayList<>();
    private static final int request = 100;
    private static final int result = 200;

    private CheckBox checkbox1;
    private CheckBox checkbox2;
    private CheckBox checkbox3;
    private CheckBox checkbox4;
    private CheckBox checkbox5;
    private CheckBox checkbox6;
    private CheckBox checkbox7;
    private CheckBox checkbox8;
    private CheckBox checkbox9;
    private CheckBox checkbox10;

    private int count_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeorder);

        int chk_id[] = {R.id.checkbox1, R.id.checkbox2, R.id.checkbox3, R.id.checkbox4, R.id.checkbox5, R.id.checkbox6, R.id.checkbox7, R.id.checkbox8, R.id.checkbox9, R.id.checkbox10};
        for (int id : chk_id) {
            CheckBox chk = findViewById(id);
            chk.setOnCheckedChangeListener((OnCheckedChangeListener) this);
        }
        //this.setTitle("代码狂欢到深夜");
    }

    //------------------------------------------------------------------
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        Log.e("count_num_old", String.valueOf(count_num));
        if (isChecked) {                      //选项被选取
            selected.add(compoundButton);   //添加到集合中
            count_num += 1;
        } else {                             //选项被取消
            selected.remove(compoundButton);//从集合中取消
            count_num -= 1;
        }
        Log.e("count_num_new", String.valueOf(count_num));
        String msg = "";
        String msg2 = "";
        String msg20 = "";
        String price = "";
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        for (CompoundButton chk : selected) {
            msg = "Customise your order($" + nf.format(count_num *1.9) + " each)";
            this.setTitle("Customise your order($" + nf.format(count_num *1.9) + " each)");
            //Toast.makeText(this, chk.getText() + "1213", Toast.LENGTH_SHORT).show();
            if (count_num == 1){
                msg2 = "" + chk.getText();
            }else{
                msg2 += chk.getText() + ",";
                 msg20 = msg2.substring(0, msg2.length() - 1);
            }

            //msg2 += "," + chk.getText();
            price = nf.format(count_num *1.9);
            //-------------------------------------------------------
            //步骤1：创建一个SharedPreferences对象
            SharedPreferences sharedPreferences= getSharedPreferences("data2",Context.MODE_PRIVATE);
            //步骤2： 实例化SharedPreferences.Editor对象
            SharedPreferences.Editor editor = sharedPreferences.edit();
            //步骤3：将获取过来的值放入文件
            editor.putString("1214-2.1",msg20);
            editor.putString("1214-2.2",price);
            editor.putInt("1214-2.3",count_num);
            //步骤4：提交
            editor.commit();
            //-------------------------------------------------------
            Log.e("1214",msg);
            Log.e("1214",msg20);

        }
        if (msg.length() == 0) {
            msg = "Customise your order!";
            this.setTitle("Foodle!");
        }

        TextView food = this.findViewById(R.id.text1);
        food.setTextSize(25);
        food.setText(msg);

    }
    //------------------------------------------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.e("1207-2", "1207-2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //----------------------------------------------------------------
        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkbox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkbox6 = (CheckBox) findViewById(R.id.checkbox6);
        checkbox7 = (CheckBox) findViewById(R.id.checkbox7);
        checkbox8 = (CheckBox) findViewById(R.id.checkbox8);
        checkbox9 = (CheckBox) findViewById(R.id.checkbox9);
        checkbox10 = (CheckBox) findViewById(R.id.checkbox10);
        //---------------------------------------------------------------
        Log.e("1209", String.valueOf(item));

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings1:
                Toast.makeText(this, "action_settings1", Toast.LENGTH_SHORT).show();
                checkbox1.setChecked(true);
                checkbox2.setChecked(true);
                checkbox3.setChecked(true);
                checkbox4.setChecked(true);
                checkbox5.setChecked(true);
                checkbox6.setChecked(true);
                checkbox7.setChecked(true);
                checkbox8.setChecked(true);
                checkbox9.setChecked(true);
                checkbox10.setChecked(true);
                //------------------
                //TODO OK了家人们
                SharedPreferences sharedPreferences= getSharedPreferences("data2", Context.MODE_PRIVATE);
                String userId1=sharedPreferences.getString("ca","");
                String userId2=sharedPreferences.getString("ca0","");
                Log.e("1212",userId1);
                if (userId2 == null){
                    Log.e("1212","userId2 is null");
                }
                else {
                    Log.e("1212","userId2 is unknow");
                }

                //
                //------------------
                break;
            case R.id.action_settings2:
                Toast.makeText(this, "action_settings2", Toast.LENGTH_SHORT).show();
                checkbox1.setChecked(false);
                checkbox2.setChecked(false);
                checkbox3.setChecked(false);
                checkbox4.setChecked(false);
                checkbox5.setChecked(false);
                checkbox6.setChecked(false);
                checkbox7.setChecked(false);
                checkbox8.setChecked(false);
                checkbox9.setChecked(false);
                checkbox10.setChecked(false);
                //---------------------------------
                //步骤1：创建一个SharedPreferences对象
                SharedPreferences sharedPreferences2= getSharedPreferences("data",Context.MODE_PRIVATE);
                //步骤2： 实例化SharedPreferences.Editor对象
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.remove("ca");
                editor.commit();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
        //-----------------------------------------------
    }

    //TODO can not exec, but it worked???
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("1211", "1211");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request) {
            if (data != null) {
                String sData = data.getStringExtra("data").toString();
                //sData: A，我是B,已经收到
                Log.e("1211", sData);
            }
        }
    }


    public void onClickNext (View view) {
        Log.e("1206-2", "1206-2");
        Intent intent = new Intent(MakeOrderActivity.this,ConfirmOrderActivity.class);
        intent.putExtra("data2","ConfirmOrderActivity,i am coming");
        //startActivity(intent);
        startActivityForResult(intent,request);

    }
}
