package persistence;

import model.Equipo;
import model.Jugador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketJDBC {

    private Connection con;

    public List<Jugador> selectAllPlayers() throws SQLException{
        List <Jugador> jugadores = new ArrayList<>();
        String query = "select * from player";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()){
            Jugador j = new Jugador();
            j.setNombre(rs.getString("name"));
            j.setFechan(rs.getDate("birth").toLocalDate());
            j.setNumCanastas(rs.getInt("nbaskets"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            j.setEquipo(new Equipo(rs.getString("team")));
        }
        rs.close();
        st.close();
        return jugadores;
    }

    public void insertTeam(Equipo e) throws SQLException{
        String insert = "insert into team values (?,?,?);";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, e.getNombre());
        ps.setString(2, e.getLocalidad());
        ps.setDate(3, java.sql.Date.valueOf(e.getFechac()));
        ps.executeUpdate();
        ps.close();
    }

    public void insertPlayer(Jugador j) throws SQLException{
        String insert = "insert into player values (?,?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(insert);
        ps.setString(1, j.getNombre());
        ps.setDate(2, java.sql.Date.valueOf(j.getFechan()));
        ps.setInt(3, j.getNumCanastas());
        ps.setInt(4, j.getNumAsistencias());
        ps.setInt(5, j.getNumRebotes());
        ps.setString(6, j.getPosicion());
        ps.setString(7, j.getEquipo().getNombre());
        ps.executeUpdate();
        ps.close();
    }

    public void modificarPuntosPlayer(Jugador j) throws SQLException {
        String update = "update player set nassists=?, nbaskets=?, nrebounds=? where name=?";
        PreparedStatement ps = con.prepareStatement(update);
        ps.setInt(1, j.getNumAsistencias());
        ps.setInt(2, j.getNumCanastas());
        ps.setInt(3, j.getNumRebotes());
        ps.setString(4, j.getNombre());
        ps.executeUpdate();
        ps.close();
    }

    public void modificarTeamPlayer(Jugador j) throws SQLException {
        String update = "update player set team=? where name=?";
        PreparedStatement ps = con.prepareStatement(update);
        ps.setString(1, j.getEquipo().getNombre());
        ps.setString(2, j.getNombre());
        ps.executeUpdate();
        ps.close();
    }

    public void borrarPlayer(Jugador j) throws SQLException {
        String delete = "delete from player where name=?";
        PreparedStatement ps = con.prepareStatement(delete);
        ps.setString(1, j.getNombre());
        ps.executeUpdate();
        ps.close();
    }



    public void conectar() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        con = DriverManager.getConnection(url, usr, pass);
    }

    public void desconectar() throws SQLException{
        if (con !=null){
            con.close();
        }
    }

}
