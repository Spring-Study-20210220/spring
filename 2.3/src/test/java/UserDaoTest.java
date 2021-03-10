import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import user.dao.UserDao;
import user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;


public class UserDaoTest {

   private UserDao dao;

   @Before
   void setUp(){
      ApplicationContext context = new GenericXmlApplicationContext(
              "applicationContext.xml");

      dao = context.getBean("userDao", UserDao.class);
   }

   @Test
   public void addAndGet() throws SQLException, ClassNotFoundException {
      ApplicationContext context = new GenericXmlApplicationContext(
              "applicationContext.xml");

      UserDao dao = context.getBean("userDao", UserDao.class);
      User user = new User();
      user.setId("id");
      user.setName("이름");
      user.setPassword("password");

      dao.add(user);

      User user2 = dao.get(user.getId());

      assertThat(user2.getName(), is(user.getName()));
      assertThat(user2.getPassword(), is(user.getPassword()));

      dao.deleteAll();
      assertThat(dao.getCount(), is(0));

      User user3 = new User();
      user.setId("test2");
      user.setName("이름2");
      user.setPassword("password2");

      dao.add(user3);

      User user4 = dao.get(user.getId());

      assertThat(user4.getName(), is(user3.getName()));
      assertThat(user4.getPassword(), is(user3.getPassword()));


   }

   @Test
   void count() throws SQLException, ClassNotFoundException {
      ApplicationContext context = new GenericXmlApplicationContext(
              "applicationContext.xml");

      UserDao dao = context.getBean("userDao", UserDao.class);
      User user1 = new User("id1", "이름1", "pwd1");
      User user2 = new User("id2", "이름2", "pwd2");
      User user3 = new User("id3", "이름3", "pwd3");


      dao.deleteAll();
      assertThat(dao.getCount(), is(0));

      dao.add(user1);
      assertThat(dao.getCount(), is(1));

      dao.add(user2);
      assertThat(dao.getCount(), is(2));

      dao.add(user3);
      assertThat(dao.getCount(), is(3));


   }

   @Test(expected = EmptyResultDataAccessException.class)
   void getUserFailure() throws SQLException {
      ApplicationContext context = new GenericXmlApplicationContext(
              "applicationContext.xml");

      UserDao dao = context.getBean("userDao", UserDao.class);
      dao.deleteAll();
      assertThat(dao.getCount(), is(0));

      dao.get("찾으려는아이디");
   }

}