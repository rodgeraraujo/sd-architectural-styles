package nf.co.rogerioaraujo.questao_02.node2;

import nf.co.rogerioaraujo.questao_02.node2.dao.UsuarioDao;
import nf.co.rogerioaraujo.questao_02.node2.domain.Usuario;
import nf.co.rogerioaraujo.questao_02.node2.request.Conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Loader {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 9898));

        // Utilizando thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        while (true) {
            Socket socket = server.accept();
            executorService.execute(new Conexao(socket, usuarioDao));
        }

        // Sem utilizar thread
//        while(true){
//            Socket socket = server.accept();
//
//            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//            Usuario usuario = (Usuario) in.readObject();
//
//            try {
//                usuarioDao.inserePostgres(usuario);
//                usuarioDao.insereMySql(usuario);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
//            socket.close();
//        }


    }
}
