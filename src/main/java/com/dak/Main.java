package com.dak;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import org.flywaydb.core.Flyway;

import com.dak.configs.EnvironmentVariable;
import com.dak.views.HomePage;
import com.dak.views.utils.ColorSet;
import com.dak.views.utils.SizeSet;
import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    public static JFrame frame = new JFrame();

    public static JPanel page;

    public static void main(String[] args) {
        // migrate(); if (true) return;

        UIManager.put( "Button.arc", 6 );
        UIManager.put( "Button.borderWidth", 0);
        UIManager.put("defaultFont",  new javax.swing.plaf.FontUIResource("Inter", Font.PLAIN, 13));
        UIManager.put("Label.foreground", ColorSet.PRIMARY_FOREGROUND);
        FlatLightLaf.setup();

        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setBorder(BorderFactory.createEmptyBorder(SizeSet.XS, SizeSet.XS, SizeSet.XS, SizeSet.XS));
        switchPanel(new HomePage());

        frame.setSize(new Dimension(1200, 800));
        frame.setMinimumSize(new Dimension(960, 720));
        frame.setTitle("Quizio");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void switchPanel(JPanel panel) {
        JPanel contentPane = (JPanel) frame.getContentPane();
        if (page != null) {
            page.setVisible(false);
            contentPane.remove(page);
        }
        page = panel;
        contentPane.add(page);
    }

    /**
     * Runs all pending migrations on the database.
     *
     * <p>Note: For best practice, consider integrating this functionality as a separate
     * console command instead of invoking it directly within the application code.</p>
     */
    private static void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(
                        EnvironmentVariable.DATABASE_URL,
                        EnvironmentVariable.DATABASE_USER,
                        EnvironmentVariable.DATABASE_PASSWORD
                )
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }
}
