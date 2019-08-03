package pers.xipiker.springbootshiro.dao;

import pers.xipiker.springbootshiro.domain.springShiroUser;

public interface springShiroUserMapper {
    public springShiroUser findByName(String username);
}