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

package com.kong.support.thread.framework.callback;

import com.kong.support.thread.framework.EventBundle;
import com.kong.support.thread.framework.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * File Name OnPostEventListener
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-13
 * EMAIL     playboxjre@Gmail.com
 */
public interface OnPostEventListener {
    Logger logger = LoggerFactory.getLogger(OnPostEventListener.class);

    public void onPost(EventBundle t);

    public void onError(Exception ex);
}
