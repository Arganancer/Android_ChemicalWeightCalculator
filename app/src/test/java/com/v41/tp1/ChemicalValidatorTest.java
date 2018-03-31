package com.v41.tp1;

import com.v41.tp1.modele.PeriodicTable;
import com.v41.tp1.viewcontroler.StringWrapper;
import com.v41.tp1.viewcontroler.ChemicalValidator;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class ChemicalValidatorTest

{
    @org.junit.Test
    public void A_initialiser_AvecComposeChimiqueValideSansParenthese_DevraitRetournerVrai() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper messageUtilisateur = new StringWrapper();

        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("H"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("H2O"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("NaCl"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("O2"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("C11H23COOH"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("C12H22O11"), messageUtilisateur));
    }

    @org.junit.Test
    public void B_initialiser_AvecComposeChimiqueValideContenantParenthesesNonImbriquees_DevraitRetournerVrai() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper messageUtilisateur = new StringWrapper();

        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H)"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(NaCl)4"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(O2)3"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(CN)4(O2)3"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("CO(CH2OH)2"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("C18H24N2O6"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("NaCl(O3H5)2H4"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(CH3)2CO"), messageUtilisateur));
    }

    @org.junit.Test
    public void C_initialiser_AvecComposeChimiqueValideContenantParenthesesImbriquees_DevraitRetournerVrai() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper messageUtilisateur = new StringWrapper();

        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H(CN)4)5"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("B(Ar(CF3)2)4"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("B(Ar(CF3)2)4"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("((MgFe)7Si8(OH)22)"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(Al2Si2O5(OH)4)"), messageUtilisateur));
        assertTrue(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2SO4(Be)3(H2O))2"), messageUtilisateur));
    }

    @org.junit.Test
    public void D_initialiser_AvecMauvaiseUtilisationParentheses_DevraitRetournerFaux() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper messageUtilisateur = new StringWrapper();

        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula((")("), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H2("), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H)O("), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(NaCl))"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("((NaCl)"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula((")(H2N2)4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("()H2N2)4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2)N2)4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2))4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2)4)"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("((H2N2)4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2(N2)4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2()4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2)(4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(H2N2)4("), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("()"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(())"), messageUtilisateur));
    }

    @org.junit.Test
    public void E_initialiser_AvecComposesChimiquesInvalides_DevraitRetournerFaux() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper messageUtilisateur = new StringWrapper();

        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("h"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("nacl"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("CH1"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("Al0"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("BONDs007"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H002O"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("al"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("fe"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("Hu"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("abc"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula((" "), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("!"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H 2"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("Na+"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("3Al"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("3(Al)4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(3Al)4"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(3Al)"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("((3Al))"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("9"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("(3)2"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("2(3)"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula(("H2 O2"), messageUtilisateur));
        assertFalse(ChemicalValidator.INSTANCE.validateChemicalFormula((""), messageUtilisateur));
    }
}