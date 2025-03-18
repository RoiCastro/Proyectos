/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import model.Game;

/**
 *
 * @author aitor.martinezparente
 */
public class MainWindowA extends javax.swing.JFrame {

    private Timer timer;
    private Timer obstacleTimer; //timer para engadir filas ao final
    private int currentInterval = 1000; // Intervalo inicial de 1 segundo
    private static final int LINES_TO_SPEED_UP = 10; // Liñas necesarias para reducir á metade o intervalo
    private static final int MIN_INTERVAL = 100; // Intervalo mínimo permitido para evitar valores demasiado pequenos

    private static final int LEVEL_THRESHOLD = 5; // Nivel a partir do cal se engaden obstáculos
    private static final int OBSTACLE_INTERVAL = 5000; // Intervalo en milisegundos para engadir obstáculos (5 segundos)

    private Game game = null; // Referenza ao obxecto do xogo actual

    /**
     * Creates new form MainWindowA
     */
    public MainWindowA() {
        initComponents();

        // Configurar KeyEventDispatcher para capturar teclas globalmente
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyDispatcher());
    }

    /**
     * Pinta un cadrado no panel de cadrados
     *
     * @param lblSquare Etiqueta co cadrado que se quere pintar no panel
     */
    public void drawSquare(JLabel lblSquare) {
        pnlGame.add(lblSquare);
        pnlGame.repaint();
    }

    /**
     * Borra un cadrado do panel de cadrados
     *
     * @param lblSquare Etiqueta co cadrado que se quere borrar do panel
     */
    public void deleteSquare(JLabel lblSquare) {
        pnlGame.remove(lblSquare);
        pnlGame.repaint();
    }

    /**
     * Inicia un novo xogo
     */
    private void startGame() {
        // Limpamos todo o que puidese haber pintado no panel do xogo
        pnlGame.removeAll();
        // Creamos un novo obxecto xogo
        game = new Game(this);
        // Desactivamos o botón de pausa
        tglbtnPause.setSelected(false);
        // Establecemos o número de liñas a cero
        lblNumberOfLines.setText("0");

        // Restauramos o intervalo inicial ao comezar unha nova partida
        currentInterval = 1000;

        // Inicializamos o temporizador
        startTimer();

        //subir filas enbaixo para dificultar a partida
        if (game.getLevel() >= LEVEL_THRESHOLD) {
            startObstacleTimer();
        }
    }

    /**
     * Inicia ou reinicia o temporizador
     */
    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(currentInterval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game != null && !tglbtnPause.isSelected()) {
                    game.movePieceDown();
                }
            }
        });
        timer.start();
    }

    private void startObstacleTimer() {
        if (obstacleTimer != null) {
            obstacleTimer.stop();
        }

        obstacleTimer = new Timer(OBSTACLE_INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game != null && !tglbtnPause.isSelected() && game.getLevel() >= LEVEL_THRESHOLD) {
                    game.addObstacleRow();
                }
            }
        });
        obstacleTimer.start();
    }

    /**
     * Diminúe á metade o intervalo do timer cando se fan múltiplos de
     * LINES_TO_SPEED_UP
     *
     * @param numberOfLines Número de liñas feitas no xogo
     */
    public void showNumberOfLines(int numberOfLines) {
        lblNumberOfLines.setText(String.valueOf(numberOfLines));

        if (numberOfLines % LINES_TO_SPEED_UP == 0) {
            currentInterval = Math.max(MIN_INTERVAL, currentInterval / 2);
            startTimer();
        }

        if (game.getLevel() >= LEVEL_THRESHOLD) {
            startObstacleTimer();
        }
    }

    /**
     * Captura os eventos do teclado globalmente
     */
    class KeyDispatcher implements KeyEventDispatcher {
        // Clase interna para capturar eventos de teclado globalmente

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (game != null && !tglbtnPause.isSelected() && e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.movePieceLeft();
                        return true;
                    case KeyEvent.VK_RIGHT:
                        game.movePieceRight();
                        return true;
                    case KeyEvent.VK_DOWN:
                        game.movePieceDown();
                        return true;
                    case KeyEvent.VK_UP:
                        game.rotatePiece();
                        return true;
                    case KeyEvent.VK_SPACE:
                        game.hardDropPiece();
                        break;
                }
            }
            return false;
        }
    }

    /**
     * Mostra unha mensaxe informando o final do xogo e resetea o timer
     */
    public void showGameOver() {
        if (timer != null) {
            timer.stop();
        }
        if (obstacleTimer != null) {
            obstacleTimer.stop();
        }
        game = null;
        JOptionPane.showMessageDialog(this, "Fin do xogo, fixeche " + lblNumberOfLines.getText() + " linea(s)");
    }

    /**
     * Actualiza a interface para mostrar o nivel actual do xogo.
     *
     * @param level O nivel actual do xogo
     */
    public void showLevel(int level) {
        lblLevelNumber.setText("Nivel: " + level);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlGame = new javax.swing.JPanel();
        aitor = new javax.swing.JLabel();
        roi = new javax.swing.JLabel();
        btnNewGame = new javax.swing.JButton();
        tglbtnPause = new javax.swing.JToggleButton();
        lblLines = new javax.swing.JLabel();
        lblNumberOfLines = new javax.swing.JLabel();
        lblControls = new javax.swing.JLabel();
        lblLevelNumber = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        pnlGame.setPreferredSize(new java.awt.Dimension(400, 460));

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        aitor.setForeground(new java.awt.Color(0, 0, 0));
        aitor.setText("Aitor");

        roi.setForeground(new java.awt.Color(0, 0, 0));
        roi.setText("Roi");

        btnNewGame.setText("Nueva Partida");
        btnNewGame.setFocusable(false);
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });

        tglbtnPause.setText("Pausa");
        tglbtnPause.setFocusable(false);
        tglbtnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglbtnPauseActionPerformed(evt);
            }
        });

        lblLines.setForeground(new java.awt.Color(0, 0, 0));
        lblLines.setText("Lineas:");

        lblControls.setForeground(new java.awt.Color(0, 0, 0));
        lblControls.setText("<html><strong>OBJETIVO:</strong><br>Usa las flechas de movimiento<br>para moverte por el panel<br>de juego, completa lineas <br>para sumar mas puntos.<br><br> <strong>CONTROLES:</strong> <br>La flecha superior<br>sirve para rotar la pieza.<br>La barra espaciadora<br> sirve para hacer \"HardDrop\"<br>(bajar rapido).<br><br><strong>Diviertete y se el mejor!!!</strong></html>");
        lblControls.setToolTipText("");
        lblControls.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblControls.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("<html>Si encuentra algun bug<br>o cualquier tipo de error,<br>por favor, no lo notifique.<br><br>Muchas Gracias.</html>");
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aviso!", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnNewGame, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(tglbtnPause)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblControls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(aitor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(roi))
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLines)
                        .addGap(18, 18, 18)
                        .addComponent(lblNumberOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblLevelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLines)
                            .addComponent(lblNumberOfLines, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLevelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNewGame)
                        .addGap(29, 29, 29)
                        .addComponent(lblControls, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tglbtnPause)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(aitor)
                            .addComponent(roi)))
                    .addComponent(pnlGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
        startGame();
    }//GEN-LAST:event_btnNewGameActionPerformed

    private void tglbtnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglbtnPauseActionPerformed
        if (game != null) {
            game.setPaused(tglbtnPause.isSelected());
        }
    }//GEN-LAST:event_tglbtnPauseActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindowA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindowA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindowA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindowA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindowA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aitor;
    private javax.swing.JButton btnNewGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblControls;
    private javax.swing.JLabel lblLevelNumber;
    private javax.swing.JLabel lblLines;
    private javax.swing.JLabel lblNumberOfLines;
    private javax.swing.JPanel pnlGame;
    private javax.swing.JLabel roi;
    private javax.swing.JToggleButton tglbtnPause;
    // End of variables declaration//GEN-END:variables
}
