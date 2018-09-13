package com.trj.tlib.assist;

import java.util.ArrayList;
import java.util.List;

public enum  Constellation {
    Aries(1,"白羊座"),Taurus(2,"金牛座"),Gemini(3,"双子座"),Cancer(4,"巨蟹座"),Leo(5,"狮子座"),Virgo(6,"处女座"),
    Libra(7,"天秤座"),Scorpio(8,"天蝎座"),Sagittarius(9,"射手座"),Capricorn(10,"摩羯座"),Aquarius(11,"水瓶座"),Pisces(12,"双鱼座");
    private Integer key;
    private String value;

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static String getValueByKey(Integer key){
        Constellation[] values = Constellation.values();
        for (int i = 0;i <values.length;i++){
            if (values[i].getKey()==key){
                return values[i].getValue();
            }
        }
        return "";
    }
    /**
     * 根据value获取key
     * @param value
     * @return
     */
    public static int getKeyByValue(String value){
        Constellation[] values = Constellation.values();
        for (int i = 0;i <values.length;i++){
            if (values[i].getValue()==value){
                return values[i].getKey();
            }
        }
        return 1;
    }

    /**
     * 将枚举类型转化为List集合
     * @return
     */
    public static List<String> getValueList(){
        List<String> list = new ArrayList<>();
        Constellation[] values = Constellation.values();
        for (int i = 0;i<values.length;i++){
            list.add(values[i].getValue());
        }
        return list;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Constellation(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
