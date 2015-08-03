package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Tarefa{
	
	// o POST é devido a alteração de estado no servidor, ou seja, faz alterações no servidor
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp){

		// Cookie Implementação 1
		/*Cookie cookie = new Cookies(req.getCookies()).buscaUsuarioLogado();	// busca o cookie do usuário logado
		PrintWriter writer = resp.getWriter();
		if(cookie==null){
			writer.println("<html><body>Usuário não estava logado!</body></html>");
			return;
		}
		cookie.setMaxAge(0);	// elimina o cookie fazenzo o seu tempo expirar
		resp.addCookie(cookie);	// adiciona o cookie na resposta com a nova configuração
		writer.println("<html><body>Deslogado com sucesso</body></html>");
		*/
		
		// Cookie Implementação 2
		/* Ajustes incompleto
		Cookie cookie = new Cookies(req.getCookies()).buscaUsuarioLogado();
		Usuario usuario = Login.USUARIOS_LOGADOS.get(cookie.getValue());
		PrintWriter writer = resp.getWriter();
		if(cookie==null){
			writer.println("<html><body>Usuário não estava logado!</body></html>");
			return;
		}
		cookie.setMaxAge(0);	// elimina o cookie fazenzo o seu tempo expirar
		resp.addCookie(cookie);	// adiciona o cookie na resposta com a nova configuração
		writer.println("<html><body>Deslogado com sucesso</body></html>");
		*/
		
		// SESSION
		// Maneira de Deslogar 1
		//req.getSession().setMaxInactiveInterval(10*60);	// pode usar o invalidate() ou esse método para a Session expirar o tempo entre duas requisições, toda vez que faz uma request, esse tempo é renovado, caso contrário, é feito o logout
		req.getSession().removeAttribute("usuarioLogado");	// removendo o atributo, o atributo deixa de existir, então quando a requisição tentar pegar o atributo, não vai existir, ou seja, está deslogado
		
		// resp.sendRedirect("logout.html");
		return "WEB-INF/paginas/logout.html";
		
		
		// Maneira de Deslogar 2
		/*req.getSession().invalidate();
				
		PrintWriter writer = resp.getWriter();		
		writer.println("<html><body>Deslogado com sucesso</body></html>");*/
	}
}
