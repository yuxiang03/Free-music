package com.example.Utils;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("food_2")
public class FoodInfo {
    Long id;
    String foodName;
    int sortId;
    String foodInfo;
}
