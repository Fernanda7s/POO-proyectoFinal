package org.example.demo.interfaces;

import org.example.demo.model.Empleado;
import org.example.demo.model.Persona;

public interface AccionesCrud<Persona> {
    boolean crear(Persona people);

    boolean crear(org.example.demo.model.Persona personas);

    boolean actualizar(Persona people);

    Persona buscar(String codigo);

    boolean eliminar(Persona people);

    boolean eliminar(String codigo);
}
