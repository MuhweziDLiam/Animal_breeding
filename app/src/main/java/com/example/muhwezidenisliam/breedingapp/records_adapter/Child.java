package com.example.muhwezidenisliam.breedingapp.records_adapter;

import android.graphics.drawable.Drawable;

/**
 * Created by apple on 11/7/16.
 */
public class Child {

    private String category_one,category_two
            ,category_three,category_four;
    private Drawable cat_one, cat_two, cat_three, cat_four;

    public Child(String category_one,String category_two, String category_three,
                 String category_four, Drawable cat_one, Drawable cat_two, Drawable cat_three, Drawable cat_four) {
        this.category_one = category_one;
        this.category_two = category_two;
        this.category_three = category_three;
        this.category_four = category_four;
        this.cat_one = cat_one;
        this.cat_two = cat_two;
        this.cat_three = cat_three;
        this.cat_four = cat_four;
    }


    public  String getCategory_one() {return category_one;}
    public String getCategory_two() {return category_two;}
    public  String getCategory_three(){return category_three;}
    public String getCategory_four(){return category_four;}

    public Drawable getCat_one(){return cat_one;}
    public Drawable getCat_two(){return cat_two;}
    public Drawable getCat_three() { return cat_three;}
    public Drawable getCat_four() { return  cat_four;}

}