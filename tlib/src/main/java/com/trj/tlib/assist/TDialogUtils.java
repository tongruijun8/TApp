package com.trj.tlib.assist;

import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.trj.tlib.R;
import com.trj.tlib.activity.InitActivity;
import com.trj.tlib.uils.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.OptionPicker;
import cn.qqtheme.framework.picker.WheelPicker;
import cn.qqtheme.framework.util.ConvertUtils;

/**
 * @author tong
 * @date 2018/4/3 16:27
 */
public class TDialogUtils {

    private InitActivity activity;
    private String jsonCity = "";
    private Calendar calendar;

    public TDialogUtils(InitActivity activity) {
        this.activity = activity;
        calendar = Calendar.getInstance();
        try {
            jsonCity = ConvertUtils.toString(activity.getAssets().open("city_m.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据areaId，获取三级列表数据
     *
     * @param areaId   第三极的区域id
     * @param listener 返回值监听
     */
    public void getThreeCityName(String areaId, AddressPicker.OnAddressPickListener listener) {
        ArrayList<Province> dataPro = (ArrayList<Province>) JSON.parseArray(jsonCity, Province.class);
        if (null != dataPro) {
            e:
            for (int i = 0; i < dataPro.size(); i++) {
                Province province = dataPro.get(i);
                List<City> cityList = province.getCities();
                for (int j = 0; j < cityList.size(); j++) {
                    City city = cityList.get(j);
                    List<County> countyList = city.getCounties();
                    for (int k = 0; k < countyList.size(); k++) {
                        County county = countyList.get(k);
                        if (county.getAreaId().equals(areaId)) {
                            if (null != listener) {
                                listener.onAddressPicked(province, city, county);
                            }
                            break e;
                        }
                    }
                }
            }
        }
    }


    /**
     * 获取区域集合
     */
    public List<County> getCountiesList(String cityName){
        ArrayList<Province> dataPro = (ArrayList<Province>) JSON.parseArray(jsonCity, Province.class);
        if (null != dataPro) {
            for (int i = 0; i < dataPro.size(); i++) {
                Province province = dataPro.get(i);
                List<City> cityList = province.getCities();
                for (int j = 0; j < cityList.size(); j++) {
                    City city = cityList.get(j);
                    if(city.getAreaName().contains(cityName)){
                        return city.getCounties();
                    }
                }
            }
        }
        return null;
    }


    /**
     * 数字选择框
     * @param minNumber
     * @param maxNumber
     * @param listener
     */
    public void selectNumber(int minNumber, int maxNumber, NumberPicker.OnNumberPickListener listener) {
        NumberPicker picker = new NumberPicker(activity);
        picker.setRange(minNumber, maxNumber);//数字范围
        picker.setSelectedItem(0);
        picker.setDividerRatio(0.7f);
        picker.setOnNumberPickListener(listener);
        selectDialogShow(picker);
    }


    /**
     * 选择文字
     *
     * @param xlList 字符串集合
     * @param index 默认显示第几个
     * @param listener
     */
    public void selectString(List<String> xlList, int index, OptionPicker.OnOptionPickListener listener) {
        OptionPicker picker = new OptionPicker(activity, xlList);
        picker.setSelectedIndex(index);
        picker.setOnOptionPickListener(listener);
        selectDialogShow(picker);
    }

    // 从1920.1.1到今天

    /**
     * 选择过去的哪一天
     * @param listener
     */
    public void selectDateBefourManyYear(DatePicker.OnYearMonthDayPickListener listener) {
        this.selectDateBefourManyYear(0, 0, 0, listener);
    }
    /**
     * 选择过去的哪一天：设置默认选择那一天
     * @param listener
     */
    public void selectDateBefourManyYear(int yearNum, int monthNum, int dayNum, DatePicker.OnYearMonthDayPickListener listener) {
        this.selectDate(yearNum, monthNum, dayNum, true, false, listener);
    }

    //    从今天开始到后5年

    /**
     * 选择将来的某一天
     * @param listener
     */
    public void selectDateAfterFiveYear(DatePicker.OnYearMonthDayPickListener listener) {
        this.selectDateAfterFiveYear(0, 0, 0, listener);
    }
    /**
     * 选择将来的某一天：设置默认选择那一天
     * @param listener
     */
    public void selectDateAfterFiveYear(int yearNum, int monthNum, int dayNum, DatePicker.OnYearMonthDayPickListener listener) {
        this.selectDate(yearNum, monthNum, dayNum, false, true, listener);
    }

    //    从1920.1.1到后5年

    /**
     * 选择某一天：[1920,toyear+5]
     * @param listener
     */
    public void selectDate(DatePicker.OnYearMonthDayPickListener listener) {
        this.selectDate(0, 0, 0, listener);
    }
    /**
     * 选择某一天：设置默认选择那一天
     * @param listener
     */
    public void selectDate(int yearNum, int monthNum, int dayNum, DatePicker.OnYearMonthDayPickListener listener) {
        this.selectDate(yearNum, monthNum, dayNum, false, false, listener);
    }

    /**
     * 选择日期:
     * 1.选择显示的初始日期：默认当天为初始日期
     * yearNum：[1920,year+5]; （year 是今年）
     * monthNum：[1,12]
     * dayNum：根据具体月份而定：传入合理的初始值
     * 1，3，5，7，8，10，12：[1,31]；
     * 4，6，9，11：[1,30]；
     * 2：[1,28] or [1,29]；
     * <p>
     * 2.时间范围是否截止到今天 ：默认false，采用默认的截止时间范围，反之，截止到今天为止
     *
     * @param yearNum    年
     * @param monthNum   月
     * @param dayNum     日
     * @param toToday    时间范围是否截止到今天
     * @param todayStart 时间范围是否从今天开始
     * @param listener
     */
    private void selectDate(int yearNum, int monthNum, int dayNum, boolean toToday, boolean todayStart, DatePicker.OnYearMonthDayPickListener listener) {

        try {
            if (yearNum == 0 || monthNum == 0 || dayNum == 0) {
                yearNum = calendar.get(Calendar.YEAR);
                monthNum = calendar.get(Calendar.MONTH) + 1;
                dayNum = calendar.get(Calendar.DAY_OF_MONTH);
            }
            DatePicker picker = new DatePicker(activity, DateTimePicker.YEAR_MONTH_DAY);
            picker.setContentPadding(20, 0);
            if (todayStart) {
                //从现在到将来
                picker.setRangeStart(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
                picker.setRangeEnd(calendar.get(Calendar.YEAR) + 5, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            } else if (toToday) {
//                从过去到现在
                picker.setRangeStart(1920, 1, 1);
                picker.setRangeEnd(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            } else {
//                从过去到将来
                picker.setRangeStart(1920, 1, 1);
                picker.setRangeEnd(calendar.get(Calendar.YEAR) + 5, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
            }
            picker.setSelectedItem(yearNum, monthNum, dayNum);
//        picker.setGravity(Gravity.CENTER);
            picker.setOnDatePickListener(listener);
            selectDialogShow(picker);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.t("时间参数错误，请填写有效的参数范围");
        }
    }

    /**
     * 民族选择
     *
     * @param listener
     */
    public void selectMinzu(OptionPicker.OnOptionPickListener listener) {
        this.selectMinzu(0, listener);
    }

    public void selectMinzu(int index, OptionPicker.OnOptionPickListener listener) {
        List<String> xlList = Nation.getValueList();
        OptionPicker picker = new OptionPicker(activity, xlList);
        picker.setSelectedIndex(index);
        picker.setOnOptionPickListener(listener);
        selectDialogShow(picker);
    }

    /**
     * 学历选择
     *
     * @param listener
     */
    public void selectXueli(OptionPicker.OnOptionPickListener listener) {
        this.selectXueli(0, listener);
    }

    public void selectXueli(int index, OptionPicker.OnOptionPickListener listener) {

        List<String> xlList = Education.getValueList();
        OptionPicker picker = new OptionPicker(activity, xlList);
        picker.setSelectedIndex(index);
        picker.setOnOptionPickListener(listener);
        selectDialogShow(picker);
    }

    /**
     * 星座选择
     *
     * @param listener
     */
    public void selectConstellation(OptionPicker.OnOptionPickListener listener) {
        this.selectConstellation(0, listener);
    }

    public void selectConstellation(int index, OptionPicker.OnOptionPickListener listener) {
        List<String> xlList = Constellation.getValueList();
        OptionPicker picker = new OptionPicker(activity, xlList);
        picker.setSelectedIndex(index);
        picker.setOnOptionPickListener(listener);
        selectDialogShow(picker);
    }

    /**
     * 生肖选择
     *
     * @param listener
     */
    public void selectZodiac(OptionPicker.OnOptionPickListener listener) {
        this.selectZodiac(0, listener);
    }

    public void selectZodiac(int index, OptionPicker.OnOptionPickListener listener) {
        List<String> xlList = Zodiac.getValueList();
        OptionPicker picker = new OptionPicker(activity, xlList);
        picker.setSelectedIndex(index);
        picker.setOnOptionPickListener(listener);
        selectDialogShow(picker);
    }


    /**
     * 选择地址
     *
     * @param listener 选择结果
     */
    public void selectAddress(AddressPicker.OnAddressPickListener listener) {
        this.selectAddress("陕西省", "西安市", "碑林区", listener);
    }

    /**
     * 选择地址
     *
     * @param provinceStr 默认省
     * @param cityStr     默认市
     * @param countyStr   默认区
     * @param listener    选择结果
     */
    public void selectAddress(String provinceStr, String cityStr, String countyStr, AddressPicker.OnAddressPickListener listener) {
        if (null == cityStr || jsonCity.equals("")) {
            Logger.t("初始化城市数据异常");
            return;
        }
        ArrayList<Province> dataPro = (ArrayList<Province>) JSON.parseArray(jsonCity, Province.class);
        AddressPicker picker = new AddressPicker(activity, dataPro);
        picker.setColumnWeight(1 / 3.0f, 1 / 3.0f, 1 / 3.0f);//省级、地级和县级的比例为2:3:3
//            picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
        picker.setSelectedItem(provinceStr, cityStr, countyStr);
        picker.setOnAddressPickListener(listener);
        selectDialogShow(picker);
    }

//    public void selectCity(String provinceStr, String cityStr, AddressPicker.OnAddressPickListener listener) {
//        try {
//            String json = ConvertUtils.toString(activity.getAssets().open("city_m.json"));
//            ArrayList<Province> dataPro = (ArrayList<Province>) JSON.parseArray(json, Province.class);
//            AddressPicker picker = new AddressPicker(activity, dataPro);
//            picker.setColumnWeight(1 / 2.0f, 1 / 2.0f);//省级、地级的比例为1:1
//            picker.setSelectedItem(provinceStr, cityStr, "");
//            picker.setOnAddressPickListener(listener);
//            selectDialogShow(picker);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private void selectDialogShow(WheelPicker picker) {
        picker.setCanceledOnTouchOutside(false);
        picker.setOffset(4);
        picker.setTopHeight(60);
        picker.setCycleDisable(true);
        picker.setTextSize(20);
        picker.setTextPadding(10);
//        picker.setHeight(getResources().getDimensionPixelOffset(R.dimen.bottom_dialog_height));
        picker.setTopBackgroundColor(activity.getResources().getColor(R.color.color_zhuse));
        picker.setCancelTextColor(activity.getResources().getColor(R.color.textcolor_white));
        picker.setSubmitTextColor(activity.getResources().getColor(R.color.textcolor_white));
        picker.setTextColor(activity.getResources().getColor(R.color.color_zhuse));
        picker.setDividerColor(activity.getResources().getColor(R.color.color_zhuse));
        picker.setLabelTextColor(activity.getResources().getColor(R.color.color_zhuse));
        picker.setTopLineColor(activity.getResources().getColor(android.R.color.transparent));
        picker.setAnimationStyle(R.style.MyDialogAnimation);
        picker.show();
    }

    /**
     * 签到成功
     *
     * @param number
     * @return
     */
    public AlertDialog qdSuccessDialog(int number) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_qd_success, null);
        TextView textView = view.findViewById(R.id.qd_success_text);
        textView.setText("+" + number);
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setView(view)
                .create();
        dialog.show();

        setAlertDailogWindow(dialog, activity.getResources().getDimensionPixelOffset(R.dimen.qd_success_w), activity.getResources().getDimensionPixelOffset(R.dimen.qd_success_h));
        return dialog;
    }


    private void setAlertDailogWindow(AlertDialog dialog, int width, int height) {
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = width;
        params.height = height;
        params.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.transparent)));
    }


    private AlertDialog qdDialog;

    /**
     * 抢单提示框
     */
    public void qdDialog(String msg, boolean isShowChickbox, final TOnClickDialogListener listener) {
        this.qdDialog("取消", "确认", msg, isShowChickbox, listener);
    }

    /**
     * 抢单提示框
     */
    public void qdDialog(String cancleStr, String affirmStr, String msg, boolean isShowChickbox, final TOnClickDialogListener listener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_qd, null);

        TextView textViewContent = view.findViewById(R.id.dialog_qd_content);
        CheckBox textViewCheckbox = view.findViewById(R.id.dialog_qd_checkbox);
        TextView textViewCancle = view.findViewById(R.id.dialog_qd_cancle);
        TextView textViewAffirm = view.findViewById(R.id.dialog_qd_affirm);
        textViewContent.setText(null == msg || msg.equals("") ? "" : msg);
        textViewCancle.setText(null == cancleStr || cancleStr.equals("") ? "取消" : cancleStr);
        textViewAffirm.setText(null == affirmStr || affirmStr.equals("") ? "确认" : affirmStr);
        if (isShowChickbox) {
            textViewCheckbox.setVisibility(View.VISIBLE);
            textViewCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (null != listener) {
                        listener.onCheck(isChecked);
                    }
                }
            });
        }

        textViewCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != qdDialog) {
                    qdDialog.dismiss();
                }
                if (null != listener) {
                    listener.onCancle();
                }
            }
        });

        textViewAffirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != qdDialog) {
                    qdDialog.dismiss();
                }
                if (null != listener) {
                    listener.onAffirm();
                }
            }
        });

        qdDialog = new AlertDialog.Builder(activity)
                .setView(view)
                .create();
        qdDialog.show();
    }


    /**
     * 底部提示框
     */
    public void bottomDialog(final TOnClickBottomDialogListener listener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_bottom, null);

        TextView textView = view.findViewById(R.id.dialog_bottom_gdmap);
        TextView textView2 = view.findViewById(R.id.dialog_bottom_bdmap);
        final AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setView(view)
                .setCancelable(true)
                .create();
        alertDialog.show();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listener){
                    listener.onItemClick(1);
                }
                alertDialog.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != listener){
                    listener.onItemClick(2);
                }
                alertDialog.dismiss();
            }
        });
        Window window = alertDialog.getWindow();
//        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setWindowAnimations(R.style.DialogBottomAnimation);
        // 获取Window的LayoutParams
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
//        attributes.height = activity.getResources().getDimensionPixelOffset(R.dimen.dialog_bottom_h);
        attributes.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        // 一定要重新设置, 才能生效
        window.setAttributes(attributes);

    }

    public interface TOnClickBottomDialogListener {
        void onItemClick(int position);
    }


    public interface TOnClickDialogListener {
        void onAffirm();

        void onCancle();

        void onCheck(boolean isCheck);
    }

    public abstract static class TOnClickDialogListenerImpl implements TOnClickDialogListener {

        @Override
        public abstract void onAffirm();

        @Override
        public void onCancle() {

        }

        @Override
        public void onCheck(boolean isCheck) {

        }
    }


}
