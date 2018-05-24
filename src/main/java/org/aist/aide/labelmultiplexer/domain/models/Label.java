package org.aist.aide.labelmultiplexer.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public abstract class Label extends AuditModel {
    protected long id;

    @NotNull
    @Column(name = "`name`", unique = true)
    protected String name;

    public Label(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
