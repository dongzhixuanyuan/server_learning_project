package db.hibernate;

import javax.persistence.*;

/**
 * @author liudong (liudong@rd.netease.com)
 * @description 文件描述
 * @date 2020/11/20 10:18 下午
 */
@Entity
class Book {
    private Long id;
    private String title;
    private Long createAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(nullable = false, updatable = false)
    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }
}
