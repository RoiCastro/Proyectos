/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado.generator;


import aforcado.ui.GenerateWordException;
import java.util.ArrayList;
import java.util.Random;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Roi
 */
public class DBWordGenerator implements WordGenerator {

    /**
     * Devuelve una palabra aleatoria desde la base de datos.
     */
    @Override
    public String generateWord() throws GenerateWordException {
        //ifDatabaseNotExist();
        ArrayList<String> words = new ArrayList<>();
        String sql = "SELECT word FROM words";

        try (Connection c = DriverManager.getConnection(DBInitializer.URL);
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                words.add(rs.getString("word"));
            }

        } catch (SQLException e) {
            throw new GenerateWordException("Error al acceder a la base de datos: " + e.getMessage(),true);

        }

        if (words.isEmpty()) {
            throw new GenerateWordException("No hay palabras disponibles en la base de datos.", true);
        }

        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }
}
