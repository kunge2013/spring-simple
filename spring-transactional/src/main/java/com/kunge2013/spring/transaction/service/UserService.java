package com.kunge2013.spring.transaction.service;

/**
 * @Author kunge2013
 * @Description TODO
 * @Date 2025/5/24 20:01
 * @Version 1.0
 */

import com.kunge2013.spring.transaction.entity.User;
import com.kunge2013.spring.transaction.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 场景1：非public方法
    @Transactional
    void insertUserPrivate() {
        userMapper.insert(new User("private", 20));
        throw new RuntimeException("private method rollback test");
    }

    public void testPrivateMethod() {
        insertUserPrivate();
    }

    // 场景2：自调用
    public void selfInvocation() {
        insertUser(); // 错误的自调用方式
        // 正确的代理调用方式：((UserService) AopContext.currentProxy()).insertUser();
    }

    @Transactional
    public void insertUser() {
        userMapper.insert(new User("self", 25));
        throw new RuntimeException("self invocation rollback test");
    }

    // 场景3：检查型异常
    @Transactional
    public void checkedException() throws Exception {
        userMapper.insert(new User("checked", 30));
        throw new Exception("checked exception rollback test");
    }

    // 场景4：吞没异常
    @Transactional
    public void swallowException() {
        try {
            userMapper.insert(new User("swallow", 35));
            throw new RuntimeException("swallowed exception");
        } catch (Exception ignored) {
        }
    }

    // 场景5：传播行为
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported() {
        userMapper.insert(new User("notsupported", 40));
        throw new RuntimeException("propagation not supported");
    }
}