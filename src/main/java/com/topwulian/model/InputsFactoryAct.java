package com.topwulian.model;

/**
 * 投入品管理
 */
public class InputsFactoryAct extends BaseEntity<Long> {

    private Long fid; //生产厂家名称
    private Long inputsId; //登记证号

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Long getInputsId() {
        return inputsId;
    }

    public void setInputsId(Long inputsId) {
        this.inputsId = inputsId;
    }
}
