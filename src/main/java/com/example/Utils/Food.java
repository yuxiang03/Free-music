package com.example.Utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("food")
public class Food {
    @TableId(type = IdType.ASSIGN_ID)
    Long id;
    String foodName;
    String url;
    int sortId;
}
