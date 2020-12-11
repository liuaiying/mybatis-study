package com.hy.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.mybatis.dao.DeptMapper;
import com.hy.mybatis.dao.EmpMapper;
import com.hy.mybatis.pojo.DeptBean;
import com.hy.mybatis.pojo.EmpBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmpServiceImpl extends ServiceImpl<EmpMapper,EmpBean> implements EmpService{

    @Autowired
    @Qualifier("empMapper")
    private EmpMapper empMapper;

    public void setEmpMapper(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public IPage<EmpBean> queryAll(String pageNum,EmpBean empBean) {
        Page<EmpBean> page = new Page<>(Integer.parseInt(pageNum),5);
        return empMapper.queryAll(page,empBean);
    }

    /*分页查询(layui)*/
    @Override
    public IPage<EmpBean> queryAll2(Integer pageNum, Integer limit, EmpBean empBean) {
        Page<EmpBean> page = new Page<>(pageNum,limit);
        return empMapper.queryAll(page,empBean);
    }

    @Override
    public EmpBean selectEmpById(String empno) {
        return empMapper.selectEmpById(empno);
    }

    @Override
    public int insertEmp(EmpBean empBean) {
        return empMapper.insertEmp(empBean);
    }

    @Override
    public int updateEmp(EmpBean empBean) {
        return empMapper.updateEmp(empBean);
    }

    @Override
    public int deleteEmp(String empno) {
        return empMapper.deleteEmp(empno);
    }

    @Override
    public int delDataFilesByIds(@Param("ids") String[] ids) {
        return empMapper.delDataFilesByIds(ids);
    }

   /* @Override
    public int batchDel(String ids) {
        ids.split(",");
        return empMapper.delDataFilesByIds();
    }*/
}
