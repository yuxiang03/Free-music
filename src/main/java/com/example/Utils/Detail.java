package com.example.Utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Detail {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private Long mealId;
    private String href;
    private String image;
    private String title;
    private String subcontent;
}
