/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.submitted.complex_column;

import java.io.Reader;
import java.sql.Connection;

import org.junit.Assert;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

public class ComplexColumnTest {
    
    private static SqlSessionFactory sqlSessionFactory;
    
    @BeforeClass
    public static void initDatabase() throws Exception {
        // create a SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/complex_column/ibatisConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();

        // populate in-memory database
        SqlSession session = sqlSessionFactory.openSession();
        Connection conn = session.getConnection();
        reader = Resources.getResourceAsReader("org/apache/ibatis/submitted/complex_column/CreateDB.sql");
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setLogWriter(null);
        runner.runScript(reader);
        reader.close();
        session.close();
    }

    @Test(groups={"tidb"})
    public void testWithoutComplex() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = personMapper.getWithoutComplex(2l);
        Assert.assertNotNull("person must not be null", person);
        Assert.assertEquals("Christian", person.getFirstName());
        Assert.assertEquals("Poitras", person.getLastName());
        Person parent = person.getParent();
        Assert.assertNotNull("parent must not be null", parent);
        Assert.assertEquals("John", parent.getFirstName());
        Assert.assertEquals("Smith", parent.getLastName());
      sqlSession.close();
    }

    @Test(groups={"tidb"})
    public void testWithComplex() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = personMapper.getWithComplex(2l);
        Assert.assertNotNull("person must not be null", person);
        Assert.assertEquals("Christian", person.getFirstName());
        Assert.assertEquals("Poitras", person.getLastName());
        Person parent = person.getParent();
        Assert.assertNotNull("parent must not be null", parent);
        Assert.assertEquals("John", parent.getFirstName());
        Assert.assertEquals("Smith", parent.getLastName());
      sqlSession.close();

    }

    @Test(groups={"tidb"})
    public void testWithComplex2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = personMapper.getWithComplex2(2l);
        Assert.assertNotNull("person must not be null", person);
        Assert.assertEquals("Christian", person.getFirstName());
        Assert.assertEquals("Poitras", person.getLastName());
        Person parent = person.getParent();
        Assert.assertNotNull("parent must not be null", parent);
        Assert.assertEquals("John", parent.getFirstName());
        Assert.assertEquals("Smith", parent.getLastName());
      sqlSession.close();

    }

    @Test(groups={"tidb"})
    public void testWithComplex3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
        Person person = personMapper.getWithComplex3(2l);
        Assert.assertNotNull("person must not be null", person);
        Assert.assertEquals("Christian", person.getFirstName());
        Assert.assertEquals("Poitras", person.getLastName());
        Person parent = person.getParent();
        Assert.assertNotNull("parent must not be null", parent);
        Assert.assertEquals("John", parent.getFirstName());
        Assert.assertEquals("Smith", parent.getLastName());
      sqlSession.close();

    }

    @Test(groups={"tidb"})
    public void testWithComplex4() {
      SqlSession sqlSession = sqlSessionFactory.openSession();
      PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
      Person criteria = new Person();
      criteria.setFirstName("Christian");
      criteria.setLastName("Poitras");
      Person person = personMapper.getParentWithComplex(criteria);
      Assert.assertNotNull("person must not be null", person);
      Assert.assertEquals("Christian", person.getFirstName());
      Assert.assertEquals("Poitras", person.getLastName());
      Person parent = person.getParent();
      Assert.assertNotNull("parent must not be null", parent);
      Assert.assertEquals("John", parent.getFirstName());
      Assert.assertEquals("Smith", parent.getLastName());
      sqlSession.close();
    }
}
