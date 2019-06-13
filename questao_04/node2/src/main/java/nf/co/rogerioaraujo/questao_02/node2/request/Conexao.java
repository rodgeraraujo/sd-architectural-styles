package nf.co.rogerioaraujo.questao_02.node2.request;

import nf.co.rogerioaraujo.questao_02.node2.dao.UsuarioDao;
import nf.co.rogerioaraujo.questao_02.node2.domain.Usuario;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;

public class Conexao implements Runnable {


    private final Socket socket;
    private final UsuarioDao usuarioDao;


    public Conexao(Socket accept, UsuarioDao usuarioDao) {
        this.socket = accept;
        this.usuarioDao = usuarioDao;
    }

    public void run() {

        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Usuario post = (Usuario) in.readObject();
            usuarioDao.inserePostgres(post);
            usuarioDao.inserePostgres(post);
            socket.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
