package org.aist.aide.labelmultiplexer.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.http.ResponseEntity;


@Entity
@Table(name = "in_label")
public class InLabel extends Label {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "out_label_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OutLabel label;

    public InLabel(long id, String name, OutLabel label) {
        this.id = id;
        this.name = name;
        this.label = label;
    }

    public InLabel(String name, OutLabel label) {
        super(name);
        this.label = label;
    }

    public InLabel(@NotNull String name) {
        super(name);
    }

    public InLabel() {
    }

    public OutLabel getLabel() {
        return label;
    }
}
