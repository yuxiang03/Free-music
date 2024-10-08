package com.example.freemusic;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class SplitFileUtil {
    public static void getSplitFile(String file, int count) {
        //预分配文件所占用的磁盘空间，在磁盘创建一个指定大小的文件，“r”表示只读，“rw”支持随机读写
        try {
            RandomAccessFile raf = new RandomAccessFile(new File(file), "r");
            //计算文件大小
            long length = raf.length();
            System.out.println(length);
            //计算文件切片后每一份文件的大小
            long maxSize = length / count;
            System.out.println(maxSize);

            //定义初始文件的偏移量(读取进度)
            long offset = 0L;
            //开始切割文件
            //count-1最后一份文件不处理
            for(int i = 0; i < count - 1; i++){
                //标记初始化
                long fbegin = offset;
                //分割第几份文件
                long fend = (i+1) * maxSize;
                //写入文件
                offset = getWrite(file, i, fbegin, fend);
            }

            //剩余部分文件写入到最后一份(如果不能平平均分配的时候)
            if (length - offset > 0) {
                //写入文件
                getWrite(file, count-1, offset, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long getWrite(String file, int index, long begin, long end) {
        long endPointer = 0L;
        try {
            //申明文件切割后的文件磁盘
            RandomAccessFile in = new RandomAccessFile(new File(file), "r");
            //定义一个可读，可写的文件并且后缀名为.tmp的二进制文件
            RandomAccessFile out = new RandomAccessFile(new File(file + "_" + index + ".tmp"), "rw");

            //申明具体每一文件的字节数组
            byte[] b = new byte[1024];
            int n = 0;
            //从指定位置读取文件字节流
            in.seek(begin);
            //判断文件流读取的边界
            while (in.getFilePointer() <= end && (n = in.read(b)) != -1) {
                //从指定每一份文件的范围，写入不同的文件
                out.write(b, 0, n);
            }

            //定义当前读取文件的指针
            endPointer = in.getFilePointer();

            //关闭输入流
            in.close();
            //关闭输出流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return endPointer;
    }

    public static void merge(String file, String tempFile, int tempCount) {
        RandomAccessFile raf = null;
        try {
            //申明随机读取文件RandomAccessFile
            raf = new RandomAccessFile(new File(file), "rw");
            //开始合并文件，对应切片的二进制文件
            for (int i = 0; i < tempCount; i++) {
                //读取切片文件
                RandomAccessFile reader = new RandomAccessFile(new File(tempFile + "_" + i + ".tmp"), "r");
                byte[] b = new byte[1024];
                int n = 0;
                while ((n = reader.read(b)) != -1) {
                    //一边读，一边写
                    raf.write(b, 0, n);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 第一步
         */
        String file = "/Users/yuxiang/Downloads/MotionProSetup.zip";
        int count = 5;
        //1.根据现有的文件编写文件编写文件切片工具类
        //2.写入到二进制临时文件
        getSplitFile(file, count);
        /**
         * 第二步
         */
        //3.根据实际的需求合并指定数量的文件
        String mergeFile = "/Users/yuxiang/Downloads/MotionProSetup1.zip";
        merge(mergeFile, file, count);
    }
}
