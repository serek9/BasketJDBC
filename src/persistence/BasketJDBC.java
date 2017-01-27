package persistence;

import model.Equipo;
import model.Jugador;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BasketJDBC {

    private Connection con;


    public ArrayList<Jugador> selectPlayers(ResultSet rs) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        while(rs.next()){
            Jugador j = new Jugador();
            j.setNombre(rs.getString("name"));
            j.setFechan(rs.getDate("birth").toLocalDate());
            j.setNumCanastas(rs.getInt("nbaskets"));
            j.setNumAsistencias(rs.getInt("nassists"));
            j.setNumRebotes(rs.getInt("nrebounds"));
            j.setPosicion(rs.getString("position"));
            j.setEquipo(new Equipo(rs.getString("team")));
            players.add(j);
        }
        rs.close();
        return players;
    }

    public ArrayList<Equipo> selectTeams(ResultSet rs) throws SQLException{
        ArrayList<Equipo> teams = new ArrayList<>();
        while(rs.next()){
            Equipo e = new Equipo();
            e.setNombre(rs.getString("name"));
            e.setFechac(rs.getDate("creation").toLocalDate());
            e.setLocalidad(rs.getString("city"));
            teams.add(e);
        }
        rs.close();
        return teams;
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

    public Jugador obtenerPlayerPorNombre(String nombre) throws SQLException{
        String select = "select * from player where name=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();

        Jugador jugador = new Jugador();
        while(rs.next()){
            jugador.setNombre(rs.getString("name"));
            jugador.setFechan(rs.getDate("birth").toLocalDate());
            jugador.setNumCanastas(rs.getInt("nbaskets"));
            jugador.setNumAsistencias(rs.getInt("nassists"));
            jugador.setNumRebotes(rs.getInt("nrebounds"));
            jugador.setPosicion(rs.getString("position"));
            Equipo equipo = new Equipo(rs.getString("team"));
            jugador.setEquipo(equipo);
        }
        rs.close();
        ps.close();
        return jugador;
    }

    public ArrayList<Jugador> obtenerPlayersPorNombre(String nombre) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player where name like ?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, "%"+nombre+"%");
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
    }

    public ArrayList<Jugador> obtenerPlayersNumCanastasMayorIgual(int numCanastas) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player where nbaskets >= ?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, numCanastas);
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
    }

    public ArrayList<Jugador> obtenerPlayersNumAsistenciasEntre(int num1, int num2) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player where nassists between ? and ?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setInt(1, num1);
        ps.setInt(2, num2);
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
    }

    public ArrayList<Jugador> obtenerPlayersPosicion(String posicion) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player where position=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, posicion);
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
    }

    public ArrayList<Jugador> obtenerPlayersFechan(LocalDate date) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player where birth<=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setDate(1, java.sql.Date.valueOf(date));
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
    }

    public ArrayList<Equipo> obtenerTeamsLocalidad(String localidad) throws SQLException{
        ArrayList<Equipo> teams = new ArrayList<>();
        String select = "select * from team where city=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, localidad);
        ResultSet rs = ps.executeQuery();
        teams = selectTeams(rs);
        rs.close();
        ps.close();
        return teams;
    }

    public ArrayList<Jugador> obtenerPlayersTeam(String name) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player where team=?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
    }

    public ArrayList<Jugador> obtenerPlayersPosicionTeam(String name, String posicion) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player where position = ? and team = ?";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, posicion);
        ps.setString(2, name);
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
    }

    public ArrayList<Jugador> obtenerPlayerMaxCanastasTeam(String name) throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player where team = ? and nbaskets=(select max(nbaskets) from player where team = ?)";
        PreparedStatement ps = con.prepareStatement(select);
        ps.setString(1, name);
        ps.setString(2,name);
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
    }

    public ArrayList<Jugador> obtenerRankingCanastas() throws SQLException{
        ArrayList<Jugador> players = new ArrayList<>();
        String select = "select * from player order by nbaskets desc";
        PreparedStatement ps = con.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        players = selectPlayers(rs);
        rs.close();
        ps.close();
        return players;
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



//        DriverManager	Para cargar un driver
//        Connection	Para establecer conexiones con las bases de datos
//        Statement	Para ejecutar sentencias SQL y enviarlas a las BBDD
//        PreparedStatement	La ruta de ejecución está predeterminada en el servidor de base de datos que le permite ser ejecutado varias veces
//        ResultSet	Para almacenar el resultado de la consulta