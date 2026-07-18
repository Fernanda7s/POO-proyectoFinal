package org.example.demo.interfaces;

import org.example.demo.model.Persona;

public interface AccionesCrud {
    boolean crear(Persona personas);
    boolean actualizar(Persona personas);
    Persona buscar(String codigo);
    boolean eliminar(Persona personas);
    boolean eliminar(String codigo);
}
