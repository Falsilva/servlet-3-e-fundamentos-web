package br.com.alura.gerenciador.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.Usuario;
import br.com.alura.gerenciador.dao.UsuarioDAO;

public class Login implements Tarefa{
	
	// Como está guardando os cookies do lado do servidor, não precisa ser String, pode ser um Objeto com um pouco mais de informação, no caso Usuário
	final static Map<String, Usuario> USUARIOS_LOGADOS = new HashMap();	// variável estática, onde todos podem ter acesso e final(constante) para que o valor não seja modificado - nunca muda, pode tirar coisas desse HashMap ou colocar
	
	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp){
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
		// PrintWriter writer = resp.getWriter();
		if(usuario==null){
			req.setAttribute("usuarioNulo", "Usuário inválido");
			return "index.jsp";
			// writer.println("<html><body>Usuário inválido</body></html>");
		}else{
			// SESSION - é seguro porque temos as informações de usuário do lado do servidor, tendo controle absoluto
			HttpSession session = req.getSession();
			session.setAttribute("usuarioLogado", usuario);	// coloca na memória do servidor e devolve um cookie para o cliente com um código maluco
			return "index.jsp";
			
			// writer.println("<html><body>Usuário logado: " + email);
			
			
			// COOKIE - não é muito seguro, porque guarda informações do usuário do lado Cliente, onde não temos controle dele
			 // Os cookies permitem que marquemos nossos clientes com determinadas informações
			
			// Cookie Implementação 1 - armazena o usuário no CLIENTE
			/*Cookie cookie = new Cookie("usuario.logado", email);	// Criando um cookie com o nome "usuario.logado" e atribuindo o valor da variável email
			cookie.setMaxAge(10 * 60); // Tempo de vida do cookie, deve ser em segundos, no caso, este expira em 10 minutos após a sua criação
			resp.addCookie(cookie);
			writer.println("<html><body>Usuário logado: " + email);*/
			
			// Cookie Implementação 2 - armazena o usuário no SERVIDOR - tornando o Cookie mais Seguro
			// Um mapa que armazena um código maluco para o email do usuário - Mapa dos usuários logados
			// Esse código, deve ficar fora do método, numa variável estática para que todos possam acessar os "usuariosLogados"
			
			//Map<String, String> usuariosLogados = new HashMap();	// Coloca no HashMap toda vez que um usuário estiver logado, um código aleatório para aquele usuário
			/*String codigoAleatorio = "" + System.currentTimeMillis() + "/" + Math.random(); // System.currentTimeMillis() - pega o instante atual // Math.random() - gera um double aleatório entre 0 e 1
			USUARIOS_LOGADOS.put(codigoAleatorio, usuario); // método put(key, value) do HashMap atribui uma chave para o valor, no caso, atribui o código maluco para o usuário que se logou
			
			Cookie cookie = new Cookie("usuario.logado", codigoAleatorio); // usuario que tem o cookie "usuario.logado" atribui o valor do "codigoAleatorio"
			cookie.setMaxAge(10*60);
			resp.addCookie(cookie);
			writer.println("<html><body>Usuário logado " + email + "</body></html>");*/
		}
	}
}
