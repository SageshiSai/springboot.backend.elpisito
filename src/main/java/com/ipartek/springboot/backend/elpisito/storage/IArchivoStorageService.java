package com.ipartek.springboot.backend.elpisito.storage;

import java.io.IOException;
import java.util.List;

import org.apache.tika.mime.MimeTypeException;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.springboot.backend.apirest.models.entity.Archivo;

public interface IArchivoStorageService {
	
	//Método auxiliar para "preparar" todo lo necesario para la subida de archivos
	void init() throws IOException;
	
	//Con este método almacenaremos FÍSICAMENTE el archivo en la carpeta de destino (mediafiles)
	//que está definida en el application.properties
	//Además, este método, hará la notación en la BBDD
	String store(MultipartFile file, Long idInmueble) throws RuntimeException, IOException, MimeTypeException;
	
	//Este método se encargará de devolvernos el recurso (pdf,word...)
	Resource loadAsResource(String nombreArchivo) throws RuntimeException;
	
	//Este método devolverá un List con todos los archivos pertenecientes a un inmueble
	List<Archivo> getArchivosByInmuebleId(Long idInmueble);
	
	//Este método devuelve la ruta completa de un archivo
	String getUrlCompletaArchivo(String nombreArchivo);
	
	//Este método borra un archivo físico.
	void deleteArchivo(Long id) throws DataAccessException, IOException;
	
	//Este método borra solo el archivo físico de la carpeta de "mediafiles"
	void deleteArchivoMediaFiles(String nombre)throws DataAccessException, IOException;

}
