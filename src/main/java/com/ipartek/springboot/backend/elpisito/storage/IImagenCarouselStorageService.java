package com.ipartek.springboot.backend.elpisito.storage;

import org.apache.tika.mime.MimeTypeException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImagenCarouselStorageService {

    //Método auxiliar para "preparar" todo lo necesario para la subida de archivos
    void init() throws IOException;

    //Con este método almacenaremos FÍSICAMENTE el archivo en la carpeta de destino (mediafiles)
    //que está definida en el application.properties
    //Además, este método, hará la notación en la BBDD
    String store(MultipartFile file, Long idBanner) throws RuntimeException, IOException, MimeTypeException;

    //Este método se encargará de devolvernos una imagen (jpg,png...)
    Resource loadAsResource(String nombreImagen) throws RuntimeException;

    //Este método devuelve la ruta completa de una imagen
    String getUrlCompletaImagen(String nombreImagen);

}
