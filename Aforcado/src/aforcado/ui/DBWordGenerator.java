/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado.ui;


import java.util.ArrayList;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Roi
 */
public class DBWordGenerator implements WordGenerator {

    private static final String URL = "jdbc:sqlite:words.db"; // Ubicación de la base de datos. Es estática para poder llamar al método de creación en cualquier momento.

    /**
     * Metodo de creacion de la base de datos SQLITE si no existe ninguna
     * previamente
     */
    public static void ifDatabaseNotExist() {
        try (Connection c = DriverManager.getConnection(URL)) {
            try (Statement st = c.createStatement()) {
                String crearTabla = "CREATE TABLE IF NOT EXISTS words ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "word TEXT NOT NULL"
                        + ")";
                st.execute(crearTabla);
                
                 // Comprobar si la tabla está vacía
                String checkTableEmpty = "SELECT COUNT(*) FROM words";
                try (ResultSet rs = st.executeQuery(checkTableEmpty)) {
                    // Si la tabla está vacía, la primera vez insertamos las palabras
                    if (rs.next() && rs.getInt(1) == 0) {
                        fillDatabaseWithWords();  // Llenamos la tabla con palabras
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("La conexión con la base de datos no se pudo establecer: " + e.getMessage());
        }
    }

    /**
     *
     * @return @throws GenerateWordException
     */
    @Override
    public String generateWord() throws GenerateWordException {

        //Arraylist de palabras
        ArrayList<String> words = new ArrayList();

        //Buscamos en la base de datos todas las palabras disponibles
        String sql = "SELECT word FROM words";

        try (Connection c = DriverManager.getConnection(URL); Statement st = c.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                words.add(rs.getString("word"));
            }

        } catch (Exception e) {
            System.out.println("Error al obtener palabras: " + e.getMessage());
        }

        //Elegimos de manera ramdom una palabra de la base de datos
        Random rand = new Random();
        int number = rand.nextInt(words.size());
        return words.get(number);
    }
    
  // Método privado para engadir palabras á base de datos
    private static void fillDatabaseWithWords() {
        String[] words = {
            "manzana", "banana", "naranja", "uva", "perro", 
            "gato", "elefante", "jirafa", "león", "tigre",
            "delfín", "ballena", "tortuga", "serpiente", "águila"
        };

        try (Connection c = DriverManager.getConnection(URL)) {
            // Inserir as palabras na base de datos
            String insertQuery = "INSERT OR IGNORE INTO words (word) VALUES (?)";
            
            try (PreparedStatement pstmt = c.prepareStatement(insertQuery)) {
                for (String word : words) {
                    pstmt.setString(1, word);  // Asignamos a palabra á sentencia
                    pstmt.executeUpdate();      // Executamos a inserción
                }
                System.out.println("Palabras engadidas correctamente á base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu un erro ao insertar as palabras: " + e.getMessage());
        }
    }
    
}
