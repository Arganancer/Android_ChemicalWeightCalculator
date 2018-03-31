package com.v41.tp1.modele;

/**
 * This class represents a syntaxical part of a chemical formula.
 * Its purpose is to facilitate calculations at a later moment.
 */
public class Token
{
    /**
     * The value of the Token. If the Token's type is chemical element, the value
     * will then specify the symbol of the element. If it's a number, the value will
     * represent the degree of multiplication, and if it's a parenthesis, the value
     * will specify whether it's an opening or a closing parenthesis.
     */
    private String tokenContent;

    /**
     * Defines the token's type.
     *      @see TokenType
     */
    TokenType tokenType;

    /**
     * When a Token is constructed, its content and type are also initialised.
     * These values cannot be modified post construction.
     *
     * @param tokenContent The value of the Token.
     *      @see #tokenContent
     * @param tokenType The token's type.
     *      @see TokenType
     */
    public Token(String tokenContent, TokenType tokenType)
    {
        this.tokenContent = tokenContent;
        this.tokenType = tokenType;
    }

    /**
     * Accessor method for the local #tokenContent attribute.
     *
     * @return Returns the content of the #tokenContent attribute.
     *      @see #tokenContent
     */
    public String getTokenContent()
    {
        return tokenContent;
    }

    /**
     * Accessor method for the local #tokenType attribute.
     *
     * @return Returns the token's type
     *      @see TokenType
     */
    public TokenType getTokenType()
    {
        return tokenType;
    }
}