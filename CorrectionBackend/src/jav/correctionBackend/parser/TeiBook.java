/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jav.correctionBackend.parser;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author flo
 */
class TeiBook extends Book {

    private final org.w3c.dom.Document document;
    private final ArrayList<Page> pages;

    public TeiBook(org.w3c.dom.Document document, File file) {
        super(null, file);
        this.document = document;
        this.pages = new ArrayList<>();
    }

    @Override
    public int getNumberOfPages() {
        return pages.size();
    }

    @Override
    public Page getPageAt(int i) {
        return pages.get(i);
    }

    public boolean isEmpty() {
        return pages.isEmpty();
    }

    public void add(Page page) {
        pages.add(page);
    }
}
