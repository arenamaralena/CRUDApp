package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }
}
