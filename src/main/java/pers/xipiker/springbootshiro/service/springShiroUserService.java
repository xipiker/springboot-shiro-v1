package pers.xipiker.springbootshiro.service;

import pers.xipiker.springbootshiro.domain.springShiroUser;

public interface springShiroUserService {
    springShiroUser findByName(String username);
    springShiroUser findById(Integer id);
}
