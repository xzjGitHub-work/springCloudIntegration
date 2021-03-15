package com.tech.eclouds.xzj.criteria;

import com.tech.eclouds.xzj.util.StringTransformUtil;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author SunBo
 * @version v1.0
 * @since 2019/7/19
 */
public class Criteria implements Serializable {

    public enum SqlType {
        HQL, SQL, QUERY;
    }

    public enum CriteriaType {
        AND, OR;
    }

    public enum OperationKey {
        MATCH,
        NOT_MATCH,
        EQUAL,
        NOT_EQUAL,
        CONTAINS,
        NOT_CONTAINS,
        BETWEEN,
        NOT_BETWEEN,
        IS_NULL,
        IS_NOT_NULL,
        IN,
        NOT_IN,
        LESS_THAN,
        LESS_EQUAL,
        GREATER_THAN,
        GREATER_EQUAL;
    }

    private String column;
    private CriteriaType criteriaType;
    private OperationKey operationKey;
    private double weight = 1.0;
    private Object value;
    private LinkedList<Criteria> criteriaChain;
    private static String ALL = "$all";


    private Criteria() {

    }

    private Criteria(String column, double weight) {
        this.column = column;
        this.weight = weight;
        this.criteriaChain = new LinkedList<>();
    }

    private <T> Criteria(SFunction<T, ?> column, double weight) {
        this.column = SerializedLambda.getColumnName(column);
        this.weight = weight;
        this.criteriaChain = new LinkedList<>();
    }

    public static Criteria field(String column) {
        return new Criteria(column, 1.0);
    }

    public static <T> Criteria field(SFunction<T, ?> column) {
        return new Criteria(column, 1.0);
    }

    public static Criteria field(String column, double weight) {
        return new Criteria(column, weight);
    }

    public static <T> Criteria field(SFunction<T, ?> column, double weight) {
        return new Criteria(column, weight);
    }

    public static Criteria ALL() {
        return new Criteria(ALL, 1.0);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Criteria eq(Object o) {
        setValue(o);
        this.operationKey = OperationKey.EQUAL;
        return this;
    }

    public Criteria ne(Object o) {
        setValue(o);
        this.operationKey = OperationKey.NOT_EQUAL;
        return this;
    }

    public Criteria contains(Object o) {
        setValue(o);
        this.operationKey = OperationKey.CONTAINS;
        return this;
    }

    public Criteria notContains(Object o) {
        setValue(o);
        this.operationKey = OperationKey.NOT_CONTAINS;
        return this;
    }

    public Criteria match(Object o) {
        setValue(o);
        this.operationKey = OperationKey.MATCH;
        return this;
    }

    public Criteria notMatch(Object o) {
        setValue(o);
        this.operationKey = OperationKey.NOT_MATCH;
        return this;
    }

    public Criteria between(Object o1, Object o2) {
        List<Object> objects = new ArrayList<>();
        objects.add(o1);
        objects.add(o2);
        setValue(objects);
        this.operationKey = OperationKey.BETWEEN;
        return this;
    }

    public Criteria notBetween(Object o1, Object o2) {
        List<Object> objects = new ArrayList<>();
        objects.add(o1);
        objects.add(o2);
        setValue(objects);
        this.operationKey = OperationKey.NOT_BETWEEN;
        return this;
    }

    public Criteria isNull() {
        this.operationKey = OperationKey.IS_NULL;
        return this;
    }

    public Criteria isNotNull() {
        this.operationKey = OperationKey.IS_NOT_NULL;
        return this;
    }

    public Criteria in(Object... o) {
        setValue(o);
        this.operationKey = OperationKey.IN;
        return this;
    }

    public Criteria notIn(Object... o) {
        setValue(o);
        this.operationKey = OperationKey.NOT_IN;
        return this;
    }

    public Criteria lt(Object o) {
        setValue(o);
        this.operationKey = OperationKey.LESS_THAN;
        return this;
    }

    public Criteria le(Object o) {
        setValue(o);
        this.operationKey = OperationKey.LESS_EQUAL;
        return this;
    }

    public Criteria gt(Object o) {
        setValue(o);
        this.operationKey = OperationKey.GREATER_THAN;
        return this;
    }

    public Criteria ge(Object o) {
        setValue(o);
        this.operationKey = OperationKey.GREATER_EQUAL;
        return this;
    }

    public Criteria and(Criteria criteria) {
        criteria.criteriaType = CriteriaType.AND;
        criteriaChain.add(criteria);
        return this;
    }


    public Criteria or(Criteria criteria) {
        criteria.criteriaType = CriteriaType.OR;
        criteriaChain.add(criteria);
        return this;
    }


    public String getColumn() {
        return column;
    }

    public CriteriaType getCriteriaType() {
        return criteriaType;
    }

    public OperationKey getOperationKey() {
        return operationKey;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public LinkedList<Criteria> getCriteriaChain() {
        return criteriaChain;
    }

    public String buildCondition(SqlType sqlType) {
        StringBuffer stringBuffer = new StringBuffer();
        if (criteriaType != null) {
            stringBuffer.append(" ").append(criteriaType).append(" ");
        }
        if (criteriaChain.size() != 0) {
            stringBuffer.append("(");
        }

        if (!StringUtils.equals(column, ALL)) {
            if (operationKey != null) {
                switch (operationKey) {
                    case NOT_MATCH:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("NOT");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" NOT LIKE '%").append(value).append("%' ");
                            break;
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" NOT LIKE '%").append(value).append("%' ");
                            break;
                        }
                    case MATCH:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append(column).append(":").append(value instanceof String ? this.escape(value) : StringTransformUtil.getValueToSQLString(value)).append(")");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" like '%").append(value).append("%' ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" like '%").append(value).append("%' ");
                        }
                        break;
                    case NOT_EQUAL:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("NOT");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" <> ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                            break;
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" <> ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                            break;
                        }
                    case EQUAL:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append(column).append(value instanceof String ? ".keyword:" : ":").append(value instanceof String ? "\"" + this.escape(value) + "\"" : StringTransformUtil.getValueToSQLString(value)).append(")");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" = ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" = ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        break;
                    case NOT_CONTAINS:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("NOT");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" NOT LIKE '%").append(value).append("%' ");
                            break;
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" NOT LIKE '%").append(value).append("%' ");
                            break;
                        }
                    case CONTAINS:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append(column).append(value instanceof String ? ".keyword:" : ":").append("*").append(this.escape(value)).append("*)");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" like '%").append(value).append("%' ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" like '%").append(value).append("%' ");
                        }
                        break;
                    case NOT_BETWEEN:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("NOT");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" NOT BETWEEN ").append(StringTransformUtil.getValueToSQLString(((List) value).get(0))).append(" and ").append(StringTransformUtil.getValueToSQLString(((List) value).get(1))).append(" ");
                            break;
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" NOT BETWEEN ").append(StringTransformUtil.getValueToSQLString(((List) value).get(0))).append(" and ").append(StringTransformUtil.getValueToSQLString(((List) value).get(1))).append(" ");
                            break;
                        }
                    case BETWEEN:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append(column).append(":[").append(value instanceof String ? this.escape(value) : StringTransformUtil.getValueToSQLString(((List) value).get(0))).append(" TO ").append(value instanceof String ? value : StringTransformUtil.getValueToSQLString(((List) value).get(1))).append("])");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" BETWEEN ").append(StringTransformUtil.getValueToSQLString(((List) value).get(0))).append(" and ").append(StringTransformUtil.getValueToSQLString(((List) value).get(1))).append(" ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" BETWEEN ").append(StringTransformUtil.getValueToSQLString(((List) value).get(0))).append(" and ").append(StringTransformUtil.getValueToSQLString(((List) value).get(1))).append(" ");
                        }
                        break;
                    case IS_NULL:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("NOT");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" is null ");
                            break;
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" is null ");
                            break;
                        }

                    case IS_NOT_NULL:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append("_exists_").append(":").append(column).append(")");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" is not null ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" is not null ");
                        }
                        break;
                    case NOT_IN:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("NOT");
                        }
                        if (sqlType.equals(SqlType.SQL) || sqlType.equals(SqlType.HQL)) {
                            List<Object> valueList = new ArrayList<>();

                            for (Object object : (Object[]) value) {
                                if (object instanceof List) {
                                    valueList.addAll((List) object);
                                } else {
                                    valueList.add(object);
                                }
                            }

                            stringBuffer.append(column).append(" not in (");
                            stringBuffer.append(StringUtils.join(valueList.stream().map(object -> StringTransformUtil.getValueToSQLString(object)).toArray(), ","));
                            stringBuffer.append(") ");
                            break;
                        }
                    case IN:
                        List<Object> valueList = new ArrayList<>();

                        if (value instanceof Collection) {
                            for (Object object : (Collection) value) {
                                if (object instanceof List) {
                                    valueList.addAll((List) object);
                                } else {
                                    valueList.add(object);
                                }
                            }
                        } else {
                            for (Object object : (Object[]) value) {
                                if (object instanceof List) {
                                    valueList.addAll((List) object);
                                } else {
                                    valueList.add(object);
                                }
                            }
                        }

                        if (sqlType.equals(SqlType.QUERY)) {
                            String valueStr = StringUtils.join(valueList.toArray(), " OR ");
                            stringBuffer.append("(").append(column).append(":(").append(valueStr).append("))");
                        }
                        if (sqlType.equals(SqlType.SQL) || sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" in (");
                            stringBuffer.append(StringUtils.join(valueList.stream().map(object -> StringTransformUtil.getValueToSQLString(object)).toArray(), ","));
                            stringBuffer.append(") ");
                        }
                        break;
                    case LESS_THAN:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append(column).append(":{* TO ").append(value instanceof String ? this.escape(value) : StringTransformUtil.getValueToSQLString(value)).append("})");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" < ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" < ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        break;
                    case LESS_EQUAL:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append(column).append(":[* TO ").append(value instanceof String ? this.escape(value) : StringTransformUtil.getValueToSQLString(value)).append("})");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" <= ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" <= ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        break;
                    case GREATER_THAN:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append(column).append(":{").append(value instanceof String ? this.escape(value) : StringTransformUtil.getValueToSQLString(value)).append(" TO *})");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" > ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" > ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        break;
                    case GREATER_EQUAL:
                        if (sqlType.equals(SqlType.QUERY)) {
                            stringBuffer.append("(").append(column).append(":{").append(value instanceof String ? this.escape(value) : StringTransformUtil.getValueToSQLString(value)).append(" TO *})");
                        }
                        if (sqlType.equals(SqlType.SQL)) {
                            stringBuffer.append(column).append(" >= ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        if (sqlType.equals(SqlType.HQL)) {
                            stringBuffer.append(column).append(" >= ").append(StringTransformUtil.getValueToSQLString(value)).append(" ");
                        }
                        break;
                    default:
                }
            }

            if (weight != 1.0 && sqlType.equals(SqlType.QUERY)) {
                stringBuffer.append("^").append(weight);
            }
        } else {
            if (sqlType.equals(SqlType.QUERY)) {
                stringBuffer.append("*");
            } else {
                stringBuffer.append("1 = 1");
            }
        }

        for (Criteria criteria : criteriaChain) {
            stringBuffer.append(criteria.buildCondition(sqlType));
        }

        if (criteriaChain.size() != 0) {
            stringBuffer.append(")");
        }

        return stringBuffer.toString();
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Criteria{");
        sb.append("column='").append(column).append('\'');
        sb.append(", criteriaType=").append(criteriaType);
        sb.append(", operationKey=").append(operationKey);
        sb.append(", value=").append(value);
        sb.append(", criteriaChain=").append(criteriaChain);
        sb.append('}');
        return sb.toString();
    }

    private Object escape(Object value) {
        if (value == null) {
            return null;
        }
        if (!(value instanceof String)) {
            return value;
        }
        String s = (String) value;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':'
                    || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
                    || c == '*' || c == '?' || c == '|' || c == '&' || c == '/') {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

}
