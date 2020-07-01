package com.hr.test;

import com.hr.dao.IRoleDao;
import com.hr.dao.IUserDao;
import com.hr.domain.Role;
import com.hr.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class RoleTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IRoleDao roleDao;

    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = sqlSessionFactory.openSession(true);
        roleDao = sqlSession.getMapper(IRoleDao.class);
    }

    @After
    public void destory() throws IOException {
        sqlSession.close();
        in.close();
    }

    /**
     * 查询角色信息 同时对应角色的用户
     */
    @Test
    public void testFindAll() {
        List<Role> roles = roleDao.findAll();
        for(Role role :roles) {
            System.out.println("----------每个角色的信息------------");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }


}
