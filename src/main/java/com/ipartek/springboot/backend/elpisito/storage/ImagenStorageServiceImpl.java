package com.ipartek.springboot.backend.elpisito.storage;


import com.ipartek.springboot.backend.elpisito.models.dao.IImagenDAO;
import com.ipartek.springboot.backend.elpisito.models.dao.IInmuebleDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Imagen;
import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.List;

@Service
public class ImagenStorageServiceImpl implements IImagenStorageService{

    @Autowired
    private IImagenDAO imagenDAO;

    @Autowired
    private IInmuebleDAO inmuebleDAO;

    @Autowired
    private HttpServletRequest request;

    //Con esta propiedad estamos indicando que la propiedad definida en "media.location" que esta en archivo
    //application.properties se refiere al nombre de la carpeta en la que queremos albergar los archivos fisicos
    @Value("${media.location}")
    private String mediaLocation;

    //Este objeto de tipo Path representara la ruta raiz del almacenamiento
    private Path rootLocation;

    @Override
    @PostConstruct //Cuando el metodo esta con @PostContruct el metodo es llamado automaticamente
    public void init() throws IOException {
        //iniciamos la ruta de almacenamiento
        rootLocation = Paths.get(mediaLocation); // El Path esta apuntando a la carpeta de destino
        Files.createDirectories(rootLocation);

    }

    @Override
    public String store(MultipartFile file, Long idInmueble) throws RuntimeException, IOException {
        //En este método vamos a bifurcar la taréa en dos fases:
        //1ª fase) la primera fase va a tener varios pasos a su vez (0, 1, 2, 3, 4)
        //Y can a consistir en crear un nombre para el almacenamiento del archivo fisico
        //Fase 2) Que va  a consistir en el registro en la BBDD (paso 5)

        //try{

            /// /////////////////////////////////////////////////////////////
            //FASE 1
            /// ////////////////////////////////////////////////////////////
            //PASO 0
            if(file.isEmpty()){
                throw new RuntimeException("No es posible almacenar un archivo vacío");
                //Cuando lanzamos una excepción con un throw new las líneas de código siguientes no se ejecutan jamás
            }

            /// /////////////////////////////////////////////////////////////
            //PASO 1) si llegamos aquí estamos seguros de que el archivo no esta vacío
            //pero no sabemos de que tipo de archivo se trata. Para ello debemos obtener su tipo de MIME

            String tipoMIME = file.getContentType(); //"image/jpeg", "application/pdf"

            //En nuestro caso solo vamos a dejar subir archivos .jpg

            String tipo = "."+tipoMIME.substring(tipoMIME.lastIndexOf("/")+1); //".jpeg", ."pdf", ".png"

            //En nuestro caso solo vamos a dejar subir archivos .jpg

            if (tipo.equals(".jpeg")) {
                tipo = ".jpg";
            }

            if (!tipo.equals(".jpg")){
                throw new RuntimeException("Error al subir el archivo. La imagen subida no es una imagen .jpg");
            }

            //En este punto tenemos en la variable tipo la extensión del nombre del archivo que guardaremos.
            //PASO 2) En este paso vamos a crear el nombre del archivo a partir de la clase Calendar

            String nombreImagen = String.valueOf(Calendar.getInstance().getTimeInMillis());

            //De esta forma conseguimos el nombre en el que vamos a guardar la imagen en la base de datos.
            nombreImagen = nombreImagen.concat(tipo); //"43434343433443.jpg"

            //PASO 3) Vamos a añadir el String nombreImagen a la ruta perfijada de destino de almacenamiento. (que estaba incompleta)
            Path pathDestino = rootLocation.resolve(Paths.get(nombreImagen)); //Ya tenemos el path completo

            //PASO 4) Ahora movemos el archivo fisicamente a si destino final.
            //Esta operación la tenemos que meter en un try con recursos. Recordemos que en un try con recursos solo son admitidos
            //objetos de clases que implementan la interface Autocloseable

            try(
                    InputStream inputStream = file.getInputStream();

                    ){
                //Aquí es donde se realiza fisicamente la subida del archivo
                //De existir un archivo con el mismo nombre será eliminado y reemplazado.
                Files.copy(inputStream, pathDestino, StandardCopyOption.REPLACE_EXISTING);
                //Esta linea de codigo puede generar un IOExeption (Exepción de tipo checked cuyo tratamiento es OBLIGATORIO)
            }
            /// /////////////////////////////////////////////////////////////
            //FASE 2
            /// ////////////////////////////////////////////////////////////
            //PASO 5) Ahora vemos a crear la notación en la BBDD para que sea compatible con el archivo físico que acabamos de subir.
            //Si la subida física hubiera arrojado algún problema el resultado sería un archivo físico no vinculado a la BBDD que no tendría
            //practicamente ninguna consecuencia (exepto el hecho de que esta ocupando espacio). Por esta razón la subida física del archivo la realizamos en primer lugar

            Imagen imagen = new Imagen();
            Inmueble inmueble =  inmuebleDAO.findById(idInmueble).orElse(null);
            imagen.setName(nombreImagen);
            imagen.setInmueble(inmueble);

            imagenDAO.save(imagen); //En este momento registramos la imagen en la BBDD

            return nombreImagen;

        //}catch (IOException e){
        //    throw new RuntimeException("Error al subir el archivo en al intentar su almacenamiento en el servidor");
        //}

    }

    @Override
    public Resource loadAsResource(String nombreImagen) throws RuntimeException {
        //Vamos a obtener el path real del archivo
        Path rutaCompleta = rootLocation.resolve(Paths.get(nombreImagen));

        try{
            //Extraemos un objeto de tipo Resource del objeto Path que contiene la ruta completa
            Resource resource = new UrlResource(rutaCompleta.toUri());

            //Vamos a comprobar si existe esa ruta de archivo...
            if (resource.exists() || resource.isReadable()){
                return resource;
            } else {
                throw new RuntimeException("Error al leer el archivo, no se puede leer");
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Error el leer el archivo. No se puede leer el archivo "+nombreImagen);
        }
    }

    @Override
    public List<Imagen> getImagenesByInmuebleId(Long idInmueble) {
        //Obtener todas las imágenes asociadas al inmueble.
        List<Imagen> imagenes =  imagenDAO.findByInmuebleId(idInmueble);

        //Verificamos si se encontraron imagenes
        if (imagenes.isEmpty() || imagenes == null) {
            return List.of();
        }
        return imagenes;
    }

    @Override
    public List<Imagen> getImagenesActivasByInmuebleId(Long idInmueble) {
        //Obtener todas las imágenes asociadas al inmueble.
        return imagenDAO.findByInmuebleIdAndActivo(idInmueble, 1);
    }

    @Override
    public String getUrlCompletaImagen(String nombreImagen) {

        String host = request.getRequestURL().toString().replace(request.getRequestURI(), ""); //"http://localhost:8080" (En desarrollo claro!!!...)

        return ServletUriComponentsBuilder
                .fromUriString(host) //Añadimos la primera parte "http://localhost:8080"
                .path("/media/imagen/") //Añadimos la ruta donde se encuentra el recurso "http://localhost:8080/media/imagen/"
                .path(nombreImagen) //"http://localhost:8080/media/imagen/879378930369036890.jpg"
                .toUriString();


    }
}
