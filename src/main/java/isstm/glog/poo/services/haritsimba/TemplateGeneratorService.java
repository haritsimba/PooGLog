package isstm.glog.poo.services.haritsimba;

import isstm.glog.poo.dtos.haritsimba.SlotDTO;
import isstm.glog.poo.entities.haritsimba.Classe;
import isstm.glog.poo.entities.haritsimba.TimeSlot;
import isstm.glog.poo.repositories.haritsimba.ClasseRepository;
import isstm.glog.poo.repositories.haritsimba.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TemplateGeneratorService {
    @Autowired
    TimeSlotRepository timeSlotRepository;
    @Autowired
    ClasseRepository classeRepository;
    public Map<String, Object> getTimeSlotTemplateByClasseId(Long classeId) {
        try {

            List<TimeSlot> timeSlots = timeSlotRepository.findByClasseId(classeId);
            Optional<Classe> classeOp = classeRepository.findById(classeId);
            if(classeOp.isEmpty()){
                return null;
            }
            Classe classe= classeOp.get();
            LocalDate date = LocalDate.now(); // 🔥 Aujourd’hui
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
            String dateFormatee = date.format(formatter);

            Map<String, Object> data = new HashMap<>();
            data.put("classe", classe);
            data.put("emploiDuTemps", timeSlotRepository.findByClasseId(classeId));
            data.put("schedule", organizeTimeSlots(timeSlots));
            data.put("days", getWorkingDays());
            data.put("jours", DayOfWeek.values());
            data.put("dateToday", dateFormatee);
            data.put("logoIsstmBase64", imageToBase64("static/images/logo_isstm.png"));
            data.put("logoUnivBase64", imageToBase64("static/images/logo_univ_mahajanga.png"));
            return data;
        }catch (IOException e){
            return null;
        }
    }

    private Map<String, Map<DayOfWeek, SlotDTO>> organizeTimeSlots(List<TimeSlot> timeSlots) {
        Map<String, Map<DayOfWeek, SlotDTO>> schedule = new LinkedHashMap<>();

        // Plages horaires fixes de 7h à 18h
        String[] timeRanges = {
                "07:00-08:00", "08:00-09:00", "09:00-10:00", "10:00-11:00",
                "11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00",
                "15:00-16:00", "16:00-17:00", "17:00-18:00"
        };

        // Initialisation de la structure vide
        for (String range : timeRanges) {
            Map<DayOfWeek, SlotDTO> dayMap = new EnumMap<>(DayOfWeek.class);
            getWorkingDays().forEach(day -> dayMap.put(day, null));
            schedule.put(range, dayMap);
        }

        // Remplissage avec les créneaux existants
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        timeSlots.forEach(ts -> {
            String slotStart = ts.getStartTime().format(formatter);
            String slotEnd = ts.getEndTime().format(formatter);
            DayOfWeek day = ts.getDay();

            for (Map.Entry<String, Map<DayOfWeek, SlotDTO>> entry : schedule.entrySet()) {
                String key = entry.getKey();
                if (isKeyInRange(key, slotStart, slotEnd)) {
                    SlotDTO slotDTO = new SlotDTO();
                    slotDTO.setSubjectName(ts.getSubject().getName());
                    slotDTO.setTeacherName(ts.getTeacher().getName());

                    // Calcul de la durée en heures
                    int rowSpan = (int) Duration.between(ts.getStartTime(), ts.getEndTime()).toHours();

                    // Vérifier si c'est le premier créneau de la série
                    if (!entry.getValue().containsKey(day) || entry.getValue().get(day) == null) {
                        slotDTO.setSkip(false);
                        slotDTO.setRowSpan(rowSpan);

                        // Marquer les créneaux suivants comme à ignorer
                        for (int i = 1; i < rowSpan; i++) {
                            String nextKey = getNextTimeSlot(key);
                            if (schedule.containsKey(nextKey)) {
                                SlotDTO skipDTO = new SlotDTO();
                                skipDTO.setSkip(true);
                                skipDTO.setRowSpan(1);
                                schedule.get(nextKey).put(day, skipDTO);
                            }
                        }
                    } else {
                        continue;
                    }

                    System.out.println("subject : " + slotDTO.getSubjectName() +
                            " key : " + key +
                            " start : " + ts.getStartTime() +
                            " end : " + ts.getEndTime() +
                            " rowSpan : " + rowSpan +
                            " skip : " + slotDTO.isSkip());

                    entry.getValue().put(day, slotDTO);
                }
            }

        });


        return schedule;
    }
    private String getNextTimeSlot(String currentKey) {
        String[] parts = currentKey.split("-");
        try {
            LocalTime start = LocalTime.parse(parts[0].trim());
            LocalTime end = LocalTime.parse(parts[1].trim());
            return end.toString() + "-" + end.plusHours(1).toString();
        } catch (Exception e) {
            return "";
        }
    }
    public static boolean isKeyInRange(String key, String rangeStart, String rangeEnd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        String[] keyParts = key.split("-");
        LocalTime keyStart = LocalTime.parse(keyParts[0], formatter);
        LocalTime keyEnd = LocalTime.parse(keyParts[1], formatter);

        LocalTime start = LocalTime.parse(rangeStart, formatter);
        LocalTime end = LocalTime.parse(rangeEnd, formatter);

        // Check if the entire key time slot is within the given range
        return !keyStart.isBefore(start) && !keyEnd.isAfter(end);
    }

    private List<DayOfWeek> getWorkingDays() {
        return Arrays.asList(
                DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY
        );
    }

    private String imageToBase64(String imagePath) throws IOException {
        byte[] imageBytes = new ClassPathResource(imagePath).getInputStream().readAllBytes();
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
    }
}
