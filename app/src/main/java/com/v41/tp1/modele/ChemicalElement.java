package com.v41.tp1.modele;

/**
 * This class represents a chemical element from the periodic table.
 *      Ex: Hydrogen is the first element in the periodic table, its
 *      symbol is "H", and its atomic weight is 1.00794.
 *          name = Hydrogen
 *          symbol = H
 *          noElement = 1
 *          atomicWeight = 1.00794
 */
public class ChemicalElement
{
    /**
     * The name of the chemical element.
     *      Ex: Hydrogen
     */
    private String name;

    /**
     * The symbol representation of the chemical element.
     *      Ex: H
     */
    private String symbol;

    /**
     * The element's position in the periodic table.
     */
    private int noElement;

    /**
     * The chemical element's atomicWeight.
     */
    private double atomicWeight;

    /**
     * The default constructor for the ChemicalElement class.
     * Strings are initialised to be empty, and numeric values
     * are initialised to 0.
     */
    public ChemicalElement()
    {
        this("", "", 0, 0);
    }

    /**
     * The overloaded constructor for the ChemicalElement class.
     * All local attributes for a chemical element are initialised via parameters
     * when using the constructor.
     *
     * @param symbol The symbol representation of the chemical element.
     * @param name The name of the chemical element.
     * @param noElement The element's position in the periodic table.
     * @param atomicWeight The chemical element's atomicWeight.
     */
    public ChemicalElement(String symbol, String name, int noElement, double atomicWeight)
    {
        this.symbol = symbol;
        this.name = name;
        this.noElement = noElement;
        this.atomicWeight = atomicWeight;
    }

    /**
     * This method accepts an unformatted chemical element in the form
     * of a string where each attribute is separated by a comma.
     * The method extracts and parses each attribute from the string, then
     * replaces the local attributes with the results of the extraction.
     *
     * The string accepted by this  method must be formatted in the
     * following manner:
     *      "name,symbol,elementNumber,atomicWeight"
     *
     * @param chemicalElement A string containing all characteristics of
     *                        a chemical element.
     */
    public void loadChemicalElement(String chemicalElement)
    {
        int indexStart = 0;
        int indexEnd = chemicalElement.indexOf(',', indexStart);
        name = chemicalElement.substring(indexStart, indexEnd);

        indexStart = indexEnd + 1;
        indexEnd = chemicalElement.indexOf(',', indexStart);
        symbol = chemicalElement.substring(indexStart, indexEnd);

        indexStart = indexEnd + 1;
        indexEnd = chemicalElement.indexOf(',', indexStart);
        noElement = Integer.parseInt(chemicalElement.substring(indexStart, indexEnd));

        indexStart = indexEnd + 1;
        atomicWeight = Double.parseDouble(chemicalElement.substring(indexStart));
    }

    /**
     * Accessor method for a chemical element's symbol.
     *
     * @return The chemical element's symbol.
     */
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * Accessor method for a chemical element's name.
     *
     * @return The chemical element's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Accessor method for the chemical element's position in the periodic table.
     *
     * @return The chemical element's position in the periodic table.
     */
    public int getNoElement()
    {
        return noElement;
    }

    /**
     * Accessor method for the chemical element's atomic weight.
     *
     * @return The chemical element's atomic weight.
     */
    public double getAtomicWeight()
    {
        return atomicWeight;
    }

    /**
     * Method that returns a formatted string containing user readable information
     * about the chemical element.
     *
     * @return string containing user readable information about the chemical element.
     */
    @Override
    public String toString()
    {
        return "Element nb. " + noElement + ": " + name + " (" + symbol + "), Atomic weight: " + atomicWeight;
    }
}
