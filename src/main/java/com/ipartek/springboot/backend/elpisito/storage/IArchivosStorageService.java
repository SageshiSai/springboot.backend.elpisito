package com.ipartek.springboot.backend.elpisito.storage;


import com.ipartek.springboot.backend.elpisito.models.entity.Archivo;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IArchivosStorageService {

    //Metodo auxiliar para "preparar" todo lo necesario para la subida de archivos
    void init() throws IOException;

    //Con este metodo, almacenaremos fisicamente el archivo en la carpeta de destino (mediafiles)
    //Que está difinida en el application.properties
    //Ademas, este metodo, hará la notacion en la BBDD

    String store(MultipartFile file, Long idInmueble) throws RuntimeException;
    //Este metodo se encargara de devolvernos el recusrso(pdf,word...)
    ResponseEntity<Resource> loadResource(String filename) throws RuntimeException;

    //Este metodo devolvera un list con todos los archivos pertenecientes a un inmueble
    List<Archivo> getArchivosByInmuebleId(Long idInmueble);
}
