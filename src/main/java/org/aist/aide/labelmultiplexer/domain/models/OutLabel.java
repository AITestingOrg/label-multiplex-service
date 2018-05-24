package org.aist.aide.labelmultiplexer.domain.models;

import javax.persistence.*;

@Entity
@Table(name = "`out_label`")
public class OutLabel extends Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "`description`")
    private String description;

    public OutLabel(String name, String description) {
        super(name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
