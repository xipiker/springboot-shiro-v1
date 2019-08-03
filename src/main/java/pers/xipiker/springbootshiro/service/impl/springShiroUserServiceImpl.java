package pers.xipiker.springbootshiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xipiker.springbootshiro.dao.springShiroUserMapper;
import pers.xipiker.springbootshiro.domain.springShiroUser;
import pers.xipiker.springbootshiro.service.springShiroUserService;

/**
 * @author: $[USER]
 * @Date: $[DATE]
 * @Description:
 */
@Service("springShiroUserService")
public class springShiroUserServiceImpl implements springShiroUserService {

    @Autowired
    private springShiroUserMapper springShiroUserMapper;

    @Override
    public springShiroUser findByName(String username) {
        return springShiroUserMapper.findByName(username);
    }
}
