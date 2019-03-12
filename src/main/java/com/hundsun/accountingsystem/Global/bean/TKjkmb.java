package com.hundsun.accountingsystem.Global.bean;

public class TKjkmb {
    private String id;

    private String parentId;

    private String name;

    private Integer lendingDirection;

    private Integer level;

    private Integer isParent;

    public TKjkmb(String id, String parentId, String name, Integer lendingDirection, Integer level, Integer isParent) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.lendingDirection = lendingDirection;
        this.level = level;
        this.isParent = isParent;
    }

    public TKjkmb() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLendingDirection() {
        return lendingDirection;
    }

    public void setLendingDirection(Integer lendingDirection) {
        this.lendingDirection = lendingDirection;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsParent() {
        return isParent;
    }

    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }
}