package com.tikal.toledo.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import com.tikal.toledo.dao.ProductoDAO;
import com.tikal.toledo.model.Producto;
import com.tikal.toledo.model.Tornillo;

public class ProductoDAOImp implements ProductoDAO{

	@Override
	public String guardar(Producto p) {
		ofy().save().entity(p).now();
		return "ok";
	}

	@Override
	public Producto cargar(Long id) {
		return ofy().load().type(Producto.class).id(id).now();
	}

	@Override
	public List<Producto> buscar(String search) {
		search=search.toLowerCase();
		List<Producto> lista= ofy().load().type(Producto.class).list();
		List<Producto> result= new ArrayList<Producto>();
		for(Producto p:lista){
			if(p.getClave()!=null){
			if(p.getId().toString().contains(search) || p.getNombre().toLowerCase().contains(search)|| p.getClave().toLowerCase().contains(search)){
				result.add(p);
			}}else{
				if(p.getId().toString().contains(search) || p.getNombre().toLowerCase().contains(search)){
					result.add(p);
				}
			}
		}
		return result;
	}

	@Override
	public List<Producto> todos() {
		return ofy().load().type(Producto.class).list();
	}

	@Override
	public List<Producto> todos(int page) {
		return ofy().load().type(Producto.class).offset(50*(page-1)).limit(50).list();
	}
	
	@Override
	public int total() {
		return ofy().load().type(Producto.class).count();
	}

	@Override
	public void formula(float impuesto, float descuento, float ganancia) {
		List<Producto> lista= ofy().load().type(Producto.class).list();
	}

	@Override
	public void guardar(List<Producto> lista) {
		ofy().save().entities(lista);
	}

	@Override
	public void alv() {
		List<Producto> lista= ofy().load().type(Producto.class).list();
		ofy().delete().entities(lista).now();
	}

	@Override
	public void eliminar(Producto p) {
		ofy().delete().entity(p).now();
	} 
	
}
