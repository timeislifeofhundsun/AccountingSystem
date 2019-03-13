package com.hundsun.accountingsystem.Global.bean;

import java.util.ArrayList;
import java.util.List;

public class TXwxxbExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TXwxxbExample() {
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

        public Criteria andXwNameIsNull() {
            addCriterion("xw_name is null");
            return (Criteria) this;
        }

        public Criteria andXwNameIsNotNull() {
            addCriterion("xw_name is not null");
            return (Criteria) this;
        }

        public Criteria andXwNameEqualTo(String value) {
            addCriterion("xw_name =", value, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameNotEqualTo(String value) {
            addCriterion("xw_name <>", value, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameGreaterThan(String value) {
            addCriterion("xw_name >", value, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameGreaterThanOrEqualTo(String value) {
            addCriterion("xw_name >=", value, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameLessThan(String value) {
            addCriterion("xw_name <", value, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameLessThanOrEqualTo(String value) {
            addCriterion("xw_name <=", value, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameLike(String value) {
            addCriterion("xw_name like", value, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameNotLike(String value) {
            addCriterion("xw_name not like", value, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameIn(List<String> values) {
            addCriterion("xw_name in", values, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameNotIn(List<String> values) {
            addCriterion("xw_name not in", values, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameBetween(String value1, String value2) {
            addCriterion("xw_name between", value1, value2, "xwName");
            return (Criteria) this;
        }

        public Criteria andXwNameNotBetween(String value1, String value2) {
            addCriterion("xw_name not between", value1, value2, "xwName");
            return (Criteria) this;
        }

        public Criteria andQsbhIsNull() {
            addCriterion("qsbh is null");
            return (Criteria) this;
        }

        public Criteria andQsbhIsNotNull() {
            addCriterion("qsbh is not null");
            return (Criteria) this;
        }

        public Criteria andQsbhEqualTo(String value) {
            addCriterion("qsbh =", value, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhNotEqualTo(String value) {
            addCriterion("qsbh <>", value, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhGreaterThan(String value) {
            addCriterion("qsbh >", value, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhGreaterThanOrEqualTo(String value) {
            addCriterion("qsbh >=", value, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhLessThan(String value) {
            addCriterion("qsbh <", value, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhLessThanOrEqualTo(String value) {
            addCriterion("qsbh <=", value, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhLike(String value) {
            addCriterion("qsbh like", value, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhNotLike(String value) {
            addCriterion("qsbh not like", value, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhIn(List<String> values) {
            addCriterion("qsbh in", values, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhNotIn(List<String> values) {
            addCriterion("qsbh not in", values, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhBetween(String value1, String value2) {
            addCriterion("qsbh between", value1, value2, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsbhNotBetween(String value1, String value2) {
            addCriterion("qsbh not between", value1, value2, "qsbh");
            return (Criteria) this;
        }

        public Criteria andQsNameIsNull() {
            addCriterion("qs_name is null");
            return (Criteria) this;
        }

        public Criteria andQsNameIsNotNull() {
            addCriterion("qs_name is not null");
            return (Criteria) this;
        }

        public Criteria andQsNameEqualTo(String value) {
            addCriterion("qs_name =", value, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameNotEqualTo(String value) {
            addCriterion("qs_name <>", value, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameGreaterThan(String value) {
            addCriterion("qs_name >", value, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameGreaterThanOrEqualTo(String value) {
            addCriterion("qs_name >=", value, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameLessThan(String value) {
            addCriterion("qs_name <", value, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameLessThanOrEqualTo(String value) {
            addCriterion("qs_name <=", value, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameLike(String value) {
            addCriterion("qs_name like", value, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameNotLike(String value) {
            addCriterion("qs_name not like", value, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameIn(List<String> values) {
            addCriterion("qs_name in", values, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameNotIn(List<String> values) {
            addCriterion("qs_name not in", values, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameBetween(String value1, String value2) {
            addCriterion("qs_name between", value1, value2, "qsName");
            return (Criteria) this;
        }

        public Criteria andQsNameNotBetween(String value1, String value2) {
            addCriterion("qs_name not between", value1, value2, "qsName");
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