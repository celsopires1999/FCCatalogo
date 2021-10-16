package com.fullcycle.FCCatalogo.domain.entity;

import java.util.UUID;

public class Genre extends BaseEntity{

    private String name;

    // public Genre() {}

    public Genre(String name) {
        super.generateUUID(); //na minha opinião deveria ser this ao invés de super
        this.setName(name);
    }
    
    public Genre(UUID id, String name) {
        super.setId(id); //na minha opinião deveria ser this ao invés de super
        this.setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("");
        if (name.length() == 0) throw new IllegalArgumentException("");
        this.name = name;
    }

}
