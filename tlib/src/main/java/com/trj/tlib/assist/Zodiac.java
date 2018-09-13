package com.trj.tlib.assist;

import java.util.ArrayList;
import java.util.List;

public enum Zodiac {
    Rat(1,"鼠"),Cow(2,"牛"),Tiger(3,"虎"),Rabbit(4,"兔"),Dragon(5,"龙"),Snake(6,"蛇"),
    Horse(7,"马"),Sheep(8,"羊"),Monkey(9,"猴"),Chicken(10,"鸡"),Dog(11,"狗"),Pig(12,"猪");
    private Integer key;
    private String value;

    /**
     *根据key获取value
     * @param key
     * @return
     */
    public static String getValueByKey(Integer key){
        Zodiac[] values = Zodiac.values();
        for (int i = 0;i <values.length;i++){
            if (values[i].getKey()==key){
                return values[i].getValue();
            }
        }
        return "";
    }
    /**
     *根据key获取value
     * @param value
     * @return
     */
    public static int getKeyByValue(String value){
        Zodiac[] values = Zodiac.values();
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
        Zodiac[] values = Zodiac.values();
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

    Zodiac(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
