package com.parcial.sweeties.DAOs;

import com.parcial.sweeties.Models.Victim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IVictimDAO extends JpaRepository<Victim, Long> {

    @Query(value = "select v.name, last_name ,f.name as family from victim v LEFT JOIN public.family f on v.family_id = f.id where v.id= :victimId ", nativeQuery = true)
    List<Map<String, Object>> findVictimDataById(Long victimId);


    @Query(value = "select k.name, k.last_name, k.nickname, mom.type,mom.description from killer k left join public.killer_manner_of_murder kmom on k.id = kmom.killer_id left join public.manner_of_murder mom on mom.id = kmom.manner_of_murder_id where killer_id= :killerid ", nativeQuery = true)
    List<Map<String, Object>> killer_manners_of_kill(Long killerid);

    @Query(value = "select v.name,v.last_name,mom.type,mom.description from victim as v  left join public.manner_of_murder mom on v.id = mom.victim_id where victim_id = :victimId ", nativeQuery = true)
    List<Map<String, Object>> victim_manner_of_death(Long victimId);

    @Query(value = "select v.name as victim,k.name as killer_name,k.nickname from victim as v  left join public.killer k on v.killer_id = k.id where v.id = :victimId ", nativeQuery = true)
    List<Map<String, Object>> killer_of_a_victim(Long victimId);

}
