package com.ipartek.springboot.backend.elpisito.storage;

import java.io.IOException;
import java.util.List;

import org.apache.tika.mime.MimeTypeException;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;


import com.ipartek.springboot.backend.apirest.models.entity.Imagen;


public interface IImagenStorageService {
	
	//Método auxiliar para "preparar" todo lo necesario para la subida de archivos
	void init() throws IOException;
	
	//Con este método almacenaremos FÍSICAMENTE el archivo en la carpeta de destino (mediafiles)
	//que está definida en el application.properties
	//Además, este método, hará la notación en la BBDD
	String store(MultipartFile file, Long idInmueble) throws RuntimeException, IOException, MimeTypeException;
	
	//Este método se encargará de devolvernos una imagen (jpg,png...)
	Resource loadAsResource(String nombreImagen) throws RuntimeException;
	
	//Este método devolverá un List con todas las imágenes pertenecientes a un inmueble
	List<Imagen> getImagenesByInmuebleId(Long idInmueble);
	
	//Este método devuelve la ruta completa de una imagen
	String getUrlCompletaImagen(String nombreImagen);
	
	//Este método borra un archivo físico.
	void deleteImagen(Long id) throws DataAccessException, IOException;
	
	//Este método borra solo la imagen fisica de la carpeta de "mediafiles"
	void deleteImagenMediaFiles(String nombre)throws DataAccessException, IOException;
	
	
	

}
