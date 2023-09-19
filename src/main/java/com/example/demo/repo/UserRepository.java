package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select count(*) > 0 from User u where u.email = :email")
    Boolean checkEmailExist(@Param("email") String email);

    @Query("select u from User u where u.code = :code")
    Optional<User> findByCode(@Param("code") String code);

    @Query("SELECT u FROM User u WHERE (:contains is null or (CONCAT(u.firstname,' ',u.lastname)" +
            " LIKE %:contains% OR  u.email LIKE %:contains%))")
    Slice<User> search(@Param("contains") String contains,
                       Pageable pageable);

    @Query("select month(u.updatedAt), count(u), u.isActive from User u group by month(u.updatedAt), u.isActive")
    List<Object[]> countByUserActive();

}
