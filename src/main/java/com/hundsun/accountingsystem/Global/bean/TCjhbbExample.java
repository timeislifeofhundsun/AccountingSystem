package com.hundsun.accountingsystem.Global.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCjhbbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TCjhbbExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGddmIsNull() {
            addCriterion("gddm is null");
            return (Criteria) this;
        }

        public Criteria andGddmIsNotNull() {
            addCriterion("gddm is not null");
            return (Criteria) this;
        }

        public Criteria andGddmEqualTo(String value) {
            addCriterion("gddm =", value, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmNotEqualTo(String value) {
            addCriterion("gddm <>", value, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmGreaterThan(String value) {
            addCriterion("gddm >", value, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmGreaterThanOrEqualTo(String value) {
            addCriterion("gddm >=", value, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmLessThan(String value) {
            addCriterion("gddm <", value, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmLessThanOrEqualTo(String value) {
            addCriterion("gddm <=", value, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmLike(String value) {
            addCriterion("gddm like", value, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmNotLike(String value) {
            addCriterion("gddm not like", value, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmIn(List<String> values) {
            addCriterion("gddm in", values, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmNotIn(List<String> values) {
            addCriterion("gddm not in", values, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmBetween(String value1, String value2) {
            addCriterion("gddm between", value1, value2, "gddm");
            return (Criteria) this;
        }

        public Criteria andGddmNotBetween(String value1, String value2) {
            addCriterion("gddm not between", value1, value2, "gddm");
            return (Criteria) this;
        }

        public Criteria andXwbhIsNull() {
            addCriterion("xwbh is null");
            return (Criteria) this;
        }

        public Criteria andXwbhIsNotNull() {
            addCriterion("xwbh is not null");
            return (Criteria) this;
        }

        public Criteria andXwbhEqualTo(String value) {
            addCriterion("xwbh =", value, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhNotEqualTo(String value) {
            addCriterion("xwbh <>", value, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhGreaterThan(String value) {
            addCriterion("xwbh >", value, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhGreaterThanOrEqualTo(String value) {
            addCriterion("xwbh >=", value, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhLessThan(String value) {
            addCriterion("xwbh <", value, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhLessThanOrEqualTo(String value) {
            addCriterion("xwbh <=", value, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhLike(String value) {
            addCriterion("xwbh like", value, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhNotLike(String value) {
            addCriterion("xwbh not like", value, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhIn(List<String> values) {
            addCriterion("xwbh in", values, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhNotIn(List<String> values) {
            addCriterion("xwbh not in", values, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhBetween(String value1, String value2) {
            addCriterion("xwbh between", value1, value2, "xwbh");
            return (Criteria) this;
        }

        public Criteria andXwbhNotBetween(String value1, String value2) {
            addCriterion("xwbh not between", value1, value2, "xwbh");
            return (Criteria) this;
        }

        public Criteria andZqdmIsNull() {
            addCriterion("zqdm is null");
            return (Criteria) this;
        }

        public Criteria andZqdmIsNotNull() {
            addCriterion("zqdm is not null");
            return (Criteria) this;
        }

        public Criteria andZqdmEqualTo(String value) {
            addCriterion("zqdm =", value, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmNotEqualTo(String value) {
            addCriterion("zqdm <>", value, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmGreaterThan(String value) {
            addCriterion("zqdm >", value, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmGreaterThanOrEqualTo(String value) {
            addCriterion("zqdm >=", value, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmLessThan(String value) {
            addCriterion("zqdm <", value, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmLessThanOrEqualTo(String value) {
            addCriterion("zqdm <=", value, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmLike(String value) {
            addCriterion("zqdm like", value, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmNotLike(String value) {
            addCriterion("zqdm not like", value, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmIn(List<String> values) {
            addCriterion("zqdm in", values, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmNotIn(List<String> values) {
            addCriterion("zqdm not in", values, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmBetween(String value1, String value2) {
            addCriterion("zqdm between", value1, value2, "zqdm");
            return (Criteria) this;
        }

        public Criteria andZqdmNotBetween(String value1, String value2) {
            addCriterion("zqdm not between", value1, value2, "zqdm");
            return (Criteria) this;
        }

        public Criteria andJyscIsNull() {
            addCriterion("jysc is null");
            return (Criteria) this;
        }

        public Criteria andJyscIsNotNull() {
            addCriterion("jysc is not null");
            return (Criteria) this;
        }

        public Criteria andJyscEqualTo(Integer value) {
            addCriterion("jysc =", value, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscNotEqualTo(Integer value) {
            addCriterion("jysc <>", value, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscGreaterThan(Integer value) {
            addCriterion("jysc >", value, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscGreaterThanOrEqualTo(Integer value) {
            addCriterion("jysc >=", value, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscLessThan(Integer value) {
            addCriterion("jysc <", value, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscLessThanOrEqualTo(Integer value) {
            addCriterion("jysc <=", value, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscIn(List<Integer> values) {
            addCriterion("jysc in", values, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscNotIn(List<Integer> values) {
            addCriterion("jysc not in", values, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscBetween(Integer value1, Integer value2) {
            addCriterion("jysc between", value1, value2, "jysc");
            return (Criteria) this;
        }

        public Criteria andJyscNotBetween(Integer value1, Integer value2) {
            addCriterion("jysc not between", value1, value2, "jysc");
            return (Criteria) this;
        }

        public Criteria andCjslIsNull() {
            addCriterion("cjsl is null");
            return (Criteria) this;
        }

        public Criteria andCjslIsNotNull() {
            addCriterion("cjsl is not null");
            return (Criteria) this;
        }

        public Criteria andCjslEqualTo(Integer value) {
            addCriterion("cjsl =", value, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslNotEqualTo(Integer value) {
            addCriterion("cjsl <>", value, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslGreaterThan(Integer value) {
            addCriterion("cjsl >", value, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslGreaterThanOrEqualTo(Integer value) {
            addCriterion("cjsl >=", value, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslLessThan(Integer value) {
            addCriterion("cjsl <", value, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslLessThanOrEqualTo(Integer value) {
            addCriterion("cjsl <=", value, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslIn(List<Integer> values) {
            addCriterion("cjsl in", values, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslNotIn(List<Integer> values) {
            addCriterion("cjsl not in", values, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslBetween(Integer value1, Integer value2) {
            addCriterion("cjsl between", value1, value2, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjslNotBetween(Integer value1, Integer value2) {
            addCriterion("cjsl not between", value1, value2, "cjsl");
            return (Criteria) this;
        }

        public Criteria andCjjgIsNull() {
            addCriterion("cjjg is null");
            return (Criteria) this;
        }

        public Criteria andCjjgIsNotNull() {
            addCriterion("cjjg is not null");
            return (Criteria) this;
        }

        public Criteria andCjjgEqualTo(Double value) {
            addCriterion("cjjg =", value, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgNotEqualTo(Double value) {
            addCriterion("cjjg <>", value, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgGreaterThan(Double value) {
            addCriterion("cjjg >", value, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgGreaterThanOrEqualTo(Double value) {
            addCriterion("cjjg >=", value, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgLessThan(Double value) {
            addCriterion("cjjg <", value, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgLessThanOrEqualTo(Double value) {
            addCriterion("cjjg <=", value, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgIn(List<Double> values) {
            addCriterion("cjjg in", values, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgNotIn(List<Double> values) {
            addCriterion("cjjg not in", values, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgBetween(Double value1, Double value2) {
            addCriterion("cjjg between", value1, value2, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjgNotBetween(Double value1, Double value2) {
            addCriterion("cjjg not between", value1, value2, "cjjg");
            return (Criteria) this;
        }

        public Criteria andCjjeIsNull() {
            addCriterion("cjje is null");
            return (Criteria) this;
        }

        public Criteria andCjjeIsNotNull() {
            addCriterion("cjje is not null");
            return (Criteria) this;
        }

        public Criteria andCjjeEqualTo(Double value) {
            addCriterion("cjje =", value, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeNotEqualTo(Double value) {
            addCriterion("cjje <>", value, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeGreaterThan(Double value) {
            addCriterion("cjje >", value, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeGreaterThanOrEqualTo(Double value) {
            addCriterion("cjje >=", value, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeLessThan(Double value) {
            addCriterion("cjje <", value, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeLessThanOrEqualTo(Double value) {
            addCriterion("cjje <=", value, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeIn(List<Double> values) {
            addCriterion("cjje in", values, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeNotIn(List<Double> values) {
            addCriterion("cjje not in", values, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeBetween(Double value1, Double value2) {
            addCriterion("cjje between", value1, value2, "cjje");
            return (Criteria) this;
        }

        public Criteria andCjjeNotBetween(Double value1, Double value2) {
            addCriterion("cjje not between", value1, value2, "cjje");
            return (Criteria) this;
        }

        public Criteria andMmfxIsNull() {
            addCriterion("mmfx is null");
            return (Criteria) this;
        }

        public Criteria andMmfxIsNotNull() {
            addCriterion("mmfx is not null");
            return (Criteria) this;
        }

        public Criteria andMmfxEqualTo(String value) {
            addCriterion("mmfx =", value, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxNotEqualTo(String value) {
            addCriterion("mmfx <>", value, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxGreaterThan(String value) {
            addCriterion("mmfx >", value, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxGreaterThanOrEqualTo(String value) {
            addCriterion("mmfx >=", value, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxLessThan(String value) {
            addCriterion("mmfx <", value, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxLessThanOrEqualTo(String value) {
            addCriterion("mmfx <=", value, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxLike(String value) {
            addCriterion("mmfx like", value, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxNotLike(String value) {
            addCriterion("mmfx not like", value, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxIn(List<String> values) {
            addCriterion("mmfx in", values, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxNotIn(List<String> values) {
            addCriterion("mmfx not in", values, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxBetween(String value1, String value2) {
            addCriterion("mmfx between", value1, value2, "mmfx");
            return (Criteria) this;
        }

        public Criteria andMmfxNotBetween(String value1, String value2) {
            addCriterion("mmfx not between", value1, value2, "mmfx");
            return (Criteria) this;
        }

        public Criteria andZtbhIsNull() {
            addCriterion("ztbh is null");
            return (Criteria) this;
        }

        public Criteria andZtbhIsNotNull() {
            addCriterion("ztbh is not null");
            return (Criteria) this;
        }

        public Criteria andZtbhEqualTo(Integer value) {
            addCriterion("ztbh =", value, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhNotEqualTo(Integer value) {
            addCriterion("ztbh <>", value, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhGreaterThan(Integer value) {
            addCriterion("ztbh >", value, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhGreaterThanOrEqualTo(Integer value) {
            addCriterion("ztbh >=", value, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhLessThan(Integer value) {
            addCriterion("ztbh <", value, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhLessThanOrEqualTo(Integer value) {
            addCriterion("ztbh <=", value, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhIn(List<Integer> values) {
            addCriterion("ztbh in", values, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhNotIn(List<Integer> values) {
            addCriterion("ztbh not in", values, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhBetween(Integer value1, Integer value2) {
            addCriterion("ztbh between", value1, value2, "ztbh");
            return (Criteria) this;
        }

        public Criteria andZtbhNotBetween(Integer value1, Integer value2) {
            addCriterion("ztbh not between", value1, value2, "ztbh");
            return (Criteria) this;
        }

        public Criteria andYwrqIsNull() {
            addCriterion("ywrq is null");
            return (Criteria) this;
        }

        public Criteria andYwrqIsNotNull() {
            addCriterion("ywrq is not null");
            return (Criteria) this;
        }

        public Criteria andYwrqEqualTo(Date value) {
            addCriterion("ywrq =", value, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqNotEqualTo(Date value) {
            addCriterion("ywrq <>", value, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqGreaterThan(Date value) {
            addCriterion("ywrq >", value, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqGreaterThanOrEqualTo(Date value) {
            addCriterion("ywrq >=", value, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqLessThan(Date value) {
            addCriterion("ywrq <", value, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqLessThanOrEqualTo(Date value) {
            addCriterion("ywrq <=", value, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqIn(List<Date> values) {
            addCriterion("ywrq in", values, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqNotIn(List<Date> values) {
            addCriterion("ywrq not in", values, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqBetween(Date value1, Date value2) {
            addCriterion("ywrq between", value1, value2, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwrqNotBetween(Date value1, Date value2) {
            addCriterion("ywrq not between", value1, value2, "ywrq");
            return (Criteria) this;
        }

        public Criteria andYwlbIsNull() {
            addCriterion("ywlb is null");
            return (Criteria) this;
        }

        public Criteria andYwlbIsNotNull() {
            addCriterion("ywlb is not null");
            return (Criteria) this;
        }

        public Criteria andYwlbEqualTo(Integer value) {
            addCriterion("ywlb =", value, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbNotEqualTo(Integer value) {
            addCriterion("ywlb <>", value, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbGreaterThan(Integer value) {
            addCriterion("ywlb >", value, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbGreaterThanOrEqualTo(Integer value) {
            addCriterion("ywlb >=", value, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbLessThan(Integer value) {
            addCriterion("ywlb <", value, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbLessThanOrEqualTo(Integer value) {
            addCriterion("ywlb <=", value, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbIn(List<Integer> values) {
            addCriterion("ywlb in", values, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbNotIn(List<Integer> values) {
            addCriterion("ywlb not in", values, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbBetween(Integer value1, Integer value2) {
            addCriterion("ywlb between", value1, value2, "ywlb");
            return (Criteria) this;
        }

        public Criteria andYwlbNotBetween(Integer value1, Integer value2) {
            addCriterion("ywlb not between", value1, value2, "ywlb");
            return (Criteria) this;
        }

        public Criteria andJsfIsNull() {
            addCriterion("jsf is null");
            return (Criteria) this;
        }

        public Criteria andJsfIsNotNull() {
            addCriterion("jsf is not null");
            return (Criteria) this;
        }

        public Criteria andJsfEqualTo(Double value) {
            addCriterion("jsf =", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfNotEqualTo(Double value) {
            addCriterion("jsf <>", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfGreaterThan(Double value) {
            addCriterion("jsf >", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfGreaterThanOrEqualTo(Double value) {
            addCriterion("jsf >=", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfLessThan(Double value) {
            addCriterion("jsf <", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfLessThanOrEqualTo(Double value) {
            addCriterion("jsf <=", value, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfIn(List<Double> values) {
            addCriterion("jsf in", values, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfNotIn(List<Double> values) {
            addCriterion("jsf not in", values, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfBetween(Double value1, Double value2) {
            addCriterion("jsf between", value1, value2, "jsf");
            return (Criteria) this;
        }

        public Criteria andJsfNotBetween(Double value1, Double value2) {
            addCriterion("jsf not between", value1, value2, "jsf");
            return (Criteria) this;
        }

        public Criteria andGhfIsNull() {
            addCriterion("ghf is null");
            return (Criteria) this;
        }

        public Criteria andGhfIsNotNull() {
            addCriterion("ghf is not null");
            return (Criteria) this;
        }

        public Criteria andGhfEqualTo(Double value) {
            addCriterion("ghf =", value, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfNotEqualTo(Double value) {
            addCriterion("ghf <>", value, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfGreaterThan(Double value) {
            addCriterion("ghf >", value, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfGreaterThanOrEqualTo(Double value) {
            addCriterion("ghf >=", value, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfLessThan(Double value) {
            addCriterion("ghf <", value, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfLessThanOrEqualTo(Double value) {
            addCriterion("ghf <=", value, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfIn(List<Double> values) {
            addCriterion("ghf in", values, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfNotIn(List<Double> values) {
            addCriterion("ghf not in", values, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfBetween(Double value1, Double value2) {
            addCriterion("ghf between", value1, value2, "ghf");
            return (Criteria) this;
        }

        public Criteria andGhfNotBetween(Double value1, Double value2) {
            addCriterion("ghf not between", value1, value2, "ghf");
            return (Criteria) this;
        }

        public Criteria andZgfIsNull() {
            addCriterion("zgf is null");
            return (Criteria) this;
        }

        public Criteria andZgfIsNotNull() {
            addCriterion("zgf is not null");
            return (Criteria) this;
        }

        public Criteria andZgfEqualTo(Double value) {
            addCriterion("zgf =", value, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfNotEqualTo(Double value) {
            addCriterion("zgf <>", value, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfGreaterThan(Double value) {
            addCriterion("zgf >", value, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfGreaterThanOrEqualTo(Double value) {
            addCriterion("zgf >=", value, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfLessThan(Double value) {
            addCriterion("zgf <", value, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfLessThanOrEqualTo(Double value) {
            addCriterion("zgf <=", value, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfIn(List<Double> values) {
            addCriterion("zgf in", values, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfNotIn(List<Double> values) {
            addCriterion("zgf not in", values, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfBetween(Double value1, Double value2) {
            addCriterion("zgf between", value1, value2, "zgf");
            return (Criteria) this;
        }

        public Criteria andZgfNotBetween(Double value1, Double value2) {
            addCriterion("zgf not between", value1, value2, "zgf");
            return (Criteria) this;
        }

        public Criteria andYhsIsNull() {
            addCriterion("yhs is null");
            return (Criteria) this;
        }

        public Criteria andYhsIsNotNull() {
            addCriterion("yhs is not null");
            return (Criteria) this;
        }

        public Criteria andYhsEqualTo(Double value) {
            addCriterion("yhs =", value, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsNotEqualTo(Double value) {
            addCriterion("yhs <>", value, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsGreaterThan(Double value) {
            addCriterion("yhs >", value, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsGreaterThanOrEqualTo(Double value) {
            addCriterion("yhs >=", value, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsLessThan(Double value) {
            addCriterion("yhs <", value, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsLessThanOrEqualTo(Double value) {
            addCriterion("yhs <=", value, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsIn(List<Double> values) {
            addCriterion("yhs in", values, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsNotIn(List<Double> values) {
            addCriterion("yhs not in", values, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsBetween(Double value1, Double value2) {
            addCriterion("yhs between", value1, value2, "yhs");
            return (Criteria) this;
        }

        public Criteria andYhsNotBetween(Double value1, Double value2) {
            addCriterion("yhs not between", value1, value2, "yhs");
            return (Criteria) this;
        }

        public Criteria andJyfyIsNull() {
            addCriterion("jyfy is null");
            return (Criteria) this;
        }

        public Criteria andJyfyIsNotNull() {
            addCriterion("jyfy is not null");
            return (Criteria) this;
        }

        public Criteria andJyfyEqualTo(Double value) {
            addCriterion("jyfy =", value, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyNotEqualTo(Double value) {
            addCriterion("jyfy <>", value, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyGreaterThan(Double value) {
            addCriterion("jyfy >", value, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyGreaterThanOrEqualTo(Double value) {
            addCriterion("jyfy >=", value, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyLessThan(Double value) {
            addCriterion("jyfy <", value, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyLessThanOrEqualTo(Double value) {
            addCriterion("jyfy <=", value, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyIn(List<Double> values) {
            addCriterion("jyfy in", values, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyNotIn(List<Double> values) {
            addCriterion("jyfy not in", values, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyBetween(Double value1, Double value2) {
            addCriterion("jyfy between", value1, value2, "jyfy");
            return (Criteria) this;
        }

        public Criteria andJyfyNotBetween(Double value1, Double value2) {
            addCriterion("jyfy not between", value1, value2, "jyfy");
            return (Criteria) this;
        }

        public Criteria andExtendaIsNull() {
            addCriterion("extenda is null");
            return (Criteria) this;
        }

        public Criteria andExtendaIsNotNull() {
            addCriterion("extenda is not null");
            return (Criteria) this;
        }

        public Criteria andExtendaEqualTo(String value) {
            addCriterion("extenda =", value, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaNotEqualTo(String value) {
            addCriterion("extenda <>", value, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaGreaterThan(String value) {
            addCriterion("extenda >", value, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaGreaterThanOrEqualTo(String value) {
            addCriterion("extenda >=", value, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaLessThan(String value) {
            addCriterion("extenda <", value, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaLessThanOrEqualTo(String value) {
            addCriterion("extenda <=", value, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaLike(String value) {
            addCriterion("extenda like", value, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaNotLike(String value) {
            addCriterion("extenda not like", value, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaIn(List<String> values) {
            addCriterion("extenda in", values, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaNotIn(List<String> values) {
            addCriterion("extenda not in", values, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaBetween(String value1, String value2) {
            addCriterion("extenda between", value1, value2, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendaNotBetween(String value1, String value2) {
            addCriterion("extenda not between", value1, value2, "extenda");
            return (Criteria) this;
        }

        public Criteria andExtendbIsNull() {
            addCriterion("extendb is null");
            return (Criteria) this;
        }

        public Criteria andExtendbIsNotNull() {
            addCriterion("extendb is not null");
            return (Criteria) this;
        }

        public Criteria andExtendbEqualTo(String value) {
            addCriterion("extendb =", value, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbNotEqualTo(String value) {
            addCriterion("extendb <>", value, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbGreaterThan(String value) {
            addCriterion("extendb >", value, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbGreaterThanOrEqualTo(String value) {
            addCriterion("extendb >=", value, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbLessThan(String value) {
            addCriterion("extendb <", value, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbLessThanOrEqualTo(String value) {
            addCriterion("extendb <=", value, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbLike(String value) {
            addCriterion("extendb like", value, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbNotLike(String value) {
            addCriterion("extendb not like", value, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbIn(List<String> values) {
            addCriterion("extendb in", values, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbNotIn(List<String> values) {
            addCriterion("extendb not in", values, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbBetween(String value1, String value2) {
            addCriterion("extendb between", value1, value2, "extendb");
            return (Criteria) this;
        }

        public Criteria andExtendbNotBetween(String value1, String value2) {
            addCriterion("extendb not between", value1, value2, "extendb");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}