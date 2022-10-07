package edu.fbansept.applicationdesktop;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {
protected boolean themeSombre;

    public Fenetre() {
        setSize(500, 500);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panneau = new JPanel();

        setContentPane(panneau);

        //------------ BOUTON -------------------

        JButton bouton = new JButton("Fermer l'application");




        //1:  bouton.addActionListener(new ClicBouton());
        //2:  ClicBouton evenment = new ClicBouton();
        //3:  ActionListener evenment = new ClicBouton();

        /*4:  ActionListener evenment = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clic");
            }
         };*/


        /* 5:  ActionListener evenment = (ActionEvent e) -> {
                System.out.println("Clic");
        };*/

        // 6:  ActionListener evenment = e -> System.out.println("Clic");


        // 7: bouton.addActionListener(evenment);


        //----Examples ----------
        //bouton.addActionListener(e -> System.out.println("Clic")); //هذا من اجل الازرار
       // bouton.addActionListener(e -> setVisible(false));//هنا مثلا نغلق القامة من خلال الزر
        //bouton.addActionListener(e -> {System.exit(0);});
       /* bouton.addActionListener(e ->
        {JOptionPane.showMessageDialog(this, "L'application va se fermer");
            System.exit(0);});*/

       /* bouton.addActionListener(e -> { int reponse =JOptionPane.showConfirmDialog(this, "Voulez vous fermer l'application");
            if(reponse == JOptionPane.YES_OPTION){
                System.exit(0);
            };});*/

        /*bouton.addActionListener(e -> { int reponse =JOptionPane.showConfirmDialog(this, "Voulez vous fermer l'application","Confirmer",JOptionPane.YES_NO_OPTION);
            if(reponse == JOptionPane.YES_NO_OPTION){
                System.exit(0);
            };});*/


        //------ DIALOG ---------
        /*bouton.addActionListener(e -> {
            Object [] choix = {"oui","Non"};
            int reponse =JOptionPane.showOptionDialog(this,
                    "Voulez vous fermer l'application",
                    "Confirmer",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choix,
                    choix[0]);

            if(reponse == JOptionPane.YES_NO_OPTION){
                System.exit(0);
            }});*/


        //-------Changer theme ---------

        JButton boutonChangeTheme = new JButton("Change theme");
        panneau.add(boutonChangeTheme);
        boutonChangeTheme.addActionListener(e ->  {
            try {
                if(themeSombre) {
                    themeSombre = false;
                    UIManager.setLookAndFeel( new FlatLightLaf());
                } else {
                    themeSombre = true;
                    UIManager.setLookAndFeel( new FlatDarculaLaf());
                }
                SwingUtilities.updateComponentTreeUI(this);
            } catch( Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
            }
        });



//-------COBOBOX---------------
        String[] listeCivilite = {"M.","Me.","Mlle.", "Non précisé"};
        JComboBox<Object> selectCivilite = new JComboBox<>(listeCivilite);
        panneau.add(selectCivilite);
        selectCivilite.addActionListener(e -> {
            JComboBox comboBox = (JComboBox) e.getSource();
            System.out.println(comboBox.getSelectedItem());
        });

        //------autre example -----

        Utilisateur[] utilisateurs = {

                new Utilisateur("BANSEPT", "Franck"),
                new Utilisateur("SNOW", "Jon"),
                new Utilisateur("SMITH", "Steeve")
        };
        JComboBox<Utilisateur> selectUtilisateur = new JComboBox<>(utilisateurs);


        //------customiser le rendu de la  liste déroulant--------

        selectUtilisateur.setRenderer(
                new DefaultListCellRenderer(){

                    @Override
                    public Component getListCellRendererComponent(
                            final JList<?> list, final Object value,
                            final int index,
                            final boolean isSelected,
                            final boolean cellHasFocus) {
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        Utilisateur utilisateur= (Utilisateur) value;
                        setText(utilisateur.getNom() + " " + utilisateur.getPrenom());
                        if(isSelected){setBackground(Color.green);} else {setBackground(Color.RED);}

                        return this;
                    }
                }
        );


        //--------bouton formulaire ------------

        JButton boutonFormulaire = new JButton("Envoyer");
        boutonFormulaire.addActionListener(e -> {

            if(selectUtilisateur.getSelectedItem() != null) {
                Utilisateur utilisateur =
                        (Utilisateur)selectUtilisateur.getSelectedItem();

                System.out.println(
                        selectCivilite.getSelectedItem() + utilisateur.getNom()
                );
            }

        });

        /*panneau.add(bouton);
        panneau.add(selectUtilisateur);
        panneau.add(boutonChangeTheme);
        panneau.add(selectCivilite);
        panneau.add(selectUtilisateur);
        panneau.add(boutonFormulaire);*/


        Box boxPrincipal = Box.createVerticalBox();
        panneau.add(boxPrincipal, BorderLayout.CENTER);
        Box boxMenu = Box.createHorizontalBox();
        boxMenu.add(bouton);
        boxMenu.add(boutonChangeTheme);
        boxPrincipal.add(boxMenu);

        boxPrincipal.add(Box.createRigidArea(new Dimension(1,50)));

        Box boxUtilisateur = Box.createHorizontalBox();
        boxUtilisateur.setMaximumSize(new Dimension(500,30));
        boxUtilisateur.add(new JLabel("Civilite"));
        boxUtilisateur.add(selectCivilite);
        boxUtilisateur.add(selectUtilisateur);
        boxPrincipal.add(boxUtilisateur);

        boxPrincipal.add(
                Champs.generate("Civilite" , selectCivilite));
        boxPrincipal.add(
                Champs.generate("Utilisateur" , selectUtilisateur));

        panneau.add(boutonFormulaire, BorderLayout.SOUTH);






/*String monTexte = "Toto";
Object unTruc = monTexte;
Utilisateur unUtilisateur = new Utilisateur("titi","toto");
Object unAutreTruc = unUtilisateur;
        System.out.println((String)unAutreTruc);*/




        setVisible(true);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();// من اجل تغيير التيم نقوم بوضع ثلاثة اسطر في صفحة بوم اكس ام ال
        new Fenetre();
    }
}
