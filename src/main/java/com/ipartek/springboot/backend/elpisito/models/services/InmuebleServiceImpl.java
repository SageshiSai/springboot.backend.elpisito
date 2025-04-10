package com.ipartek.springboot.backend.elpisito.models.services;

import com.ipartek.springboot.backend.elpisito.models.dao.IInmobiliariaDAO;
import com.ipartek.springboot.backend.elpisito.models.dao.IInmuebleDAO;
import com.ipartek.springboot.backend.elpisito.models.entity.Inmobiliaria;
import com.ipartek.springboot.backend.elpisito.models.entity.Inmueble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InmuebleServiceImpl implements IInmuebleService {

    @Autowired
    private IInmuebleDAO inmuebleDAO;

    @Autowired
    private IInmobiliariaDAO inmobiliariaDAO;



    @Override
    public List<Inmueble> findAll() {


        return (List<Inmueble>) inmuebleDAO.findAll();
    }

    @Override
    public List<Inmueble> findAllActive() {

        //Sustituir con native query methods de hibernate!!!
				/*return findAll().stream()
				.filter(  i -> i.getActivo().equals(1))
				.toList();*/
        return inmuebleDAO.findByActivo(1);

    }

    @Override
    public Inmueble findById(Long id) {

        return inmuebleDAO.findById(id).orElse(null);
    }

    @Override
    public Inmueble save(Inmueble inmueble) {

        return inmuebleDAO.save(inmueble);
    }

    @Override
    public void deleteById(Long id) {

        inmuebleDAO.deleteById(id);

    }

    @Override
    public List<Inmueble> findAllPortada() {
        //De esta manera el "trabajo" la hace el servidor y eso no es correcto
		/*return findAll().stream()
				.filter(  i -> i.getPortada().equals(1))
				.toList();*/
        //De esta manera el "trabajo" lo hace la BBDD
        return inmuebleDAO.findByActivoAndPortada(1, 1);

    }

    @Override
    public List<Inmueble> findAllInmobiliaria(Long idInmobiliaria) {

        Inmobiliaria inmobiliaria = inmobiliariaDAO.findById(idInmobiliaria).orElse(null);
        return inmuebleDAO.findByInmobiliaria(inmobiliaria);

    }




}
