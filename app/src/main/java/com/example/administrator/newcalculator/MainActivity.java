package com.example.administrator.newcalculator;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Double.parseDouble;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //组件设置
    private Button bt0, bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9;
    private Button add, sub, div, multiply, reset, cancel, percent, point, calculate;
    private Button square, cube, Nth, sin, cos, tan, lg, ln, du, factorial, extract, reciprocal;
    //已经存在的文本
    private String str = "0";
    private boolean clear_flag = false;
    String result;
    /**
     * 结果
     */
    private TextView text;
    final String TAG ="--MainActivity--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //利用log视察横竖屏状态，并初始化组件
        //横竖屏组件个数不一样，不用同一个方法，避免空指针
        int Orientation;
        Orientation = getResources().getConfiguration().orientation;
        Log.d("----", "" + Orientation);
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            // If current screen is portrait
            Log.i("info", "portrait"); // 竖屏日志
            initView_por();
            initEvent_por();  //初始化竖屏组件

        } else if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            //If current screen is landscape
            Log.i("info", "landscape"); // 横屏日志
            initView_por();
            initView_land();
            initEvent_por();  //初始化竖屏组件
            initEvent_land();  //初始化横屏组件
        }
    }

    @Override
    protected void onPause() {

        super.onPause();

    }

    //与onCreate中的log功能一样
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    //竖屏初始化组件
    private void initView_por() {
        /*
        数字按钮初始化
         */
        bt0 = (Button) findViewById(R.id.bt_0);
        bt1 = (Button) findViewById(R.id.bt_1);
        bt2 = (Button) findViewById(R.id.bt_2);
        bt3 = (Button) findViewById(R.id.bt_3);
        bt4 = (Button) findViewById(R.id.bt_4);
        bt5 = (Button) findViewById(R.id.bt_5);
        bt6 = (Button) findViewById(R.id.bt_6);
        bt7 = (Button) findViewById(R.id.bt_7);
        bt8 = (Button) findViewById(R.id.bt_8);
        bt9 = (Button) findViewById(R.id.bt_9);
        /*
           运算符按钮初始化
         */
        add = (Button) findViewById(R.id.bt_add);
        sub = (Button) findViewById(R.id.bt_sub);
        div = (Button) findViewById(R.id.bt_div);
        multiply = (Button) findViewById(R.id.bt_multiply);
        reset = (Button) findViewById(R.id.bt_C);
        cancel = (Button) findViewById(R.id.bt_DEL);
        percent = (Button) findViewById(R.id.bt_per);
        point = (Button) findViewById(R.id.bt_point);
        calculate = (Button) findViewById(R.id.bt_equals);

        text = (TextView) findViewById(R.id.textView2);
        //
    }
    //竖屏监听器
    private void initEvent_por() {
        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        div.setOnClickListener(this);
        multiply.setOnClickListener(this);
        reset.setOnClickListener(this);
        cancel.setOnClickListener(this);
        percent.setOnClickListener(this);
        point.setOnClickListener(this);
        calculate.setOnClickListener(this);
    }



    //横屏实现按钮的初始化
    private void initView_land() {
        square = (Button) findViewById(R.id.bt_square);
        cube = (Button) findViewById(R.id.bt_cube);
        Nth = (Button) findViewById(R.id.bt_Nth);
        sin = (Button) findViewById(R.id.bt_sin);
        cos = (Button) findViewById(R.id.bt_cos);
        tan = (Button) findViewById(R.id.bt_tan);
        lg = (Button) findViewById(R.id.bt_lg);
        ln = (Button) findViewById(R.id.bt_ln);
        du = (Button) findViewById(R.id.bt_du);
        factorial = (Button) findViewById(R.id.bt_factory);
        extract = (Button) findViewById(R.id.bt_sqrt);
        reciprocal = (Button) findViewById(R.id.bt_dao);

            /*
            文本框初始化
             */
        text = (TextView) findViewById(R.id.textView2);


    }

    //横屏监听器
    private void initEvent_land() {
        square.setOnClickListener(this);
        cube.setOnClickListener(this);
        Nth.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        ln.setOnClickListener(this);
        lg.setOnClickListener(this);
        du.setOnClickListener(this);
        factorial.setOnClickListener(this);
        extract.setOnClickListener(this);
        reciprocal.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v 点击的控件
     */
    @Override
    public void onClick(View v) {

        str = text.getText().toString();
        switch (v.getId()) {
            /*
             * 数字
             */
            case R.id.bt_0://0
            case R.id.bt_1://1
            case R.id.bt_2://2
            case R.id.bt_3://3
            case R.id.bt_4://4
            case R.id.bt_5://5
            case R.id.bt_6://6
            case R.id.bt_7://7
            case R.id.bt_8://8
            case R.id.bt_9://9
            case R.id.bt_point://.
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    text.setText("");
                }
                str += ((Button) v).getText();
                text.setText(str);
//                Toast.makeText(this,"click",Toast.LENGTH_LONG).show();  //如果被点击则提示“click”
                break;

            //可以实现连续计算，str+=。。。
            case R.id.bt_add://+
            case R.id.bt_sub://-
            case R.id.bt_multiply://*
            case R.id.bt_div:///
                str += ((Button) v).getText();      //没有加空格了
                text.setText(str);
                clear_flag = false;
                break;
            //运算符输入时在两端加空格，以标志，在之后可以根据空格的位置，提取出运算符和数字
            //删除，判断字符串长度，0和1都置为0，大于1则长度减少一位
            case R.id.bt_DEL:  //DEL
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    text.setText("");
                }
                if (str != null && !str.equals("")) {
                    text.setText(str.substring(0, str.length() - 1));
                }
                break;
            //clean
            case R.id.bt_C:  //C
                clear_flag = false;
                str = "";
                text.setText(str);
                break;

            //%
            case R.id.bt_per:
                String string;
                if (!str.equals("")){
                    result = Double.toString((Double.valueOf(str)) / 100.0);
                    clear_flag = false;
                    string = result+"";

                }
                else {
                    string = "error";
                    return;
                }
                text.setText(string);
                break;

            //=按钮
            case R.id.bt_equals:
                getResult();
                break;

            //平方
            case R.id.bt_square:
                if (str.length() != 0) {
                    double temp = Double.valueOf(str);
                    result = Double.toString(temp * temp);
                } else {
                    result = "error";
                    return;
                }
                text.setText(result);
                break;

            //立方
            case R.id.bt_cube:
                if (str.length() != 0) {
                    double temp = Double.valueOf(str);
                    result = Double.toString(temp * temp * temp);
                } else {
                    result = "error";
                    return;
                }
                text.setText(result);
                break;

            //x的y次方
            case R.id.bt_Nth:
                if (str.length() != 0) {
                    str += "^";
                    text.setText(str);
                    clear_flag = false;
                } else {
                    result = "0";
                    return;
                }
                break;

            case R.id.bt_sin:  //sin
            case R.id.bt_cos:  //cos
            case R.id.bt_tan:  //tan
            case R.id.bt_lg:  //lg
            case R.id.bt_ln:  //ln
            case R.id.bt_factory:  //!
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    text.setText("");
                }
                str +=((Button) v).getText()+" ";
                text.setText(str);
                break;

            case R.id.bt_sqrt:  //开方
                if (str.length() != 0) {
                    double temp = Double.valueOf(str);
                    result = Double.toString(Math.sqrt(temp));
                } else
                    result = "0";
                //
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    text.setText("");
                }
                if (result.endsWith(".0"))
                {
                    result = result.substring(0,result.indexOf("."));
                }
                text.setText(result);
                break;

            case R.id.bt_dao: //倒数
                if (str.length() != 0) {
                    double temp = 1 / Double.valueOf(str);
                    temp = (double) Math.round(temp * 100) / 100;  //保留两位小数
                    result = Double.toString(temp);
                } else
                    result = "error";
                //
                if (clear_flag) {
                    clear_flag = false;
                    str = "";
                    text.setText("");
                }
                if (result.endsWith(".0"))
                {
                    result = result.substring(0,result.indexOf("."));
                }
                text.setText(result);
                break;
            case R.id.bt_du:  //度数
                str += ((Button) v).getText();                                   //度数！后期改
                text.setText(str);
                break;
        }
    }


    private void getResult() {


        /*
         * 两个String类型的参数
         */
        double param1;
//        String op=null;
        /*
         * 转换后的两个double类型的参数
         */

        /*
         * 如果有运算符，则进行运算
         * 没有运算符，则把已经存在的数据再传出去
         */
        if (str == null || str.equals("")) {
            return;
        }
        clear_flag = true;
//        t = 0 - arg2;
        Double result;
        String str_1;
        boolean tan_flag =true;


        //如果没有三角函数和对数指数运算，则进入多项式运算类中
        if (!(str.contains(" ")||str.contains("^")))
        {
            Counter_Detail all = new Counter_Detail();
            str_1 = all.counter(str);    					//传入文本框中的式子
//            result = Double.valueOf(str_1);

        }
        else
        {
            double tmp=0;

            if (str.contains("^")) 			//指数运算
            {
                String str_left ,str_right ;
                double num_left, num_right;
                int seat = str.indexOf("^");
                str_left = str.substring(0,seat);
                str_right = str.substring(seat+1,str.length());
                num_left = Double.valueOf(str_left);
                num_right = Double.valueOf(str_right);
                result = Math.pow(num_left, num_right);
            }
            else
            {
                if (str.contains("!"))
                {
                    param1 =Double.parseDouble(str.substring(0,str.indexOf("!")));
                    int tmpResult = 1;
                    for (int i = 1; i <= (int) param1; i++)
                        tmpResult = tmpResult * i;
                    result = (double) tmpResult;
                }
                else
                {
                    if (!str.contains("°"))			//三角函数的度数转换
                    {
                        //没有角度
                        param1 = Double.valueOf(str.substring(str.indexOf(" ")+1,str.length()));
                    }
                    else
                    {
                        //角度制的转变
                        tmp = parseDouble(str.substring(str.indexOf(" ")+1,str.indexOf("°")));
                        param1 = Math.toRadians(tmp);
                    }

                    if (str.contains("sin"))
                    {
                        result = (double)Math.round(Math.sin(param1)*100)/100;   //相当于：((double)Math.round(Math.sin(param1)*100))/100
                    }

                    else if (str.contains("cos"))
                    {
                        result = (double)Math.round(Math.cos(param1)*100)/100;
                    }
                    else if (str.contains("tan"))
                    {
                        if (str.contains("°")&&(tmp%90==0))			//处理tan值不存在的情况
                        {
                            tan_flag = false;
                            result = 0.0;
                        }
                        else
                            result = (double)Math.round(Math.tan(param1)*100)/100;
                    }
                    else if (str.contains("lg"))
                    {
                        result = Math.log10(param1);
                    }
                    else if (str.contains("ln"))
                    {
                        result = Math.log(param1);
                    }
                    else
                        result = 0.0;
                }

            }
            if (tan_flag)
                str_1 = Double.toString(result);
            else
                str_1 = "tan值不存在";
        }
        if (str_1.endsWith(".0"))					//能得到整数的就以整数的形式输出
        {
            str_1 = str_1.substring(0,str_1.indexOf("."));
        }
        str =str_1;		//将原式与等号、结果一起输出

        text.setText(str);

    }

}
