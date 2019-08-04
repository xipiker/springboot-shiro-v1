package pers.xipiker.springbootshiro.dao;

import pers.xipiker.springbootshiro.domain.springShiroUser;

public interface springShiroUserMapper {
    springShiroUser findByName(String username);
    springShiroUser findById(Integer id);
}