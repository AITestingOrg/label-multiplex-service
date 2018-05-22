package org.aist.aide.labelmultiplexer.domain.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "standard_label")
public class OutLabel extends Label {
    @Column(name = "description")
    private String description;

    public OutLabel(String name, String description) {
        super(name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
