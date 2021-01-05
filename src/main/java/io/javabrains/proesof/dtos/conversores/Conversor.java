package io.javabrains.proesof.dtos.conversores;

public interface Conversor<O,I>{
    O converter(I i);
}
