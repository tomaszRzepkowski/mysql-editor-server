package com.rzepkowski.mysqleditorserver.model;

public enum YNEnum {
    Y("Y"),
    N("N");

    String value;

    YNEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    static YNEnum valueFrom(String stringVal) {
        if (stringVal == null && stringVal.isEmpty()) {
            throw new NullPointerException("YNEnum, parameter cannot be null");
        }
        for ( YNEnum value : YNEnum.values() ) {
            if (stringVal.equals(value.getValue())) {
                return value;
            }
        }
        return null;
    }

    public static YNEnum valueFrom(Object objectVal) {
        String stringVal = String.valueOf(objectVal);
        return valueFrom(stringVal);
    }
}
