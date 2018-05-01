package com.example.variacion.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;
import java.util.Vector;


public class CalculateMenu extends AppCompatActivity {

    String Operations = "";

    protected void SetView(final TextView resultView) {
        int startView = 0;
        if (Operations.length() > 20)
            startView = Operations.length() - 20;
        resultView.setText(Operations.substring(startView));
    }

    protected void ClearButton(final TextView operationsView, final TextView resultView) {
        Button clearButton = findViewById(R.id.ClearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operationsView.setText("");
                resultView.setText("");
                Operations = "";
            }
        });
    }

    protected void DotButton(final TextView resultView) {
        Button dotButton = findViewById(R.id.DotButton);
        dotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Operations.length() <= 0)
                    return;

                char getCharInOperation;
                for (int i = Operations.length() - 1; i >= 0; --i) {
                    getCharInOperation = Operations.charAt(i);
                    if (getCharInOperation == '+' || getCharInOperation == '-' ||
                            getCharInOperation == 'x' || getCharInOperation == '/')
                        break;
                    if (getCharInOperation == '.')
                        return;
                }

                Operations += ".";
                SetView(resultView);
            }
        });
    }

    protected void ChangeSignedButton(final TextView resultView) {
        Button changeSignedButton = findViewById(R.id.ChangeSignedButton);
        changeSignedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Operations.length() <= 0)
                    return;

                int size = Operations.length();
                Vector<Character>getOperator = new Vector<>(size);
                for (int i = 0; i < size; ++i)
                    getOperator.add(Operations.charAt(i));

                if (getOperator.size() == 1 && getOperator.get(0) > 47 && getOperator.get(0) < 58)
                    getOperator.insertElementAt('-', 0);
                else {
                    for (int i = getOperator.size() - 2; i >= 0; --i) {
                        if (i == 0) {
                            if (getOperator.get(i) == '-')
                                getOperator.remove(0);
                            else
                                getOperator.insertElementAt('-', 0);
                            break;
                        } else if (getOperator.get(i) == '+') {
                            getOperator.setElementAt('-', i);
                            break;
                        } else if (getOperator.get(i) == '-') {
                            if (getOperator.get(i - 1) < 48 || getOperator.get(i - 1) > 57)
                                getOperator.remove(i);
                            else
                                getOperator.setElementAt('+', i);
                            break;
                        } else if (getOperator.get(i) == 'x' || getOperator.get(i) == '/' ||
                                getOperator.get(i) == '%') {
                            if (getOperator.get(i + 1) != '-')
                                getOperator.insertElementAt('-', i + 1);
                            else
                                getOperator.remove(i + 1);
                            break;
                        }
                    }
                }

                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < getOperator.size(); ++i)
                    temp.append(getOperator.get(i).toString());

                Operations = temp.toString();
                SetView(resultView);
            }
        });
    }

    protected void PlusButton(final TextView resultView) {
        Button plusButton = findViewById(R.id.PlusButton);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Operations.length() <= 0)
                    return;

                char getLastCharacter = Operations.charAt(Operations.length() - 1);
                if (getLastCharacter == '+' || getLastCharacter == '-' ||
                        getLastCharacter == 'x' || getLastCharacter == '/')
                    Operations = Operations.substring(0, Operations.length() - 1);

                Operations += "+";
                SetView(resultView);
            }
        });
    }

    protected void MinusButton(final TextView resultView) {
        Button minusButton = findViewById(R.id.MinusButton);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Operations.length() == 1 && Operations.charAt(0) == '-')
                    return;

                if (Operations.length() > 0 && Operations.charAt(Operations.length() - 1) == '+')
                    Operations = Operations.substring(0, Operations.length() - 1);

                Operations += "-";
                SetView(resultView);
            }
        });
    }

    protected void MultiplyButton(final TextView resultView) {
        Button multiplyButton = findViewById(R.id.MultiplyButton);
        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Operations.length() <= 0)
                    return;

                char getLastCharacter = Operations.charAt(Operations.length() - 1);
                if (getLastCharacter == '+' || getLastCharacter == '-' ||
                        getLastCharacter == 'x' || getLastCharacter == '/')
                    Operations = Operations.substring(0, Operations.length() - 1);

                Operations += "x";
                SetView(resultView);
            }
        });
    }

    protected void DivideButton(final TextView resultView) {
        Button divideButton = findViewById(R.id.DivideButton);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Operations.length() <= 0)
                    return;

                char getLastCharacter = Operations.charAt(Operations.length() - 1);
                if (getLastCharacter == '+' || getLastCharacter == '-' ||
                        getLastCharacter == 'x' || getLastCharacter == '/')
                    Operations = Operations.substring(0, Operations.length() - 1);

                Operations += "/";
                SetView(resultView);
            }
        });
    }

    protected void PercentButton(final TextView resultView) {
        Button percentButton = findViewById(R.id.PercentButton);
        percentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Operations.length() <= 0)
                    return;
                char getLastCharacter = Operations.charAt(Operations.length() - 1);
                if (getLastCharacter == '+' || getLastCharacter == '-' ||
                        getLastCharacter == 'x' || getLastCharacter == '/')
                    return;

                Operations += "%";
                SetView(resultView);
            }
        });
    }

    protected void DeleteButton(final TextView resultView, final TextView operationsView) {
        Button deleteButton = findViewById(R.id.DeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Operations.length() > 0)
                    Operations = Operations.substring(0, Operations.length() - 1);
                SetView(resultView);
            }
        });
    }

    protected void ZeroButton(final TextView resultView) {
        Button zeroButton = findViewById(R.id.ZeroButton);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "0";
                SetView(resultView);
            }
        });
    }

    protected void OneButton(final TextView resultView) {
        Button OneButton = findViewById(R.id.OneButton);
        OneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "1";
                SetView(resultView);
            }
        });
    }

    protected void TwoButton(final TextView resultView) {
        Button twoButton = findViewById(R.id.TwoButton);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "2";
                SetView(resultView);
            }
        });
    }

    protected void ThreeButton(final TextView resultView) {
        Button threeButton = findViewById(R.id.ThreeButton);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "3";
                SetView(resultView);
            }
        });
    }

    protected void FourButton(final TextView resultView) {
        Button fourButton = findViewById(R.id.FourButton);
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "4";
                SetView(resultView);
            }
        });
    }

    protected void FiveButton(final TextView resultView) {
        Button fiveButton = findViewById(R.id.FiveButton);
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "5";
                SetView(resultView);
            }
        });
    }

    protected void SixButton(final TextView resultView) {
        Button sixButton = findViewById(R.id.SixButton);
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "6";
                SetView(resultView);
            }
        });
    }

    protected void SevenButton(final TextView resultView) {
        Button sevenButton = findViewById(R.id.SevenButton);
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "7";
                SetView(resultView);
            }
        });
    }

    protected void EightButton(final TextView resultView) {
        Button eightButton = findViewById(R.id.EightButton);
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "8";
                SetView(resultView);
            }
        });
    }

    protected void NineButton(final TextView resultView) {
        Button nineButton = findViewById(R.id.NineButton);
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Operations += "9";
                SetView(resultView);
            }
        });
    }

    private double Calculate(Stack<Double> operands, Stack<Character> operators) {
        double firstNum, secondNum, thirdNum;
        while (!operators.empty()) {
            if (operators.peek() == 'x' || operators.peek() == '/') {
                firstNum = operands.pop();
                secondNum = operands.pop();
                if (operators.peek() == '/' && secondNum == 0) {
                    operators.push('!');
                    return 0;
                }

                if (operators.peek() == 'x')
                    operands.push(firstNum * secondNum);
                else
                    operands.push(firstNum / secondNum);
                operators.pop();
            } else {
                char firstOperator = operators.peek();
                operators.pop();
                if (operators.empty() || operators.peek() == '+' || operators.peek() == '-') {
                    firstNum = operands.peek();
                    operands.pop();
                    secondNum = operands.peek();
                    operands.pop();
                    if (firstOperator == '+')
                        operands.push(firstNum + secondNum);
                    else
                        operands.push(firstNum - secondNum);
                } else {
                    char secondOperator = operators.peek();
                    operators.pop();
                    operators.push(firstOperator);
                    firstNum = operands.peek();
                    operands.pop();
                    secondNum = operands.peek();
                    operands.pop();
                    thirdNum = operands.peek();
                    operands.pop();
                    if (secondOperator == 'x')
                        operands.push(secondNum * thirdNum);
                    else {
                        if (secondOperator == '/' && thirdNum == 0) {
                            operators.push('!');
                            return 0;
                        }
                        operands.push(secondNum / thirdNum);
                    }
                    operands.push(firstNum);
                }
            }
        }
        return operands.peek();
    }

    protected void EqualButton(final TextView resultView, final TextView operationsView) {
        Button equalButton = findViewById(R.id.EqualButton);
        equalButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (Operations.equals("04121999")) {
                    Intent intent = new Intent(CalculateMenu.this, EasterEggsMenu.class);
                    startActivity(intent);
                }
                if (Operations.length() == 0)
                    return;
                else if ((Operations.charAt(Operations.length() - 1) <= 47 ||
                        Operations.charAt(Operations.length() - 1) >= 58) &&
                        Operations.charAt(Operations.length() - 1) != '.') {
                    operationsView.setText("INVALID OPERATIONS");
                    return;
                } else if (Operations.charAt(Operations.length() - 1) == '.')
                    Operations += "0";

                Stack<Character> operators = new Stack<>();
                Stack<Double> operands = new Stack<>();
                StringBuilder convert = new StringBuilder();
                boolean hasPercentChar = false;

                for (int i = Operations.length() - 1; i >= 0; --i) {
                    if (Operations.charAt(i) == 'x' || Operations.charAt(i) == '/') {
                        operators.push(Operations.charAt(i));
                        if (!hasPercentChar)
                            operands.push(Double.parseDouble(convert.toString()));
                        else
                            operands.push(Double.parseDouble(convert.toString()) / 100);
                        hasPercentChar = false;
                        convert.delete(0, convert.length());
                    } else if (Operations.charAt(i) == '%') {
                        if (Operations.charAt(i + 1) <= 47 || Operations.charAt(i + 1) >= 58)
                            hasPercentChar = true;
                        else {
                            operationsView.setText("INVALID OPERATORS");
                            return;
                        }
                    }
                    else if ((Operations.charAt(i) > 47 && Operations.charAt(i) < 58) ||
                            Operations.charAt(i) == '.')
                        convert.insert(0, Operations.charAt(i));
                    else {
                        if ((i == 0 || Operations.charAt(i - 1) <= 47 || Operations.charAt(i - 1) >= 58)
                                && Operations.charAt(i - 1) != '%')
                            convert.insert(0, Operations.charAt(i));
                        else {
                            operators.push(Operations.charAt(i));
                            if (!hasPercentChar)
                                operands.push(Double.parseDouble(convert.toString()));
                            else
                                operands.push(Double.parseDouble(convert.toString()) / 100);
                            convert.delete(0, convert.length());
                            hasPercentChar = false;
                        }
                    }
                }

                if (!hasPercentChar)
                    operands.push(Double.parseDouble(convert.toString()));
                else
                    operands.push(Double.parseDouble(convert.toString()) / 100);

                if (operands.size() != operators.size() + 1) {
                    operationsView.setText("INVALID OPERATORS");
                    return;
                }

                double result = Calculate(operands, operators);
                if (!operators.empty()) {
                    operationsView.setText("CAN NOT DIVIDE BY 0");
                    return;
                }

                resultView.setText(Double.toString(result));
                Operations = Double.toString(result);
                operationsView.setText(Operations);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_menu);

        TextView operationsView = findViewById(R.id.OperationsTextView);
        TextView resultView = findViewById(R.id.ResultTextView);

        ClearButton(operationsView, resultView);
        DeleteButton(resultView, operationsView);

        DotButton(resultView);
        ChangeSignedButton(resultView);

        ZeroButton(resultView);
        OneButton(resultView);
        TwoButton(resultView);
        ThreeButton(resultView);
        FourButton(resultView);
        FiveButton(resultView);
        SixButton(resultView);
        SevenButton(resultView);
        EightButton(resultView);
        NineButton(resultView);

        PlusButton(resultView);
        MinusButton(resultView);
        MultiplyButton(resultView);
        DivideButton(resultView);
        PercentButton(resultView);

        EqualButton(resultView, operationsView);
    }
}
