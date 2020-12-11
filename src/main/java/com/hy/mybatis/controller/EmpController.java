package com.hy.mybatis.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.mybatis.pojo.DeptBean;
import com.hy.mybatis.pojo.EmpBean;
import com.hy.mybatis.service.DeptService;
import com.hy.mybatis.service.EmpService;
import com.hy.mybatis.utils.LayuiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
public class EmpController {
    @Autowired
    private EmpService empService;
    @Autowired
    private DeptService deptService;
    /**
     * 查询所有员工信息
     * @param model
     * @return
     */
    @RequestMapping("/queryAll")
    public String queryAll(@RequestParam(defaultValue = "1") String page,Model model,EmpBean empBean){
        IPage<EmpBean> empBeans = empService.queryAll(page,empBean);
        //分页查询的结果集
        List<EmpBean> beanList = empBeans.getRecords();
        model.addAttribute("empBeans",beanList);
        //empBeans.getCurrent(); 当前页数
        model.addAttribute("page",empBeans.getCurrent());
        //getPages();一页几条
        model.addAttribute("pages",empBeans.getPages());
        //getTotal();总条数
        model.addAttribute("total",empBeans.getTotal());
        return "/jsp/page.jsp";
    }


    /**
     * 根据id查询（去修改）
     * @param model
     * @param empno
     * @return
     */
    @RequestMapping("/queryById")
    public String queryById(Model model,String empno){
        EmpBean empBean = empService.selectEmpById(empno);
        model.addAttribute("empBean",empBean);
        return "/jsp/upEmp.jsp";
    }

    /**
     * 添加员工信息
     * @param empBean
     * @return
     */
    @RequestMapping("/insert")
    public String insert(EmpBean empBean,@RequestParam("fileName") MultipartFile pictureFile,HttpServletRequest request) throws IOException {
        if(!pictureFile.isEmpty()){
            // 重命名
            String picName = UUID.randomUUID().toString();
            //获取文件全名
            String filename = pictureFile.getOriginalFilename();
            //获取文件后缀名
            String suffixName = filename.substring(filename.lastIndexOf("."));
            //获取服务所在根路径
            String realPath = request.getServletContext().getRealPath("ssmupload/");
            //开始上传
            File file = new File(realPath+picName+suffixName);
            pictureFile.transferTo(file);
            empBean.setImg(picName+suffixName);
        }else {
            empBean.setImg("");
        }
        empService.insertEmp(empBean);
        return "redirect:/queryAll";
    }

    /**
     * 修改员工信息
     * @param empBean
     * @return
     */
    @RequestMapping("/update")
    public String update(EmpBean empBean,@RequestParam("fileName") MultipartFile pictureFile,HttpServletRequest request) throws IOException {
        if(!StringUtils.isEmpty(pictureFile.getOriginalFilename())){
            // 重命名
            String picName = UUID.randomUUID().toString();
            //获取文件全名
            String filename = pictureFile.getOriginalFilename();
            //获取文件后缀名
            String suffixName = filename.substring(filename.lastIndexOf("."));
            //获取服务所在根路径
            String realPath = request.getServletContext().getRealPath("ssmupload/");
            //开始上传
            File file = new File(realPath+picName+suffixName);
            pictureFile.transferTo(file);
            empBean.setImg(picName+suffixName);
        }
        empService.updateEmp(empBean);
        return "redirect:/queryAll";
    }

    /**
     * 删除员工信息
     * @param empno
     * @return
     */
    @RequestMapping("/delete")
    public String delete(String empno){
        empService.deleteEmp(empno);
        return "/queryAll";
    }

    /**
     * 查询所有部门信息
     */
    @RequestMapping("/queryAllDept")
    @ResponseBody
    public List queryAllDept(){
        List<DeptBean> deptBean = deptService.queryAllDept();
        return deptBean;
    }

    /**
     * 批量删除
     * @return
     */
    @RequestMapping("/delDataFilesByIds")
    public String delDataFilesByIds(String[] ids){
        empService.delDataFilesByIds(ids);
        return "forward:queryAll";
    }


    /**
     * 查询（layui发异步）
     * @param page
     * @param limit
     * @param empBean
     * @return
     */
    @RequestMapping(value = "/queryEmpJson", produces={"application/json;charset=UTF-8"})
    @ResponseBody
    public LayuiUtils queryEmpJson(Integer page,Integer limit,EmpBean empBean) {
        IPage<EmpBean> beanIPage = empService.queryAll2(page,limit,empBean);
        LayuiUtils utils = new LayuiUtils();
        utils.setCode(0);
        utils.setMsg("");
        utils.setCount(beanIPage.getTotal());
        utils.setData(beanIPage.getRecords());
        return utils;
    }

    /**
     * 批量删除（layui发异步）
     * @return
     */
    @RequestMapping("/batchDel/{ids}")
    @ResponseBody
    public String batchDel(@PathVariable("ids")String ids){
        boolean flag =empService.removeByIds(Arrays.asList(ids.split(",")));
        if(flag){
          return  "1" ;//删除成功
        }else {
          return  "0" ;//删除失败
        }
    }

    /**
     * 删除（layui发异步）
     * @param empno
     * @return
     */
    @RequestMapping("/deleteEmp/{empno}")
    @ResponseBody
    public int deleteEmp(@PathVariable("empno") String empno){
        int flag = empService.deleteEmp(empno);
        return flag;
    }


    @RequestMapping("/save")
    @ResponseBody
    public int save(EmpBean empBean){
        System.out.println(empBean);
        return empService.insertEmp(empBean);
    }
    /**
     * 修改员工信息（layui发异步）
     * @param empBean
     * @return
     */
    @RequestMapping("/updateEmp")
    @ResponseBody
    public boolean updateEmp(EmpBean empBean){
        UpdateWrapper<EmpBean> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("empno",empBean.getEmpno());
        return empService.update(empBean,updateWrapper);
    }

    /**
     * 根据id查询（去修改layui发异步）
     * @param empno
     * @return
     */
    @RequestMapping("/queryById2")
    @ResponseBody
    public EmpBean queryById2(String empno){
        EmpBean empBean = empService.selectEmpById(empno);
        return empBean;
    }

    /**
     * 图片上传（layui发异步）
     * @param pictureFile
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public LayuiUtils upload(@RequestParam("file") MultipartFile pictureFile,HttpServletRequest request) throws IOException {
        // 重命名
        String picName = UUID.randomUUID().toString();
        //获取文件全名
        String filename = pictureFile.getOriginalFilename();
        //获取文件后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));
        //获取服务所在根路径
        String realPath = request.getServletContext().getRealPath("ssmupload/");
        //开始上传
        File file = new File(realPath+picName+suffixName);
        pictureFile.transferTo(file);
        LayuiUtils layuiUtils = new LayuiUtils();
        ArrayList<EmpBean> list = new ArrayList<>();
        EmpBean empBean = new EmpBean();
        empBean.setImg(picName+suffixName);
        list.add(empBean);
        layuiUtils.setData(list);
        return layuiUtils;
    }
}
