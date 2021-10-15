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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.NumberFormat;

import android.widget.CompoundButton.OnCheckedChangeListener;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MakeOrderActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    ArrayList<CompoundButton> selected = new ArrayList<>();
    private static final int request = 1;
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
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
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
        //check if order history
        int reorder = sharedPreferences.getInt("reorder",0);
        Log.e("1223","before:record value is set to " + reorder);
        String ca = "0";
        if (reorder == 1) {
            String reorderlist = sharedPreferences.getString("reorder_list", "");
            /*
            for (int i = 1; i < 4; i++) {
                //===========================================================
                checkbox1 = (CheckBox) findViewById(chk_id[i]);
                checkbox1.setChecked(true);
                Log.e("1224", "setcheck compulete to:" + i);
                //===========================================================
            }
            */
            Log.e("reorder_list",reorderlist);
            //截取
            String rgex = "(\\*).*?(@)";
            String rgex2 = ".*?(?=,E)";//匹配之前所有字符
            String rgex3 = "([^:]+)$";//匹配之后所有字符
            String rgex4 = "(?<=,).*?(?=,)";
            String str = reorderlist;

            Pattern p1 = Pattern.compile(rgex2); //编译对象
            Pattern p2 = Pattern.compile(rgex3);
            Pattern p3 = Pattern.compile(rgex4);

            Matcher m1 = p1.matcher(str); //进行匹配
            Matcher m2 = p2.matcher(str);

            if(m1.find()) {
                //0TODO 离谱，离大谱
                //if(m1.find())原来是while(m1.find()),while在m1.find（）这里会循环两次
                // （第二次循环m1.find（）也是true）
                //第二次循环时ca是空字符串，会把第一次ca的存值覆盖掉，所以后边死活拿不到ca值了。
                //奇奇怪怪，明明只有一个可匹配目标。
                //多写Log.e...
                Log.e("1235-1",m1.group(0));//默认是group(0)
                ca = "" +m1.group(0);
                //editor.putString("ca",ca);
                //editor.commit();
                Log.e("1235-1.1","ca instant is " + ca);


                while(m2.find()) {
                    Log.e("1235-2",m2.group());//默认是group(0)
                    String reorderlist_ex = "," + m2.group() + ",";
                    Matcher m3 = p3.matcher(reorderlist_ex);
                    while(m3.find()) {
                        Log.e("1235-3.1",m3.group());
                        //checkbox1.setChecked(true);

                        switch (m3.group()) {
                            case "2131230819":
                                checkbox1.setChecked(true);
                                //记得写break，要不全执行一遍，采坑...
                                break;
                            case "2131230821":
                                checkbox2.setChecked(true);
                                break;
                            case "2131230822":
                                checkbox3.setChecked(true);
                                break;
                            case "2131230823":
                                checkbox4.setChecked(true);
                                break;
                            case "2131230824":
                                checkbox5.setChecked(true);
                                break;
                            case "2131230825":
                                checkbox6.setChecked(true);
                                break;
                            case "2131230826":
                                checkbox7.setChecked(true);
                                break;
                            default:
                                Log.e("1235-3.2",m3.group());
                                break;

                        }



                    }
                    Log.e("1223-2.","CA in m2 is " + ca);
                }

                Log.e("1223-2.","CA in m1 is " + ca);
            }
            Log.e("1223-2.","CA in out while is " + ca);

            //TODO 正则匹配获取传过来的checkbox id，获取id的数量，然后for循环选中，之后
            // 清空shareP，或者直接用intent
            //还需要checkboxid 转化成chk_id，可能，if语句实现吧
            //重新初始化
            editor.putInt("reorder", 0);
            editor.putString("reorder_list","");
            editor.commit();
            Log.e("1223-1","after:record value is set to " + reorder);

            //获取的是顺序订单传过来的
            //ca = sharedPreferences.getString("ca", "02");
            //Log.e("1223-2.","CA Value " + ca);
            //最后设置就有效
            //ca = "pizza";
        }else {
            ca = sharedPreferences.getString("ca", "02");
            Log.e("1223-2.","CA Value " + ca);
        }

        //String cas = "";
        Log.e("1220-3","now ca is :" + ca);
        //What the fuck? 异步？
        //已解决，if的原因，java的一些特性。
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
                    Log.e("1220-1", String.valueOf(i));
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
                    Log.e("1220-2", String.valueOf(i));
                }
                break;
            case "sundae":
                this.setTitle("Ordering a Sundae ($2.9)");
                checkbox1.setText("more ice1");
                checkbox2.setText("more ice2");
                checkbox3.setText("more ice3");
                checkbox4.setText("more ice4");
                checkbox5.setText("more ice5");
                for(int i = 5;i<=9;i++) {
                    CheckBox boxid = findViewById(chk_id[i]);
                    boxid.setVisibility(View.GONE);
                    Log.e("1220-3", String.valueOf(i));
                }
                break;
            default:
                this.setTitle("Please Ordering Someing,loll");
                Log.e("1220-", "ca is"+ ca + "");
                break;
        }

        //justify is apk started just now?
        Intent intent_backcode = new Intent();
        intent_backcode.putExtra("back_code_string","back_code_string:3");
        setResult(2,intent_backcode);
        //
        Intent intent_get_request_code = getIntent();
        String values = intent_get_request_code.getStringExtra("data");
        Log.e("intent2",values);
        //
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
        //ArrayList<String> order_hist = new ArrayList<>();
        String msg = "";
        String msg2 = "";
        String msg20 = "";
        String order2 = "";
        String order20 = "";
        double price = 0;
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        for (CompoundButton chk : selected) {
            msg = "Customise your order($" + nf.format(count_num * 1.9) + " )";
            //this.setTitle("Customise your order($" + nf.format(count_num *1.9) + " each)");
            //Toast.makeText(this, chk.getText() + "1213", Toast.LENGTH_SHORT).show();
            if (count_num == 1) {
                msg20 = "" + chk.getText();
                //extra food in every order
                order2 = "" + chk.getId();
                order20 = "" + order2;
                //order_hist.add(order20);
                Log.e("1225-1", order20);

            } else {
                msg2 += chk.getText() + ",";
                msg20 = msg2.substring(0, msg2.length() - 1);
                //extra food in every order
                order2 += chk.getId() + ",";
                //@不能在这里加，因为当只选一个小菜的时候会删掉@...
                //order20 = order2.substring(0, order2.length() - 1) + "@";
                order20 = order2.substring(0, order2.length() - 1);
                //order_hist.add(order20);
                Log.e("1225-2", order20);
            }
            //msg2 += "," + chk.getText();
        }
        //Avrrylist ?
        //no ,不用arraylist，shareP传不过去直接，所以用正则了
        /*
        order_hist.add(order20);
        order_hist.add(1,order20);
        Log.e("1226-0",order_hist.get(0));
        Log.e("1226-1",order_hist.get(1));

         */
        //
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
        editor.putString("1214-2.4",order20);
        editor.putFloat("1214-2.2", (float) price);
        editor.putInt("1214-2.3", count_num);
        //步骤4：提交
        editor.commit();
        //
        Log.e("1214-2.1.msg", msg);
        Log.e("1214-2.2.msg20", msg20);
        Log.e("1214-2.3.order20", order20);
        //-------------------------------------------------------
        if (msg.length() == 0) {
            msg = "Customise your order!";
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
                //OK了家人们：
                // 刚开始有进展的时候写了这句话
                SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
                //String userId1=sharedPreferences.getString("ca","");
                /*
                int userId1 = sharedPreferences.getInt("ids", 0);
                String userId2 = sharedPreferences.getString("ca0", "");
                Log.e("1206-2", String.valueOf(userId1));
                if (userId2 == null) {
                    Log.e("1206-2.1", "userId2 is null");
                } else {
                    Log.e("1206-2.2", "userId2 is unknow");
                }
                */

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

    //can not exec, but it worked???
    //已解决，接收的问题，接收的activity在后台，除了toast，Log.e不显示。
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
