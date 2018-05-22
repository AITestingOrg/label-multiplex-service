package org.aist.aide.labelmultiplexer.domain.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "in_label")
public class InLabel extends Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "out_label_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OutLabel label;

    public InLabel(String name, OutLabel label) {
        super(name);
        this.label = label;
    }

    public OutLabel getLabel() {
        return label;
    }
}
