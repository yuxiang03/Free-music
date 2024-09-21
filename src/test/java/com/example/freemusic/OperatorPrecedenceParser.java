package com.example.freemusic;

import java.util.Stack;

public class OperatorPrecedenceParser {
    private static final char[] OPERATORS = {'i', '+', '*', '#'};
    private static final char[][] PRECEDENCE_TABLE = {
            //   i    +    *    #
            { ' ', '>', '>', '>' },  // i
            { '<', '>', '<', '>' },  // +
            { '<', '>', '>', '>' },  // *
            { '<', '<', '<', '=' }   // #
    };

    private static int getPrecedenceIndex(char symbol) {
        for (int i = 0; i < OPERATORS.length; i++) {
            if (OPERATORS[i] == symbol) {
                return i;
            }
        }
        return -1;
    }

    private static char getPrecedence(char top, char current) {
        int row = getPrecedenceIndex(top);
        int col = getPrecedenceIndex(current);
        if (row == -1 || col == -1) {
            throw new IllegalArgumentException("Invalid operator");
        }
        return PRECEDENCE_TABLE[row][col];
    }

    public static boolean syntaxAnalysis(String input) {
        Stack<Character> operatorStack = new Stack<>();
        Stack<Character> symbolStack = new Stack<>();
        operatorStack.push('#');

        int i = 0;
        System.out.printf("%-20s%-20s%-20s%-20s\n", "当前输入", "操作", "符号栈", "运算符栈");
        System.out.printf("%-20s%-20s%-20s%-20s\n", input, "初始化", symbolStack, operatorStack);

        while (i < input.length()) {
            char currentChar = input.charAt(i);

            if (Character.isLetterOrDigit(currentChar)) {
                symbolStack.push(currentChar);
                i++;
                System.out.printf("%-20s%-20s%-20s%-20s\n", input.substring(i), "移入: " + currentChar, symbolStack, operatorStack);
            } else {
                while (true) {
                    char topOperator = operatorStack.peek();
                    char precedence = getPrecedence(topOperator, currentChar);

                    if (precedence == '<' || precedence == '=') {
                        operatorStack.push(currentChar);
                        i++;
                        System.out.printf("%-20s%-20s%-20s%-20s\n", input.substring(i), "移入: " + currentChar, symbolStack, operatorStack);
                        break;
                    } else if (precedence == '>') {
                        topOperator = operatorStack.pop();
                        if (topOperator == '+' || topOperator == '*') {
                            if (symbolStack.size() < 2) {
                                return false;
                            }
                            char right = symbolStack.pop();
                            char left = symbolStack.pop();
                            symbolStack.push('E');
                        } else if (topOperator == 'i') {
                            symbolStack.push('E');
                        } else {
                            return false;
                        }
                        System.out.printf("%-20s%-20s%-20s%-20s\n", input.substring(i), "归约: " + topOperator, symbolStack, operatorStack);
                    } else {
                        return false;
                    }
                }
            }
        }

        System.out.printf("%-20s%-20s%-20s%-20s\n", "", "完成", symbolStack, operatorStack);
        return symbolStack.size() == 1 && symbolStack.peek() == 'E';
    }

    public static void main(String[] args) {
        String inputString = "i+i*i#";
        if (syntaxAnalysis(inputString)) {
            System.out.println("输入串 '" + inputString + "' 符合文法。");
        } else {
            System.out.println("输入串 '" + inputString + "' 不符合文法。");
        }
    }
}
