package com.tikal.toledo.controllersRest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.toledo.controllersRest.VO.ListaLotesVO;
import com.tikal.toledo.dao.LoteDAO;
import com.tikal.toledo.model.Lote;
import com.tikal.toledo.util.JsonConvertidor;

@Controller
@RequestMapping(value={"/lotes"})
public class LoteController {

	@Autowired
	LoteDAO lotedao;
	
	@RequestMapping(value = {
	"/add" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public void add(HttpServletRequest re, HttpServletResponse rs, @RequestBody String json) throws IOException{
			Lote l= (Lote)JsonConvertidor.fromJson(json, Lote.class);
			lotedao.guardar(l);
			rs.getWriter().println(JsonConvertidor.toJson(l));
	}
	
	@RequestMapping(value = {
	"/save" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public void guardar(HttpServletRequest re, HttpServletResponse rs, @RequestBody String json) throws IOException{
		ListaLotesVO listavo= (ListaLotesVO) JsonConvertidor.fromJson(json, ListaLotesVO.class);
		lotedao.guardarLotes(listavo.getLista());
		rs.getWriter().println(JsonConvertidor.toJson(listavo));
	}
	
	@RequestMapping(value = {
	"/find/{id}" }, method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
	public void buscar(HttpServletRequest re, HttpServletResponse rs, @PathVariable String id) throws IOException{
		List<Lote> lotes= lotedao.porProducto(Long.parseLong(id));
		rs.getWriter().println(JsonConvertidor.toJson(lotes));
	}
	
}