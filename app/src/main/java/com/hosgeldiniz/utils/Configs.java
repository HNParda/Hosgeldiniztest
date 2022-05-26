package com.hosgeldiniz.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Configs {

    public static DefActivity d;
    static SharedPreferences settings;
    static SharedPreferences menu;

    public static void init(DefActivity defActivity) {
        d = defActivity;
        settings = defActivity.getSharedPreferences("settings", Context.MODE_PRIVATE);
        menu = defActivity.getSharedPreferences("menus", Context.MODE_PRIVATE);
    }

    public static void Toast(String s) {
        Log.e("testtest", s);
        d.runOnUiThread(() -> Toast.makeText(d, s, Toast.LENGTH_SHORT).show());
    }

    public static void setConfigs(String Name, String IP, int Port) {
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Name", Name);
        editor.putString("IP", IP);
        editor.putInt("Port", Port);
        editor.apply();
    }

    public static List<String> getMenu() {
        return new Gson().fromJson(
                menu.getString("menu", String.valueOf(new ArrayList<String>())),
                new TypeToken<ArrayList<String>>() {
                }.getType());
    }

    public static void setMenu(List<String> list) {
        SharedPreferences.Editor editor = menu.edit();
        editor.putString("menu", new Gson().toJson(list));
        editor.apply();
    }

    public static int getTableCount() {
        return menu.getInt("tables", 0);
    }

    public static void setTableCount(int i) {
        SharedPreferences.Editor editor = menu.edit();
        editor.putInt("tables", i);
        editor.apply();
    }

    public static String getIp() {
        return settings.getString("IP", "192.168.1.103");
    }

    public static int getPort() {
        return settings.getInt("Port", 8080);
    }

    public static int getEmptyPort() {
        boolean free = false;
        int Port = getPort();
        while (!free)
            try (ServerSocket s = new ServerSocket(Port)) {
                free = true;
                s.close();
            } catch (IOException e) {
                Port++;
            }
        return Port;
    }

    public static String getName() {
        return settings.getString("Name", "Device");
    }

}
