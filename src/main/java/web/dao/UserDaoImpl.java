package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserDaoImpl implements UserDao {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(1);
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void edit(User user) {
        User editedUser = entityManager.find(User.class, user.getId());
        if (editedUser != null) {
            editedUser.setName(user.getName());
            editedUser.setAge(user.getAge());
            entityManager.merge(editedUser);
        }
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }
}