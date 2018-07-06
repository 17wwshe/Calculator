package com.example.administrator.newcalculator;

import android.support.v7.app.AppCompatActivity;

public class Counter_Detail extends AppCompatActivity {

    Counter_Detail()
    {

    }
    //传入了最开始完整的str
    public String counter(String str)
    {
        String str_Result;
        double tmp_result;
        int lastOp_seat=0,nextOp_seat=0;
        String flag;
        //定义变量
        int seat;
        String str_left ="",str_right ="";
        double num_left,num_right;

        while (str.contains("×")||str.contains("÷"))   //四则运算先乘除后加减，先判断有没有乘除运算
        {
            int mul_seat = str.indexOf("×");
            int div_seat = str.indexOf("÷");   //找到第一个乘号和除号的位置

            if ((mul_seat!=-1)&&(div_seat ==-1))  //没有乘号而有除号，则第一个为除号
            {
                seat = mul_seat;
            }
            else
            {
                if ((mul_seat ==-1)&&(div_seat)!=-1)  //没有除号而有乘号
                {
                    seat = div_seat;
                }
                else
                if (mul_seat>div_seat)  //两个都存在的情况，那个符号的位置先则先算哪一个
                {
                    seat = div_seat;
                }
                else
                {
                    seat = mul_seat;
                }
            }
            boolean leftOpExist = false;   //判断第一个乘除号左边有没有符号
            for (int i = seat;i>0;i--)              //从第一个乘除号开始，如果下一个是加减，则赋值为左符号
            {
                if (str.substring(i-1,i).equals("+")||(str.substring(i-1,i).equals("-")))  //第一个乘除号左边只可能是加减
                {
                    lastOp_seat = i-1;      //上一个加减符号的位置
                    str_left = str.substring(lastOp_seat+1,seat);  //左边的数字，由左边的符号和当前的乘除号之间取出
                    leftOpExist = true;
                    break;
                }
            }
            if (!leftOpExist)   //如果左边没有符号
            {
                lastOp_seat = -1;
                str_left = str.substring(0,seat);  //左边即是左边数字
            }

            for (int i = seat+1;i<str.length();i++) //从第一个乘除号的下一个位置开始，如果下一个是加减，则赋值为右符号
            {
                if (str.substring(i,i+1).equals("+")||str.substring(i,i+1).equals("-")||str.substring(i,i+1).equals("×")||str.substring(i,i+1).equals("÷"))
                {
                    nextOp_seat = i;
                    str_right = str.substring(seat+1,nextOp_seat);
                    break;
                }
                else
                {
                    nextOp_seat = str.length();
                    str_right = str.substring(seat+1,nextOp_seat);
                }
            }

            //若左右两边的数字都存在，则将提取出来的字符串转为数字;
            if (!(str_left.equals("")&&str_right.equals("")))
            {
                num_left = Double.valueOf(str_left);
                num_right = Double.valueOf(str_right);
                if (seat == div_seat)
                {
                    tmp_result = num_left/num_right;
                }
                else
                {
                    tmp_result = num_left*num_right;
                }
                String replaced = str.substring(lastOp_seat+1,nextOp_seat);   //将此时运算部分的字符串提取
                String replace = Double.toString(tmp_result);                   //将运算的结果提取

                str = str.replace(replaced,replace);                            //将运算结果替代原始的运算部分，成为新的算式，继续while循环

            }
        }

        while (str.contains("+")||str.contains("-"))   //加减法有做开头符号
        {
            boolean opExist = false;
            flag = "false";
            if (str.startsWith("-")||str.startsWith("+"))
            {
                flag = "true";
            }
            if (flag.equals("true"))        //以加号或减号开头
            {
                String op=null;
                int nextOp_seat_2;
                nextOp_seat_2 = str.length();  //如果没有找到第二个表示运算的加减号，则赋值为字符串长度表示结束
                num_left = 0;
                num_right = 0;
                nextOp_seat = str.length();     //
                seat = 0;


                for (int i=1;i<str.length();i++)   //找到下一个加减号
                {
                    if (str.substring(i,i+1).equals("+")||str.substring(i,i+1).equals("-"))
                    {
                        seat = i;
                        if (!opExist)
                        {
                            opExist = true;
                            op = str.substring(i,i+1);
                        }
                        else                            //能找到下一个加减号，则继续找下一个加减号
                        {
                            opExist = true;
                            nextOp_seat_2 = i;
                            nextOp_seat = nextOp_seat_2;
                            break;
                        }
                    }
                }

                if (!opExist)           //只有表正负的加减号
                {
                    tmp_result = Double.valueOf(str);
                    break;
                }
                else
                {
                    str_right = str.substring(seat+1,nextOp_seat_2);
                    num_right = Double.valueOf(str_right);
                    str_left = str.substring(0,seat);
                    num_left = Double.valueOf(str_left);

                    if (op.equals("+"))
                    {
                        tmp_result = num_left+num_right;
                    }
                    else
                    {
                        tmp_result = num_left-num_right;
                    }
                }

            }
            //不是以符号开头
            else
            {
                int add_seat = str.indexOf("+");
                int sub_seat = str.indexOf("-");

                if ((add_seat==-1)&&(sub_seat!=-1)) seat = sub_seat;
                else
                if ((add_seat!=-1)&&(sub_seat==-1))  seat = add_seat;
                else
                if (add_seat<sub_seat)
                {
                    seat = add_seat;
                }
                else seat = sub_seat;
                for (int i=seat+1;i<str.length();i++)
                {
                    if (str.substring(i,i+1).equals("+")||str.substring(i,i+1).equals("-"))
                    {
                        nextOp_seat = i;break;
                    }
                    else nextOp_seat = str.length();
                }
                str_left = str.substring(0,seat);

                str_right = str.substring(seat+1,nextOp_seat);

                num_left = Double.valueOf(str_left);
                num_right = Double.valueOf(str_right);
                if (seat == add_seat)
                {
                    tmp_result = num_left+num_right;
                }
                else
                {
                    tmp_result = num_left-num_right;
                }
            }
            String replaced = str.substring(0,nextOp_seat); //替代字符串
            String replace = Double.toString(tmp_result);
            str = str.replace(replaced,replace);
            if (flag.equals("true")&&!opExist) break;

        }

        str_Result = str;
        return str_Result;
    }
}


