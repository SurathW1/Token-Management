package com.Surathw1.token_system.reposistory;


import com.Surathw1.token_system.entity.Token;
import com.Surathw1.token_system.entity.Token.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {

    // Find tokens by status
    List<Token> findByStatus(String status);

    //Find tokens by customer ID
    @Query("SELECT t FROM Token t WHERE t.customerId = :customerId")
    List<Token> findTokensByCustomerId(@Param("customerId") Integer customerId);

    //Find tokens by event name
    @Query("SELECT t FROM Token t WHERE t.eventName = :eventName")
    List<Token> findTokensByEventName(@Param("eventName") String eventName);

    //Find tokens by customer ID, status, and event name
    @Query("SELECT t FROM Token t WHERE t.customerId = :customerId AND t.status = :status AND t.eventName = :eventName")
    List<Token> findTokensByCustomerIdAndStatusAndEventName(
            @Param("customerId") Integer customerId,
            @Param("status") String status,
            @Param("eventName") String eventName
    );


}
