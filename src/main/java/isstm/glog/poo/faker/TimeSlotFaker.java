package isstm.glog.poo.faker;

import net.datafaker.Faker;

import java.sql.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TimeSlotFaker {

    public static void applyFaker() {
        Faker faker = new Faker();

        // Connexion à la base de données
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/poo", "franck", "franck@123!")) {

            // 1. Création des enseignants
            for (int i = 0; i < 15; i++) {
                Long teacherId = insertTeacher(conn, faker.name().fullName());

                // 2. Création des classes
                String[] departments = {"BTP", "GBM", "GINFO", "GL", "GLOG", "GTL"};
                String[] levels = {"L1", "L2", "L3", "M1", "M2"};

                for (String dept : departments) {
                    for (String level : levels) {
                        Long classeId = insertClasse(conn, dept, level);

                        // 3. Création des matières pour chaque classe
                        for (int j = 0; j < 8; j++) {
                            Long subjectId = insertSubject(conn,
                                    faker.educator().course(),
                                    classeId,
                                    teacherId);

                            // 4. Création des créneaux horaires
                            generateTimeSlots(conn, classeId, subjectId, teacherId);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Long insertTeacher(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO teacher (id, name) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, generateId()); // Méthode pour générer un ID unique
            stmt.setString(2, name);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }

    // Méthode pour générer un ID
    private static long generateId() {
        return System.currentTimeMillis() + new Random().nextInt(1000);
    }

    private static Long insertClasse(Connection conn, String department, String level) throws SQLException {
        String sql = "INSERT INTO classe (department, level) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, department);
            stmt.setString(2, level);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }

    private static Long insertSubject(Connection conn, String name, Long classeId, Long teacherId) throws SQLException {
        String sql = "INSERT INTO subject (name, classe_id, teacher_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setLong(2, classeId);
            stmt.setLong(3, teacherId);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }

    private static void generateTimeSlots(Connection conn, Long classeId, Long subjectId, Long teacherId) throws SQLException {
        List<Integer> days = Arrays.asList(1, 2, 3, 4, 5, 6); // Lundi=1 à Samedi=6
        LocalTime startHour = LocalTime.of(7, 0);
        LocalTime endHour = LocalTime.of(18, 0);

        String sql = "INSERT INTO time_slot (day, start_time, end_time, classe_id, subject_id, teacher_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        for (int day : days) {
            // Générer entre 1 et 3 créneaux par jour
            int slotsCount = new Faker().number().numberBetween(1, 4);
            LocalTime currentStart = startHour;

            for (int i = 0; i < slotsCount && currentStart.isBefore(endHour); i++) {
                // Durée aléatoire entre 1 et 3 heures
                int duration = new Faker().number().numberBetween(1, 4);
                LocalTime currentEnd = currentStart.plusHours(duration);

                if (currentEnd.isAfter(endHour)) {
                    currentEnd = endHour;
                }

                // 70% de chance d'avoir un cours ce créneau
                if (new Faker().random().nextDouble() < 0.7) {
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, day);
                        stmt.setTime(2, Time.valueOf(currentStart));
                        stmt.setTime(3, Time.valueOf(currentEnd));
                        stmt.setLong(4, classeId);
                        stmt.setLong(5, subjectId);
                        stmt.setLong(6, teacherId);
                        stmt.executeUpdate();
                    }
                }

                currentStart = currentEnd.plusHours(1); // Pause d'au moins 1h entre les cours
            }
        }
    }
}
