package com.topwulian.model;

import java.util.List;

public class InputsDto {

    private List<InputsType> inputsTypeList; //投入品名称集合
    private List<InputsFactory> inputsFactoryList; //厂家集合

    public List<InputsType> getInputsTypeList() {
        return inputsTypeList;
    }

    public void setInputsTypeList(List<InputsType> inputsTypeList) {
        this.inputsTypeList = inputsTypeList;
    }

    public List<InputsFactory> getInputsFactoryList() {
        return inputsFactoryList;
    }

    public void setInputsFactoryList(List<InputsFactory> inputsFactoryList) {
        this.inputsFactoryList = inputsFactoryList;
    }
}
