package com.jiguang.jpushexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
    static final String tag = "rongang";
    static final String JMessageExtra = "JMessageExtra";
    static final String channel = "jpushMessage";
    MethodChannel jpushMessageChannel;
    Intent intentStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
        System.out.println("==========start==========");
        intentStart = getIntent();
        if (intentStart != null) {
            Log.i(tag, intentStart.toString());
            if (intentStart.getData() != null)
                Log.i(tag, intentStart.getData().toString());
            else
                Log.i(tag, "intent getData null");
            Log.i(tag, intentStart.getAction());
            if (intentStart.getExtras() != null)
                Log.i(tag, intentStart.getExtras().toString());
            else
                Log.i(tag, "intent getExtras null");
        } else
            Log.i(tag, "intent null");
        System.out.println("==========end==========");
        jpushMessageChannel = new MethodChannel(getFlutterView(), channel);
        jpushMessageChannel.setMethodCallHandler(new MethodChannel.MethodCallHandler() {
            @Override
            public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                if (channel.equals(call.method)) {
                    if (intentStart.getExtras() != null)
                        result.success(intentStart.getExtras().getString(JMessageExtra));
                    else
                        result.success("intent getExtras null");
                } else {
                    result.notImplemented();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        intentStart = intent;
    }
}
