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

package com.kong.support.socket.nio.request;

/**
 * File Name RequestHeader
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-04
 * EMAIL     playboxjre@Gmail.com
 */
public class RequestHeader {
    private int cryptoAlgothem;
    private String cryptoAlgothemKey;
    private int decodeAlgothem;
    private String decodeAlgothemKey;

    public int getCryptoAlgothem() {
        return cryptoAlgothem;
    }

    public void setCryptoAlgothem(int cryptoAlgothem) {
        this.cryptoAlgothem = cryptoAlgothem;
    }

    public String getCryptoAlgothemKey() {
        return cryptoAlgothemKey;
    }

    public void setCryptoAlgothemKey(String cryptoAlgothemKey) {
        this.cryptoAlgothemKey = cryptoAlgothemKey;
    }

    public int getDecodeAlgothem() {
        return decodeAlgothem;
    }

    public void setDecodeAlgothem(int decodeAlgothem) {
        this.decodeAlgothem = decodeAlgothem;
    }

    public String getDecodeAlgothemKey() {
        return decodeAlgothemKey;
    }

    public void setDecodeAlgothemKey(String decodeAlgothemKey) {
        this.decodeAlgothemKey = decodeAlgothemKey;
    }
}
