package com.dien_dan.repository;

import com.dien_dan.model.Account;
import com.dien_dan.model.query.AccountDistance;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepo extends CrudRepository<Account, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Account where username= :username and password= :password")
    Account getAccountLogin(@Param("username") String username,@Param("password") String password);

    @Query(value = "SELECT a FROM Account a where a.status =:status and a.id != :id")
    List<Account> getAccountByStatus(@Param("status") String status, @Param("id") long id);


    Account getAccountByUsername(String username);

    List<Account> getAllByAreaIdAndStatus(long id, String status);

    @Query(nativeQuery = true, value = "SELECT a.*,\n" +
            "    ( \n" +
            "        6371 * \n" +
            "        acos( \n" +
            "            cos(radians(location.latitude)) * \n" +
            "            cos(radians(area.latitude)) * \n" +
            "            cos(radians(area.longitude) - radians(location.longitude)) + \n" +
            "            sin(radians(location.latitude)) * \n" +
            "            sin(radians(area.latitude))\n" +
            "        ) \n" +
            "    ) AS distance \n" +
            "FROM \n" +
            "    Account a \n" +
            "JOIN \n" +
            "    Area area ON a.area_id = area.id \n" +
            "JOIN \n" +
            "    Location ON Location.account_id = a.id \n" +
            "WHERE \n" +
            "    a.id != :idUser and a.status='online' \n" +
            "ORDER BY \n" +
            "    distance;")
    List<AccountDistance> getAllByAreaIdAndStatusAndKm(@Param("idUser") long idUser);

}
