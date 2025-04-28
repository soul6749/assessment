package Maybank;

import Maybank.Transaction;
import Maybank.TransactionRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> updateTransaction(Transaction newTransaction) {

    	List<Transaction> transactionList = transactionRepository.findByCustomerIdOrDescriptionOrAccountNumber(transaction.getCustomerId(), transaction.getDescription(), transaction.getAccountNumber());
        
        if (transactionList.isEmpty()) {
            throw new IllegalArgumentException("Transaction not found");
        }

        Transaction existingTransaction = transactionList.get(0);

        existingTransaction.setTrxAmount(newTransaction.getTrxAmount());
        existingTransaction.setDescription(newTransaction.getDescription());
        existingTransaction.setAccountNumber(newTransaction.getAccountNumber());
        existingTransaction.setCustomerId(newTransaction.getCustomerId());
        existingTransaction.setTrxDate(newTransaction.getTrxDate());
        existingTransaction.setTrxTime(newTransaction.getTrxTime());

        boolean isSame = existingTransaction.getTrxAmount() == newTransaction.getTrxAmount()
                && existingTransaction.getDescription().equals(newTransaction.getDescription())
                && existingTransaction.getAccountNumber().equals(newTransaction.getAccountNumber())
                && existingTransaction.getCustomerId().equals(newTransaction.getCustomerId())
                && existingTransaction.getTrxDate().equals(newTransaction.getTrxDate())
                && existingTransaction.getTrxTime().equals(newTransaction.getTrxTime());

        if (isSame) {
            return Optional.of(existingTransaction);
        }
        
        return Optional.of(transactionRepository.save(existingTransaction));
    }
    
    public List<Transaction> retrieveTransaction(Transaction transaction) {
    	List<Transaction> transactionList = transactionRepository.findByCustomerIdOrDescriptionOrAccountNumber(transaction.getCustomerId(), transaction.getDescription(), transaction.getAccountNumber());
        
        if (transactionList.isEmpty()) {
            throw new IllegalArgumentException("Transaction not found");
        }

        return transactionList;
    }
}
