package com.hundsun.accountingsystem.Global.bean;
public class TSequence implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String name;//序列名称
    private Integer increment;//增长大小
    private Integer currentValue;//当前值
    public TSequence() {
        super();
    }
    public TSequence(String name,Integer increment,Integer currentValue) {
        super();
        this.name = name;
        this.increment = increment;
        this.currentValue = currentValue;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIncrement() {
        return this.increment;
    }

    public void setIncrement(Integer increment) {
        this.increment = increment;
    }

    public Integer getCurrentValue() {
        return this.currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

}
