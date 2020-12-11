package com.hy.mybatis.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.mybatis.dao.DeptMapper;
import com.hy.mybatis.pojo.DeptBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptService extends ServiceImpl<DeptMapper,DeptBean> {
    @Autowired
    @Qualifier("deptMapper")
    private DeptMapper deptMapper;

    public void setDeptMapper(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    /**
     * 查询所有部门信息
     * @return
     */
    public List<DeptBean> queryAllDept(){
        return deptMapper.selectList(null);
    }
}
