package com.v41.tp1.modele;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 * This class contains every chemical element found in the periodic table.
 * The content is loaded from the chemicalElementEnglish.txt file.
 */
public enum PeriodicTable
{
    /**
     * The instance of the PeriodicTable class.
     */
    INSTANCE;

    /**
     * This TreeMap contains every chemical element from the periodic table.
     *      String:
     *          The key to the table. it represents a chemical element's associated
     *          symbol.
     *              ex: H, Li, Be.
     *
     *      ChemicalElement:
     *          Contains all of the information related to the chemical element.
     *              @see ChemicalElement
     */
    private TreeMap<String,ChemicalElement> elements;

    /**
     * Constructor for the class PeriodicTable. This constructor is private
     * to ensure the integrity of the class, since the class is a Singleton.
     *
     * In this constructor, we initialize the local TreeMap attribute #elements.
     */
    private PeriodicTable()
    {
        elements = new TreeMap<String, ChemicalElement>();
    }

    /**
     * This method parses the whole periodic table from a text file and creates a
     * new ChemicalElement for each element. The ChemicalElements are stored
     * in the local variable #elements.
     *
     * @param inputStream A string containing the name and extension of the file
     *                    to be parsed.
     */
    public void loadPeriodicTable(InputStream inputStream)
    {
        BufferedReader br = null;

        try
        {
            br = new BufferedReader(new InputStreamReader(inputStream));

            String line = br.readLine();

            while (line != null)
            {
                ChemicalElement element = new ChemicalElement();
                element.loadChemicalElement(line);

                if (element.getAtomicWeight() > 0)
                {
                    elements.put(element.getSymbol(), element);
                }

                line = br.readLine();
            }

            br.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

    /**
     * Accessor method for a single element from the local TreeMap attribute.
     * This class does not manage whether or not the symbol given to it is valid.
     *
     * @param symbol The symbol representation of the element to be returned.
     * @return A single ChemicalElement from the local #elements attribute.
     *      @see #elements
     */
    ChemicalElement getChemicalElement(String symbol)
    {
        return elements.get(symbol);
    }

    /**
     * This method validates whether or not a given symbol is present in the
     * #elements TreeMap.
     *
     * @param symbol The symbol to be validated.
     * @return Returns true if the element exists, else false.
     */
    public boolean isSymbol(String symbol)
    {
        ChemicalElement value = elements.get(symbol);

        if (value == null)
        {
            return false;
        }
        return true;
    }
}
