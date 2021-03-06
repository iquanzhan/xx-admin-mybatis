package com.chengxiaoxiao.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.chengxiaoxiao.common.config.JwtConfig;
import com.chengxiaoxiao.common.jwt.JwtUtil;
import com.chengxiaoxiao.common.utils.IdWorker;
import com.chengxiaoxiao.common.utils.ResultUtil;
import com.chengxiaoxiao.model.common.dtos.result.CodeMsg;
import com.chengxiaoxiao.model.common.dtos.result.Result;
import com.chengxiaoxiao.model.mappers.web.SysUserMapper;
import com.chengxiaoxiao.model.repository.BaseDao;
import com.chengxiaoxiao.model.web.dtos.UserEntitySecurity;
import com.chengxiaoxiao.model.web.dtos.query.sysuser.SysLoginModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysuser.SysUserModelDto;
import com.chengxiaoxiao.model.web.dtos.query.sysuser.SysUserSearchDto;
import com.chengxiaoxiao.model.web.dtos.result.SysRoleSimpleDtos;
import com.chengxiaoxiao.model.web.dtos.result.UserInfoRolesDto;
import com.chengxiaoxiao.model.web.pojos.SysUser;
import com.chengxiaoxiao.model.repository.SysUserRepository;
import com.chengxiaoxiao.model.web.pojos.SysUserRole;
import com.chengxiaoxiao.web.exception.GlobleException;
import com.chengxiaoxiao.web.service.SysRoleService;
import com.chengxiaoxiao.web.service.SysUserRoleService;
import com.chengxiaoxiao.web.service.SysUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/1/21 10:45 下午
 * @Description:
 */
@Service
@SuppressWarnings("all")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, String> implements SysUserService {

    @Autowired
    SysUserRepository sysUserRepository;
    @Autowired
    SysUserRoleService sysUserRoleService;
    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    IdWorker idWorker;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public BaseDao<SysUser, String> getBaseDao() {
        return this.sysUserRepository;
    }

    @Override
    public SysUser insert(SysUserModelDto userDto) {
        SysUser user = new SysUser();
        BeanUtil.copyProperties(userDto, user, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));

        user.setId(idWorker.nextId() + "");
        user.setLocked(0);
        user.setDeleteStatus(0);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setPassword(encoder.encode(user.getPassword()));
        save(user);
        return user;
    }

    @Override
    public SysUser update(String id, SysUserModelDto userDto) {
        SysUser userDb = find(id);
        if (userDb == null) {
            throw new GlobleException(CodeMsg.USER_NOT_EXIST);
        }

        BeanUtil.copyProperties(userDto, userDb, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));

        userDb.setUpdateTime(new Date());
        save(userDb);
        return userDb;
    }

    @Override
    public Page<SysUser> search(SysUserSearchDto sysUserSearchDto, PageRequest pageRequest) {

        return getBaseDao().findAll(new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isBlank(sysUserSearchDto.getUserName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("userName").as(String.class), "%" + sysUserSearchDto.getUserName() + "%");
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(sysUserSearchDto.getTelephone())) {
                    Predicate predicate = criteriaBuilder.like(root.get("telephone").as(String.class), "%" + sysUserSearchDto.getTelephone() + "%");
                    list.add(predicate);
                }
                if (sysUserSearchDto.getBirthdayStartTime() != null) {
                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday").as(Date.class), sysUserSearchDto.getBirthdayStartTime());
                    list.add(predicate);
                }
                if (sysUserSearchDto.getBirthdayEndTime() != null) {
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("birthday").as(Date.class), sysUserSearchDto.getBirthdayEndTime());
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(sysUserSearchDto.getEmail())) {
                    Predicate predicate = criteriaBuilder.like(root.get("email").as(String.class), "%" + sysUserSearchDto.getEmail() + "%");
                    list.add(predicate);
                }
                if (sysUserSearchDto.getSex() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("sex").as(Integer.class), sysUserSearchDto.getSex());
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(sysUserSearchDto.getAddress())) {
                    Predicate predicate = criteriaBuilder.like(root.get("address").as(String.class), "%" + sysUserSearchDto.getAddress() + "%");
                    list.add(predicate);
                }
                if (!StringUtils.isBlank(sysUserSearchDto.getDescript())) {
                    Predicate predicate = criteriaBuilder.like(root.get("descript").as(String.class), "%" + sysUserSearchDto.getDescript() + "%");
                    list.add(predicate);
                }
                if (sysUserSearchDto.getLocked() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("locked").as(Integer.class), sysUserSearchDto.getLocked());
                    list.add(predicate);
                }

                if (!StringUtils.isBlank(sysUserSearchDto.getNickName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("nickName").as(String.class), "%" + sysUserSearchDto.getNickName() + "%");
                    list.add(predicate);
                }

                if (sysUserSearchDto.getStartCreateTime() != null) {
                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), sysUserSearchDto.getStartCreateTime());
                    list.add(predicate);
                }
                if (sysUserSearchDto.getEndCreateTime() != null) {
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), sysUserSearchDto.getEndCreateTime());
                    list.add(predicate);
                }

                list.add(criteriaBuilder.equal(root.get("deleteStatus").as(Integer.class), 0));

                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageRequest);
    }

    @Override
    public SysUser findUserByUserName(String userName) {
        return this.sysUserRepository.findByUserName(userName);
    }

    @Override
    public String login(SysLoginModelDto loginModelDto) {
        //根据用户名查询用户是否存在
        SysUser user = this.sysUserRepository.findByUserName(loginModelDto.getUserName());
        if (user == null) {
            throw new GlobleException(CodeMsg.USER_PASSWORD_INCORRENT);
        }

        if (!encoder.matches(loginModelDto.getPassword(), user.getPassword())) {
            throw new GlobleException(CodeMsg.USER_PASSWORD_INCORRENT);
        }

        String token = jwtUtil.createJWT(user.getId(), user.getUserName(), "roles");

        return token;
    }

    @Override
    public List<SysUser> findUsersByRoleId(String roleId) {
        return sysUserMapper.findUsersByRoleId(roleId);
    }

    @Override
    public SysUser selectUserByName(String username) {
        return sysUserRepository.findByUserName(username);
    }

    @Override
    public UserInfoRolesDto loadUserInfoBytoken(String token) {
        try {
            // 截取JWT前缀
            token = token.replace(jwtConfig.getTokenPrefix(), "");
            Claims claims = jwtUtil.parseJWT(token);
            String userId = claims.getId();

            Optional<SysUser> userInfo = sysUserRepository.findById(userId);
            if (!userInfo.isPresent()) {
                return null;
            }

            SysUser sysUser = userInfo.get();
            UserInfoRolesDto userInfoRolesDto = new UserInfoRolesDto();
            BeanUtil.copyProperties(sysUser,userInfoRolesDto,CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
            userInfoRolesDto.setRoles(sysRoleService.getRolesByUserId(sysUser.getId()));

            return userInfoRolesDto;

        } catch (ExpiredJwtException e) {
            throw new GlobleException(CodeMsg.AUTHENTICATION_TOKEN_EXPIRED);
        }
    }
}
