package db.hibernate

import org.hibernate.criterion.DetachedCriteria
import org.hibernate.criterion.Restrictions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.orm.hibernate5.HibernateTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


/**
 * @description 文件描述
 *
 * @author liudong (735106520@qq.com)
 * @date 2020/11/20 10:22 下午
 */

@Component
@Transactional
open class UserServices(@Autowired var hibernateTemplate: HibernateTemplate) {

    open fun register(email: String, password: String, name: String): User? {
        // 创建一个User对象:
        val user = User()
        // 设置好各个属性:
        user.email = email
        user.password = password
        user.name = name
        user.jaofjai =  System.currentTimeMillis()
        // 不要设置id，因为使用了自增主键
        // 保存到数据库:
        hibernateTemplate.save(user)
        // 现在已经自动获得了id:
        println(user.id)
        return user
    }

    open fun deleteUser(id: Long?): Boolean {
        val user = hibernateTemplate.get(User::class.java, id)
        if (user != null) {
            hibernateTemplate.delete(user)
            return true
        }
        return false
    }

    open fun updateUser(id: Long?, name: String?) {
        val user = hibernateTemplate.load(User::class.java, id)
        user.name = name
        hibernateTemplate.update(user)
    }

    open fun login(email: String?, password: String?): User? {
        val example = User()
        example.email = email
        example.password = password
        val list = hibernateTemplate.findByExample(example)
        return if (list.isEmpty()) null else list[0]
    }

    open fun login2(email: String?, password: String?): User? {
        val criteria = DetachedCriteria.forClass(User::class.java)
        criteria.add(Restrictions.eq("email", email))
            .add(Restrictions.eq("password", password))
        val list = hibernateTemplate.findByCriteria(criteria) as List<User?>
        return if (list.isEmpty()) null else list[0]
    }
}