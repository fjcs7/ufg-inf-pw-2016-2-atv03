package jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/index")
public class CadastroDeAlunoController extends HttpServlet {

	  @Override
	  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
	    String op = req.getParameter("op");
	    op = (op == null ? "Iniciar" : op);
	    
	    Aluno aluno = new Aluno();
	    aluno.setMatricula(req.getParameter("codigo"));
	    aluno.setNome(req.getParameter("nome"));
	    aluno.setMatricula(req.getParameter("matricula"));

	    List<Aluno> alunos = null;
	    try {
	      if (op.equals("Salvar")) {
	        CadastroDeAlunoModel.salvar(aluno);
	      } else if (op.equals("Excluir")) {
	        CadastroDeAlunoModel.excluir(aluno);
	      }

	      alunos = CadastroDeAlunoModel.listar();

	    } catch (SQLException e) {
	      throw new RuntimeException(e);
	    }
	    
	    req.setAttribute("alunos", alunos);

	    //Chamando o JSP.
	    String nextJsp = "/cadastro-aluno.jsp";
	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJsp);
	    dispatcher.forward(req, resp);

	  }
	
}
