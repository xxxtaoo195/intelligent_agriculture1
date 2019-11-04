package com.topwulian.service.impl;

import com.topwulian.dao.InputsActDao;
import com.topwulian.model.InputsAct;
import com.topwulian.model.InputsFactory;
import com.topwulian.model.InputsFactoryAct;
import com.topwulian.model.InputsType;
import com.topwulian.service.InputsActService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.acl.LastOwnerException;
import java.util.List;
import java.util.Map;

@Service
public class InputsActServiceImpl implements InputsActService {


    @Autowired
    private InputsActDao inputsActDao;


    @Override
    public int save(InputsAct InputsAct) {

        //添加投入品名称
        List<InputsType> inputsTypeList =  inputsActDao.getByNameTypes(InputsAct.getInputsName());
        Integer inputsId = 0;
        if(inputsTypeList.size()>0){
            inputsId = inputsTypeList.get(0).getInputsId();
        }else{
            InputsType inputsType = new InputsType();
            inputsType.setInputsName(InputsAct.getInputsName());
            inputsType.setInputsType(InputsAct.getInputsType());
            inputsActDao.saveType(inputsType);
            inputsId = inputsType.getInputsId();
        }

        //添加生产厂家
        InputsFactory inputsFactory = new InputsFactory();
        inputsFactory.setFactoryLicense(InputsAct.getFactoryLicense());
        inputsFactory.setFactoryName(InputsAct.getManufactor());
        inputsActDao.saveFactory(inputsFactory);
        Long inputsFactoryId  = inputsFactory.getId();


        //添加生产厂家和投入品名称关联
        InputsFactoryAct inputsFactoryAct = new InputsFactoryAct();
        inputsFactoryAct.setFid(inputsFactoryId);
        inputsFactoryAct.setInputsId(Long.parseLong(inputsId+""));
        inputsActDao.saveFactoryAct(inputsFactoryAct);

        //添加投入品
        InputsAct.setInputsId(inputsId);
        InputsAct.setFid(inputsFactoryId);
        inputsActDao.save(InputsAct);


        return 1;
    }

    @Override
    public int count(Map<String, Object> params) {
        return inputsActDao.count(params);
    }

    @Override
    public List<InputsAct> list(Map<String, Object> params, Integer offset, Integer limit) {
        return inputsActDao.list(params,offset,limit);
    }

    @Override
    public int actcount(Map<String, Object> params) {
        return inputsActDao.actcount(params);
    }

    @Override
    public List<InputsAct> actlist(Map<String, Object> params, Integer offset, Integer limit) {
        return inputsActDao.actlist(params,offset,limit);
    }

    @Override
    public List<InputsAct> getByBatchIdInfo(String batchId) {
        return inputsActDao.getByBatchIdInfo(batchId);
    }

    @Override
    public List<InputsAct> getActInfoList() {
        return inputsActDao.getActInfoList();
    }

}
