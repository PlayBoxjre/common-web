# hibernate 错误解决

### 设定hibernate里面的时间字段为自动生成

1. 设置字段：birthday timestamp CURRENT_TIMESTAMP

2. 设置动态插入不需要手动输入
    ```
     <class name="com.dada.pojo.Test" table="T_REP_FORM" dynamic-insert="true" dynamic-update="true">
    ```
3. 需要注意的是作为自动插入的字段，它如果是必填字段，那么需要在配置文件中把not-null="true"属性给去掉，否则还是会报not-null property references a null or transient value:错误
```<?xml version="1.0"?>
   <!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
   "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
   <!-- Generated 2012-9-13 19:37:57 by Hibernate Tools 3.4.0.CR1 -->
   <hibernate-mapping>
       <class name="com.dada.pojo.Test" table="T_REP_FORM" dynamic-insert="true" dynamic-update="true">
          <id name="id" type="java.lang.Long">
               <column name="id" />
           </id>
           <property name="name" type="java.lang.String">
               <column name="NAME" />
           </property>
            <property name="birthday" type="java.util.Date">
               <column name="birthday" />
           </property>
       </class>
   </hibernate-mapping>```
   