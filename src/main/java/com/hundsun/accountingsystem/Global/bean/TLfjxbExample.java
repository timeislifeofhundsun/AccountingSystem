package com.hundsun.accountingsystem.Global.bean;

import java.util.ArrayList;
import java.util.List;

public class TLfjxbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TLfjxbExample() {
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

        public Criteria andYhlxIsNull() {
            addCriterion("yhlx is null");
            return (Criteria) this;
        }

        public Criteria andYhlxIsNotNull() {
            addCriterion("yhlx is not null");
            return (Criteria) this;
        }

        public Criteria andYhlxEqualTo(String value) {
            addCriterion("yhlx =", value, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxNotEqualTo(String value) {
            addCriterion("yhlx <>", value, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxGreaterThan(String value) {
            addCriterion("yhlx >", value, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxGreaterThanOrEqualTo(String value) {
            addCriterion("yhlx >=", value, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxLessThan(String value) {
            addCriterion("yhlx <", value, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxLessThanOrEqualTo(String value) {
            addCriterion("yhlx <=", value, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxLike(String value) {
            addCriterion("yhlx like", value, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxNotLike(String value) {
            addCriterion("yhlx not like", value, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxIn(List<String> values) {
            addCriterion("yhlx in", values, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxNotIn(List<String> values) {
            addCriterion("yhlx not in", values, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxBetween(String value1, String value2) {
            addCriterion("yhlx between", value1, value2, "yhlx");
            return (Criteria) this;
        }

        public Criteria andYhlxNotBetween(String value1, String value2) {
            addCriterion("yhlx not between", value1, value2, "yhlx");
            return (Criteria) this;
        }

        public Criteria andHglxIsNull() {
            addCriterion("hglx is null");
            return (Criteria) this;
        }

        public Criteria andHglxIsNotNull() {
            addCriterion("hglx is not null");
            return (Criteria) this;
        }

        public Criteria andHglxEqualTo(String value) {
            addCriterion("hglx =", value, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxNotEqualTo(String value) {
            addCriterion("hglx <>", value, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxGreaterThan(String value) {
            addCriterion("hglx >", value, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxGreaterThanOrEqualTo(String value) {
            addCriterion("hglx >=", value, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxLessThan(String value) {
            addCriterion("hglx <", value, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxLessThanOrEqualTo(String value) {
            addCriterion("hglx <=", value, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxLike(String value) {
            addCriterion("hglx like", value, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxNotLike(String value) {
            addCriterion("hglx not like", value, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxIn(List<String> values) {
            addCriterion("hglx in", values, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxNotIn(List<String> values) {
            addCriterion("hglx not in", values, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxBetween(String value1, String value2) {
            addCriterion("hglx between", value1, value2, "hglx");
            return (Criteria) this;
        }

        public Criteria andHglxNotBetween(String value1, String value2) {
            addCriterion("hglx not between", value1, value2, "hglx");
            return (Criteria) this;
        }

        public Criteria andXxplfIsNull() {
            addCriterion("xxplf is null");
            return (Criteria) this;
        }

        public Criteria andXxplfIsNotNull() {
            addCriterion("xxplf is not null");
            return (Criteria) this;
        }

        public Criteria andXxplfEqualTo(String value) {
            addCriterion("xxplf =", value, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfNotEqualTo(String value) {
            addCriterion("xxplf <>", value, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfGreaterThan(String value) {
            addCriterion("xxplf >", value, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfGreaterThanOrEqualTo(String value) {
            addCriterion("xxplf >=", value, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfLessThan(String value) {
            addCriterion("xxplf <", value, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfLessThanOrEqualTo(String value) {
            addCriterion("xxplf <=", value, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfLike(String value) {
            addCriterion("xxplf like", value, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfNotLike(String value) {
            addCriterion("xxplf not like", value, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfIn(List<String> values) {
            addCriterion("xxplf in", values, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfNotIn(List<String> values) {
            addCriterion("xxplf not in", values, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfBetween(String value1, String value2) {
            addCriterion("xxplf between", value1, value2, "xxplf");
            return (Criteria) this;
        }

        public Criteria andXxplfNotBetween(String value1, String value2) {
            addCriterion("xxplf not between", value1, value2, "xxplf");
            return (Criteria) this;
        }

        public Criteria andSjfIsNull() {
            addCriterion("sjf is null");
            return (Criteria) this;
        }

        public Criteria andSjfIsNotNull() {
            addCriterion("sjf is not null");
            return (Criteria) this;
        }

        public Criteria andSjfEqualTo(String value) {
            addCriterion("sjf =", value, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfNotEqualTo(String value) {
            addCriterion("sjf <>", value, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfGreaterThan(String value) {
            addCriterion("sjf >", value, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfGreaterThanOrEqualTo(String value) {
            addCriterion("sjf >=", value, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfLessThan(String value) {
            addCriterion("sjf <", value, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfLessThanOrEqualTo(String value) {
            addCriterion("sjf <=", value, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfLike(String value) {
            addCriterion("sjf like", value, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfNotLike(String value) {
            addCriterion("sjf not like", value, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfIn(List<String> values) {
            addCriterion("sjf in", values, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfNotIn(List<String> values) {
            addCriterion("sjf not in", values, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfBetween(String value1, String value2) {
            addCriterion("sjf between", value1, value2, "sjf");
            return (Criteria) this;
        }

        public Criteria andSjfNotBetween(String value1, String value2) {
            addCriterion("sjf not between", value1, value2, "sjf");
            return (Criteria) this;
        }

        public Criteria andXxplcsIsNull() {
            addCriterion("xxplcs is null");
            return (Criteria) this;
        }

        public Criteria andXxplcsIsNotNull() {
            addCriterion("xxplcs is not null");
            return (Criteria) this;
        }

        public Criteria andXxplcsEqualTo(Integer value) {
            addCriterion("xxplcs =", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsNotEqualTo(Integer value) {
            addCriterion("xxplcs <>", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsGreaterThan(Integer value) {
            addCriterion("xxplcs >", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsGreaterThanOrEqualTo(Integer value) {
            addCriterion("xxplcs >=", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsLessThan(Integer value) {
            addCriterion("xxplcs <", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsLessThanOrEqualTo(Integer value) {
            addCriterion("xxplcs <=", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsIn(List<Integer> values) {
            addCriterion("xxplcs in", values, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsNotIn(List<Integer> values) {
            addCriterion("xxplcs not in", values, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsBetween(Integer value1, Integer value2) {
            addCriterion("xxplcs between", value1, value2, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsNotBetween(Integer value1, Integer value2) {
            addCriterion("xxplcs not between", value1, value2, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andSjcsIsNull() {
            addCriterion("sjcs is null");
            return (Criteria) this;
        }

        public Criteria andSjcsIsNotNull() {
            addCriterion("sjcs is not null");
            return (Criteria) this;
        }

        public Criteria andSjcsEqualTo(Integer value) {
            addCriterion("sjcs =", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsNotEqualTo(Integer value) {
            addCriterion("sjcs <>", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsGreaterThan(Integer value) {
            addCriterion("sjcs >", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsGreaterThanOrEqualTo(Integer value) {
            addCriterion("sjcs >=", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsLessThan(Integer value) {
            addCriterion("sjcs <", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsLessThanOrEqualTo(Integer value) {
            addCriterion("sjcs <=", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsIn(List<Integer> values) {
            addCriterion("sjcs in", values, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsNotIn(List<Integer> values) {
            addCriterion("sjcs not in", values, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsBetween(Integer value1, Integer value2) {
            addCriterion("sjcs between", value1, value2, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsNotBetween(Integer value1, Integer value2) {
            addCriterion("sjcs not between", value1, value2, "sjcs");
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