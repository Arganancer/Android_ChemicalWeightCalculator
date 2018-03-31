package com.v41.tp1;

import com.v41.tp1.modele.ChemicalComposition;
import com.v41.tp1.modele.PeriodicTable;
import com.v41.tp1.viewcontroler.StringWrapper;
import com.v41.tp1.viewcontroler.ChemicalValidator;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;


public class ChemicalCompositionTest
{
    private final double PRECISION = 0.005;  //Si jamais ça ne passe pas descendez la précision
                                             // 0.01 devrait fonctionner avec tout le monde
    @org.junit.Test
    public void F_getWeight_AvecChemicalCompositionSansParenthese_DevraitRetournerLePoidsCorrespondant() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper sw = new StringWrapper();
        ChemicalComposition ChemicalComposition = new ChemicalComposition();

        ChemicalValidator.INSTANCE.validateChemicalFormula("H", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 1.007947, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("H2O", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 18.01532, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("NaCl", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 58.4430, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("O2", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 31.99886, PRECISION);
    }

    @org.junit.Test
    public void G_getWeight_AvecChemicalCompositionContenantParenthesesNonImbriquees_DevraitRetournerLePoidsCorrespondant() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper sw = new StringWrapper();
        ChemicalComposition ChemicalComposition = new ChemicalComposition();

        ChemicalValidator.INSTANCE.validateChemicalFormula("(NaCl)4", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 233.7719, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("(O2)3", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 95.99658, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("CO(CH2OH)2", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 90.07831, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("(CH3)2CO", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 58.07945, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("NaCl(O3H5)2H4", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 168.5508, PRECISION);
    }

    @org.junit.Test
    public void H_getWeight_AvecChemicalCompositionContenantParenthesesImbriquées_DevraitRetournerLePoidsCorrespondant() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper sw = new StringWrapper();
        ChemicalComposition ChemicalComposition = new ChemicalComposition();

        ChemicalValidator.INSTANCE.validateChemicalFormula("(H(CN)4)5", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 525.3897, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("(CN)4(O2)3", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 200.0666, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("B(Ar(CF3)2)4", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 722.6520, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("(Al2Si2O5(OH)4)", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 258.1608, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("(Ca4Si2O6(CO3)(OHF))2", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 816.9922, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("(H2SO4(Be)3(H2O))2", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 286.2620, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("B(Ar(CF3)2)4", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 722.6520, PRECISION);
    }

    @org.junit.Test
    public void I_getWeight_AvecChemicalCompositionContenantNombrePlusGrandQue9_DevraitRetournerLePoidsCorrespondant() throws Exception
    {
        PeriodicTable.INSTANCE.loadPeriodicTable(new FileInputStream(new File(".\\app\\src\\main\\res\\raw\\PeriodicTable.txt")));
        StringWrapper sw = new StringWrapper();
        ChemicalComposition ChemicalComposition = new ChemicalComposition();

        ChemicalValidator.INSTANCE.validateChemicalFormula("((MgFe)7Si8(OH)22)", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 1159.8984, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("(C18H24N2O6)", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 364.3948, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("C11H23COOH", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 200.3189, PRECISION);

        ChemicalValidator.INSTANCE.validateChemicalFormula("C12H22O11", sw);
        ChemicalComposition.initialise(ChemicalValidator.INSTANCE.getFormulaTokens());
        assertEquals(ChemicalComposition.getWeight(), 342.2979, PRECISION);
    }
}