package br.com.estudo.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.estudo.gerenciador.acao.Acao;

//@WebServlet(name = "entrada", urlPatterns = { "/entrada" })
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "deprecation", "rawtypes" })
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String paramAcao = request.getParameter("acao");
		String nome;
		try {

			String nomeDaClasse = "br.com.estudo.gerenciador.acao." + paramAcao;

			Class classe = Class.forName(nomeDaClasse);// carrega a classe com o nome
			Acao acao = (Acao) classe.newInstance();
			nome = acao.executa(request, response);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}

		String[] tipoEEndereco = nome.split(":");
		if (tipoEEndereco[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/" + tipoEEndereco[1]);
			rd.forward(request, response);
		} else {
			response.sendRedirect(tipoEEndereco[1]);

		}

	}

}
