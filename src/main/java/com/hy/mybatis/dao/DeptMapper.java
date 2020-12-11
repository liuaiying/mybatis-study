package com.hy.mybatis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.mybatis.pojo.DeptBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DeptMapper extends BaseMapper<DeptBean> {
//    @Select("select * from DEPT where DEPTNO = #{VALUE}")
    DeptBean selectDeptById(String deptno);
//    @Select("select * from DEPT")
//    List<DeptBean> queryAll();
}
