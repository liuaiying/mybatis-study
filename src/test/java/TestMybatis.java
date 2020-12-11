import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.mybatis.dao.EmpMapper;
import com.hy.mybatis.pojo.EmpBean;
import com.hy.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestMybatis {

    @Test
    public void test01(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        /*String ename = sqlSession.selectOne("emp.selectEmpById","ff80808175aaf4d20175aaf6bab90001");
        System.out.println(ename);*/

        /*EmpBean empBean = sqlSession.selectOne("emp.selectEmp2","ff80808175aaf4d20175aaf6bab90001");
        System.out.println(empBean);*/

       /* List<EmpBean> empBeans = sqlSession.selectList("emp.selectEmp");
        System.out.println(empBeans);*/

  /*      EmpBean emp = new EmpBean();
        emp.setEmpno("4028ab3875bf9a550175bfa05fcc0003");
        emp.setEname("你是猪X");
        emp.setJob("服装店老板");
        sqlSession.update("emp.updateEmp",emp);
        sqlSession.commit();*/

       /* EmpBean emp = new EmpBean();
        emp.setEname("腾轩");
        emp.setJob("吃fan");
        emp.setDeptno(8);
        System.out.println(sqlSession.insert("emp.insertEmp", emp));
        sqlSession.commit();*/

        /*int falg = sqlSession.delete("emp.deleteEmp","1212");
        sqlSession.commit();
        System.out.println(falg);*/

        /*EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        EmpBean emp = new EmpBean();
        emp.setEname("大毛");
        empMapper.queryLike(emp);*/
    }

    @Test
    public void demo(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpMapper bean = context.getBean(EmpMapper.class);
       /* QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ENAME","上海");
        Page<EmpMapper> page = new Page(2,3);
        IPage empBeanIPage = bean.selectPage(page,queryWrapper);*/

//        bean.deleteEmp("ff80808175aaf4d20175aaf6bab90001");
        /*ArrayList<EmpBean> empBeans = bean.queryAll();
        for (EmpBean empBean : empBeans) {
            System.out.println(empBean);
        }*/
        /*EmpBean emp = new EmpBean();
        emp.setEname("大毛");
        EmpBean empBean = bean.queryLike(emp);
        System.out.println(empBean);*/
    }
}
