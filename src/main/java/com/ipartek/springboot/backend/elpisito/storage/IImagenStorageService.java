package com.ipartek.springboot.backend.elpisito.storage;

import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImagenStorageService {

    //Método auxiliar para "preparar" todo lo necesario para la subida de archivos
    void init() throws IOException;

    //Con este método, almacenaremos físicamente el archivo en la carpeta de destino (mediafiles)
    //Que está definida en el application.properties
    //Ademas, este método, hará la notación en la BBDD

    String store(MultipartFile file, Long idInmueble) throws RuntimeException, IOException, MimeTypeException;
    //Este método se encargara de devolvernos el recursos(pdf,word...)
    Resource loadAsResource(String nombreImagen) throws RuntimeException;

    //Este método devolverá un list con todos las imágenes pertenecientes a un inmueble
    List<Imagen> getImagenesByInmuebleId(Long idInmueble);

    List<Imagen> getImagenesActivasByInmuebleId(Long idInmueble);

    //Este método devuelve la ruta completa de una imagen
    String getUrlCompletaImagen(String nombreImagen);
}
