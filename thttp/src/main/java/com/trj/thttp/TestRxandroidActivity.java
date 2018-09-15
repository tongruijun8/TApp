package com.trj.thttp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import com.trj.tlib.activity.BaseTitleActivity;
import com.trj.tlib.uils.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static java.lang.Thread.sleep;

/**
 * rxandroid 在rxjava的基础上进行了扩展
 * 所有的异步都可以用RxJava来做
 */
public class TestRxandroidActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rxandroid);
    }

    @Override
    protected void initView() {
        super.initView();
        titleModule.initTitle("测试Rxjava",false);

        beiguanchazhe();
        guanchazhe();

//        观察着和被观察着之间建立关系
        observable.subscribe(observer);

    }

    private Observable observable;
    private void beiguanchazhe(){
        observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("连载1");
                emitter.onNext("连载2");
                emitter.onNext("连载3");
                emitter.onComplete();
            }
        });
    }


    private Observer<String> observer;
    private void guanchazhe(){

//        观察者
        observer = new Observer<String>() {
            Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Logger.t("s = " + s);
//                disposable.dispose();//取消订阅
            }

            @Override
            public void onError(Throwable e) {
                Logger.t("onError = " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Logger.t("onComplete");
            }
        };
    }

    //链式写法
    private void lianshixiefa(){

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("链式写法");
            }
        }).observeOn(AndroidSchedulers.mainThread())//回调主线程
                .subscribeOn(Schedulers.io())//执行在io线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Logger.t("s = " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //定时操作
    private void dingshicaozuo(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(123);
                sleep(3000);
                emitter.onNext(456);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Logger.t("---inte = " + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                });
    }

//    不同的方式
    private void moreMode(){

        Observable.create(new ObservableOnSubscribe<Drawable>() {
            @Override
            public void subscribe(ObservableEmitter<Drawable> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                    //第6个图片延时3秒后架子
                    if (i==5){
                        sleep(3000);
                    }
                    //复制第7张图片到sd卡
                    if (i==6){
                        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
                        saveBitmap(bitmap,"test.png", Bitmap.CompressFormat.PNG);
                    }
                    //上传到网络
                    if (i==7){
                        updateIcon(drawable);
                    }
                    emitter.onNext(drawable);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Drawable>() {
                    @Override
                    public void accept(Drawable drawable) throws Exception {
                        //回调后在UI界面上展示出来
                    }
                });


    }

    private void updateIcon(Drawable drawable) {
    }

    /**
     * 将Bitmap以指定格式保存到指定路径
     * @param bitmap
     * @param name
     */
    public void saveBitmap(Bitmap bitmap, String name, Bitmap.CompressFormat format) {
        // 创建一个位于SD卡上的文件
        File file = new File(Environment.getExternalStorageDirectory(),
                name);
        FileOutputStream out = null;
        try{
            // 打开指定文件输出流
            out = new FileOutputStream(file);
            // 将位图输出到指定文件
            bitmap.compress(format, 100,
                    out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
