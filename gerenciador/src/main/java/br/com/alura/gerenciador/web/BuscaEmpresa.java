package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

//@WebServlet(urlPatterns="/busca")
public class BuscaEmpresa implements Tarefa{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp){
		
		String filtro = req.getParameter("filtro");
		Collection<Empresa> empresas = new EmpresaDAO().buscaPorSimilaridade(filtro);		
		req.setAttribute("empresas", empresas);
		return "WEB-INF/paginas/buscaEmpresa.jsp";
		
	}
	
	//protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//PrintWriter writer = resp.getWriter();
		//writer.println("<html><body>");
		//writer.println("Resultado da busca:<br />");
		//String filtro = req.getParameter("filtro");
		//Collection<Empresa> empresas = new EmpresaDAO().buscaPorSimilaridade(filtro);
		//RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/paginas/buscaEmpresa.jsp");
		//req.setAttribute("empresas", empresas);
		//dispatcher.forward(req, resp);
		
		/*
		writer.println("<ul>");
		for(Empresa empresa : empresas){
			writer.println("<li>" + empresa.getId() + ": " + empresa.getNome() + "</li>");
		}
		writer.println("</ul>");
		writer.println("</body></html>");
		*/
	//}
}
