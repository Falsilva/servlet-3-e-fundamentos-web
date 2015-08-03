package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.alura.gerenciador.Usuario;

// FILTRO - também conhecido como INTERCEPTOR
@WebFilter(urlPatterns = "/*") // Todo mundo vai ser filtrado. O Filtro vai ser executado para todas as requisições
public class FiltroDeAuditoria implements Filter{

	// O método destroy() quando executado, destrói o FiltroDeAuditoria
	@Override
	public void destroy() {		
	}
	
	// Filtra as requisições - chain é a cadeia de coisas a serem executadas
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {	
		
		HttpServletRequest req = (HttpServletRequest) request;	// o cast é feito, porque a request pode ser de qualquer tipo de protocolo

		// Implementação 1 - necessária para acrescentar 10 min no cookie
		// HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();	// pega a URI da requisição	
		String usuario = getUsuario(req);
		
		// Implementação 2 - necessária para acrescentar 10 min no cookie
		/*
		String usuario = "<deslogado>";
		Cookie cookie = new Cookies(req.getCookies()).buscaUsuarioLogado();
		if(cookie!=null){
			usuario = cookie.getValue();
			cookie.setMaxAge(10*60);			
			resp.addCookie(cookie);			
		}*/
		
		System.out.println("Usuário " + usuario + " acessando a URI " + uri);
		
		// Executa o próximo passo
		chain.doFilter(request, response);
	}
	
	// Método não necessário, se for acrescentar 10 min no cookie
	/*private String getUsuario(HttpServletRequest req) {
		Cookie cookie = new Cookies(req.getCookies()).buscaUsuarioLogado();	// recebe os cookies da requisição e repassa ao construtor, executando o método para buscar o usuário
		if(cookie==null) return "<deslogado>";
		return cookie.getValue();
	}*/
	
	// SESSION - buscando o usuário
	private String getUsuario(HttpServletRequest req) {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado"); // pega a Session e pega o usuario logado
		if(usuario==null) return "<deslogado>";
		return usuario.getEmail();
	}
	// O método init() quando executado, inicializa o FiltroDeAuditoria
	@Override
	public void init(FilterConfig arg0) throws ServletException {		
	}

}
