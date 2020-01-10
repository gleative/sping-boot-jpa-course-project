package com.appsdeveloperblog.app.ws.mobileappws;

import com.appsdeveloperblog.app.ws.mobileappws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

// CrudRepository<ClassOfTheObject, DataTypeOfIdField>

// CrudRepository inneholder funksjoner som 'create, update user, delete' dette gjør det enklere for oss, CrudRepository trenger at du sender inn ENTITY

// ! THIS WE HAD ORIGINAL, BUT HAD TO CHANGE INTERFACE FOR PAGING AND LIMIT
// public interface UserRepository extends CrudRepository<UserEntity, Long>{
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    // Naming er viktig, slik at Spring data JPA kan execute disse QUERYMetodene
    // VIKTIG AT etter findUserBy så har du navn på hva feltet er i CAMEL case

    UserEntity findUserByEmail(String email);

    // Remember we find by using PUBLIC id
    UserEntity findUserByUserId(String userId);
}
