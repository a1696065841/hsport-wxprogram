package com.hsport.wxprogram.service;

public interface RedisService {
    public String get(String key) ;
    public boolean set(String key, String val);
    public boolean setWithTime(String key, String val,int time);
    public boolean delKey(String key);
}
