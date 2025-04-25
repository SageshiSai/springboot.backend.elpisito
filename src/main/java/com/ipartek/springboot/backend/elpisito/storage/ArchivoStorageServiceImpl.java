package com.ipartek.springboot.backend.elpisito.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ipartek.springboot.backend.apirest.models.dao.IArchivoDAO;

import com.ipartek.springboot.backend.apirest.models.dao.IInmuebleDAO;
import com.ipartek.springboot.backend.apirest.models.entity.Archivo;

import com.ipartek.springboot.backend.apirest.models.entity.Inmueble;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;



@Service
public class ArchivoStorageServiceImpl implements IArchivoStorageService{

	@Autowired
	private IArchivoDAO archivoDAO;
	
	@Autowired
	private IInmuebleDAO inmuebleDAO;
	
	@Autowired
	private HttpServletRequest request;
	
	//CON ESTA PROPIEDAD ESTAMOS INDICANDO QUE LA PROPIEDAD DEFINIDA EN 
	//"media.location" que está en el archivo application-properties
	//se refiere al nombre de la carpeta en la que queremos albergar
	//los archivos físicos que subamos al servidor
	@Value("${media.location}")
	private String mediaLocation;
	
	//Este objeto de tipo Path representará la ruta raiz de almacenamiento
	private Path rootLocation;
	
	
	@Override
	@PostConstruct //Cuando un método está anotado con @PostConstruct el método es llamado automáticamente en el momento en que la clase se instancia
	public void init() throws IOException {
		
		//Iniciamos la ruta de almacenamiento
		rootLocation = Paths.get(mediaLocation);//El Path está apuntando a la carpeta de destino
		Files.createDirectories(rootLocation);
			
	}

	@Override
	public String store(MultipartFile file, Long idInmueble) throws RuntimeException, IOException, MimeTypeException {
		
		//Dejamos el nombre original (sin extensión) del archivo en una variable...
		//String nombreOriginal = file.getName().split(".")[0];
		String nombreOriginal = file.getName();
		
		//En este método vamos a bifurcar la taréa en dos fases:
		//1 FASE) La primera fase va a tener varios pasos a su vez (0,1,2,3,4)
		//y van a consistir en crear un nombre para el almacenamiento del archivo
		//físico a un directorio de un servidor
		//2 FASE) Que va a consistir en el registro en al BBDD (paso 5)
		
		
			
			/////////////////////////////////////////////////////
			//FASE 1
			////////////////////////////////////////////////////
			//PASO 0: Vamos a verificar si el Multipart está vacío
			if(file.isEmpty()) {
				
				throw new RuntimeException("Error al subir el archivo. No es posible almacenar un archivo vacío");
				//Cuando lanzamos una excepción con un throw new las líneas de código siguientes no se ejecutan jamás
			
			}
			
			//PASO 1: Si llegamos aquí, estamos seguros de que el archivo no está vacío
			//pero no sabemos de que tipo de archivo se trata. Para ello debemos 
			//obtener su tipo MIME
			//http://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types
			
			MimeTypes allMimeTypes = MimeTypes.getDefaultMimeTypes();
			
			MimeType tipoMime = allMimeTypes.forName(file.getContentType());
			
			
								
			String tipo = tipoMime.getExtension();// ".jpeg",".pdf"...
			
			/*String tipoMIME = file.getContentType();//"image/jpeg","application/pdf"
				
			String tipo = "." + tipoMIME.substring(tipoMIME.lastIndexOf("/") + 1); //".jpeg", ".pdf", ".png"
			*/
			
			//En nuestro caso solo vamos a dejar subir archivos de estos tipos:
			List<String> tiposPermitidos = Arrays.asList(".pdf",".doc",".docx",".xls",".xlsx");//añadir a esta lista los tipos permitidos
			
			if(!tiposPermitidos.contains(tipo)) {
				
				throw new RuntimeException("Error al subir el archivo. El archivo subido no es tipo de archivo permitido");
			}
			
		
			/*if(!tipo.equals(".pdf")){
				
				throw new RuntimeException("Error al subir el archivo. La archivo subido no es una imagen jpg");
				//Cuando lanzamos una excepción con un throw new las líneas de código siguientes no se ejecutan jamás
			}*/
			
			//En este punto tenemos en la variable tipo la extensión del nombre del archivo que guardaremos en la BBDD
			
			//PASO 2: En este paso vamos a crear el nombre del archivo a partir de la clase Calendar
			
			String nombreArchivo = String.valueOf(Calendar.getInstance().getTimeInMillis());
			
			//De esta forma conseguimos en nombre en el que vamos a guardar la imagen en la BBDD
			nombreArchivo = nombreArchivo.concat(tipo); //"6474847847474747.pdf"...etc.
			
			
			//PASO 3: Vamos a añadir el String nombreArchivo a la ruta prefijada de destino de almacenamiento (que estaba incompleta)
			Path pathDestino =  rootLocation.resolve(Paths.get(nombreArchivo)); //Ya tenemos el Path completo de destino
			
			//PASO 4: Ahora movemos el archivo FÍSICAMENTE a su destino final
			//Esta operación la tenemos que meter en un try con recursos. Vamos a recordar
			//que en un try con recursos solo son admitidos objetos de clases que implementan
			//la interface Autocloseable
			
		
			
			try(
					
					InputStream inputStream = file.getInputStream()
					
					){
				
				//Aquí es donde se realiza físicamente la subida del archivo
				//De existir un archivo con el mismo nombre será eliminado y reemplazado
				Files.copy(inputStream, pathDestino, StandardCopyOption.REPLACE_EXISTING);
				//Esta línea de código puede generar un IOException (excepción de tipo checked
				//cuyo tratamiento es OBLIGATORIO)
				
			}
			
			/////////////////////////////////////////////////////
			//FASE 2
			////////////////////////////////////////////////////
			//PASO 5: Ahora vamos a crear la notación en la BBDD para que sea compatible
			//con el archivo físico que acabamos de subir
			
			//Si la subida física hubiera arrojado algún problema el resultado sería un
			//archivo físico no vinculado a la BBDD que no tendría prácticamente ninguna
			//consecuencia (excepto el hecho de que está ocupando espacio). Por esta razón
			//la subida física del archivo la realizamos en primer lugar.
			
			Archivo archivo = new Archivo();
			Inmueble inmueble = inmuebleDAO.findById(idInmueble).orElse(null);
			archivo.setNombre(nombreArchivo);

			archivo.setDescripcion(nombreOriginal);
			archivo.setInmueble(inmueble);
			
			try {
				archivoDAO.save(archivo); //En este momento registramos el archivo en la BBDD
			}catch (Exception e) {
				//BORRAR EL ARCHIVO FÍSICO DE LA CARPETA mediafiles
				deleteArchivoMediaFiles(nombreArchivo);
				throw new RuntimeException("Error al registrar el archivo en la BBDD por un error interno");
			}
			
			
			return nombreArchivo;
		
		
		
		
	}

	@Override
	public Resource loadAsResource(String nombreArchivo) throws RuntimeException {
		
		
		//Vamos a obtener el path real del archivo
		Path rutaCompleta = rootLocation.resolve(Paths.get(nombreArchivo));
		
		try {
			//Extraemos un objeto de tipo Resource del objeto Path que contiene la ruta completa
			Resource resource = new UrlResource(rutaCompleta.toUri());
			
			//Vamos a comprobar si existe esa ruta al archivo...
			if(resource.exists() || resource.isReadable()) {
				
				return resource;
				
			}else {
				
				throw new RuntimeException("Error al leer el archivo. No se puede leer el archivo " + nombreArchivo);
			}
			
			
		}catch(MalformedURLException e) {
			
			throw new RuntimeException("Error al leer el archivo. No se puede leer el archivo " + nombreArchivo);
			
		}
	}

	@Override
	public List<Archivo> getArchivosByInmuebleId(Long idInmueble) {
		
		//Obtener todos los archivos asociados al inmueble
		
		return archivoDAO.findByInmuebleId(idInmueble);
		
	}

	@Override
	public String getUrlCompletaArchivo(String nombreArchivo) {
		
		
		String host = request.getRequestURL().toString().replace(request.getRequestURI(), ""); //"http://localhost:8080" (En desarrollo claro!!!...)
		
		return ServletUriComponentsBuilder
				.fromUriString(host) //Añadimos la primera parte "http://localhost:8080"
				.path("/media/archivo/") //Añadimos la ruta donde se encuentra el recurso "http://localhost:8080/media/archivo/"
				.path(nombreArchivo) //"http://localhost:8080/media/archivo/879378930369036890.pdf"
				.toUriString();
		
	}
	

	@Override
	public void deleteArchivo(Long id) throws DataAccessException, IOException {
		
		//1)Elimino el registro en la BBDD
		Archivo archivo = archivoDAO.findById(id).orElse(null);
		archivoDAO.deleteById(id);
		
		//2)Elimino el archivo físicamente
		Path filePath =	rootLocation.resolve(archivo.getNombre()).normalize();
		File file = filePath.toFile();
		
		if(file.exists()) {
			
			file.delete();
			
		}
		
		
		
		
		
	}

	@Override
	public void deleteArchivoMediaFiles(String nombre) throws DataAccessException, IOException {
		
		//1)Elimino el archivo físicamente
		
		Path filePath =	rootLocation.resolve(nombre).normalize();
		File file = filePath.toFile();
		
		if(file.exists()) {
			
			file.delete();
			
		}
		
	}

	
	
	
	
	
}
