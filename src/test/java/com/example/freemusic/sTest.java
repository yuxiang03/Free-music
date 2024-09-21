package com.example.freemusic;

import com.example.Utils.JwtUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;
import org.python.antlr.runtime.Lexer;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@SpringBootTest
public class sTest {
    /*public static void main(String[] args) {
        // 定义输入文件和输出文件路径
        String sourceFile = "/Users/yuxiang/Downloads/Linux性能优化.zip";
        String targetFile = "/Users/yuxiang/Downloads/Linux性能优化2.zip";
        // 使用try-with-resources确保流在使用后正确关闭
        try (
                InputStream inputStream = new FileInputStream(sourceFile);
                OutputStream outputStream = new FileOutputStream(targetFile);
        ) {
            // 调用IOUtils.copy方法复制输入流到输出流
            IOUtils.copy(inputStream, outputStream);
            System.out.println("文件复制成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件复制失败！");
        }
    }*/

    /*public static void main(String[] args) {
        *//*Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int i = in.nextInt();
        while (i>0) { // 注意 while 处理多个 case
            int a = in.nextInt(), b = in.nextInt(), n1 = 0, n2 = 0;
            in.nextLine();
            String s1 = in.nextLine();
            String s2 = in.nextLine();
            for(int j = 0;j<s1.length();j++){
                n1 += s1.charAt(j);
                n2 += s2.charAt(j);
            }
            int temp = n2 - n1;
            b %= a*26;
            System.out.println(temp==b||b-temp==0?"Yes":"No");
            i--;
        }*//*
        *//*Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt();
        while (n>0) { // 注意 while 处理多个 case
            in.nextLine();
            String s = in.nextLine();
            char[] arr = s.toCharArray();
            if(arr.length<2){
                System.out.println(arr[0]>'9'?"Yes":"No");
                continue;
            }
            for(int i = 1;i<s.length();i++){
                if(arr[i]==arr[i-1]){
                    System.out.println(false);
                    return;
                }
            }
            System.out.println(true);
            n--;
        }*//*
        int count = 0, n = 10;
        while(n>0){
            if((n & 1) == 1) count++;
            n >>= 1;
        }
        System.out.println(count);
    }*/

    @Test
    public void test() {
        /*Long a = -101L,res = Long.MIN_VALUE;
        int b = 10;
        while(a/b!=0){
            Long c = a/b, d = Math.abs(a%b);
            res = Math.max(res,c+d);
            b *= 10;
        }
        System.out.println(res);*/
    }

    @Test
    public void test2(){
        productExceptSelf(new int[]{1,2,3,4});
    }

    public static int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        answer[0] = 1;
        for (int i = 1; i < length; i++)
            answer[i] = nums[i - 1] * answer[i - 1];
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            answer[i] = answer[i] * R;
            R *= nums[i];
        }
        return answer;
    }

    @Test
    public void autoTest(){
        //spiralOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
        isValid("()");
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList();
        int l = 0, r = matrix[0].length-1, h = 0, d = matrix.length-1;
        while(true){
            for(int i = l;i<=r;i++) list.add(matrix[h][i]);
            if(++h > d) break;
            for(int i = h;i<=d;i++) list.add(matrix[i][r]);
            if(--r < l) break;
            for(int i = r;i>=l;i--) list.add(matrix[d][i]);
            if(--d < h) break;
            for(int i = d;i>=h;i--) list.add(matrix[i][l]);
            if(++l > r) break;
        }
        return list;
    }

    @Test
    public void jwtTest(){
        Map map = new HashMap();
        map.put("account","HRlELXqpSB");
        map.put("password","HRlELXqpSB");
        String s = JwtUtils.generateJwt(map);
        Long start = System.currentTimeMillis();
        System.out.println(JwtUtils.parseJWT(s));
        Long end = System.currentTimeMillis();
        System.out.println(s+"----->"+(end-start));
    }

    public static boolean isValid(String s) {
        Deque<Character> stack = new LinkedList();
        for(char c:s.toCharArray()){
            if(c == '(') stack.push(')');
            else if(c == '{') stack.push('}');
            else if(c == '[') stack.push(']');
            else if(stack.pop()!=c||stack.isEmpty()) return false;
        }
        return stack.isEmpty();
    }

    @Test
    public void nk(){
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            if(s == null || s.length() == 0) break;
            int l = 0, r = s.length()-1;
            StringBuffer str = new StringBuffer();
            while(s.charAt(l++)=='*')
                str.append('*');
            while(s.charAt(r)=='*'&&r>l)
                r--;
            while(l<r){
                if(s.charAt(l)!='*') str.append(s.charAt(l));
                l++;
            }
            while(r++<s.length()){
                str.append('*');
            }
            System.out.println(str.toString());
        }
    }

    public static void main(String[] args) {
        /*String input = "i+i*i";
        List<String> tokens = Lexer.tokenize(input);
        System.out.println("Tokens: " + tokens);

        boolean isAccepted = Parser.parse(tokens);
        if (isAccepted) {
            System.out.println("Input string \"" + input + "\" is accepted by the grammar.");
        } else {
            System.out.println("Input string \"" + input + "\" is not accepted by the grammar.");
        }*/
        minSubArrayLen(11,new int[]{1,2,3,4,5});
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int w = 1, len = nums.length;
        for(int num:nums) if(target<=num) return w;
        while(w<len){
            for(int i = 0;(i+w)<len;i++){
                int res = 0;
                for(int j = i;j<=(i+w);j++) res += nums[j];
                if(target<=res) return w+1;
            }
            w++;
        }
        return 0;
    }

    @Test
    public void calculatorTest(){
        int[][] a = {
                {-1,1},
                {1,-1},
                {0,0}
        };
        int[][] b = {
                {-1,0},
                {1,0}
        };
        Calculator calculator = new Calculator();
        /*for (int num : a) {
            calculator.subtration(num);
            System.out.println(calculator.getResult());
            calculator.clear();
        }
        for (int num : a) {
            calculator.divide(num);
            System.out.println(calculator.getResult());
            calculator.clear();
        }*/
        for (int[] nums:a) {
            calculator.subtration(nums[0]);
            System.out.println(calculator.getResult().equals(nums[1]));
            calculator.clear();
        }
        for (int[] nums:b) {
            calculator.divide(nums[0]);
            System.out.println(calculator.getResult().equals(nums[1]));
            calculator.clear();
        }
    }

    @Test
    public void exchangeTest(){
        int[][] data = {
                {80,83,'C'},
                {80,90,'B'},
                {80,80,'C'},
                {83,90,'B'},
                {83,83,'B'},
                {90,90,'A'}
        };
        Exchange exchange = new Exchange();
        for (int[] d:data)
            System.out.println(exchange.function(d[0], d[1])==d[2]);
    }
}