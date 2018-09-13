package com.trj.tlib.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trj.tlib.R;
import com.trj.tlib.assist.Constants;
import com.trj.tlib.assist.GlideCircleTransform;
import com.trj.tlib.uils.Logger;
import com.trj.tlib.uils.ToastUtil;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import pub.devrel.easypermissions.EasyPermissions;

import static android.text.TextUtils.isEmpty;

public class ActivityAssist implements EasyPermissions.PermissionCallbacks {

    private InitActivity initActivity;

    public String token = "";

    public ActivityAssist(InitActivity initActivity) {
        this.initActivity = initActivity;
    }

    /**
     * deviceID的组成为：渠道标志+识别符来源标志+hash后的终端识别符
     *
     * 渠道标志为：
     * 1，andriod（a）
     *
     * 识别符来源标志：
     * 1， wifi mac地址（wifi）；
     * 2， IMEI（imei）；
     * 3， 序列号（sn）；
     * 4， id：随机码。若前面的都取不到时，则随机生成一个随机码，需要缓存。
     *
     * @return
     */
    public String getDeviceId() {
        StringBuilder deviceId = new StringBuilder();
        // 渠道标志
        deviceId.append("a");
        try {
            //wifi mac地址
            WifiManager wifi = (WifiManager) initActivity.context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            String wifiMac = info.getMacAddress();
            if (!isEmpty(wifiMac)) {
                deviceId.append("wifi");
                deviceId.append(wifiMac);
                Logger.t(deviceId.toString());
                return deviceId.toString();
            }
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) initActivity.context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(initActivity.context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                String imei = tm.getDeviceId();
                if(!isEmpty(imei)){
                    deviceId.append("imei");
                    deviceId.append(imei);
                    Logger.t(deviceId.toString());
                    return deviceId.toString();
                }
            }
            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if(!isEmpty(sn)){
                deviceId.append("sn");
                deviceId.append(sn);
                Logger.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
            //如果上面都没有， 则生成一个id：随机码
            String uuid = getUUID();
            if(!isEmpty(uuid)){
                deviceId.append("id");
                deviceId.append(uuid);
                Logger.e("getDeviceId : ", deviceId.toString());
                return deviceId.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            deviceId.append("id").append(getUUID());
        }
        Logger.e("getDeviceId : ", deviceId.toString());
        return deviceId.toString();
    }

    /**
     * 得到全局唯一UUID
     */
    public String getUUID(){
        String uuid = initActivity.sharedPreferences.getString("uuid", "");
        if(isEmpty(uuid)){
            uuid = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = initActivity.sharedPreferences.edit();
            editor.putString("uuid", uuid);
            editor.commit();
        }
        Logger.t("getUUID : " + uuid);
        return uuid;
    }

    /**
     * 获取SHA值
     * @return
     */
    public String sHA1() {
        try {
            PackageInfo info = initActivity.getPackageManager().getPackageInfo(
                    initActivity.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 绑定列表数据，资格证，身份证，社区，课程，趣生活等图片
     * (不需要频繁变更的,默认图片为圆形图片)
     * @param path
     * @param imageView
     */
    public void bindImageView(Object path, ImageView imageView){
        this.bindImageView(path,imageView,true);
    }

    /**
     * 绑定列表数据，资格证，身份证，社区，课程，趣生活等图片
     * (不需要频繁变更的)
     *
     * @param path
     * @param imageView
     * @param isCircle 是否为圆形图片
     */
    public void bindImageView(Object path, ImageView imageView, boolean isCircle) {
        if(isCircle){
            Glide.with(initActivity).load(path).transform(new GlideCircleTransform(initActivity)).error(R.mipmap.headimg_default).into(imageView);
        }else{
            Glide.with(initActivity).load(path).error(R.mipmap.touxiang).into(imageView);
        }
    }

    protected AlertDialog dialog = null;

    protected TextView dialogText;
    private void createDialog(String msg){
        View view = LayoutInflater.from(initActivity).inflate(R.layout.tdialog, null);
        dialogText = view.findViewById(R.id.tdilog_text);
        if(!msg.isEmpty()){
            dialogText.setText(msg);
        }
        dialog = new AlertDialog.Builder(initActivity)
//                .setView(view)
                .setCancelable(false)
                .create();
        dialog.setView(view,0,0,0,0);
    }

    public void showDialog() {
        showDialog(Constants.CString.DIALOG_MSG_UP_LOAD);
    }
    public void showDialog(String msg) {
        if(null == dialog){
            createDialog(msg);
        }
        if(dialog.isShowing()){
            return;
        }
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = initActivity.getResources().getDimensionPixelOffset(R.dimen.dialog_w);
        params.height = initActivity.getResources().getDimensionPixelOffset(R.dimen.dialog_h);
        dialog.getWindow().setAttributes(params);


//        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
//        params.width = getResources().getDimensionPixelOffset(R.dimen.dialog_w);
//        params.height = getResources().getDimensionPixelOffset(R.dimen.dialog_h);
//        dialog.getWindow().setAttributes(params);
    }

    public void hideDialog(){
        if(null != dialog){
//            Logger.t("-------------hideDialog = " + dialog.toString());
            dialog.cancel();
        }
    }

    public String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

//    /**
//     * 绑定图片
//     * (需要频繁变更的)
//     * @param path
//     * @param imageView
//     */
//    public void bindChangeImageView(Object path,ImageView imageView){
//        this.bindChangeImageView(path,imageView,true);
//    }
//
//    /**
//     * 绑定图片
//     * (需要频繁变更的)
//     * @param path
//     * @param imageView
//     */
//    public void bindChangeImageView(Object path,ImageView imageView,boolean isCircle){
//        if(isCircle){
//            Glide.with(context).load(path).signature(new StringSignature(UUID.randomUUID().toString())).transform(new GlideCircleTransform(context)).error(R.mipmap.headimg_default).into(imageView);
//        }else{
//            Glide.with(context).load(path).signature(new StringSignature(UUID.randomUUID().toString())).error(R.mipmap.touxiang).into(imageView);
//        }
//    }

    //退出系统
    public boolean exitApp() {
        if (exit) {
            initActivity.activityManager.exit();
        } else {
            ToastUtil.showToast(initActivity, "再次点击退出");
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    exit = true;
                    handler.sendEmptyMessageDelayed(1, 2000);
                }
            }.start();
        }
        return true;
    }

    private boolean exit = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int whats = msg.what;
            if (whats == 1) {
                exit = false;
            }
        }
    };



    private String phoneNum = "";
    /* 打电话 */
    protected void callPhone(String phoneStr) {
        this.callPhone(phoneStr, "确认要联系此雇主吗？");
    }

    protected void callPhone(String phoneStr, String message) {
        phoneNum = phoneStr;
        new AlertDialog.Builder(initActivity)
                .setTitle("提示")
                .setMessage(message)
                .setNegativeButton("取消", null)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        //我们需要告诉系统，我们的动作：我要打电话
//                        //创建意图对象
//                        Intent intent = new Intent();
//                        //把打电话的动作ACTION_CALL封装至意图对象当中
//                        intent.setAction(Intent.ACTION_CALL);
//                        //设置打给谁
//                        intent.setData(Uri.parse("tel:" + phoneStr));//这个tel：必须要加上，表示我要打电话。否则不会有打电话功能，由于在打电话清单文件里设置了这个“协议”
//                        //把动作告诉系统,启动系统打电话功能。
//                        startActivity(intent);
                        requestCallPhone();
                    }
                })
                .create().show();
    }

    /***
     * 请求拨打电话的权限
     */
    private void requestCallPhone() {
        String[] perms = {Manifest.permission.CALL_PHONE};
        if (EasyPermissions.hasPermissions(initActivity, perms)) {
//            Toast.makeText(this, "拨打电话的权限", Toast.LENGTH_LONG).show();
            callPhoneNumber();
        } else {
            // request for both permissions
            EasyPermissions.requestPermissions(initActivity, Manifest.permission.CALL_PHONE,
                    Constants.CInt.CALL_PHONE, perms);
        }
    }

    private void callPhoneNumber() {
        //我们需要告诉系统，我们的动作：我要打电话
        //创建意图对象
        Intent intent = new Intent();
        //把打电话的动作ACTION_CALL封装至意图对象当中
        intent.setAction(Intent.ACTION_CALL);
        //设置打给谁
        intent.setData(Uri.parse("tel:" + phoneNum));//这个tel：必须要加上，表示我要打电话。否则不会有打电话功能，由于在打电话清单文件里设置了这个“协议”

        //把动作告诉系统,启动系统打电话功能。
        initActivity.startActivity(intent);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (Constants.CInt.CALL_PHONE == requestCode) {
            callPhoneNumber();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (Constants.CInt.CALL_PHONE == requestCode) {
            ToastUtil.showToast(initActivity, "取消影响拨打电话");
        }
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strings, @NonNull int[] ints) {
        EasyPermissions.onRequestPermissionsResult(i, strings, ints, initActivity);
    }

}
