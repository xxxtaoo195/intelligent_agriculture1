package com.topwulian.model;

import java.util.Date;

/**
 * 投入品管理
 */
public class InputsType {

    private Integer inputsId; //投入品编号

    private String  inputsName;  //投入品名称

    private Integer inputsType; //投入品种类 :1:农药 2:种子 3:肥料 4:兽药 5 饲料

    public Integer getInputsType() {
        return inputsType;
    }

    public void setInputsType(Integer inputsType) {
        this.inputsType = inputsType;
    }

    public Integer getInputsId() {
        return inputsId;
    }

    public void setInputsId(Integer inputsId) {
        this.inputsId = inputsId;
    }

    public String getInputsName() {
        return inputsName;
    }

    public void setInputsName(String inputsName) {
        this.inputsName = inputsName;
    }
}
