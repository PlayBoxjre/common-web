/*
 * Copyright [2018] [ kong&xiang ]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kong.support.thread.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * File Name EventBundle
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-12
 * EMAIL     playboxjre@Gmail.com
 * 事件绑定数据对象
 */
public class EventBundle {
    Logger logger = LoggerFactory.getLogger(EventBundle.class);
    private String bundleString;
    private int bundleInt1;
    private int bundleInt2;
    private float bundleFloat1;
    private float bundleFloat2;
    private double bundleDouble1;
    private double bundleDouble2;
    private long bundleLong1;
    private long bundleLong2;
    private char bundleChar;
    private boolean bundleBoolean;
    private byte[] bundleByteArray;

    private Collection<Object> bundleList;
    private Map<String,Object> bundleMap;

    public String getBundleString() {
        return bundleString;
    }

    public void setBundleString(String bundleString) {
        this.bundleString = bundleString;
    }

    public int getBundleInt1() {
        return bundleInt1;
    }

    public void setBundleInt1(int bundleInt1) {
        this.bundleInt1 = bundleInt1;
    }

    public int getBundleInt2() {
        return bundleInt2;
    }

    public void setBundleInt2(int bundleInt2) {
        this.bundleInt2 = bundleInt2;
    }

    public float getBundleFloat1() {
        return bundleFloat1;
    }

    public void setBundleFloat1(float bundleFloat1) {
        this.bundleFloat1 = bundleFloat1;
    }

    public float getBundleFloat2() {
        return bundleFloat2;
    }

    public void setBundleFloat2(float bundleFloat2) {
        this.bundleFloat2 = bundleFloat2;
    }

    public double getBundleDouble1() {
        return bundleDouble1;
    }

    public void setBundleDouble1(double bundleDouble1) {
        this.bundleDouble1 = bundleDouble1;
    }

    public double getBundleDouble2() {
        return bundleDouble2;
    }

    public void setBundleDouble2(double bundleDouble2) {
        this.bundleDouble2 = bundleDouble2;
    }

    public long getBundleLong1() {
        return bundleLong1;
    }

    public void setBundleLong1(long bundleLong1) {
        this.bundleLong1 = bundleLong1;
    }

    public long getBundleLong2() {
        return bundleLong2;
    }

    public void setBundleLong2(long bundleLong2) {
        this.bundleLong2 = bundleLong2;
    }

    public char getBundleChar() {
        return bundleChar;
    }

    public void setBundleChar(char bundleChar) {
        this.bundleChar = bundleChar;
    }

    public boolean isBundleBoolean() {
        return bundleBoolean;
    }

    public void setBundleBoolean(boolean bundleBoolean) {
        this.bundleBoolean = bundleBoolean;
    }

    public byte[] getBundleByteArray() {
        return bundleByteArray;
    }

    public void setBundleByteArray(byte[] bundleByteArray) {
        this.bundleByteArray = bundleByteArray;
    }

    public Collection<Object> getBundleList() {
        return bundleList;
    }

    public void setBundleList(Collection<Object> bundleList) {
        this.bundleList = bundleList;
    }

    public Map<String, Object> getBundleMap() {
        return bundleMap;
    }

    public void setBundleMap(Map<String, Object> bundleMap) {
        this.bundleMap = bundleMap;
    }

    @Override
    public String toString() {
        return "EventBundle{" +
                "bundleString='" + bundleString + '\'' +
                ", bundleInt1=" + bundleInt1 +
                ", bundleInt2=" + bundleInt2 +
                ", bundleFloat1=" + bundleFloat1 +
                ", bundleFloat2=" + bundleFloat2 +
                ", bundleDouble1=" + bundleDouble1 +
                ", bundleDouble2=" + bundleDouble2 +
                ", bundleLong1=" + bundleLong1 +
                ", bundleLong2=" + bundleLong2 +
                ", bundleChar=" + bundleChar +
                ", bundleBoolean=" + bundleBoolean +
                ", bundleByteArray=" + Arrays.toString(bundleByteArray) +
                ", bundleList=" + bundleList +
                ", bundleMap=" + bundleMap +
                '}';
    }
}
