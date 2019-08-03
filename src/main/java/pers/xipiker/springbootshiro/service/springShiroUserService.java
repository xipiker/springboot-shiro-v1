package pers.xipiker.springbootshiro.service;

import pers.xipiker.springbootshiro.domain.springShiroUser;

public interface springShiroUserService {
    public springShiroUser findByName(String username);
}
