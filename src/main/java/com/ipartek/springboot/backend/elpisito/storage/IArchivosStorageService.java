package com.ipartek.springboot.backend.elpisito.storage;


import com.ipartek.springboot.backend.elpisito.models.entity.Archivo;
import org.springframework.core.io.Resource;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IArchivosStorageService {

    //Método auxiliar para "preparar" todo lo necesario para la subida de archivos
    void init() throws IOException;

    //Con este método almacenaremos FÍSICAMENTE el archivo en la carpeta de destino (mediafiles)
    //que está definida en el application.properties
    //Además, este método, hará la notación en la BBDD
    String store(MultipartFile file, Long idInmueble) throws RuntimeException, IOException, MimeTypeException;

    //Este método se encargará de devolvernos el recurso (pdf,word...)
    ResponseEntity<Resource> loadAsResource(String filename) throws RuntimeException;

    //Este método devolverá un List con todos los archivos pertenecientes a un inmueble
    List<Archivo> getArchivosActivosByInmuebleId(Long idInmueble);

    //Este método devuelve la ruta completa de un archivo
    String getUrlCompletaArchivo(String nombreArchivo);
}
