package db.hibernate;

import javax.persistence.*;

/**
 * @author magina
 * @description 文件描述
 * @date 2020/11/15 9:38 下午
 */

@Entity
@Table(name = "users")
public class User {

    private Long id;
    private String email;
    private String password;
    private String name;
    private Long jaofjai;

    @Column()
    public Long getJaofjai() {
        return jaofjai;
    }

    public void setJaofjai(Long jaofjai) {
        this.jaofjai = jaofjai;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
