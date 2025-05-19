/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tacebook.view;

import tacebook.controller.ProfileController;
import tacebook.model.Profile;

/**
 * Vista gráfica de perfil para la aplicación Tacebook. Permite mostrar y
 * gestionar información del perfil mediante interfaz gráfica.
 *
 * @author Roi
 */
public class GUIProfileView extends javax.swing.JDialog implements ProfileView {

    private ProfileController profileController;

    /**
     * Crea una nueva instancia de GUIProfileView.
     *
     * @param controller el controlador del perfil
     */
    public GUIProfileView(ProfileController controller) {
        initComponents();
        this.profileController = controller;

        // Configurar para que al cerrar esta ventana se abra el menú inicial gráfico
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dispose(); // Cierra la ventana actual
                new tacebook.controller.InitMenuController(false).init();
            }
        });

        // Actualizar comentarios al seleccionar una publicación
        tablaPublicaciones.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                showProfileMenu(profileController.getShownProfile());
            }
        });
    }

    /**
     * Devuelve el número de publicaciones mostradas.
     *
     * @return número de publicaciones mostradas
     */
    @Override
    public int getPostsShowed() {
        // Cuenta las filas no vacías (columna texto no nula)
        int count = 0;
        javax.swing.table.TableModel model = tablaPublicaciones.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Object value = model.getValueAt(i, 2); // columna "Texto"
            if (value != null && !value.toString().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Muestra el menú de perfil para el usuario.
     *
     * @param profile el perfil a mostrar
     */
    @Override
    public void showProfileMenu(Profile profile) {
        if (profile == null) return;

        labelNomeDoPerfil.setText("Perfil do usuario: " + profile.getName());
        labelEstadoActual.setText("Estado actual: " + profile.getStatus());

        // Recopilar publicaciones propias y de amigos
        java.util.List<tacebook.model.Post> allPosts = new java.util.ArrayList<>();
        // Añadir publicaciones propias
        allPosts.addAll(profile.getPosts());
        // Añadir publicaciones de amigos
        java.util.List<tacebook.model.Profile> friends = profile.getFriends();
        for (tacebook.model.Profile friend : friends) {
            allPosts.addAll(friend.getPosts());
        }
        // Ordenar por fecha descendente (más reciente primero)
        allPosts.sort((a, b) -> b.getDate().compareTo(a.getDate()));

        // Rellenar la tabla de publicaciones con los datos recopilados
        javax.swing.table.TableModel model = tablaPublicaciones.getModel();
        int maxRows = model.getRowCount();
        int postsToShow = Math.min(allPosts.size(), maxRows);

        // Limpiar la tabla de publicaciones
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
                model.setValueAt(null, i, j);
            }
        }

        // Rellenar con los datos de las publicaciones si existen
        for (int i = 0; i < postsToShow; i++) {
            tacebook.model.Post post = allPosts.get(i);
            model.setValueAt(post.getDate(), i, 0); // Fecha
            model.setValueAt(post.getAuthor() != null ? post.getAuthor().getName() : "", i, 1); // Autor
            model.setValueAt(post.getText(), i, 2); // Texto
            model.setValueAt(post.getProfileLikes() != null ? post.getProfileLikes().size() : 0, i, 3); // Me gustas
        }

        // Limpiar y rellenar la tabla de comentarios (solo si hay post seleccionado)
        javax.swing.table.TableModel commentModel = tablaComentarios.getModel();
        int commentRows = commentModel.getRowCount();
        for (int i = 0; i < commentRows; i++) {
            for (int j = 0; j < commentModel.getColumnCount(); j++) {
                commentModel.setValueAt(null, i, j);
            }
        }
        int selectedPost = tablaPublicaciones.getSelectedRow();
        if (selectedPost >= 0 && selectedPost < allPosts.size()) {
            java.util.List<tacebook.model.Comment> comments = allPosts.get(selectedPost).getComments();
            int commentsToShow = Math.min(comments.size(), commentRows);
            for (int i = 0; i < commentsToShow; i++) {
                tacebook.model.Comment comment = comments.get(i);
                commentModel.setValueAt(comment.getText(), i, 0);
                commentModel.setValueAt(comment.getSourceProfile() != null ? comment.getSourceProfile().getName() : "", i, 1);
                commentModel.setValueAt(comment.getDate(), i, 2);
            }
        }

        // Rellenar la tabla de amigos
        javax.swing.table.DefaultTableModel amigosModel = (javax.swing.table.DefaultTableModel) tablaListaAmigos.getModel();
        java.util.List<tacebook.model.Profile> friendsList = profile.getFriends();
        // Limpiar tabla
        while (amigosModel.getRowCount() > 0) amigosModel.removeRow(0);
        for (tacebook.model.Profile friend : friendsList) {
            amigosModel.addRow(new Object[]{friend.getName(), friend.getStatus()});
        }

        // Rellenar la lista de solicitudes de amistad
        java.util.List<tacebook.model.Profile> requests = profile.getFriendshipRequests();
        javax.swing.DefaultListModel<String> listModel = new javax.swing.DefaultListModel<>();
        for (tacebook.model.Profile req : requests) {
            listModel.addElement(req.getName());
        }
        listaSolicitudesAmistad.setModel(listModel);
    }

    /**
     * Muestra un mensaje cuando el perfil no se encuentra.
     */
    @Override
    public void showProfileNotFoundMessage() {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Perfil non atopado.",
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje cuando se intenta dar like a la propia publicación.
     */
    @Override
    public void showCannotLikeOwnPostMessage() {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Non podes facer like á túa propia publicación.");
    }

    /**
     * Muestra un mensaje cuando ya se ha dado like a una publicación.
     */
    @Override
    public void showAlreadyLikedPostMessage() {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Xa fixeches like nesta publicación.");
    }

    /**
     * Muestra un mensaje cuando ya existe amistad con el perfil indicado.
     *
     * @param profileName nombre del perfil
     */
    @Override
    public void showIsAlreadyFriendMessage(String profileName) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Xa tes amizade con " + profileName + ".");
    }

    /**
     * Muestra un mensaje cuando ya existe una solicitud de amistad.
     *
     * @param profileName nombre del perfil
     */
    @Override
    public void showExistsFrienshipRequestMessage(String profileName) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Xa existe unha solicitude de amizade con " + profileName + ".");
    }

    /**
     * Muestra un mensaje cuando ya hay una solicitud de amistad pendiente.
     *
     * @param profileName nombre del perfil
     */
    @Override
    public void showDuplicateFrienshipRequestMessage(String profileName) {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Xa tes unha solicitude pendente con " + profileName + ".");
    }

    /**
     * Muestra un mensaje de error de conexión.
     */
    @Override
    public void showConnectionErrorMessage() {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Erro na conexión co almacén de datos!", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de error de lectura de datos.
     */
    @Override
    public void showReadErrorMessage() {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Erro na lectura de datos!", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de error de escritura de datos.
     */
    @Override
    public void showWriteErrorMessage() {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Erro na escritura dos datos!", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje cuando se intenta enviar una solicitud de amistad a uno mismo.
     */
    @Override
    public void showCannotAddSelfAsFriendMessage() {
        javax.swing.JOptionPane.showMessageDialog(this,
                "Non podes enviarte unha solicitude de amizade a ti mesmo.",
                "Erro",
                javax.swing.JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Este método es llamado desde el constructor para inicializar el
     * formulario. ADVERTENCIA: No modificar este código. El contenido de este
     * método siempre es regenerado por el Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cabecera = new javax.swing.JPanel();
        labelNomeDoPerfil = new javax.swing.JLabel();
        labelLogo = new javax.swing.JLabel();
        labelEstadoActual = new javax.swing.JLabel();
        cuerpo = new javax.swing.JTabbedPane();
        biografia = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelPublicaiones = new javax.swing.JPanel();
        labelPublicacions = new javax.swing.JLabel();
        scrollPanelPublicaiones = new javax.swing.JScrollPane();
        tablaPublicaciones = new javax.swing.JTable();
        botonComentar = new javax.swing.JButton();
        botonGustame = new javax.swing.JButton();
        botonVerAnterioresPublicacions = new javax.swing.JButton();
        botonNovaPublicacion = new javax.swing.JButton();
        panelComentario = new javax.swing.JPanel();
        labelComentarios = new javax.swing.JLabel();
        scrollPanelComentarios = new javax.swing.JScrollPane();
        tablaComentarios = new javax.swing.JTable();
        amigos = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        panelListaAmigos = new javax.swing.JPanel();
        labelListaAmigos = new javax.swing.JLabel();
        scrollPanelPublicaiones1 = new javax.swing.JScrollPane();
        tablaListaAmigos = new javax.swing.JTable();
        botonVerBiografia = new javax.swing.JButton();
        botonEnviarMensaxesPrivadas = new javax.swing.JButton();
        panelSolicitudesAmistad = new javax.swing.JPanel();
        labelSolicitudesAmistad = new javax.swing.JLabel();
        scrollPanelAmista = new javax.swing.JScrollPane();
        listaSolicitudesAmistad = new javax.swing.JList<>();
        botonDenegarSolicitud = new javax.swing.JButton();
        botonNuevaSolicitudAmistad = new javax.swing.JButton();
        botonAceptarSolicitud = new javax.swing.JButton();
        menxasesPrivados = new javax.swing.JPanel();
        footer = new javax.swing.JPanel();
        botonCerrarSesion = new javax.swing.JButton();
        botonEditarPerfil = new javax.swing.JButton();

        labelNomeDoPerfil.setText("Perfil do usuario: Roi");

        labelEstadoActual.setText("Estado actual: Feliz");

        javax.swing.GroupLayout cabeceraLayout = new javax.swing.GroupLayout(cabecera);
        cabecera.setLayout(cabeceraLayout);
        cabeceraLayout.setHorizontalGroup(
            cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cabeceraLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(labelNomeDoPerfil)
                .addGap(318, 318, 318)
                .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelEstadoActual)
                .addGap(80, 80, 80))
        );
        cabeceraLayout.setVerticalGroup(
            cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cabeceraLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(cabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelEstadoActual)
                    .addComponent(labelNomeDoPerfil)
                    .addComponent(labelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setOneTouchExpandable(true);

        panelPublicaiones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelPublicacions.setText("10 ultimas publicacións:");

        tablaPublicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha", "Autor", "Texto", "Me gustas"
            }
        ));
        scrollPanelPublicaiones.setViewportView(tablaPublicaciones);
        if (tablaPublicaciones.getColumnModel().getColumnCount() > 0) {
            tablaPublicaciones.getColumnModel().getColumn(2).setHeaderValue("Texto");
            tablaPublicaciones.getColumnModel().getColumn(3).setHeaderValue("Me gustas");
        }

        botonComentar.setText("Comentar");
        botonComentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonComentarActionPerformed(evt);
            }
        });

        botonGustame.setText("Gústame");
        botonGustame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGustameActionPerformed(evt);
            }
        });

        botonVerAnterioresPublicacions.setText("Ver anteriores publicacións");
        botonVerAnterioresPublicacions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerAnterioresPublicacionsActionPerformed(evt);
            }
        });

        botonNovaPublicacion.setText("Nova publicación");
        botonNovaPublicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNovaPublicacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPublicaionesLayout = new javax.swing.GroupLayout(panelPublicaiones);
        panelPublicaiones.setLayout(panelPublicaionesLayout);
        panelPublicaionesLayout.setHorizontalGroup(
            panelPublicaionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPublicaionesLayout.createSequentialGroup()
                .addComponent(labelPublicacions)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(scrollPanelPublicaiones)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPublicaionesLayout.createSequentialGroup()
                .addContainerGap(244, Short.MAX_VALUE)
                .addComponent(botonNovaPublicacion)
                .addGap(18, 18, 18)
                .addComponent(botonComentar)
                .addGap(18, 18, 18)
                .addComponent(botonGustame)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonVerAnterioresPublicacions)
                .addContainerGap(245, Short.MAX_VALUE))
        );
        panelPublicaionesLayout.setVerticalGroup(
            panelPublicaionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPublicaionesLayout.createSequentialGroup()
                .addComponent(labelPublicacions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanelPublicaiones, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelPublicaionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonGustame)
                    .addComponent(botonComentar)
                    .addComponent(botonVerAnterioresPublicacions)
                    .addComponent(botonNovaPublicacion)))
        );

        jSplitPane1.setLeftComponent(panelPublicaiones);

        panelComentario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelComentarios.setText("Comentarios:");

        tablaComentarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Texto", "De", "Fecha"
            }
        ));
        scrollPanelComentarios.setViewportView(tablaComentarios);

        javax.swing.GroupLayout panelComentarioLayout = new javax.swing.GroupLayout(panelComentario);
        panelComentario.setLayout(panelComentarioLayout);
        panelComentarioLayout.setHorizontalGroup(
            panelComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPanelComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
            .addGroup(panelComentarioLayout.createSequentialGroup()
                .addComponent(labelComentarios)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelComentarioLayout.setVerticalGroup(
            panelComentarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelComentarioLayout.createSequentialGroup()
                .addComponent(labelComentarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanelComentarios, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(panelComentario);

        javax.swing.GroupLayout biografiaLayout = new javax.swing.GroupLayout(biografia);
        biografia.setLayout(biografiaLayout);
        biografiaLayout.setHorizontalGroup(
            biografiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        biografiaLayout.setVerticalGroup(
            biografiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        cuerpo.addTab("Biografia", null, biografia, "");

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setOneTouchExpandable(true);

        panelListaAmigos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelListaAmigos.setText("Lista de amig@s:");
        labelListaAmigos.setToolTipText("Aquí se muestra tu lista de amigos");

        tablaListaAmigos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Estado"
            }
        ));
        scrollPanelPublicaiones1.setViewportView(tablaListaAmigos);

        botonVerBiografia.setText("Ver biografía");
        botonVerBiografia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerBiografiaActionPerformed(evt);
            }
        });

        botonEnviarMensaxesPrivadas.setText("Enviar mensaxe privada");
        botonEnviarMensaxesPrivadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEnviarMensaxesPrivadasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaAmigosLayout = new javax.swing.GroupLayout(panelListaAmigos);
        panelListaAmigos.setLayout(panelListaAmigosLayout);
        panelListaAmigosLayout.setHorizontalGroup(
            panelListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaAmigosLayout.createSequentialGroup()
                .addComponent(labelListaAmigos)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(scrollPanelPublicaiones1)
            .addGroup(panelListaAmigosLayout.createSequentialGroup()
                .addContainerGap(402, Short.MAX_VALUE)
                .addComponent(botonVerBiografia)
                .addGap(18, 18, 18)
                .addComponent(botonEnviarMensaxesPrivadas)
                .addContainerGap(403, Short.MAX_VALUE))
        );
        panelListaAmigosLayout.setVerticalGroup(
            panelListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaAmigosLayout.createSequentialGroup()
                .addComponent(labelListaAmigos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPanelPublicaiones1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelListaAmigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonVerBiografia)
                    .addComponent(botonEnviarMensaxesPrivadas))
                .addContainerGap())
        );

        jSplitPane2.setLeftComponent(panelListaAmigos);

        panelSolicitudesAmistad.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        labelSolicitudesAmistad.setText("Solicitudes de amistad");
        labelSolicitudesAmistad.setToolTipText("Solicitudes pendientes de amistad");

        scrollPanelAmista.setViewportView(listaSolicitudesAmistad);

        botonDenegarSolicitud.setText("Rexeitar solicitude");
        botonDenegarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDenegarSolicitudActionPerformed(evt);
            }
        });

        botonNuevaSolicitudAmistad.setText("Nova solicitude de amizade");
        botonNuevaSolicitudAmistad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaSolicitudAmistadActionPerformed(evt);
            }
        });

        botonAceptarSolicitud.setText("Aceptar solicitude");
        botonAceptarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarSolicitudActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSolicitudesAmistadLayout = new javax.swing.GroupLayout(panelSolicitudesAmistad);
        panelSolicitudesAmistad.setLayout(panelSolicitudesAmistadLayout);
        panelSolicitudesAmistadLayout.setHorizontalGroup(
            panelSolicitudesAmistadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSolicitudesAmistadLayout.createSequentialGroup()
                .addGroup(panelSolicitudesAmistadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPanelAmista, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSolicitudesAmistadLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelSolicitudesAmistad)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelSolicitudesAmistadLayout.createSequentialGroup()
                        .addContainerGap(153, Short.MAX_VALUE)
                        .addComponent(botonAceptarSolicitud, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(botonDenegarSolicitud, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(botonNuevaSolicitudAmistad, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        panelSolicitudesAmistadLayout.setVerticalGroup(
            panelSolicitudesAmistadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSolicitudesAmistadLayout.createSequentialGroup()
                .addComponent(labelSolicitudesAmistad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPanelAmista, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelSolicitudesAmistadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonDenegarSolicitud)
                    .addComponent(botonNuevaSolicitudAmistad)
                    .addComponent(botonAceptarSolicitud))
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(panelSolicitudesAmistad);

        javax.swing.GroupLayout amigosLayout = new javax.swing.GroupLayout(amigos);
        amigos.setLayout(amigosLayout);
        amigosLayout.setHorizontalGroup(
            amigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        amigosLayout.setVerticalGroup(
            amigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
        );

        cuerpo.addTab("Amig@s", amigos);

        javax.swing.GroupLayout menxasesPrivadosLayout = new javax.swing.GroupLayout(menxasesPrivados);
        menxasesPrivados.setLayout(menxasesPrivadosLayout);
        menxasesPrivadosLayout.setHorizontalGroup(
            menxasesPrivadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1077, Short.MAX_VALUE)
        );
        menxasesPrivadosLayout.setVerticalGroup(
            menxasesPrivadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 323, Short.MAX_VALUE)
        );

        cuerpo.addTab("Mensaxes privadas", menxasesPrivados);

        botonCerrarSesion.setMnemonic('C');
        botonCerrarSesion.setText("Cerrar sesión");
        botonCerrarSesion.setToolTipText("Cerrar la sesión actual (Alt+C)");
        botonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarSesionActionPerformed(evt);
            }
        });

        botonEditarPerfil.setMnemonic('E');
        botonEditarPerfil.setText("Editar perfil");
        botonEditarPerfil.setToolTipText("Editar los datos del perfil (Alt+E)");
        botonEditarPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarPerfilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonCerrarSesion)
                .addGap(43, 43, 43)
                .addComponent(botonEditarPerfil)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCerrarSesion)
                    .addComponent(botonEditarPerfil))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cuerpo)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cabecera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cuerpo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNovaPublicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNovaPublicacionActionPerformed
        // Solicita el texto de la nueva publicación y la crea
        String texto = javax.swing.JOptionPane.showInputDialog(this, "Introduce o texto da publicación:");
        if (texto != null && !texto.trim().isEmpty()) {
            profileController.newPost(texto, profileController.getShownProfile());
            // Actualizar la vista después de crear el post
            showProfileMenu(profileController.getShownProfile());
        }
    }//GEN-LAST:event_botonNovaPublicacionActionPerformed

    private void botonVerAnterioresPublicacionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerAnterioresPublicacionsActionPerformed
        // Solicita cuántas publicaciones más mostrar
        String input = javax.swing.JOptionPane.showInputDialog(this, "Número de publicacións a mostrar:");
        try {
            int n = Integer.parseInt(input);
            if (n > 0) {
                // Simula el comportamiento de TextProfileView: suma n y recarga
                for (int i = 0; i < n; i++) {
                    // Añade una fila vacía para permitir mostrar más publicaciones
                    javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) tablaPublicaciones.getModel();
                    model.addRow(new Object[]{null, null, null, null});
                }
                profileController.reloadProfile();
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Introduce un número válido.");
        }
    }//GEN-LAST:event_botonVerAnterioresPublicacionsActionPerformed

    private void botonGustameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGustameActionPerformed
        int row = tablaPublicaciones.getSelectedRow();
        if (row >= 0) {
            java.util.List<tacebook.model.Post> posts = profileController.getShownProfile().getPosts();
            if (row < posts.size()) {
                tacebook.model.Post post = posts.get(row);
                tacebook.model.Profile sessionProfile = profileController.getSessionProfile();
                if (post.getAuthor().equals(sessionProfile)) {
                    showCannotLikeOwnPostMessage();
                } else if (post.getProfileLikes().contains(sessionProfile)) {
                    showAlreadyLikedPostMessage();
                } else {
                    profileController.newLike(post);
                    showProfileMenu(profileController.getShownProfile());
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona unha publicación para dar like.");
        }
    }//GEN-LAST:event_botonGustameActionPerformed

    private void botonComentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonComentarActionPerformed
        int row = tablaPublicaciones.getSelectedRow();
        if (row >= 0) {
            java.util.List<tacebook.model.Post> allPosts = new java.util.ArrayList<>();
            Profile profile = profileController.getShownProfile();
            
            // Recopilar todos los posts (propios y de amigos)
            allPosts.addAll(profile.getPosts());
            if (profile.equals(profileController.getSessionProfile())) {
                for (Profile friend : profile.getFriends()) {
                    allPosts.addAll(friend.getPosts());
                }
            }
            
            // Ordenar por fecha descendente
            allPosts.sort((a, b) -> b.getDate().compareTo(a.getDate()));
            
            if (row < allPosts.size()) {
                String texto = javax.swing.JOptionPane.showInputDialog(this, "Introduce o texto do comentario:");
                if (texto != null && !texto.trim().isEmpty()) {
                    profileController.newComment(allPosts.get(row), texto);
                    showProfileMenu(profileController.getShownProfile());
                }
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona unha publicación para comentar.");
        }
    }//GEN-LAST:event_botonComentarActionPerformed

    private void botonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCerrarSesionActionPerformed
        // Cierra la ventana actual y abre el menú de inicio
        this.setVisible(false);
        this.dispose();
        // Crea y muestra una nueva instancia del menú inicial en modo gráfico
        new tacebook.controller.InitMenuController(false).init();
    }//GEN-LAST:event_botonCerrarSesionActionPerformed

    private void botonEditarPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarPerfilActionPerformed
        // Mostrar un cuadro de diálogo para editar el estado
        String estadoActual = profileController.getSessionProfile().getStatus();
        String nuevoEstado = javax.swing.JOptionPane.showInputDialog(this, "Edita o teu estado:", estadoActual);
        if (nuevoEstado != null && !nuevoEstado.trim().isEmpty() && !nuevoEstado.equals(estadoActual)) {
            profileController.updateProfileStatus(nuevoEstado.trim());
            javax.swing.JOptionPane.showMessageDialog(this, "Estado actualizado.");
            showProfileMenu(profileController.getShownProfile());
        }
    }//GEN-LAST:event_botonEditarPerfilActionPerformed

    private void botonVerBiografiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerBiografiaActionPerformed
        int row = tablaListaAmigos.getSelectedRow();
        java.util.List<tacebook.model.Profile> friends = profileController.getShownProfile().getFriends();
        if (row >= 0 && row < friends.size()) {
            profileController.setShownProfile(friends.get(row));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un amigo para ver su biografía.");
        }
    }//GEN-LAST:event_botonVerBiografiaActionPerformed

    private void botonEnviarMensaxesPrivadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEnviarMensaxesPrivadasActionPerformed
        int row = tablaListaAmigos.getSelectedRow();
        java.util.List<tacebook.model.Profile> friends = profileController.getShownProfile().getFriends();
        if (row >= 0 && row < friends.size()) {
            String texto = javax.swing.JOptionPane.showInputDialog(this, "Introduce o texto da mensaxe privada:");
            if (texto != null && !texto.trim().isEmpty()) {
                profileController.newMessage(friends.get(row), texto);
                javax.swing.JOptionPane.showMessageDialog(this, "Mensaxe enviada.");
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona un amigo para enviar mensaxe privada.");
        }
    }//GEN-LAST:event_botonEnviarMensaxesPrivadasActionPerformed

    private void botonNuevaSolicitudAmistadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaSolicitudAmistadActionPerformed
        String nombre = javax.swing.JOptionPane.showInputDialog(this, "Introduce o nome do usuario ao que enviar solicitude:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            if (nombre.equals(profileController.getSessionProfile().getName())) {
                showCannotAddSelfAsFriendMessage();
                return;
            }
            profileController.newFriendshipRequest(nombre.trim());
        }
    }//GEN-LAST:event_botonNuevaSolicitudAmistadActionPerformed

    private void botonAceptarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarSolicitudActionPerformed
        int idx = listaSolicitudesAmistad.getSelectedIndex();
        java.util.List<tacebook.model.Profile> requests = profileController.getShownProfile().getFriendshipRequests();
        if (idx >= 0 && idx < requests.size()) {
            profileController.acceptFriendshipRequest(requests.get(idx));
            javax.swing.JOptionPane.showMessageDialog(this, "Solicitude aceptada.");
            showProfileMenu(profileController.getShownProfile());
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona unha solicitude para aceptar.");
        }
    }//GEN-LAST:event_botonAceptarSolicitudActionPerformed

    private void botonDenegarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDenegarSolicitudActionPerformed
        int idx = listaSolicitudesAmistad.getSelectedIndex();
        java.util.List<tacebook.model.Profile> requests = profileController.getShownProfile().getFriendshipRequests();
        if (idx >= 0 && idx < requests.size()) {
            profileController.rejectFriendshipRequest(requests.get(idx));
            javax.swing.JOptionPane.showMessageDialog(this, "Solicitude rexeitada.");
            showProfileMenu(profileController.getShownProfile());
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona unha solicitude para rexeitar.");
        }
    }//GEN-LAST:event_botonDenegarSolicitudActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel amigos;
    private javax.swing.JPanel biografia;
    private javax.swing.JButton botonAceptarSolicitud;
    private javax.swing.JButton botonCerrarSesion;
    private javax.swing.JButton botonComentar;
    private javax.swing.JButton botonDenegarSolicitud;
    private javax.swing.JButton botonEditarPerfil;
    private javax.swing.JButton botonEnviarMensaxesPrivadas;
    private javax.swing.JButton botonGustame;
    private javax.swing.JButton botonNovaPublicacion;
    private javax.swing.JButton botonNuevaSolicitudAmistad;
    private javax.swing.JButton botonVerAnterioresPublicacions;
    private javax.swing.JButton botonVerBiografia;
    private javax.swing.JPanel cabecera;
    private javax.swing.JTabbedPane cuerpo;
    private javax.swing.JPanel footer;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JLabel labelComentarios;
    private javax.swing.JLabel labelEstadoActual;
    private javax.swing.JLabel labelListaAmigos;
    private javax.swing.JLabel labelLogo;
    private javax.swing.JLabel labelNomeDoPerfil;
    private javax.swing.JLabel labelPublicacions;
    private javax.swing.JLabel labelSolicitudesAmistad;
    private javax.swing.JList<String> listaSolicitudesAmistad;
    private javax.swing.JPanel menxasesPrivados;
    private javax.swing.JPanel panelComentario;
    private javax.swing.JPanel panelListaAmigos;
    private javax.swing.JPanel panelPublicaiones;
    private javax.swing.JPanel panelSolicitudesAmistad;
    private javax.swing.JScrollPane scrollPanelAmista;
    private javax.swing.JScrollPane scrollPanelComentarios;
    private javax.swing.JScrollPane scrollPanelPublicaiones;
    private javax.swing.JScrollPane scrollPanelPublicaiones1;
    private javax.swing.JTable tablaComentarios;
    private javax.swing.JTable tablaListaAmigos;
    private javax.swing.JTable tablaPublicaciones;
    // End of variables declaration//GEN-END:variables
}
