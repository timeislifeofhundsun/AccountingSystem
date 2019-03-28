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

        public Criteria andYhlvIsNull() {
            addCriterion("yhlv is null");
            return (Criteria) this;
        }

        public Criteria andYhlvIsNotNull() {
            addCriterion("yhlv is not null");
            return (Criteria) this;
        }

        public Criteria andYhlvEqualTo(String value) {
            addCriterion("yhlv =", value, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvNotEqualTo(String value) {
            addCriterion("yhlv <>", value, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvGreaterThan(String value) {
            addCriterion("yhlv >", value, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvGreaterThanOrEqualTo(String value) {
            addCriterion("yhlv >=", value, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvLessThan(String value) {
            addCriterion("yhlv <", value, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvLessThanOrEqualTo(String value) {
            addCriterion("yhlv <=", value, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvLike(String value) {
            addCriterion("yhlv like", value, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvNotLike(String value) {
            addCriterion("yhlv not like", value, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvIn(List<String> values) {
            addCriterion("yhlv in", values, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvNotIn(List<String> values) {
            addCriterion("yhlv not in", values, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvBetween(String value1, String value2) {
            addCriterion("yhlv between", value1, value2, "yhlv");
            return (Criteria) this;
        }

        public Criteria andYhlvNotBetween(String value1, String value2) {
            addCriterion("yhlv not between", value1, value2, "yhlv");
            return (Criteria) this;
        }

        public Criteria andHglvIsNull() {
            addCriterion("hglv is null");
            return (Criteria) this;
        }

        public Criteria andHglvIsNotNull() {
            addCriterion("hglv is not null");
            return (Criteria) this;
        }

        public Criteria andHglvEqualTo(String value) {
            addCriterion("hglv =", value, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvNotEqualTo(String value) {
            addCriterion("hglv <>", value, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvGreaterThan(String value) {
            addCriterion("hglv >", value, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvGreaterThanOrEqualTo(String value) {
            addCriterion("hglv >=", value, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvLessThan(String value) {
            addCriterion("hglv <", value, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvLessThanOrEqualTo(String value) {
            addCriterion("hglv <=", value, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvLike(String value) {
            addCriterion("hglv like", value, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvNotLike(String value) {
            addCriterion("hglv not like", value, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvIn(List<String> values) {
            addCriterion("hglv in", values, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvNotIn(List<String> values) {
            addCriterion("hglv not in", values, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvBetween(String value1, String value2) {
            addCriterion("hglv between", value1, value2, "hglv");
            return (Criteria) this;
        }

        public Criteria andHglvNotBetween(String value1, String value2) {
            addCriterion("hglv not between", value1, value2, "hglv");
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

        public Criteria andXxplcsEqualTo(String value) {
            addCriterion("xxplcs =", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsNotEqualTo(String value) {
            addCriterion("xxplcs <>", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsGreaterThan(String value) {
            addCriterion("xxplcs >", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsGreaterThanOrEqualTo(String value) {
            addCriterion("xxplcs >=", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsLessThan(String value) {
            addCriterion("xxplcs <", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsLessThanOrEqualTo(String value) {
            addCriterion("xxplcs <=", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsLike(String value) {
            addCriterion("xxplcs like", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsNotLike(String value) {
            addCriterion("xxplcs not like", value, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsIn(List<String> values) {
            addCriterion("xxplcs in", values, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsNotIn(List<String> values) {
            addCriterion("xxplcs not in", values, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsBetween(String value1, String value2) {
            addCriterion("xxplcs between", value1, value2, "xxplcs");
            return (Criteria) this;
        }

        public Criteria andXxplcsNotBetween(String value1, String value2) {
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

        public Criteria andSjcsEqualTo(String value) {
            addCriterion("sjcs =", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsNotEqualTo(String value) {
            addCriterion("sjcs <>", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsGreaterThan(String value) {
            addCriterion("sjcs >", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsGreaterThanOrEqualTo(String value) {
            addCriterion("sjcs >=", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsLessThan(String value) {
            addCriterion("sjcs <", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsLessThanOrEqualTo(String value) {
            addCriterion("sjcs <=", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsLike(String value) {
            addCriterion("sjcs like", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsNotLike(String value) {
            addCriterion("sjcs not like", value, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsIn(List<String> values) {
            addCriterion("sjcs in", values, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsNotIn(List<String> values) {
            addCriterion("sjcs not in", values, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsBetween(String value1, String value2) {
            addCriterion("sjcs between", value1, value2, "sjcs");
            return (Criteria) this;
        }

        public Criteria andSjcsNotBetween(String value1, String value2) {
            addCriterion("sjcs not between", value1, value2, "sjcs");
            return (Criteria) this;
        }

        public Criteria andJylvIsNull() {
            addCriterion("jylv is null");
            return (Criteria) this;
        }

        public Criteria andJylvIsNotNull() {
            addCriterion("jylv is not null");
            return (Criteria) this;
        }

        public Criteria andJylvEqualTo(String value) {
            addCriterion("jylv =", value, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvNotEqualTo(String value) {
            addCriterion("jylv <>", value, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvGreaterThan(String value) {
            addCriterion("jylv >", value, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvGreaterThanOrEqualTo(String value) {
            addCriterion("jylv >=", value, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvLessThan(String value) {
            addCriterion("jylv <", value, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvLessThanOrEqualTo(String value) {
            addCriterion("jylv <=", value, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvLike(String value) {
            addCriterion("jylv like", value, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvNotLike(String value) {
            addCriterion("jylv not like", value, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvIn(List<String> values) {
            addCriterion("jylv in", values, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvNotIn(List<String> values) {
            addCriterion("jylv not in", values, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvBetween(String value1, String value2) {
            addCriterion("jylv between", value1, value2, "jylv");
            return (Criteria) this;
        }

        public Criteria andJylvNotBetween(String value1, String value2) {
            addCriterion("jylv not between", value1, value2, "jylv");
            return (Criteria) this;
        }

        public Criteria andJslvIsNull() {
            addCriterion("jslv is null");
            return (Criteria) this;
        }

        public Criteria andJslvIsNotNull() {
            addCriterion("jslv is not null");
            return (Criteria) this;
        }

        public Criteria andJslvEqualTo(String value) {
            addCriterion("jslv =", value, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvNotEqualTo(String value) {
            addCriterion("jslv <>", value, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvGreaterThan(String value) {
            addCriterion("jslv >", value, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvGreaterThanOrEqualTo(String value) {
            addCriterion("jslv >=", value, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvLessThan(String value) {
            addCriterion("jslv <", value, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvLessThanOrEqualTo(String value) {
            addCriterion("jslv <=", value, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvLike(String value) {
            addCriterion("jslv like", value, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvNotLike(String value) {
            addCriterion("jslv not like", value, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvIn(List<String> values) {
            addCriterion("jslv in", values, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvNotIn(List<String> values) {
            addCriterion("jslv not in", values, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvBetween(String value1, String value2) {
            addCriterion("jslv between", value1, value2, "jslv");
            return (Criteria) this;
        }

        public Criteria andJslvNotBetween(String value1, String value2) {
            addCriterion("jslv not between", value1, value2, "jslv");
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