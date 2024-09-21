package com.example.Utils;

import lombok.Data;

@Data
public class Meal extends Meal1{
    private Long id;
    private String mealName;
    private String url;
    private int sortId;
}
