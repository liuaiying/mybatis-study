package com.hy.mybatis.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {

     static SqlSessionFactory sqlSessionFactory;
     static SqlSession sqlSession;


    static {
        InputStream inputStream=null;
        try {
            String resource = "mybatis-config.xml";
             inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession=sqlSessionFactory.openSession();
    }
    public static SqlSession getSqlSession(){
        if(sqlSession!=null){
            sqlSession=sqlSessionFactory.openSession();
        }
        return sqlSession;
    }
}
