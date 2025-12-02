package service;

import connexion.Connexion;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LoginService {

    // Méthode pour hasher le mot de passe
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            System.out.println("Erreur hashPassword : " + e.getMessage());
            return null;
        }
    }

    // Vérification du login (CORRECTION : vérifier soit username soit email)
    public boolean checkLogin(String identifier, String password) {
        try {
            String hashedPassword = hashPassword(password);
            String sql = "SELECT * FROM utilisateur WHERE (username = ? OR email = ?) AND password = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, hashedPassword);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Erreur checkLogin : " + e.getMessage());
            return false;
        }
    }

    // Vérifie si un utilisateur existe par email
    public boolean checkUserExists(String email) {
        try {
            String sql = "SELECT * FROM utilisateur WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("Erreur checkUserExists : " + e.getMessage());
            return false;
        }
    }

    // Met à jour le mot de passe dans la base
    public boolean updatePasswordByEmail(String email, String newPass) {
        try {
            String hashedPassword = hashPassword(newPass);
            String sql = "UPDATE utilisateur SET password = ? WHERE email = ?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(sql);
            ps.setString(1, hashedPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Erreur updatePasswordByEmail : " + e.getMessage());
            return false;
        }
    }

    // Génère un mot de passe aléatoire
    public String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@_!)(&?%$#";
        StringBuilder password = new StringBuilder();
        java.util.Random rnd = new java.util.Random();
        for (int i = 0; i < 8; i++) {
            password.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return password.toString();
    }

    // CORRECTION COMPLÈTE : Réinitialise le mot de passe et envoie un email
    public void resetPassword(String emailTo) {
        final String newPassword = generateRandomPassword();

        boolean updated = updatePasswordByEmail(emailTo, newPassword);

        if (!updated) {
            JOptionPane.showMessageDialog(null,
                    "Erreur : Email introuvable dans la base !",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        final String senderEmail = "hafssachk846@gmail.com"; // Votre email Gmail
        final String senderPassword = "qokg uunm hsrt ggei"; // Mot de passe d'application
        
        

        final String subject = "Réinitialisation du mot de passe";
        String msg = "Bonjour,\n\nVotre nouveau mot de passe est : " + newPassword +
                     "\n\nMerci de vous connecter et de le modifier dès que possible.\n" +
                     "Cordialement,\nL'équipe de support";

        try {
            java.util.Properties props = new java.util.Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            // Désactiver la vérification du certificat (pour développement uniquement)
            // props.put("mail.smtp.ssl.trust", "*");

            javax.mail.Session session = javax.mail.Session.getInstance(
                props,
                new javax.mail.Authenticator() {
                    @Override
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(senderEmail, senderPassword);
                    }
                }
            );

            javax.mail.Message message = new javax.mail.internet.MimeMessage(session);
            message.setFrom(new javax.mail.internet.InternetAddress(senderEmail));
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    javax.mail.internet.InternetAddress.parse(emailTo));
            message.setSubject(subject);
            message.setText(msg);

            javax.mail.Transport.send(message);

            JOptionPane.showMessageDialog(null,
                    " Votre nouveau mot de passe a été envoyé par email et enregistré !\n" +
                    "Vérifiez votre boîte de réception (et les spams).",
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (javax.mail.AuthenticationFailedException e) {
            JOptionPane.showMessageDialog(null,
                    " Erreur d'authentification Gmail :\n" +
                    "1. Vérifiez que l'email et le mot de passe sont corrects\n" +
                    "2. Assurez-vous d'utiliser un mot de passe d'application\n" +
                    "3. Activez la vérification en 2 étapes sur votre compte Google\n\n" +
                    "Message : " + e.getMessage(),
                    "Erreur d'authentification",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    " Erreur lors de l'envoi de l'email : " + e.getMessage() + "\n" +
                    "Code d'erreur : 536.5.7.8\n" +
                    "Problème : Username and Password not accepted\n\n" +
                    "Solution :\n" +
                    "1. Utilisez un mot de passe d'application Gmail\n" +
                    "2. Vérifiez que l'email expéditeur est correct\n" +
                    "3. Activez l'accès aux applications moins sécurisées\n" +
                    "   (https://myaccount.google.com/lesssecureapps)",
                    "Erreur d'envoi",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}