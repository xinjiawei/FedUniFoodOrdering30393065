package au.edu.federation.itech3106.fedunifoodordering30393065;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderHistoryActivity extends AppCompatActivity {
    private static final int request = 100;
    int[] radio_id = {R.id.history1, R.id.history2, R.id.history3, R.id.history4,
            R.id.history5, R.id.history6, R.id.history7, R.id.history8, R.id.history9, R.id.history10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderhistoryactivity);
        Button reOrderbutton = (Button) findViewById(R.id.place_order2);
        reOrderbutton.setBackgroundColor(Color.parseColor("#F8F8FF"));
        reOrderbutton.setTextColor(Color.parseColor("#000000"));
        //
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String msg3 = sharedPreferences.getString("1214-2.1","");
        int his_order_count = sharedPreferences.getInt("his_order_count", 0);
        int i = sharedPreferences.getInt("history_order",0);
        String order_his = sharedPreferences.getString("order_all","");
        Log.e("1226-1",order_his);

        for(int i0 = 0;i0<=9;i0++) {
            RadioButton boxid = findViewById(radio_id[i0]);
            boxid.setVisibility(View.GONE);
            Log.e("1220", String.valueOf(i0));
        }

        String rgex = "(\\*).*?(@)";
        String rgex2 = "(?<=\\*).*?(?=\\@)";
        String rgex3 = "(?<=\\*).*?(?=#)";
        String rgex4 = "(?<=#).*?(?=@)";

        String str = order_his;

        Pattern p = Pattern.compile(rgex);
        Pattern p2 = Pattern.compile(rgex3);
        Pattern p3 = Pattern.compile(rgex4);

        Matcher m = p.matcher(str);

        Log.e("9999-1", String.valueOf(i));
        Log.e("9999-2", String.valueOf(his_order_count));
        int i1 = 0;
        //String out_order_his = "";
        ArrayList order_his_arraylist =new ArrayList();

        while(m.find()) {
            Log.e("1231-2.1",m.group());

            Matcher m2 = p2.matcher(m.group());
            Matcher m3 = p3.matcher(m.group());

            while(m2.find() && m3.find()) {
                Log.e("1231-2.2",m2.group());
                Log.e("1231-2.3",m3.group());
                RadioButton boxid = findViewById(radio_id[i1]);
                boxid.setText(m2.group() + ",Extras:" + m3.group());
                Log.e("1232-2", "i1 now is " + String.valueOf(i1));
            }
            order_his_arraylist.add(i1,m.group());
            i1 += 1;
            //Log.e("1232-2", "find cycle is " + String.valueOf(i1));
            Log.e("9999-3", "his_order_count is " + String.valueOf(his_order_count));
        }
        Log.e("0000", String.valueOf(order_his_arraylist.toString()));

        //转置列表
        StringBuilder out_order_his = new StringBuilder();
        for(int i3 = his_order_count-1;i3 >= 0;i3--){
             out_order_his.append(order_his_arraylist.get(i3));
             Log.e("1232-1", String.valueOf(i3));
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("out_order_his", String.valueOf(out_order_his));
        editor.commit();

        //设置显隐性
        Log.e("0001", String.valueOf(out_order_his.toString()));
        for(int i0 = 0;i0 <= his_order_count-1;i0++) {
            RadioButton boxid = findViewById(radio_id[i0]);
            boxid.setVisibility(View.VISIBLE);
            Log.e("1232-2", String.valueOf(i0));
        }

        //backcode: is apk started just now?
        Intent intent_backcode = new Intent();
        intent_backcode.putExtra("back_code_string","back_code_string:3");
        setResult(2,intent_backcode);
        //
        Intent intent_get_request_code = getIntent();
        String values = intent_get_request_code.getStringExtra("data");
        Log.e("intent2",values);

        //
        RadioGroup rg1 = (RadioGroup) findViewById(R.id.historygroup);
        rg1.setOnCheckedChangeListener(new MyRadioButtonListener2());
    }

    public void onClickHistoryConfirm (View view) {
        Log.e("1206-1.4", "1206");
        SharedPreferences sharedPreferences= getSharedPreferences("data2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String reorder_list = sharedPreferences.getString("reorder_list","");
        Intent intent = new Intent(OrderHistoryActivity.this,MakeOrderActivity.class);
        intent.putExtra("data","this is OrderHistoryActivity");
        //-----------------------------------------------------
        if (reorder_list != "") {

            editor.putInt("reorder",1);
            editor.commit();
            //-----------------------------------------------------
            startActivityForResult(intent,request);
            // refresh();
        }
    }
    //------------------------------------------------------------------

    public void refresh() {
        onCreate(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);

        Log.e("1207-2", "1207-2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("1209", String.valueOf(item));
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        String out_order_his = sharedPreferences.getString("out_order_his","");
        String order_his = sharedPreferences.getString("order_all","");
        int his_order_count = sharedPreferences.getInt("his_order_count", 0);
        Log.e("1234", String.valueOf(out_order_his));
        String rgex = "(\\*).*?(@)";
        String rgex2 = "(?<=\\*).*?(?=\\@)";
        String rgex3 = "(?<=\\*).*?(?=#)";
        String rgex4 = "(?<=#).*?(?=@)";

        Pattern p = Pattern.compile(rgex); //编译对象
        Pattern p2 = Pattern.compile(rgex3);
        Pattern p3 = Pattern.compile(rgex4);

        Log.e("9999-2", String.valueOf(his_order_count));
        int i1 = 0;
        //String out_order_his = "";
        ArrayList order_his_arraylist =new ArrayList();

        switch (item.getItemId()) {
            case R.id.action_settings3:
                //
                String str1 = out_order_his;
                Matcher o_m = p.matcher(str1);
                while(o_m.find()) {
                    Log.e("1231-2.1",o_m.group());

                    Matcher m2 = p2.matcher(o_m.group());
                    Matcher m3 = p3.matcher(o_m.group());

                    while(m2.find() && m3.find()) {
                        Log.e("1231-2.2",m2.group());
                        Log.e("1231-2.3",m3.group());
                        //
                        RadioButton boxid = findViewById(radio_id[i1]);
                        boxid.setText(m2.group() + ",Extras:" + m3.group());
                        Log.e("1232-2", "i1 now is " + String.valueOf(i1));
                    }
                    order_his_arraylist.add(i1,o_m.group());
                    i1 += 1;
                    Log.e("9999-3", "his_order_count is " + String.valueOf(his_order_count));
                }

                break;
            case R.id.action_settings4:
                Toast.makeText(this, "action_settings2", Toast.LENGTH_SHORT).show();
                //
                String str2 = order_his;
                Matcher m = p.matcher(str2);
                while(m.find()) {
                    Log.e("1231-2.1",m.group());

                    Matcher m2 = p2.matcher(m.group());
                    Matcher m3 = p3.matcher(m.group());

                    while(m2.find() && m3.find()) {
                        Log.e("1231-2.2",m2.group());
                        Log.e("1231-2.3",m3.group());
                        RadioButton boxid = findViewById(radio_id[i1]);
                        boxid.setText(m2.group() + ",Extras:" + m3.group());
                        Log.e("1232-2", "i1 now is " + String.valueOf(i1));
                    }
                    order_his_arraylist.add(i1,m.group());
                    i1 += 1;
                    //Log.e("1232-2", "find cycle is " + String.valueOf(i1));
                    Log.e("9999-3", "his_order_count is " + String.valueOf(his_order_count));
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
        //-----------------------------------------------
    }


    class MyRadioButtonListener2 implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Button reOrderbutton = (Button) findViewById(R.id.place_order2);
            SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            RadioGroup rg = (RadioGroup) findViewById(R.id.historygroup);
            RadioButton radiobutton1 = (RadioButton) findViewById(R.id.history1);
            RadioButton radiobutton2 = (RadioButton) findViewById(R.id.history2);
            RadioButton radiobutton3 = (RadioButton) findViewById(R.id.history3);
            RadioButton radiobutton4 = (RadioButton) findViewById(R.id.history4);
            RadioButton radiobutton5 = (RadioButton) findViewById(R.id.history5);
            RadioButton radiobutton6 = (RadioButton) findViewById(R.id.history6);
            RadioButton radiobutton7 = (RadioButton) findViewById(R.id.history7);
            RadioButton radiobutton8 = (RadioButton) findViewById(R.id.history8);
            RadioButton radiobutton9 = (RadioButton) findViewById(R.id.history9);
            RadioButton radiobutton10 = (RadioButton) findViewById(R.id.history10);
            switch (checkedId) {
                case R.id.history1:
                    Log.e("1216", "当前用户选择" + radiobutton1.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton1.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history2:
                    Log.e("1216", "当前用户选择" + radiobutton2.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton2.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history3:
                    Log.e("1216", "当前用户选择" + radiobutton3.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton3.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history4:
                    Log.e("1216", "当前用户选择" + radiobutton4.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton4.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history5:
                    Log.e("1216", "当前用户选择" + radiobutton5.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton5.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history6:
                    Log.e("1216", "当前用户选择" + radiobutton6.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton6.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history7:
                    Log.e("1216", "当前用户选择" + radiobutton7.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton7.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history8:
                    Log.e("1216", "当前用户选择" + radiobutton8.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton8.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history9:
                    Log.e("1216", "当前用户选择" + radiobutton9.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton9.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                case R.id.history10:
                    Log.e("1216", "当前用户选择" + radiobutton10.getText().toString() + ",");
                    editor.putString("reorder_list",radiobutton10.getText().toString());
                    reOrderbutton.setBackgroundColor(Color.parseColor("#8BC34A"));
                    reOrderbutton.setTextColor(Color.parseColor("#000000"));
                    editor.commit();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + checkedId);
            }

        }

    }

}

