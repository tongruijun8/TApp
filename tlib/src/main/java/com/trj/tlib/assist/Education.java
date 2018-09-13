package com.trj.tlib.assist;

import java.util.ArrayList;
import java.util.List;

public enum Education {
    Education1(1,"小学"),
    Education2(2,"初中"),
    Education3(3,"高中"),
    Education5(4,"中专"),
    Education4(5,"大专"),
    Education6(6,"本科"),
    Education7(7,"硕士");
    private Integer key;
    private String value;
    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static String getValueByKey(Integer key){
        Education[] values = Education.values();
        for (int i =0;i<values.length;i++){
            if (values[i].getKey()==key){
                return values[i].getValue();
            }
        }
        return "";
    }

    /**
     * 根据key获取value
     * @param value
     * @return
     */
    public static int getKeyByValue(String value){
        Education[] values = Education.values();
        for (int i =0;i<values.length;i++){
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
        Education[] values = Education.values();
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

    Education(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
