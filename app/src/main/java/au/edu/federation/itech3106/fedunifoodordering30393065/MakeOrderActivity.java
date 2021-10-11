package au.edu.federation.itech3106.fedunifoodordering30393065;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
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

public class MakeOrderActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
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
        int[] chk_id = {R.id.checkbox1, R.id.checkbox2, R.id.checkbox3, R.id.checkbox4,
                R.id.checkbox5, R.id.checkbox6, R.id.checkbox7, R.id.checkbox8, R.id.checkbox9, R.id.checkbox10};
        for (int id : chk_id) {
            CheckBox chk = findViewById(id);
            chk.setOnCheckedChangeListener((OnCheckedChangeListener) this);
        }
        //this.setTitle("代码狂欢到深夜");
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
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String ca = sharedPreferences.getString("ca", "burger");
        int reorder = sharedPreferences.getInt("reorder",0);
        //String cas = "";
        Log.e("1220",ca);
        //TODO What the fuck? 异步？
        switch (ca) {
            case "burger":
                this.setTitle("Ordering a Burger ($2.9)");
                checkbox1.setText("more burger meet1");
                checkbox2.setText("more burger meet2");
                checkbox3.setText("more burger meet3");
                checkbox4.setText("more burger meet4");
                checkbox5.setText("more burger meet5");
                checkbox6.setText("more burger meet6");
                for(int i = 6;i<=9;i++) {
                    CheckBox boxid = findViewById(chk_id[i]);
                    boxid.setVisibility(View.GONE);
                    Log.e("1220", String.valueOf(i));
                }
                break;
            case "pizza":
                this.setTitle("Ordering a Pizza ($2.9)");
                checkbox1.setText("more pinciple1");
                checkbox2.setText("more pinciple2");
                checkbox3.setText("more pinciple3");
                checkbox4.setText("more pinciple4");
                for(int i = 4;i<=9;i++) {
                    CheckBox boxid = findViewById(chk_id[i]);
                    boxid.setVisibility(View.GONE);
                    Log.e("1220", String.valueOf(i));
                }
                break;
            case "sundae":
                this.setTitle("Ordering a Sundae ($2.9)");
                checkbox1.setText("more ice1");
                checkbox2.setText("more ice2");
                checkbox3.setText("more ice3");
                checkbox4.setText("more ice4");
                checkbox5.setText("more ice4");
                for(int i = 5;i<=9;i++) {
                    CheckBox boxid = findViewById(chk_id[i]);
                    boxid.setVisibility(View.GONE);
                    Log.e("1220", String.valueOf(i));
                }
                break;
            default:
                this.setTitle("Please Ordering Someing,lol");
                break;
        }
        Log.e("1223","before:record value is set to " + reorder);
        if (reorder == 1) {
            for (int i = 1; i < 4; i++) {
                checkbox1 = (CheckBox) findViewById(chk_id[i]);
                checkbox1.setChecked(true);
                Log.e("1224", "setcheck compulete to:" + i);
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("reorder", 0);
            editor.commit();
            Log.e("1223","after:record value is set to " + reorder);
        }
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
        double price = 0;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        for (CompoundButton chk : selected) {
            msg = "Customise your order($" + nf.format(count_num * 1.9) + " )";
            //this.setTitle("Customise your order($" + nf.format(count_num *1.9) + " each)");
            //Toast.makeText(this, chk.getText() + "1213", Toast.LENGTH_SHORT).show();
            if (count_num == 1) {
                msg20 = "" + chk.getText();
            } else {
                msg2 += chk.getText() + ",";
                msg20 = msg2.substring(0, msg2.length() - 1);
            }
            //msg2 += "," + chk.getText();
        }
        double base_price = 2.9;
        price = base_price + Double.parseDouble(nf.format(count_num * 1.9));
        Log.e("1214", String.valueOf(count_num));
        //-------------------------------------------------------
        //步骤1：创建一个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        //步骤2： 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //步骤3：将获取过来的值放入文件
        editor.putString("1214-2.1", msg20);
        editor.putFloat("1214-2.2", (float) price);
        editor.putInt("1214-2.3", count_num);
        //步骤4：提交
        editor.commit();
        //
        Log.e("1214-2.1", msg);
        Log.e("1214-2.2", msg20);
        //-------------------------------------------------------
        if (msg.length() == 0) {
            msg = "Customise your order!";
        }
        TextView food = this.findViewById(R.id.text1);
        food.setTextSize(25);
        food.setText(msg);
        //TODO how to save many order historys?
        StringBuffer stringbuffer = new StringBuffer();
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
                SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
                //String userId1=sharedPreferences.getString("ca","");
                int userId1 = sharedPreferences.getInt("ids", 0);
                String userId2 = sharedPreferences.getString("ca0", "");
                Log.e("1206-2", String.valueOf(userId1));
                if (userId2 == null) {
                    Log.e("1206-2.1", "userId2 is null");
                } else {
                    Log.e("1206-2.2", "userId2 is unknow");
                }

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
                SharedPreferences sharedPreferences2 = getSharedPreferences("data", Context.MODE_PRIVATE);
                //步骤2： 实例化SharedPreferences.Editor对象
                SharedPreferences.Editor editor = sharedPreferences2.edit();
                editor.remove("ca");
                editor.commit();
                Log.e("1206-2", "editor.remove(\"ca\");");
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
                String sData = data.getStringExtra("data");
                //sData: A，我是B,已经收到
                Log.e("1211", sData);
            }
        }
    }

    //把String转化为double
    public static double convertToDouble(String number, double defaultValue) {
        if (TextUtils.isEmpty(number)) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }



    public void onClickNext(View view) {
        Log.e("1206-2", "1206-2");
        Intent intent = new Intent(MakeOrderActivity.this, ConfirmOrderActivity.class);
        intent.putExtra("data2", "ConfirmOrderActivity,i am coming");
        //startActivity(intent);
        startActivityForResult(intent, request);

    }
}
