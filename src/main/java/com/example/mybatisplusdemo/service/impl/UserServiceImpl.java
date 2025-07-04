package com.example.mybatisplusdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplusdemo.common.utls.SessionUtils;
import com.example.mybatisplusdemo.model.domain.User;
import com.example.mybatisplusdemo.mapper.UserMapper;
import com.example.mybatisplusdemo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hxy
 * @since 2025-06-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> listByKey(String key) {
        LambdaQueryWrapper<User> wrapper =new LambdaQueryWrapper<>();
        wrapper.like(User::getLoginName,key);
        //userMapper.selectList(wrapper);
        return userMapper.selectList(wrapper);
    }

    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(User::getLoginName,user.getLoginName()).eq(User::getPassword,user.getPassword());
        //登录用户存进Session
        User one = userMapper.selectOne(wrapper);
        SessionUtils.saveCurrentUserInfo(one);
        return one;
    }


}
