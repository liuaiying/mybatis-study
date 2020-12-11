package com.hy.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "DEPT")
public class DeptBean {
    private Integer deptno;
    private String dname;
    private String loc;

    @Override
    public String toString() {
        return "DeptBean{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
