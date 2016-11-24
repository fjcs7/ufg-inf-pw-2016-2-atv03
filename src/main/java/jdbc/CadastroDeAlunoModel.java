package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CadastroDeAlunoModel {
	  
	private static Connection obterConexao() throws SQLException {
		  return Conexao.obterConexao();
	  }

	  public static void salvar(Aluno aluno) throws SQLException {
	    Connection conn = obterConexao();
	    if (aluno.hasCodigo()) {
	      alterar(conn, aluno);
	    } else {
	      inserir(conn, aluno);
	    }
	  }


	  public static void excluir(Aluno aluno) throws SQLException {
	    Connection conn = obterConexao();
	    
	    //Obter sentença SQL.
	    String sql = "delete from aluno where matricula = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, aluno.getMatricula());
	    pstmt.execute();
	  }

	  public static List<Aluno> listar() throws SQLException {
	    Connection conn = obterConexao();
	    
	    Statement stmt = conn.createStatement();
	    String sql = "select matricula, nome from aluno order by matricula";
	    ResultSet rs = stmt.executeQuery(sql);

	    List<Aluno> listaDeAluno = new ArrayList<Aluno>();
	    while (rs.next()) {
	      // Cria um usuário para o registro.
	      Aluno usuario = new Aluno();
	      usuario.setMatricula(rs.getString("matricula"));
	      usuario.setNome(rs.getString("nome"));
	      // Adiciona o usuário na lista de usuários.
	      listaDeAluno.add(usuario);
	    }
	    return listaDeAluno;
	  }

	  private static void inserir(Connection conn, Aluno aluno) throws SQLException {
	    //Obter sentença SQL.
	    String sql = "insert into usuario (nome, curso) values (?, ?)";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, aluno.getNome());
	    pstmt.setString(2, aluno.getMatricula());
	    pstmt.execute();
	  }

	  private static void alterar(Connection conn, Aluno aluno) throws SQLException {
	    //Obter sentença SQL.
	    String sql = "update aluno set nome = ?, curso = ? where matricula = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, aluno.getNome());
	    pstmt.setString(2, aluno.getCurso());
	    pstmt.setString(3, aluno.getMatricula());
	    pstmt.execute();
	  }
}
