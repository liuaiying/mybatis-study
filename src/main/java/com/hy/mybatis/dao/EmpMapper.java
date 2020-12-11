package com.hy.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.mybatis.pojo.EmpBean;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.ArrayList;
@Mapper
public interface EmpMapper extends BaseMapper<EmpBean> {
    /*查询所有员工信息*/
    /*@Results({
            @Result(column = "deptno",property ="DeptBean",
            one = @One(select = "com.hy.mybatis.dao.deptMapper.selectDeptById",
            fetchType = FetchType.EAGER))
    })*/
//    @Select("select * from emp")
    IPage<EmpBean> queryAll(Page<EmpBean> page,@Param("emp") EmpBean empBean);

    /*分页查询*/
    //IPage<EmpBean> queryAll2(Page<EmpBean> page,EmpBean empBean);

    /*查询员工信息 返回对象*/
    @Select("select * from EMP where EMPNO = #{VALUE}")
    EmpBean selectEmpById(String empno);

    /*添加员工信息*/
    /*主键生成策略uuid*/
    @SelectKey(statement="select sys_guid() from dual", before=true,resultType=String.class,keyProperty="empno")
    @Insert("insert into EMP(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO,IMG) VALUES(#{empno},#{ename},#{job},#{mgr},#{hiredate},#{sal},#{comm},#{deptno},#{img})")
    int insertEmp(EmpBean empBean);

    /*修改员工信息*/
    @Update("UPDATE EMP SET ENAME=#{ename}, JOB=#{job}, MGR=#{mgr}, HIREDATE=#{hiredate}, SAL=#{sal}, COMM=#{comm}, DEPTNO=#{deptno},IMG=#{img}WHERE EMPNO = #{empno}")
    int updateEmp(EmpBean empBean);

    /*删除员工信息*/
    @Delete("delete from EMP where EMPNO = #{VALUE}")
    int deleteEmp(String empno);

    /*批量删除*/
    int delDataFilesByIds(@Param("ids") String[] ids);
}
