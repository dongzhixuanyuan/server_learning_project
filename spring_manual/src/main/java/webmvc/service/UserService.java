package webmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import webmvc.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional  //这个得加，不然出现Write operations are not allowed in read-only mode (FlushMode.MANUAL): Turn your Session into FlushMode.COMMIT/AUTO or remove 'readOnly' marker from transaction definition.
public class UserService {

    @Autowired
    HibernateTemplate mHibernateTemplate;

    public String insertUser(String email,String name,String password){
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        try {
            mHibernateTemplate.save(user);
            return "success insert";
        } catch (Exception e) {
            return "fail";
        }
    }

    public User findUser(String name) {
        User user = new User();
        user.setName(name);
        List<User> users = mHibernateTemplate.findByExample(user);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }


}
