package com.vacomall.act.service.impl;

import org.springframework.stereotype.Service;

import com.vacomall.act.common.service.impl.LayuiServiceImpl;
import com.vacomall.act.entity.ActPage;
import com.vacomall.act.repository.ActPageRepository;
import com.vacomall.act.service.IActPageService;

@Service
public class ActPageServiceImpl extends LayuiServiceImpl<ActPageRepository, ActPage, Long> implements IActPageService{

}
