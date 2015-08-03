package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/executa")
public class Controller extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String tarefa = req.getParameter("tarefa");
		
		// qual tarefa
		if(tarefa == null) throw new IllegalArgumentException("Você esqueceu de passar a tarefa");
		
		tarefa = "br.com.alura.gerenciador.web." + tarefa;	// pega a string da tarefa
		
		try {
			Class<?> tipo = Class.forName(tarefa);			// pega a classe da string tarefa			
			Tarefa instancia = (Tarefa) tipo.newInstance();	// instancia a classe
			String pagina = instancia.executa(req, resp);	// chama o método executa
			RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);	// redireciona
			dispatcher.forward(req, resp);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {	
			throw new ServletException(e);
		}				
	}
}
