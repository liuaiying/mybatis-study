package com.hy.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.mybatis.pojo.EmpBean;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface EmpService extends IService<EmpBean> {
    /*查询所有员工信息*/
    IPage<EmpBean> queryAll(String pageNum,EmpBean empBean);
    /*查询员工信息 返回对象*/
    EmpBean selectEmpById(String empno);
    /*添加员工信息*/
    int insertEmp(EmpBean empBean);
    /*修改员工信息*/
    int updateEmp(EmpBean empBean);
    /*删除员工信息*/
    int deleteEmp(String empno);
    /*批量删除*/
    int delDataFilesByIds(@Param("ids") String[] ids);
    /*批量删除（layuifa异步）*/
//    int batchDel(String ids);
    /*分页查询*/
    IPage<EmpBean> queryAll2(Integer pageNum,Integer limit,EmpBean empBean);
}
