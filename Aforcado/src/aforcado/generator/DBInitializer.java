/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado.generator;

import java.sql.*;

public class DBInitializer {

    private static final String URL = "jdbc:sqlite:words.db";

    public static void init() {
        try (Connection c = DriverManager.getConnection(URL);
             Statement st = c.createStatement()) {

            String crearTabla = "CREATE TABLE IF NOT EXISTS words (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "word TEXT NOT NULL UNIQUE)";
            st.execute(crearTabla);

            String checkTableEmpty = "SELECT COUNT(*) FROM words";
            try (ResultSet rs = st.executeQuery(checkTableEmpty)) {
                if (rs.next() && rs.getInt(1) == 0) {
                    insertInitialWords(c);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error inicializando la base de datos: " + e.getMessage());
        }
    }

    private static void insertInitialWords(Connection c) throws SQLException {
        String[] words = {
                "manzana", "banana", "naranja", "uva", "perro",
                "gato", "elefante", "jirafa", "león", "tigre",
                "delfín", "ballena", "tortuga", "serpiente", "águila"
        };

        String insertQuery = "INSERT OR IGNORE INTO words (word) VALUES (?)";

        try (PreparedStatement pstmt = c.prepareStatement(insertQuery)) {
            for (String word : words) {
                pstmt.setString(1, word);
                pstmt.executeUpdate();
            }
        }
    }
}

