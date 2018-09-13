package com.trj.tlib.assist;

import java.util.ArrayList;
import java.util.List;

public enum Nation {
    nation1(1,"汉族"),
    nation2(2,"壮族"),
    nation3(3,"回族"),
    nation4(4,"满族"),
    nation5(5,"维吾尔族"),
    nation6(6,"苗族"),
    nation7(7,"彝族"),
    nation8(8,"土家族"),
    nation9(9,"藏族"),
    nation10(10,"蒙古族"),
    nation11(11,"侗族"),
    nation12(12,"布依族"),
    nation13(13,"瑶族"),
    nation14(14,"白族"),
    nation15(15,"朝鲜族"),
    nation16(16,"哈尼族"),
    nation17(17,"黎族"),
    nation18(18,"哈萨克族"),
    nation19(19,"傣族"),
    nation20(20,"畲族"),
    nation21(21,"傈僳族"),
    nation22(22,"东乡族"),
    nation23(23,"仡佬族"),
    nation24(24,"拉祜族"),
    nation25(25,"佤族"),
    nation26(26,"水族"),
    nation27(27,"纳西族"),
    nation28(28,"羌族"),
    nation29(29,"土族"),
    nation30(30,"仫佬族"),
    nation31(31,"锡伯族"),
    nation32(32,"柯尔克孜族"),
    nation33(33,"景颇族"),
    nation34(34,"达斡尔族"),
    nation35(35,"撒拉族"),
    nation36(36,"布朗族"),
    nation37(37,"毛南族"),
    nation38(38,"塔吉克族"),
    nation39(39,"普米族"),
    nation40(40,"阿昌族"),
    nation41(41,"怒族"),
    nation42(42,"鄂温克族"),
    nation43(43,"京族"),
    nation44(44,"基诺族"),
    nation45(45,"德昂族"),
    nation46(46,"保安族"),
    nation47(47,"俄罗斯族"),
    nation48(48,"裕固族"),
    nation49(49,"乌孜别克族"),
    nation50(50,"门巴族"),
    nation51(51,"鄂伦春族"),
    nation52(52,"独龙族"),
    nation53(53,"赫哲族"),
    nation54(54,"高山族"),
    nation55(55,"珞巴族"),
    nation56(56,"塔塔尔族");

    private Integer key;
    private String value;

    /**
     * 根据key获取value
     * @param key
     * @return
     */
    public static String getValueByKey(Integer key){
        Nation[] values = Nation.values();
        for (int i =0;i<values.length;i++){
            if (values[i].getKey()==key){
                return values[i].getValue();
            }
        }
        return "汉族";
    }

    /**
     * 根据value获取key
     * @param value
     * @return
     */
    public static int getValueByKey(String value){
        Nation[] values = Nation.values();
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
        Nation[] values = Nation.values();
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

    Nation(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
