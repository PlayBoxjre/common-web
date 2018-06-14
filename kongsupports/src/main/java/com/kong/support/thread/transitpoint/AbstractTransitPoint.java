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

package com.kong.support.thread.transitpoint;

import com.kong.support.thread.transitpoint.callback.OnTransitPointTransitListner;
import com.kong.support.toolboxes.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * File Name AbstractTransitPoint
 * Author    aaron (EN) & 孔翔kongxiang(CN)
 * DATE      2018-06-14
 * EMAIL     playboxjre@Gmail.com
 */
public abstract class AbstractTransitPoint<T,R> extends ArrayBlockingQueue<T> implements TransitPoint<T,R> {
    Logger logger = LoggerFactory.getLogger(AbstractTransitPoint.class);

    private Statistic statistic;
    private OnTransitPointTransitListner<T,R> onTransitPointTransitListner;
    /**
     * 名称
     */
    private String transitPointName;
    /**
     * 优先级 挑选的优先级 数组越大，被选中的几率越大
     */
    private int transitPriority;
    /**
     * 该传输点的标记 用来标记这个传输点的一些特征
     */
    private Object flag;

    public AbstractTransitPoint(int capacity,String transitPointName,int transitPriority,Object flag){
        super(capacity);
        this.transitPointName = transitPointName;
        this.transitPriority = transitPriority;
        this.flag = flag;

    }


    public AbstractTransitPoint(int capacity) {
        super(capacity);
    }

    @Override
    public void consume(T thing) throws InterruptedException {
        put(thing);
        logger.debug("consume {}",thing);
        if (statistic!=null)
            statistic.increment("consumer");
     }
    /**
     * 加工物料成产品
     * @param thing
     * @return
     */
    public abstract R transit(T thing);

    @Override
    public R product() throws InterruptedException {
        R transit = null;
        if (onTransitPointTransitListner!=null){
            transit = onTransitPointTransitListner.onTransfer(this.take());
        }else {
            transit = transit(this.take());
        }
        logger.debug("product {}", transit);
        if (statistic != null)
            statistic.increment("product");
        return transit;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public OnTransitPointTransitListner<T, R> getOnTransitPointTransitListner() {
        return onTransitPointTransitListner;
    }

    public void setOnTransitPointTransitListner(OnTransitPointTransitListner<T, R> onTransitPointTransitListner) {
        this.onTransitPointTransitListner = onTransitPointTransitListner;
    }

    public String getTransitPointName() {
        return transitPointName;
    }

    public void setTransitPointName(String transitPointName) {
        this.transitPointName = transitPointName;
    }

    public int getTransitPriority() {
        return transitPriority;
    }

    public void setTransitPriority(int transitPriority) {
        this.transitPriority = transitPriority;
    }

    public Object getFlag() {
        return flag;
    }

    public void setFlag(Object flag) {
        this.flag = flag;
    }
}
