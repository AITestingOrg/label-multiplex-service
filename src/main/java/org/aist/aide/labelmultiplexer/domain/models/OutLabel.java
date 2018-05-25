package org.aist.aide.labelmultiplexer.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "out_label")
public class OutLabel extends Label {
    @Column(name = "description")
    private String description;

    public OutLabel(long id, String name, String description) {
        super(name);
        this.id = id;
        this.description = description;
    }

    public OutLabel(String name, String description) {
        super(name);
        this.description = description;
    }

    public OutLabel(String name) {
        super(name);
    }

    public OutLabel() {
    }

    public String getDescription() {
        return description;
    }
}
