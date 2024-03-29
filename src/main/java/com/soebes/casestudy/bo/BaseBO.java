package com.soebes.casestudy.bo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Basis für alle BO Klassen.
 *
 * @author Karl Heinz Marbaise
 *
 */
@MappedSuperclass
public class BaseBO {
    private Long Id;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

}
