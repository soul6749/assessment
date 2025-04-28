package Maybank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/update")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction newTransaction) {
        try {
            Optional<Transaction> updatedTransaction = transactionService.updateTransaction(newTransaction);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/retrieve")
    public ResponseEntity<List<Transaction>> retrieveTransaction(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String accountNumber) {

        try {
            Transaction transaction = new Transaction();
            transaction.setCustomerId(customerId);
            transaction.setDescription(description);
            transaction.setAccountNumber(accountNumber);

            List<Transaction> transactions = transactionService.retrieveTransaction(transaction);
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}