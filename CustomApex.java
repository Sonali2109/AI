public class User {
    public static void createAccount(String accName, String accType) {
        Account newAcc = new Account();
        newAcc.Name = accName;
        newAcc.Type = accType; 
        
        try {
            insert newAcc;
            System.debug('Account created with id: ' + newAcc.Id);
        } catch (DmlException e) {
            System.debug('Error creating Account: ' + e.getMessage());
        }
    }
    
     public static void deleteAccount(Id accId) {
        // Check if the account ID is valid
        if (accId == null) {
            System.debug('Account Id cannot be null');
            return;
        }
        
        try {
            delete [SELECT Id FROM Account WHERE Id = :accId LIMIT 1];
            System.debug('Account with ID: ' + accId + ' has been deleted.');
        } catch (DmlException e) {
            System.debug('Error deleting Account: ' + e.getMessage());
        } catch (QueryException e) {
            System.debug('Error finding Account: ' + e.getMessage());
        }
    }
}

// User.createAccount('Test_Account', 'Savings');
// User.deleteAccount(accountIdToDelete);
