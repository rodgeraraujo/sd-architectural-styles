package nf.co.rogerioaraujo.questao_02.node2.dao;

import nf.co.rogerioaraujo.questao_02.node2.database.DatabaseHelper;
import nf.co.rogerioaraujo.questao_02.node2.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao {

    public void inserePostgres(Usuario usuario) throws SQLException {
        Connection con = null;
        try{
            con = DatabaseHelper.getPostgres();
            String sql = "INSERT INTO usuario (codigo,nome) VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, usuario.getCodigo());
            statement.setString(2, usuario.getNome());
            statement.executeUpdate();
        }finally {
            if(con != null)
                con.close();
        }
    }

    public void insereMySql(Usuario usuario) throws SQLException {
        Connection con = null;
        try{
            con = DatabaseHelper.getMySql();
            String sql = "INSERT INTO usuario (codigo,nome) VALUES (?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, usuario.getCodigo());
            statement.setString(2, usuario.getNome());
            statement.executeUpdate();
        }finally {
            if(con != null)
                con.close();
        }
    }
}
