package com.cdc.train.service.impl;

import com.cdc.train.common.Result;
import com.cdc.train.common.ResultCode;
import com.cdc.train.dao.UserDao;
import com.cdc.train.dao.UserInfoDao;
import com.cdc.train.dao.UserRoleDao;
import com.cdc.train.entity.User;
import com.cdc.train.entity.UserInfo;
import com.cdc.train.entity.UserRole;
import com.cdc.train.entity.dto.UserDTO;
import com.cdc.train.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2020-08-25 15:40:01
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UserRoleDao userRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(String userId) {
        if (StringUtils.isEmpty(userId)){
            return new Result(ResultCode.ERROR,"userId为空");
        }
        UserDTO userDTO = userDao.queryById(userId);
        if (userDTO!=null){
            return new Result(ResultCode.SUCCESS,userDTO);
        }
        return new Result(ResultCode.ERROR,null);
    }

    @Override
    public User queryByUser(User user) {
        return this.userDao.queryByUser(user);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public Result insert(UserDTO user) {
        if (user == null) {
            return new Result(ResultCode.ERROR,"传进来参数为空");
        }
        if (user.getUserId() == null) {
            return new Result(ResultCode.ERROR,"openId为空");
        }
        if (StringUtils.isEmpty(user.getPhone()) || StringUtils.isEmpty(user.getEmail())) {
            return new Result(ResultCode.ERROR,"手机号/邮箱为空");
        }
        if (StringUtils.isEmpty(user.getGender()) || StringUtils.isEmpty(user.getRealName())) {
            return new Result(ResultCode.ERROR,"性别/真名为空");
        }
        if (StringUtils.isEmpty(user.getDeptId()) || StringUtils.isEmpty(user.getRoleId())) {
            return new Result(ResultCode.ERROR,"部门/角色为空");
        }
        if (userInfoDao.queryById(user.getUserId())!=null){
            return new Result(ResultCode.ERROR,"该用户已存在");
        }
        if (StringUtils.isEmpty(user.getAvatar()) || StringUtils.isEmpty(user.getNickname())) {
            return new Result(ResultCode.ERROR,"头像/昵称为空");
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setUserId(user.getUserId());
        userInfo.setName(user.getRealName());
        userInfo.setGender(user.getGender());
        userInfoDao.insert(userInfo);

        UserRole userRole = new UserRole();
        userRole.setRoleId(user.getRoleId());
        userRole.setUserId(user.getUserId());

        userRoleDao.insert(userRole);

        User newUser = new User();
        BeanUtils.copyProperties(user,newUser);
        //未审核
        user.setStatus("0");
        user.setCreateTime(new Date());

        userDao.insert(user);
        return new Result(ResultCode.SUCCESS,"操作成功");
    }

    /**
     * 修改数据
     *
     * @param params 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public Result update(Map<String,Object> params) {
        int result;
        if (params.isEmpty()||!params.containsKey("userId")||!params.containsKey("status")){
            return new Result(ResultCode.ERROR,"参数或userId为空");
        }
        UserDTO queryById = userDao.queryById(params.get("userId").toString());
        if (queryById==null){
            return new Result(ResultCode.ERROR,"不存在该用户");
        }

        result = userDao.update(params);
        result = userInfoDao.update(params);
        if (result<=0){
            return new Result(ResultCode.ERROR,"没有数据更新");
        }
        return new Result(ResultCode.SUCCESS,"操作成功");
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userId) {
        return this.userDao.deleteById(userId) > 0;
    }

    @Override
    public Result queryAllUser(Map<String,Object> params) {
        List<UserDTO> userList = userDao.queryAll(params);
        return new Result(ResultCode.SUCCESS, CollectionUtils.isEmpty(userList)?null:userList);
    }

    @Override
    public Result updateStatus(Map<String, Object> params) {
        String userId = params.get("userId").toString();
        if (userDao.queryById(userId)==null){
            return new Result(ResultCode.ERROR,"该用户不存在");
        }
        userDao.update(params);
        return new Result(ResultCode.SUCCESS,"更新成功");
    }

    @Override
    public Result checkUser(Map<String, Object> params) {
        UserDTO userDTO = userDao.queryById(params.get("userId").toString());
        if (userDTO==null||!userDTO.getStatus().equals("1")){
            return new Result(ResultCode.ERROR,"用户不存在");
        }
        return new Result(ResultCode.SUCCESS,userDTO);
    }
}