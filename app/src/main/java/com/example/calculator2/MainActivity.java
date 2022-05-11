package com.example.calculator2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ///실수 a,b 및 연산자 char을 받아
    ///연산자에 맞는 계산결과 리턴
    double Calc(double a, double b, char operator){
        switch (operator){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '×':
                return a*b;
            case '÷':
                return a/b;
        }
        return 0;
    }
    //연산자 저장 변수
    char operator;
    //이전 값 저장 변수
    double calcTemp;

    TextView textView;
    EditText editText;

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_dot;
    //숫자버튼 및 소수점(.)버튼 이벤트 리스너
    class NumpadOnClickListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            String buttonText = button.getText().toString();
            System.out.print(buttonText);
            textView.setText(textView.getText().toString()+buttonText);
            
            //editText가 가진 문자열 전달
            String strEditText = editText.getText().toString();

            //소수점 버튼 처리
            if(view.getId() == R.id.btn_dot){
                //소수점 1개만 찍히도록 예외처리
                if(!(strEditText.contains("."))){
                    editText.setText(strEditText + ".");
                }
            }

            //누른 버튼의 숫자를 가능하다면 정수로 변환
            int buttonNumber;
            try{
                buttonNumber = Integer.parseInt(buttonText);
            }
            catch (NumberFormatException e){
                buttonNumber = -1;
            }
            //0~9번 버튼 처리
            if(buttonNumber >= 0 && buttonNumber <= 9){
                //editText에 "0"만 있을 경우 예외처리
                if(strEditText.equals("0")){
                    strEditText = "";
                }
                editText.setText(strEditText + buttonText);
            }
        }
    }

    Button btnClear, btnBackspace;
    //clear, backspace 버튼 이벤트 리스너
    class MiscOnClickListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String strEditText = editText.getText().toString();
            switch (view.getId()){
                case R.id.btn_clear:
                    editText.setText("0");
                    textView.setText("");
                    break;
                case R.id.btn_backspace:
                    //1글자 미만으로 지우지 못하게 예외 처리
                    if(strEditText.length() <= 1){
                        textView.setText("");
                        editText.setText("0");
                        break;
                    }
                    String s = strEditText;
                    //마지막 1글자씩 지우기
                    strEditText = s.substring(0, s.length()-1);
                    editText.setText(strEditText);
                    String text = textView.getText().toString();
                    textView.setText(text.substring(0, text.length()-1));
                    break;
            }
        }
    }

    Button btnAdd, btnSub, btnMul, btnDiv, btnResult;
    //사칙연산 및 결과(=) 버튼 이벤트 리스너
    class OperaterOnClickListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            //editText내용을 문자열로 전달 및 실수 로 형변환
            String str = editText.getText().toString();
            double num = Double.parseDouble(str);
            
            Button button = (Button)view;
            String buttonText = button.getText().toString();
            char buttonChar = buttonText.charAt(0);
            
            //사칙연산 기호(+, -, ...)버튼 처리
            char[] operatorArr = {'+', '-', '×', '÷'};            
            for (char oper: operatorArr) {
                if(buttonChar == oper){
                    System.out.print(oper);
                    textView.setText(textView.getText().toString()+oper);
                    operator = oper;
                    calcTemp = num;
                    editText.setText("0");
                    break;
                }
            }

            switch (view.getId()){
                //결과(=) 버튼 처리
                case R.id.btn_result:
                    double nowEdit = Double.parseDouble(editText.getText().toString());
                    //현재 입력값(nowEdit)과 이전 입력값(calcTemp) 및 연산자로 연산
                    double result = Calc(calcTemp, nowEdit, operator);
                    
                    //연산결과가 정수표현 가능하다면 정수형태로 문자열로 전달(ex: 1.0 -> 1)
                    String strResult = "";
                    if((int)result == result){
                        strResult += (int)result;
                    }
                    else{
                        strResult += result;
                    }
                    editText.setText(strResult);
                    System.out.println("\n="+strResult);
                    textView.setText(textView.getText().toString()+"=");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextNumber);

        //넘버패드(0~9, .) 리스너
        NumpadOnClickListner numpadOnclickListner = new NumpadOnClickListner();
        btn0 = findViewById(R.id.btn_0);
        btn0.setOnClickListener(numpadOnclickListner);
        btn1 = findViewById(R.id.btn_1);
        btn1.setOnClickListener(numpadOnclickListner);
        btn2 = findViewById(R.id.btn_2);
        btn2.setOnClickListener(numpadOnclickListner);
        btn3 = findViewById(R.id.btn_3);
        btn3.setOnClickListener(numpadOnclickListner);
        btn4 = findViewById(R.id.btn_4);
        btn4.setOnClickListener(numpadOnclickListner);
        btn5 = findViewById(R.id.btn_5);
        btn5.setOnClickListener(numpadOnclickListner);
        btn6 = findViewById(R.id.btn_6);
        btn6.setOnClickListener(numpadOnclickListner);
        btn7 = findViewById(R.id.btn_7);
        btn7.setOnClickListener(numpadOnclickListner);
        btn8 = findViewById(R.id.btn_8);
        btn8.setOnClickListener(numpadOnclickListner);
        btn9 = findViewById(R.id.btn_9);
        btn9.setOnClickListener(numpadOnclickListner);
        btn_dot = findViewById(R.id.btn_dot);
        btn_dot.setOnClickListener(numpadOnclickListner);

        //Clear, 백스페이스(<)리스너
        MiscOnClickListner miscOnclickListner = new MiscOnClickListner();
        btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(miscOnclickListner);
        btnBackspace = findViewById(R.id.btn_backspace);
        btnBackspace.setOnClickListener(miscOnclickListner);

        //사칙연산 연산자 리스너
        OperaterOnClickListner operaterOnClickListner = new OperaterOnClickListner();
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(operaterOnClickListner);
        btnSub = findViewById(R.id.btn_sub);
        btnSub.setOnClickListener(operaterOnClickListner);
        btnMul = findViewById(R.id.btn_mul);
        btnMul.setOnClickListener(operaterOnClickListner);
        btnDiv = findViewById(R.id.btn_div);
        btnDiv.setOnClickListener(operaterOnClickListner);
        btnResult = findViewById(R.id.btn_result);
        btnResult.setOnClickListener(operaterOnClickListner);
    }
}