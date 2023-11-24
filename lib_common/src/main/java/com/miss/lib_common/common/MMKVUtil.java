package com.miss.lib_common.common;

import android.content.Context;
import android.os.Environment;
import com.tencent.mmkv.MMKV;

/**
 *      代理模式替换
 */
public class MMKVUtil {
    private MMKV mmkv;
    private static volatile MMKVUtil mInstance;

    private MMKVUtil() {

    }

    public void init(Context context) {
        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mmkv";
        //mmkv初始化
        MMKV.initialize(context, dir);
        mmkv = MMKV.mmkvWithID("MyMMID");
        //开启跨进程通信
        mmkv = MMKV.mmkvWithID("MyMMID", MMKV.MULTI_PROCESS_MODE);
    }

    public static MMKVUtil getInstance() {
        if (mInstance == null) {
            synchronized (MMKVUtil.class) {
                if (mInstance == null) {
                    mInstance = new MMKVUtil();
                }
            }
        }
        return mInstance;
    }

    public void encode(String key, Object value) {
        if (value instanceof String) {
            mmkv.encode(key, (String) value);
        } else if (value instanceof Integer) {
            mmkv.encode(key, (Integer) value);
        } else if (value instanceof Boolean) {
            mmkv.encode(key, (Boolean) value);
        } else if (value instanceof Long) {
            mmkv.encode(key, (Long) value);
        } else if (value instanceof Float) {
            mmkv.encode(key, (Float) value);
        } else if (value instanceof Double) {
            mmkv.encode(key, (Double) value);
        }
    }


    public Integer decodeInt(String key) {
        return mmkv.decodeInt(key);
    }

    public String decodeString(String key) {
        return mmkv.decodeString(key, "");
    }

    public Boolean decodeBoolean(String key) {
        return mmkv.decodeBool(key);
    }

    public Long decodeLong(String key) {
        return mmkv.decodeLong(key);
    }

    public Float decodeFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    public Double decodeDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    public void clearAllData() {
        mmkv.clearAll();
    }
}

