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


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
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

    private int count_num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeorder);
        int[] chk_id = {R.id.checkbox1, R.id.checkbox2, R.id.checkbox3, R.id.checkbox4,
                R.id.checkbox5, R.id.checkbox6};
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int id : chk_id) {
            CheckBox chk = findViewById(id);
            chk.setOnCheckedChangeListener((OnCheckedChangeListener) this);
        }
        //----------------------------------------------------------------
        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkbox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkbox6 = (CheckBox) findViewById(R.id.checkbox6);
        ImageView setimg = (ImageView) findViewById(R.id.image1);
        //---------------------------------------------------------------
        //check if order history
        int reorder = sharedPreferences.getInt("reorder",0);
        Log.e("1223","before:record value is set to " + reorder);
        String ca = "0";
        if (reorder == 1) {
            String reorderlist = sharedPreferences.getString("reorder_list", "");
            Log.e("reorder_list",reorderlist);
            String rgex = "(\\*).*?(@)";
            String rgex2 = ".*?(?=,E)";
            String rgex3 = "([^:]+)$";
            String rgex4 = "(?<=,).*?(?=,)";
            String str = reorderlist;

            Pattern p1 = Pattern.compile(rgex2);
            Pattern p2 = Pattern.compile(rgex3);
            Pattern p3 = Pattern.compile(rgex4);

            Matcher m1 = p1.matcher(str);
            Matcher m2 = p2.matcher(str);

            if(m1.find()) {

                Log.e("1235-1",m1.group(0));
                ca = "" +m1.group(0);
                Log.e("1235-1.1","ca instant is " + ca);

                while(m2.find()) {
                    Log.e("1235-2",m2.group());
                    String reorderlist_ex = "," + m2.group() + ",";
                    Matcher m3 = p3.matcher(reorderlist_ex);
                    while(m3.find()) {
                        Log.e("1235-3.1",m3.group());

                        switch (m3.group()) {
                            case "2131230819":
                                checkbox1.setChecked(true);
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

            editor.putInt("reorder", 0);
            editor.putString("reorder_list","");
            editor.commit();
            Log.e("1223-1","after:record value is set to " + reorder);

        }else {
            ca = sharedPreferences.getString("ca", "02");
            Log.e("1223-2.","CA Value " + ca);
        }

        Log.e("1220-3","now ca is :" + ca);
        switch (ca) {
            case "burger":
                setimg.setImageResource(R.drawable.hanber);
                this.setTitle("Ordering a Burger ($2.9)");
                checkbox1.setText("A egg");
                checkbox2.setText("Pork");
                checkbox3.setText("Cheese");
                checkbox4.setText("Beef");
                checkbox5.setText("Sausage");
                checkbox6.setText("Lettuce");
                break;
            case "pizza":
                setimg.setImageResource(R.drawable.pizza);
                this.setTitle("Ordering a Pizza ($2.9)");
                checkbox1.setText("Tomato");
                checkbox2.setText("Bacon piece");
                checkbox3.setText("Olive");
                checkbox4.setText("Beef piece");
                checkbox5.setText("Pinciple");
                checkbox6.setText("Burgermeet");
                break;
            case "sundae":
                this.setTitle("Ordering a Sundae ($2.9)");
                setimg.setImageResource(R.drawable.sunda);
                checkbox1.setText("Banana");
                checkbox2.setText("Caramel Sauce");
                checkbox3.setText("Chopped Nuts");
                checkbox4.setText("More ice");
                checkbox5.setText("Pinciple");
                checkbox6.setText("Strawberry");
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
        if (isChecked) {
            selected.add(compoundButton);
            count_num += 1;
        } else {
            selected.remove(compoundButton);
            count_num -= 1;
        }
        Log.e("count_num_new", String.valueOf(count_num));
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
            if (count_num == 1) {
                msg20 = "" + chk.getText();
                //extra food in every order
                order2 = "" + chk.getId();
                order20 = "" + order2;
                Log.e("1225-1", order20);

            } else {
                msg2 += chk.getText() + ",";
                msg20 = msg2.substring(0, msg2.length() - 1);
                //extra food in every order
                order2 += chk.getId() + ",";
                order20 = order2.substring(0, order2.length() - 1);
                //order_hist.add(order20);
                Log.e("1225-2", order20);
            }
            //msg2 += "," + chk.getText();
        }
        double base_price = 2.9;
        price = base_price + Double.parseDouble(nf.format(count_num * 1.9));
        Log.e("1214", String.valueOf(count_num));
        SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("1214-2.1", msg20);
        editor.putString("1214-2.4",order20);
        editor.putFloat("1214-2.2", (float) price);
        editor.putInt("1214-2.3", count_num);
        editor.commit();
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
        int id = item.getItemId();
        //----------------------------------------------------------------
        checkbox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkbox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkbox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkbox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkbox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkbox6 = (CheckBox) findViewById(R.id.checkbox6);
        //---------------------------------------------------------------
        Log.e("1209", String.valueOf(item));

        switch (item.getItemId()) {
            case R.id.action_settings1:
                //Toast.makeText(this, "action_settings1", Toast.LENGTH_SHORT).show();
                checkbox1.setChecked(true);
                checkbox2.setChecked(true);
                checkbox3.setChecked(true);
                checkbox4.setChecked(true);
                checkbox5.setChecked(true);
                checkbox6.setChecked(true);

                SharedPreferences sharedPreferences = getSharedPreferences("data2", Context.MODE_PRIVATE);

                break;
            case R.id.action_settings2:
                //Toast.makeText(this, "action_settings2", Toast.LENGTH_SHORT).show();
                checkbox1.setChecked(false);
                checkbox2.setChecked(false);
                checkbox3.setChecked(false);
                checkbox4.setChecked(false);
                checkbox5.setChecked(false);
                checkbox6.setChecked(false);
                //---------------------------------
                SharedPreferences sharedPreferences2 = getSharedPreferences("data", Context.MODE_PRIVATE);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("1211", "1211");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request) {
            if (data != null) {
                String sData = data.getStringExtra("data");
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
