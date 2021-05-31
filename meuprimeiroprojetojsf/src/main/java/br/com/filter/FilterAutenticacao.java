package br.com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.entidades.Pessoa;
import br.com.jpautil.JPAUtil;

@WebFilter(urlPatterns = { "/*" })
public class FilterAutenticacao implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		/**
		 * Primeiro temos que pegar os dados da requisição. Aqui, atribuimos para a
		 * variável req
		 **/
		HttpServletRequest req = (HttpServletRequest) request;

		/** Pegamos a sessão da requisição que é única e atribuimos para a session **/
		HttpSession session = req.getSession();

		/**
		 * Dentro da sessão, carregamos os atributos do usuárioLogado no objeto
		 * usuarioLogado (Pessoa)
		 **/
		Pessoa usuarioLogado = (Pessoa) session.getAttribute("usuarioLogado");

		/** Pegamos o endereço da url para saber o que o user ta acessando **/
		String url = req.getServletPath();

		/**
		 * Se a URL for diferente da página de login e o usuario não tiver logado ele pega o requestDispatcher e manda para o index.jsf, 
		 * até que o usuario esteja logado.
		 * Caso esteja logado, ele redireciona para o chain e executa o request e o response
		 **/
		if (!url.equalsIgnoreCase("index.jsf") && usuarioLogado == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsf");
			dispatcher.forward(request, response);
			return;
		} else {

			/***
			 * Tem que chamar o chain, pois todo request e response deve passar pelo filter
			 *  --- CHAIN SÓ É CHAMADO SE ENTRAR NO ELSE (SE O USER ESTIVER LOGADO) ---
			 ***/
			chain.doFilter(request, response);

		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		JPAUtil.getEntityManager();
	}

}
