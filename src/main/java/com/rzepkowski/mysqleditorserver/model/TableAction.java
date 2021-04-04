package com.rzepkowski.mysqleditorserver.model;

import java.util.Arrays;
import java.util.Optional;

public enum TableAction {
    SELECT("SELECT");

    String label;

    TableAction(String label) {
        this.label = label;
    }

    static TableAction get(String label) {
        Optional<TableAction> found = Arrays.stream(TableAction.values())
                .filter(val -> val.label.equalsIgnoreCase(label))
                .findFirst();
        return found.get();
    }
}
