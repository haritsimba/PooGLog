package isstm.glog.poo.repositories.haritsimba;

import isstm.glog.poo.entities.haritsimba.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    @Query("select ts from TimeSlot ts where ts.teacher.id = ?1")
    List<TimeSlot> findByTeacherId(Long teacherId);

    @Query("select ts from TimeSlot ts where ts.classe.id = ?1")
    List<TimeSlot> findByClasseId(Long classeId);

    @Query("SELECT ts FROM TimeSlot ts WHERE ts.day = :day AND ((:start < ts.endTime AND :end > ts.startTime))")
    List<TimeSlot> checkConfusionForCreate(@Param("start") LocalTime start,
                                           @Param("end") LocalTime end,
                                           @Param("day") DayOfWeek day);

    @Query("""
                SELECT ts FROM TimeSlot ts
                WHERE ts.day = :day
                  AND (:start < ts.endTime AND :end > ts.startTime)
                  AND (:id IS NULL OR ts.id <> :id)
            """)
    List<TimeSlot> checkConfusionForUpdate(@Param("start") LocalTime start,
                                           @Param("end") LocalTime end,
                                           @Param("day") DayOfWeek day,
                                           @Param("id") Long id);

    @Modifying
    @Query("""
                DELETE FROM TimeSlot ts
                WHERE ts.classe.id = :classeId
                  AND ts.startTime = :startTime
                  AND ts.endTime = :endTime
                  AND ts.day = :day
            """)
    void deleteByClasseIdAndStartTimeAndEndTimeAndDay(@Param("classeId") Long classeId,
                                                      @Param("startTime") LocalTime startTime,
                                                      @Param("endTime") LocalTime endTime,
                                                      @Param("day") DayOfWeek day);


}