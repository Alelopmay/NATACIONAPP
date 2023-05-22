package org.example.Controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.Domain.Swimmer;
import org.example.dao.SwimmerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static javafx.beans.binding.Bindings.when;
import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControllerDeleteSwimmerTest {

    private ControllerDeleteSwimmer controller;

    @Mock
    private Button buttonDeleteSwimmer;
    @Mock
    private TextField intCodSwimmer;
    @Mock
    private SwimmerDAO swimmerDAO;
    @Mock
    private Swimmer swimmer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ControllerDeleteSwimmer();
        controller.ButtonDeleteSwimmer = buttonDeleteSwimmer;
        controller.IntCod_Swimmer = intCodSwimmer;
    }

    @Test
    void areFieldsFilledAndValid_emptyCod_ReturnsFalse() {
        when(intCodSwimmer.getText()).thenReturn("");
        assertFalse(controller.areFieldsFilledAndValid());
    }

    @Test
    void areFieldsFilledAndValid_invalidCod_ReturnsFalse() throws SQLException {
        when(intCodSwimmer.getText()).thenReturn("1");
        when(swimmerDAO.findById(1)).thenReturn(null);
        whenNew(SwimmerDAO.class).withNoArguments().thenReturn(swimmerDAO);
        assertFalse(controller.areFieldsFilledAndValid());
        verify(swimmerDAO).findById(1);
    }

    @Test
    void areFieldsFilledAndValid_validCod_ReturnsTrue() throws SQLException {
        when(intCodSwimmer.getText()).thenReturn("1");
        when(swimmerDAO.findById(1)).thenReturn(swimmer);
        whenNew(SwimmerDAO.class).withNoArguments().thenReturn(swimmerDAO);
        assertTrue(controller.areFieldsFilledAndValid());
        verify(swimmerDAO).findById(1);
    }

    @Test
    void deleteSwimmer_fieldsNotFilled_ShowErrorAlert() throws SQLException {
        when(intCodSwimmer.getText()).thenReturn("");
        controller.deleteSwimmer();
        verifyZeroInteractions(swimmerDAO);
        // Verificar que se muestre la alerta de error adecuada
    }

    @Test
    void deleteSwimmer_invalidCod_ShowErrorAlert() throws SQLException {
        when(intCodSwimmer.getText()).thenReturn("1");
        when(swimmerDAO.findById(1)).thenReturn(null);
        whenNew(SwimmerDAO.class).withNoArguments().thenReturn(swimmerDAO);
        controller.deleteSwimmer();
        verify(swimmerDAO).findById(1);
        // Verificar que se muestre la alerta de error adecuada
    }

    @Test
    void deleteSwimmer_validCod_DeleteSwimmerAndShowSuccessAlert() throws SQLException {
        when(intCodSwimmer.getText()).thenReturn("1");
        when(swimmerDAO.findById(1)).thenReturn(swimmer);
        whenNew(SwimmerDAO.class).withNoArguments().thenReturn(swimmerDAO);
        controller.deleteSwimmer();
        verify(swimmerDAO).findById(1);
        verify(swimmerDAO).delete(swimmer);
        // Verificar que se muestre la alerta de éxito adecuada
    }

    private Object whenNew(Class<SwimmerDAO> swimmerDAOClass) {
    }

    @Test
    void deleteSwimmer_databaseError_ShowErrorAlert() throws SQLException {
        when(intCodSwimmer.getText()).thenReturn("1");
        when(swimmerDAO.findById(1)).thenReturn(swimmer);
        when(swimmerDAO.delete(swimmer)).thenThrow(new SQLException());
        whenNew(SwimmerDAO.class).finalize().thenReturn(swimmerDAO);
        controller.deleteSwimmer();
        verify(swimmerDAO).findById(1);
        verify(swimmerDAO).delete(swimmer);
        // Verificar que se muestre la alerta de error adecuada
    }
}
