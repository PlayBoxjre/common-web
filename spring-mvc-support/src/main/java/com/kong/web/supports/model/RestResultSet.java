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

package com.kong.web.supports.model;

import java.util.Objects;

/**
 * File Name RestResultSet
 * Author    lantoev & kong xiang
 * DATE      2018-05-21
 * EMAIL     playboxjre@Gmail.com
 */
public class RestResultSet<T>  {

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 数据
     */
    private T datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestResultSet<?> that = (RestResultSet<?>) o;
        return Objects.equals(code, that.code) &&
                Objects.equals(message, that.message) &&
                Objects.equals(datas, that.datas);
    }

    @Override
    public int hashCode() {

        return Objects.hash(code, message, datas);
    }

    @Override
    public String toString() {
        return "RestResultSet{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", datas=" + datas +
                '}';
    }
}
