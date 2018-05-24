package org.aist.aide.labelmultiplexer.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public class Label extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @NotNull
    @Column(name = "name", unique = true)
    protected String name;

    public Label(@NotNull String name) {
        this.name = name;
    }

    public Label() {
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
