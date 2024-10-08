package com.dgp.excel.head;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class HeadMeta {

    /**
     * <p>
     * 自定义头部信息
     * </p>
     * 实现类根据数据的class信息，定制Excel头<br/>
     *
     * @param clazz 当前sheet的数据类型
     * @return List<List < String>> Head头信息
     */
    private List<List<String>> head;

    /**
     * 忽略头对应字段名称
     */
    private Set<String> ignoreHeadFields;

}
