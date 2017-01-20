package basket;

import model.Equipo;
import model.Jugador;
import persistence.BasketJDBC;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BasketJDBC gestor = new BasketJDBC();
        try {
            System.out.println("                      __------__\n" +
                    "                    /~          ~\\\n" +
                    "                   |    //^\\//^\\|         Oh..My great god ...     \n" +
                    "                 /~~\\  ||  o| |o|:~\\       Please grant me many many\n" +
                    "                | |6   ||___|_|_||:|    /  bananas .. I want to give them\n" +
                    "                 \\__.  /      o  \\/'       to my dear Mary, then she will\n" +
                    "                  |   (       O   )        agree to marry me!!\n" +
                    "         /~~~~\\    `\\  \\         /\n" +
                    "        | |~~\\ |     )  ~------~`\\\n" +
                    "       /' |  | |   /     ____ /~~~)\\\n" +
                    "      (_/'   | | |     /'    |    ( |\n" +
                    "             | | |     \\    /   __)/ \\\n" +
                    "             \\  \\ \\      \\/    /' \\   `\\\n" +
                    "               \\  \\|\\        /   | |\\___|\n" +
                    "                 \\ |  \\____/     | |\n" +
                    "                 /^~>  \\        _/ <\n" +
                    "                |  |         \\       \\\n" +
                    "                |  | \\        \\        \\\n" +
                    "                -^-\\  \\       |        )\n" +
                    "                     `\\_______/^\\______/");
            System.out.println("Estableciendo conexión con la bbdd...");
            gestor.conectar();
            System.out.println("Conexión establecida.");
            Equipo e1 = new Equipo("Equipo 1", "Barcelona", LocalDate.of(1980,10,10));
            Equipo e2 = new Equipo("Equipo 2", "Bilbao", LocalDate.of(1970,10,10));
            Equipo e3 = new Equipo("Equipo 3", "Madrid", LocalDate.of(1960,10,10));
            Jugador j1 = new Jugador("Sergio", LocalDate.of(1996,8,8), 100, 100, 100, "base", e1);
            Jugador j2 = new Jugador("Fernando", LocalDate.of(1996,8,8), 200, 200, 200, "base", e1);
            //gestor.insertTeam(e1);
            //gestor.insertPlayer(j1);
            //gestor.insertPlayer(j2);
            j1.setNumRebotes(234); j1.setNumAsistencias(234); j1.setNumCanastas(234);
            gestor.modificarPuntosPlayer(j1);
            gestor.borrarPlayer(j2);
            gestor.desconectar();
        }catch (SQLException ex){
            System.out.println("Error con la BBDD: "+ex.getMessage());
        }
    }
}