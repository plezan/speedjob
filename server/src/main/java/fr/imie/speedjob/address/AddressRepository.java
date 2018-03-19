package fr.imie.speedjob.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
  List<Address> findAll();
  Address findById(Long id);
  Long countById(Long id);
}
