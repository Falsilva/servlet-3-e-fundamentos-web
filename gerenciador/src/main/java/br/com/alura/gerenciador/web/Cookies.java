package br.com.alura.gerenciador.web;

import javax.servlet.http.Cookie;

public class Cookies {

	private final Cookie[] cookies;

	public Cookies(Cookie[] cookies) {
		this.cookies = cookies;	// recebe os cookies da requisição
	}

	public Cookie buscaUsuarioLogado() {
		if(cookies==null) return null;		
		for(Cookie cookie : cookies){		// senão, procura pelo nome do cookie, resgata o seu valor, retornando o cookie
			if(cookie.getName().equals("usuario.logado")){				
				return cookie;
			}
		}
		return null;
	}
}
