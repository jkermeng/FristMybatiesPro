package server;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatiseConnetiong {

    public static SqlSession getMyBatise() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis_conf.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        return sqlSession;
    }

}
