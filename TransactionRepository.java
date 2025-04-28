package Maybank;

import Maybank.Transaction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByCustomerIdOrDescriptionOrAccountNumber(String customerId, String description, String accountNumber);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	public Optional<Transaction> updateTransaction(Transaction newTransaction); 
}