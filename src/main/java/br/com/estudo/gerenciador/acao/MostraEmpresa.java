package br.com.estudo.gerenciador.acao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.estudo.gerenciador.modelo.Banco;
import br.com.estudo.gerenciador.modelo.Empresa;

public class MostraEmpresa implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("mostrando dados da empresa");

		String paramId = request.getParameter("id");
		Integer id = Integer.valueOf(paramId);

		Banco banco = new Banco();

		Empresa empresa = banco.buscaEmpresaPelaId(id);

		System.out.println(empresa.getNome());

		request.setAttribute("empresa", empresa);

		// RequestDispatcher rd =
		// request.getRequestDispatcher("/formAlteraEmpresa.jsp");
		// rd.forward(request, response);

		return "forward:formAlteraEmpresa.jsp";
	}

}
