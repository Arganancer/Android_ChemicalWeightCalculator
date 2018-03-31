package com.v41.tp1.modele;

/**
 * Enum representing the 3 different types of Tokens.
 *      CHEMICAL_ELEMENT_SYMBOL:
 *          A one or two character string representing a chemical
 *          element from the periodic table.
 *              ex: H, Li, Be.
 *
 *      NUMBER:
 *          An integer representing an amount of times a chemical
 *          element is present in the chemical formula. Acts as a
 *          multiplier for either a single element, or for a group
 *          of elements inside parenthesis.
 *
 *      PARENTHESIS:
 *          Can be either an opening or a closing parenthesis.
 */
public enum TokenType
{
    CHEMICAL_ELEMENT_SYMBOL,
    NUMBER,
    PARENTHESIS
}