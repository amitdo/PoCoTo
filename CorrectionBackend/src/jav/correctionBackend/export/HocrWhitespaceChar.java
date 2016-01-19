/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jav.correctionBackend.export;

/**
 * This class represents HOCR Whitespace chars (which are not explicitly in the
 * file). Any instance of this class *must* lie between two token.
 *
 * @author finkf
 */
public class HocrWhitespaceChar extends AbstractHocrChar {

    private static final String WS = " ";

    private final HocrToken prevToken, nextToken;

    /**
     * Construct an instance.
     *
     * @param prev the previous token. Not null.
     * @param next the next token. Not null
     */
    public HocrWhitespaceChar(HocrToken prev, HocrToken next) {
        assert (prev != null);
        assert (next != null);
        this.prevToken = prev;
        this.nextToken = next;
        setupPrevAndNextChar();
    }

    private void setupPrevAndNextChar() {
        HocrChar prevChar = prevToken.getLastChar();
        HocrChar nextChar = nextToken.getFirstChar();
        this.setPrev(prevChar);
        prevChar.setNext(this);
        this.setNext(nextChar);
        nextChar.setPrev(this);
    }

    @Override
    public BoundingBox getBoundingBox() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isSuspicious() {
        return false;
    }

    @Override
    public String getChar() {
        return WS;
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Char substitute(String c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Char append(String c) {
        return nextToken.get(0).prepend(c);
    }

    @Override
    public Char prepend(String c) {
        return prevToken.get(prevToken.size() - 1).append(c);
    }
}
