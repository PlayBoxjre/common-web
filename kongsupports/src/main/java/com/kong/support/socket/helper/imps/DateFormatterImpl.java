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

package com.kong.support.socket.helper.imps;

import com.kong.support.socket.helper.DataFormatter;

import java.nio.charset.Charset;
import java.util.zip.DataFormatException;

/**
 * File Name DateFormatterImpl
 * Author    lantoev & kong xiang
 * DATE      2018-05-21
 * EMAIL     playboxjre@Gmail.com
 */
public class DateFormatterImpl implements DataFormatter {


    @Override
    public void preFormat() {

    }

    @Override
    public <T> byte[] format(T data, Charset charset) throws DataFormatException {
        return new byte[0];
    }

    @Override
    public byte[] afterFormat(byte[] t) {
        return new byte[0];
    }
}
