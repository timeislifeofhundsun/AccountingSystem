package com.hundsun.accountingsystem.Global.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TZqxxExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TZqxxExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
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

        public Criteria andZqnmIsNull() {
            addCriterion("zqnm is null");
            return (Criteria) this;
        }

        public Criteria andZqnmIsNotNull() {
            addCriterion("zqnm is not null");
            return (Criteria) this;
        }

        public Criteria andZqnmEqualTo(Integer value) {
            addCriterion("zqnm =", value, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmNotEqualTo(Integer value) {
            addCriterion("zqnm <>", value, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmGreaterThan(Integer value) {
            addCriterion("zqnm >", value, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmGreaterThanOrEqualTo(Integer value) {
            addCriterion("zqnm >=", value, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmLessThan(Integer value) {
            addCriterion("zqnm <", value, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmLessThanOrEqualTo(Integer value) {
            addCriterion("zqnm <=", value, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmIn(List<Integer> values) {
            addCriterion("zqnm in", values, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmNotIn(List<Integer> values) {
            addCriterion("zqnm not in", values, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmBetween(Integer value1, Integer value2) {
            addCriterion("zqnm between", value1, value2, "zqnm");
            return (Criteria) this;
        }

        public Criteria andZqnmNotBetween(Integer value1, Integer value2) {
            addCriterion("zqnm not between", value1, value2, "zqnm");
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

        public Criteria andZqlbIsNull() {
            addCriterion("zqlb is null");
            return (Criteria) this;
        }

        public Criteria andZqlbIsNotNull() {
            addCriterion("zqlb is not null");
            return (Criteria) this;
        }

        public Criteria andZqlbEqualTo(Integer value) {
            addCriterion("zqlb =", value, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbNotEqualTo(Integer value) {
            addCriterion("zqlb <>", value, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbGreaterThan(Integer value) {
            addCriterion("zqlb >", value, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbGreaterThanOrEqualTo(Integer value) {
            addCriterion("zqlb >=", value, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbLessThan(Integer value) {
            addCriterion("zqlb <", value, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbLessThanOrEqualTo(Integer value) {
            addCriterion("zqlb <=", value, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbIn(List<Integer> values) {
            addCriterion("zqlb in", values, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbNotIn(List<Integer> values) {
            addCriterion("zqlb not in", values, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbBetween(Integer value1, Integer value2) {
            addCriterion("zqlb between", value1, value2, "zqlb");
            return (Criteria) this;
        }

        public Criteria andZqlbNotBetween(Integer value1, Integer value2) {
            addCriterion("zqlb not between", value1, value2, "zqlb");
            return (Criteria) this;
        }

        public Criteria andSclbIsNull() {
            addCriterion("sclb is null");
            return (Criteria) this;
        }

        public Criteria andSclbIsNotNull() {
            addCriterion("sclb is not null");
            return (Criteria) this;
        }

        public Criteria andSclbEqualTo(Integer value) {
            addCriterion("sclb =", value, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbNotEqualTo(Integer value) {
            addCriterion("sclb <>", value, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbGreaterThan(Integer value) {
            addCriterion("sclb >", value, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbGreaterThanOrEqualTo(Integer value) {
            addCriterion("sclb >=", value, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbLessThan(Integer value) {
            addCriterion("sclb <", value, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbLessThanOrEqualTo(Integer value) {
            addCriterion("sclb <=", value, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbIn(List<Integer> values) {
            addCriterion("sclb in", values, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbNotIn(List<Integer> values) {
            addCriterion("sclb not in", values, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbBetween(Integer value1, Integer value2) {
            addCriterion("sclb between", value1, value2, "sclb");
            return (Criteria) this;
        }

        public Criteria andSclbNotBetween(Integer value1, Integer value2) {
            addCriterion("sclb not between", value1, value2, "sclb");
            return (Criteria) this;
        }

        public Criteria andZqjgIsNull() {
            addCriterion("zqjg is null");
            return (Criteria) this;
        }

        public Criteria andZqjgIsNotNull() {
            addCriterion("zqjg is not null");
            return (Criteria) this;
        }

        public Criteria andZqjgEqualTo(String value) {
            addCriterion("zqjg =", value, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgNotEqualTo(String value) {
            addCriterion("zqjg <>", value, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgGreaterThan(String value) {
            addCriterion("zqjg >", value, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgGreaterThanOrEqualTo(String value) {
            addCriterion("zqjg >=", value, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgLessThan(String value) {
            addCriterion("zqjg <", value, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgLessThanOrEqualTo(String value) {
            addCriterion("zqjg <=", value, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgLike(String value) {
            addCriterion("zqjg like", value, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgNotLike(String value) {
            addCriterion("zqjg not like", value, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgIn(List<String> values) {
            addCriterion("zqjg in", values, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgNotIn(List<String> values) {
            addCriterion("zqjg not in", values, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgBetween(String value1, String value2) {
            addCriterion("zqjg between", value1, value2, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZqjgNotBetween(String value1, String value2) {
            addCriterion("zqjg not between", value1, value2, "zqjg");
            return (Criteria) this;
        }

        public Criteria andZgbIsNull() {
            addCriterion("zgb is null");
            return (Criteria) this;
        }

        public Criteria andZgbIsNotNull() {
            addCriterion("zgb is not null");
            return (Criteria) this;
        }

        public Criteria andZgbEqualTo(Double value) {
            addCriterion("zgb =", value, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbNotEqualTo(Double value) {
            addCriterion("zgb <>", value, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbGreaterThan(Double value) {
            addCriterion("zgb >", value, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbGreaterThanOrEqualTo(Double value) {
            addCriterion("zgb >=", value, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbLessThan(Double value) {
            addCriterion("zgb <", value, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbLessThanOrEqualTo(Double value) {
            addCriterion("zgb <=", value, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbIn(List<Double> values) {
            addCriterion("zgb in", values, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbNotIn(List<Double> values) {
            addCriterion("zgb not in", values, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbBetween(Double value1, Double value2) {
            addCriterion("zgb between", value1, value2, "zgb");
            return (Criteria) this;
        }

        public Criteria andZgbNotBetween(Double value1, Double value2) {
            addCriterion("zgb not between", value1, value2, "zgb");
            return (Criteria) this;
        }

        public Criteria andLtgsIsNull() {
            addCriterion("ltgs is null");
            return (Criteria) this;
        }

        public Criteria andLtgsIsNotNull() {
            addCriterion("ltgs is not null");
            return (Criteria) this;
        }

        public Criteria andLtgsEqualTo(Double value) {
            addCriterion("ltgs =", value, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsNotEqualTo(Double value) {
            addCriterion("ltgs <>", value, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsGreaterThan(Double value) {
            addCriterion("ltgs >", value, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsGreaterThanOrEqualTo(Double value) {
            addCriterion("ltgs >=", value, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsLessThan(Double value) {
            addCriterion("ltgs <", value, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsLessThanOrEqualTo(Double value) {
            addCriterion("ltgs <=", value, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsIn(List<Double> values) {
            addCriterion("ltgs in", values, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsNotIn(List<Double> values) {
            addCriterion("ltgs not in", values, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsBetween(Double value1, Double value2) {
            addCriterion("ltgs between", value1, value2, "ltgs");
            return (Criteria) this;
        }

        public Criteria andLtgsNotBetween(Double value1, Double value2) {
            addCriterion("ltgs not between", value1, value2, "ltgs");
            return (Criteria) this;
        }

        public Criteria andMgmzIsNull() {
            addCriterion("mgmz is null");
            return (Criteria) this;
        }

        public Criteria andMgmzIsNotNull() {
            addCriterion("mgmz is not null");
            return (Criteria) this;
        }

        public Criteria andMgmzEqualTo(Double value) {
            addCriterion("mgmz =", value, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzNotEqualTo(Double value) {
            addCriterion("mgmz <>", value, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzGreaterThan(Double value) {
            addCriterion("mgmz >", value, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzGreaterThanOrEqualTo(Double value) {
            addCriterion("mgmz >=", value, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzLessThan(Double value) {
            addCriterion("mgmz <", value, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzLessThanOrEqualTo(Double value) {
            addCriterion("mgmz <=", value, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzIn(List<Double> values) {
            addCriterion("mgmz in", values, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzNotIn(List<Double> values) {
            addCriterion("mgmz not in", values, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzBetween(Double value1, Double value2) {
            addCriterion("mgmz between", value1, value2, "mgmz");
            return (Criteria) this;
        }

        public Criteria andMgmzNotBetween(Double value1, Double value2) {
            addCriterion("mgmz not between", value1, value2, "mgmz");
            return (Criteria) this;
        }

        public Criteria andFxrqIsNull() {
            addCriterion("fxrq is null");
            return (Criteria) this;
        }

        public Criteria andFxrqIsNotNull() {
            addCriterion("fxrq is not null");
            return (Criteria) this;
        }

        public Criteria andFxrqEqualTo(Date value) {
            addCriterion("fxrq =", value, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqNotEqualTo(Date value) {
            addCriterion("fxrq <>", value, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqGreaterThan(Date value) {
            addCriterion("fxrq >", value, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqGreaterThanOrEqualTo(Date value) {
            addCriterion("fxrq >=", value, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqLessThan(Date value) {
            addCriterion("fxrq <", value, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqLessThanOrEqualTo(Date value) {
            addCriterion("fxrq <=", value, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqIn(List<Date> values) {
            addCriterion("fxrq in", values, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqNotIn(List<Date> values) {
            addCriterion("fxrq not in", values, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqBetween(Date value1, Date value2) {
            addCriterion("fxrq between", value1, value2, "fxrq");
            return (Criteria) this;
        }

        public Criteria andFxrqNotBetween(Date value1, Date value2) {
            addCriterion("fxrq not between", value1, value2, "fxrq");
            return (Criteria) this;
        }

        public Criteria andDqrqIsNull() {
            addCriterion("dqrq is null");
            return (Criteria) this;
        }

        public Criteria andDqrqIsNotNull() {
            addCriterion("dqrq is not null");
            return (Criteria) this;
        }

        public Criteria andDqrqEqualTo(Date value) {
            addCriterion("dqrq =", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqNotEqualTo(Date value) {
            addCriterion("dqrq <>", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqGreaterThan(Date value) {
            addCriterion("dqrq >", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqGreaterThanOrEqualTo(Date value) {
            addCriterion("dqrq >=", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqLessThan(Date value) {
            addCriterion("dqrq <", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqLessThanOrEqualTo(Date value) {
            addCriterion("dqrq <=", value, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqIn(List<Date> values) {
            addCriterion("dqrq in", values, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqNotIn(List<Date> values) {
            addCriterion("dqrq not in", values, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqBetween(Date value1, Date value2) {
            addCriterion("dqrq between", value1, value2, "dqrq");
            return (Criteria) this;
        }

        public Criteria andDqrqNotBetween(Date value1, Date value2) {
            addCriterion("dqrq not between", value1, value2, "dqrq");
            return (Criteria) this;
        }

        public Criteria andHgtsIsNull() {
            addCriterion("hgts is null");
            return (Criteria) this;
        }

        public Criteria andHgtsIsNotNull() {
            addCriterion("hgts is not null");
            return (Criteria) this;
        }

        public Criteria andHgtsEqualTo(Integer value) {
            addCriterion("hgts =", value, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsNotEqualTo(Integer value) {
            addCriterion("hgts <>", value, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsGreaterThan(Integer value) {
            addCriterion("hgts >", value, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsGreaterThanOrEqualTo(Integer value) {
            addCriterion("hgts >=", value, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsLessThan(Integer value) {
            addCriterion("hgts <", value, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsLessThanOrEqualTo(Integer value) {
            addCriterion("hgts <=", value, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsIn(List<Integer> values) {
            addCriterion("hgts in", values, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsNotIn(List<Integer> values) {
            addCriterion("hgts not in", values, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsBetween(Integer value1, Integer value2) {
            addCriterion("hgts between", value1, value2, "hgts");
            return (Criteria) this;
        }

        public Criteria andHgtsNotBetween(Integer value1, Integer value2) {
            addCriterion("hgts not between", value1, value2, "hgts");
            return (Criteria) this;
        }

        public Criteria andNjxtsIsNull() {
            addCriterion("njxts is null");
            return (Criteria) this;
        }

        public Criteria andNjxtsIsNotNull() {
            addCriterion("njxts is not null");
            return (Criteria) this;
        }

        public Criteria andNjxtsEqualTo(Double value) {
            addCriterion("njxts =", value, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsNotEqualTo(Double value) {
            addCriterion("njxts <>", value, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsGreaterThan(Double value) {
            addCriterion("njxts >", value, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsGreaterThanOrEqualTo(Double value) {
            addCriterion("njxts >=", value, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsLessThan(Double value) {
            addCriterion("njxts <", value, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsLessThanOrEqualTo(Double value) {
            addCriterion("njxts <=", value, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsIn(List<Double> values) {
            addCriterion("njxts in", values, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsNotIn(List<Double> values) {
            addCriterion("njxts not in", values, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsBetween(Double value1, Double value2) {
            addCriterion("njxts between", value1, value2, "njxts");
            return (Criteria) this;
        }

        public Criteria andNjxtsNotBetween(Double value1, Double value2) {
            addCriterion("njxts not between", value1, value2, "njxts");
            return (Criteria) this;
        }

        public Criteria andNllIsNull() {
            addCriterion("nll is null");
            return (Criteria) this;
        }

        public Criteria andNllIsNotNull() {
            addCriterion("nll is not null");
            return (Criteria) this;
        }

        public Criteria andNllEqualTo(Double value) {
            addCriterion("nll =", value, "nll");
            return (Criteria) this;
        }

        public Criteria andNllNotEqualTo(Double value) {
            addCriterion("nll <>", value, "nll");
            return (Criteria) this;
        }

        public Criteria andNllGreaterThan(Double value) {
            addCriterion("nll >", value, "nll");
            return (Criteria) this;
        }

        public Criteria andNllGreaterThanOrEqualTo(Double value) {
            addCriterion("nll >=", value, "nll");
            return (Criteria) this;
        }

        public Criteria andNllLessThan(Double value) {
            addCriterion("nll <", value, "nll");
            return (Criteria) this;
        }

        public Criteria andNllLessThanOrEqualTo(Double value) {
            addCriterion("nll <=", value, "nll");
            return (Criteria) this;
        }

        public Criteria andNllIn(List<Double> values) {
            addCriterion("nll in", values, "nll");
            return (Criteria) this;
        }

        public Criteria andNllNotIn(List<Double> values) {
            addCriterion("nll not in", values, "nll");
            return (Criteria) this;
        }

        public Criteria andNllBetween(Double value1, Double value2) {
            addCriterion("nll between", value1, value2, "nll");
            return (Criteria) this;
        }

        public Criteria andNllNotBetween(Double value1, Double value2) {
            addCriterion("nll not between", value1, value2, "nll");
            return (Criteria) this;
        }

        public Criteria andQxrIsNull() {
            addCriterion("qxr is null");
            return (Criteria) this;
        }

        public Criteria andQxrIsNotNull() {
            addCriterion("qxr is not null");
            return (Criteria) this;
        }

        public Criteria andQxrEqualTo(Date value) {
            addCriterion("qxr =", value, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrNotEqualTo(Date value) {
            addCriterion("qxr <>", value, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrGreaterThan(Date value) {
            addCriterion("qxr >", value, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrGreaterThanOrEqualTo(Date value) {
            addCriterion("qxr >=", value, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrLessThan(Date value) {
            addCriterion("qxr <", value, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrLessThanOrEqualTo(Date value) {
            addCriterion("qxr <=", value, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrIn(List<Date> values) {
            addCriterion("qxr in", values, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrNotIn(List<Date> values) {
            addCriterion("qxr not in", values, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrBetween(Date value1, Date value2) {
            addCriterion("qxr between", value1, value2, "qxr");
            return (Criteria) this;
        }

        public Criteria andQxrNotBetween(Date value1, Date value2) {
            addCriterion("qxr not between", value1, value2, "qxr");
            return (Criteria) this;
        }

        public Criteria andFxfsIsNull() {
            addCriterion("fxfs is null");
            return (Criteria) this;
        }

        public Criteria andFxfsIsNotNull() {
            addCriterion("fxfs is not null");
            return (Criteria) this;
        }

        public Criteria andFxfsEqualTo(Integer value) {
            addCriterion("fxfs =", value, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsNotEqualTo(Integer value) {
            addCriterion("fxfs <>", value, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsGreaterThan(Integer value) {
            addCriterion("fxfs >", value, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsGreaterThanOrEqualTo(Integer value) {
            addCriterion("fxfs >=", value, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsLessThan(Integer value) {
            addCriterion("fxfs <", value, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsLessThanOrEqualTo(Integer value) {
            addCriterion("fxfs <=", value, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsIn(List<Integer> values) {
            addCriterion("fxfs in", values, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsNotIn(List<Integer> values) {
            addCriterion("fxfs not in", values, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsBetween(Integer value1, Integer value2) {
            addCriterion("fxfs between", value1, value2, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxfsNotBetween(Integer value1, Integer value2) {
            addCriterion("fxfs not between", value1, value2, "fxfs");
            return (Criteria) this;
        }

        public Criteria andFxjgIsNull() {
            addCriterion("fxjg is null");
            return (Criteria) this;
        }

        public Criteria andFxjgIsNotNull() {
            addCriterion("fxjg is not null");
            return (Criteria) this;
        }

        public Criteria andFxjgEqualTo(Double value) {
            addCriterion("fxjg =", value, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgNotEqualTo(Double value) {
            addCriterion("fxjg <>", value, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgGreaterThan(Double value) {
            addCriterion("fxjg >", value, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgGreaterThanOrEqualTo(Double value) {
            addCriterion("fxjg >=", value, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgLessThan(Double value) {
            addCriterion("fxjg <", value, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgLessThanOrEqualTo(Double value) {
            addCriterion("fxjg <=", value, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgIn(List<Double> values) {
            addCriterion("fxjg in", values, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgNotIn(List<Double> values) {
            addCriterion("fxjg not in", values, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgBetween(Double value1, Double value2) {
            addCriterion("fxjg between", value1, value2, "fxjg");
            return (Criteria) this;
        }

        public Criteria andFxjgNotBetween(Double value1, Double value2) {
            addCriterion("fxjg not between", value1, value2, "fxjg");
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
    }
}