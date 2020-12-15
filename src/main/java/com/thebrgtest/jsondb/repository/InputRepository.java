package com.thebrgtest.jsondb.repository;

import com.thebrgtest.jsondb.controller.OffsetBasedPageRequest;
import com.thebrgtest.jsondb.domain.Input;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InputRepository extends JpaRepository<Input,Long> {

    /** Querying based on productId and typeId **/
    String FIND_SEATS = "select seats_available as seatsAvailable from input ," +
            " input_properties ,property where product_id = input_product_id" +
            " and product_id =:productId and type_id = properties_type_id " +
            " and type_id = :typeId ";

    @Query(value = FIND_SEATS,nativeQuery = true)
    List<InputSeatProjection> findByProductIdAndTypeId(@Param("productId") Long productId,
                                                       @Param("typeId") Long typeId, Pageable pageable);


    /** Querying based on start date and end date **/
    String FIND_SEATS_BY_TIME = "select distinct seats_available " +
            " as seatsAvailable " +
            " from input where start >=:startDate" +
            " and end <=:endDate" ;
    Pageable pageRequest1 = PageRequest.of(1,
            10,
            Sort.by(Sort.Direction.DESC, "seats_available"));

    @Query(value = FIND_SEATS_BY_TIME, nativeQuery = true)
    List<InputSeatProjection> findByTime(@Param("startDate") Date startDate,
                                                       @Param("endDate") Date endDate,Pageable pageable);

    /** Querying based on all parameters **/
    String FIND_SEATS_BY_ALL = "select distinct seats_available " +
            " as seatsAvailable from input i, input_properties ip, property  p " +
            " where product_id = :productId and i.product_id = ip.input_product_id " +
            " and start >= :startDate AND end <= :endDate" +
            " and type_id = properties_type_id and type_id = :typeId " +
            " order by seats_available" ;

    @Query(value = FIND_SEATS_BY_ALL, nativeQuery = true)
    List<InputSeatProjection> findByAllParams(@Param("productId") Long productId,
                                         @Param("typeId") Long typeId,
                                         @Param("startDate") Date startDate,
                                         @Param("endDate") Date endDate,Pageable pageable);

}
