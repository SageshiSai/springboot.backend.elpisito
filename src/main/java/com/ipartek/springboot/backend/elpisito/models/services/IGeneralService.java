package com.ipartek.springboot.backend.elpisito.models.services;

import java.util.List;

public interface IGeneralService<T> {
    //Capa de negocio
    //Creamos unos metodos abstractos basados en el CrudRepository por arquitectura
    //Podemos elegir los que nos interesa ( no hace falta todos)
    //Y tambien podemos añadir metodos personaliizados, es decir, metodos que no estan en el CrudRepository
    Iterable<T> findAll();
    List<T> findAllActive();
    T findById(Long id);
    T save(T usuario);
    void deleteById(Long id);

}
