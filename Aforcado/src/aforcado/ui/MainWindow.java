/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package aforcado.ui;

import aforcado.generator.DBWordGenerator;
import aforcado.generator.WordGenerator;
import aforcado.generator.GUIKeyboardWordGenerator;
import aforcado.generator.ArrayWordGenerator;
import aforcado.generator.DBInitializer;
import aforcado.model.HangMan;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author roi.castrocalvar
 */
public class MainWindow extends javax.swing.JFrame {

    private HangMan hangman;
    private JLabel[] monicreque;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        monicreque = new JLabel[]{Hangman0, Hangman1, Hangman2, Hangman3, Hangman4, Hangman5, Hangman6};
        DBInitializer.init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Aforcado = new javax.swing.JPanel();
        Hangman0 = new javax.swing.JLabel();
        Hangman1 = new javax.swing.JLabel();
        Hangman2 = new javax.swing.JLabel();
        Hangman3 = new javax.swing.JLabel();
        Hangman4 = new javax.swing.JLabel();
        Hangman5 = new javax.swing.JLabel();
        Hangman6 = new javax.swing.JLabel();
        MenuJuego = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButtonProbar = new javax.swing.JButton();
        jLabelFallos = new javax.swing.JLabel();
        jLabelAdivinar = new javax.swing.JLabel();
        Titulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        jButtonSair = new javax.swing.JButton();
        jButtonNovaPartida = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Aforcado.setLayout(new java.awt.CardLayout());

        Hangman0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Hangman-0.png"))); // NOI18N
        Aforcado.add(Hangman0, "card8");

        Hangman1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Hangman-1.png"))); // NOI18N
        Aforcado.add(Hangman1, "card2");

        Hangman2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Hangman-2.png"))); // NOI18N
        Aforcado.add(Hangman2, "card3");

        Hangman3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Hangman-3.png"))); // NOI18N
        Aforcado.add(Hangman3, "card4");

        Hangman4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Hangman-4.png"))); // NOI18N
        Aforcado.add(Hangman4, "card5");

        Hangman5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Hangman-5.png"))); // NOI18N
        Aforcado.add(Hangman5, "card6");

        Hangman6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Hangman-6.png"))); // NOI18N
        Aforcado.add(Hangman6, "card7");

        MenuJuego.setMinimumSize(new java.awt.Dimension(100, 100));
        MenuJuego.setName(""); // NOI18N

        jLabel2.setText("Palabra a adiviñar:");

        jLabel3.setText("Letras falladas:");

        jLabel4.setText("Introduce unha letra:");

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jButtonProbar.setText("Probar");
        jButtonProbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProbarActionPerformed(evt);
            }
        });

        jLabelFallos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabelAdivinar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout MenuJuegoLayout = new javax.swing.GroupLayout(MenuJuego);
        MenuJuego.setLayout(MenuJuegoLayout);
        MenuJuegoLayout.setHorizontalGroup(
            MenuJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuJuegoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(MenuJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MenuJuegoLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(18, 18, 18))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuJuegoLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(40, 40, 40)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuJuegoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(57, 57, 57)))
                .addGroup(MenuJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelFallos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MenuJuegoLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonProbar))
                    .addComponent(jLabelAdivinar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        MenuJuegoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonProbar, jTextField1});

        MenuJuegoLayout.setVerticalGroup(
            MenuJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuJuegoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelAdivinar))
                .addGap(18, 18, 18)
                .addGroup(MenuJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabelFallos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(MenuJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButtonProbar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MenuJuegoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonProbar, jLabel2, jLabel3, jLabel4, jLabelAdivinar, jLabelFallos, jTextField1});

        jLabelFallos.getAccessibleContext().setAccessibleName("jLabel6");

        jLabelTitulo.setFont(new java.awt.Font("OpenDyslexic", 0, 18)); // NOI18N
        jLabelTitulo.setText("O XOGO DO AFORCADO");

        javax.swing.GroupLayout TituloLayout = new javax.swing.GroupLayout(Titulo);
        Titulo.setLayout(TituloLayout);
        TituloLayout.setHorizontalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TituloLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        TituloLayout.setVerticalGroup(
            TituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonSair.setText("Saír");
        jButtonSair.setToolTipText("");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jButtonNovaPartida.setText("Nova partida");
        jButtonNovaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaPartidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButtonNovaPartida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSair)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNovaPartida)
                    .addComponent(jButtonSair))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MenuJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                        .addComponent(Aforcado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MenuJuego, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Aforcado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonProbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProbarActionPerformed
        tryChar();
        showGameStatus();
    }//GEN-LAST:event_jButtonProbarActionPerformed

    private void jButtonNovaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaPartidaActionPerformed

        startNewGame();
        jButtonProbar.setEnabled(true);
        jLabelFallos.setText("");
    }//GEN-LAST:event_jButtonNovaPartidaActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        //Para la ejecucion
        System.exit(0);
    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        //Cada vez que obtenga el focus borra el contenido
        jTextField1.setText("");
    }//GEN-LAST:event_jTextField1FocusGained

    private void startNewGame() {

        // Opciones del ComboBox
        String[] opciones = {
            "Palabra Aleatoria de array",
            "Palabra escrita por usuario",
            "Palabra de la base de datos",
            "Opción 4"};

        // Mostrar el cuadro de diálogo con ComboBox
        int index = JOptionPane.showOptionDialog(
                null, // Componente padre
                "Selecciona una opción:", // Mensaje
                "Diálogo con ComboBox", // Título
                JOptionPane.DEFAULT_OPTION, // Tipo de opciones (por defecto)
                JOptionPane.QUESTION_MESSAGE, // Tipo de mensaje
                null, // Icono
                opciones, // Opciones
                opciones[0] // Opción seleccionada por defecto
        );
        //Si cierra va a la por defecto
        if (index == JOptionPane.CLOSED_OPTION) {
            index = 0;
        }
        for (JLabel jLabel : monicreque) {
            jLabel.setVisible(false);
        }
        try {
            WordGenerator wordAleatory;
            switch (index) {
                case 0 -> {
                    wordAleatory = new ArrayWordGenerator();
                    break;
                }
                case 1 -> {
                    wordAleatory = new GUIKeyboardWordGenerator();
                    break;
                }
                case 2 -> {
                    wordAleatory = new DBWordGenerator();
                    break;
                }
                default ->
                    throw new AssertionError();
            }
            String palabra = wordAleatory.generateWord();
            hangman = new HangMan(palabra);
            jLabelAdivinar.setText(hangman.showHiddenWord());
        } catch (GenerateWordException e) {

        }
    }

    private void showGameStatus() {
        jLabelAdivinar.setText(hangman.showHiddenWord());
        jLabelFallos.setText(hangman.getStringFails());
        if (hangman.isGameOver()) {
            JOptionPane.showMessageDialog(this, "Gañaches parabens", "Fin do xogo", JOptionPane.INFORMATION_MESSAGE);
            jButtonProbar.setEnabled(false);
        }
        if (hangman.maxFailsExceeded()) {
            JOptionPane.showMessageDialog(this, "Fin do xogo. A palabra oculta era " + hangman.showFullWord(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
            jButtonProbar.setEnabled(false);
        }
    }

    private void tryChar() {
        // Obter a letra do campo de texto e converter a minúsculas
        String text = jTextField1.getText(); // Se puede usar trim() para eliminar espazos en branco 
        if (text.isEmpty()) {
            // Se o campo está vacío, non facemos nada ou mostramos un mensaxe de erro
            JOptionPane.showMessageDialog(null, "Por favor, ingresa unha letra válida.");
            return;  // Saímos do método sen continuar
        }

        char letter = text.charAt(0);  // Tomamos a primeira letra
        hangman.tryChar(letter);       // Intentamos a letra no xogo

        // Mostramos o monicreque correspondente ao número de erros
        monicreque[hangman.getFails().size()].setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Aforcado;
    private javax.swing.JLabel Hangman0;
    private javax.swing.JLabel Hangman1;
    private javax.swing.JLabel Hangman2;
    private javax.swing.JLabel Hangman3;
    private javax.swing.JLabel Hangman4;
    private javax.swing.JLabel Hangman5;
    private javax.swing.JLabel Hangman6;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel MenuJuego;
    private javax.swing.JPanel Titulo;
    private javax.swing.JButton jButtonNovaPartida;
    private javax.swing.JButton jButtonProbar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAdivinar;
    private javax.swing.JLabel jLabelFallos;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
