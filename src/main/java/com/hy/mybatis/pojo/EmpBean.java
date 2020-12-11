package com.hy.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

@TableName(value = "EMP")
public class EmpBean {
    //主键生成
    @TableId(value = "EMPNO",type = IdType.ASSIGN_UUID)
    private String empno;
    private String ename;
    private String job;
    private String mgr;
    private Date hiredate;
    private String sal;
    private String comm;
    private String img;
    private Integer deptno;//所在部门id
    private DeptBean deptBean;

    @Override
    public String toString() {
        return "EmpBean{" +
                "empno='" + empno + '\'' +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr='" + mgr + '\'' +
                ", hiredate=" + hiredate +
                ", sal='" + sal + '\'' +
                ", comm='" + comm + '\'' +
                ", img='" + img + '\'' +
                ", deptno=" + deptno +
                ", deptBean=" + deptBean +
                '}';
    }
}
