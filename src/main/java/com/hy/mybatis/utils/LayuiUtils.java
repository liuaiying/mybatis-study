package com.hy.mybatis.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayuiUtils {
    private Integer code;
    private String msg;
    private long count;
    private List data;
}
