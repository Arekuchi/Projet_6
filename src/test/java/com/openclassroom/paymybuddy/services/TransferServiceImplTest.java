package com.openclassroom.paymybuddy.services;

import com.openclassroom.paymybuddy.DAO.*;
import com.openclassroom.paymybuddy.DTO.ExternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.InternalTransferDTO;
import com.openclassroom.paymybuddy.DTO.UserRegistrationDTO;
import com.openclassroom.paymybuddy.model.*;
import com.openclassroom.paymybuddy.web.exception.DataNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TransferServiceImplTest {

    @Autowired
    TransferServiceImpl transferService;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IRelationDAO relationDAO;

    @MockBean
    ITransferDAO transferDAO;

    @MockBean
    IInternalTransferDAO internalTransferDAO;

    @MockBean
    IExternalTransferDAO externalTransferDAO;

    @MockBean
    IBankAccountDAO bankAccountDAO;

    User owner = new User(1, "Barack", "Obama", "usa@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));
    User buddy = new User(2, "George", "Bush", "mail@mail.com", "dudu", BigDecimal.ZERO, Timestamp.valueOf(LocalDateTime.now()));

    Relation relation = new Relation(owner, buddy);

    BankAccount bankAccount = new BankAccount("iban_obama", "bic_ obama", "CIC", "Compte courant", owner, Arrays.asList(new ExternalTransfer()));

    Transfer transfer = new Transfer();

    Role role = new Role("USER");

    ExternalTransferDTO externalTransferDTO = new ExternalTransferDTO(1, "iban_obama", BigDecimal.ONE, "usa@mail.com");
    InternalTransferDTO internalTransferDTO = new InternalTransferDTO();
    ExternalTransfer externalTransfer = new ExternalTransfer(1, BigDecimal.ONE, "test", Timestamp.valueOf(LocalDateTime.now()), "COMPLETED", BigDecimal.ONE, bankAccount);
    InternalTransfer internalTransfer = new InternalTransfer(owner, buddy);



    @Test
    void findInternalTransferByUser() {

        // GIVEN

        // WHEN
        when(internalTransferDAO.findAllBySenderID_EmailOrderByTransactionDateDesc("usa@mail.com")).thenReturn(Arrays.asList(internalTransfer));
        transferService.findInternalTransferByUser("usa@mail.com");
        verify(internalTransferDAO, times(1)).findAllBySenderID_EmailOrderByTransactionDateDesc("usa@mail.com");

        // THEN


    }

    @Test
    void findExternalTransferByUser() {

        // GIVEN

        // WHEN
        when(externalTransferDAO.findAllByBankAccount_User_EmailOrderByTransactionDateDesc("usa@mail.com")).thenReturn(Arrays.asList(externalTransfer));
        transferService.findExternalTransferByUser("usa@mail.com");
        verify(externalTransferDAO, times(1)).findAllByBankAccount_User_EmailOrderByTransactionDateDesc("usa@mail.com");


        // THEN


    }

    @Test
    void doInternalTransfer() throws SQLException {

        // GIVEN
        try {
            transferService.doInternalTransfer(internalTransferDTO);
            verify(transferDAO, times(0)).save(transfer);
            verify(userDAO, times(0)).save(owner);
            verify(relationDAO, times(1)).findByOwner_EmailAndBuddy_Email(any(), any());
        } catch (DataNotFoundException e) {
            assert (e.getMessage().contains("Les utilisateurs ne sont pas amis"));
        }
        // WHEN

        // THEN


    }

    @Test
    void doExternalTransfer() throws SQLException {

        // GIVEN

        // WHEN
        when(bankAccountDAO.findBankAccountByIbanAndUser_Email(externalTransferDTO.getIbanUser(), externalTransferDTO.getEmailUser())).thenReturn(bankAccount);
        transferService.doExternalTransfer(externalTransferDTO);
        verify(bankAccountDAO, times(1)).findBankAccountByIbanAndUser_Email(any(), any());
        // THEN


    }
}