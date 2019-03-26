package com.hundsun.accountingsystem.Global.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundsun.accountingsystem.Global.bean.TSequence;
import com.hundsun.accountingsystem.Global.mapper.TSequenceMapper;
import com.hundsun.accountingsystem.Global.service.TSequenceService;
@Service
public class TSequenceServiceImpl implements TSequenceService{
    @Autowired
    private TSequenceMapper tSequenceMapper;
    
    @Override
    public int getSequenceByName(String name) {
    	int value;
        TSequence sequence = tSequenceMapper.selectTSequenceById(name);
        if(sequence==null) {
        	value = 1;
        	sequence = new TSequence("pz", 1, 1);
        	tSequenceMapper.insertNonEmptyTSequence(sequence);
        }else {
        	value = sequence.getCurrentValue()+sequence.getIncrement();
        	sequence.setCurrentValue(value);
        	tSequenceMapper.updateNonEmptyTSequenceById(sequence);
        }
        return value;
    }
  

}