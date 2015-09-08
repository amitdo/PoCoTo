package jav.gui.actions;

import jav.gui.cookies.PageCookie;
import javax.swing.Action;
import org.openide.util.Lookup;
import org.openide.util.Utilities;

/**
 *Copyright (c) 2012, IMPACT working group at the Centrum für Informations- und Sprachverarbeitung, University of Munich.
 *All rights reserved.

 *Redistribution and use in source and binary forms, with or without
 *modification, are permitted provided that the following conditions are met:

 *Redistributions of source code must retain the above copyright
 *notice, this list of conditions and the following disclaimer.
 *Redistributions in binary form must reproduce the above copyright
 *notice, this list of conditions and the following disclaimer in the
 *documentation and/or other materials provided with the distribution.

 *THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 *IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 *TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 *PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 *HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * This file is part of the ocr-postcorrection tool developed
 * by the IMPACT working group at the Centrum für Informations- und Sprachverarbeitung, University of Munich.
 * For further information and contacts visit http://ocr.cis.uni-muenchen.de/
 * 
 * @author thorsten (thorsten.vobl@googlemail.com)
 */
public final class TenPageBackward extends ContextAction<PageCookie> {

    public TenPageBackward() {
        this(Utilities.actionsGlobalContext());
    }

    public TenPageBackward(Lookup context) {
        super(context);
        putValue(NAME, java.util.ResourceBundle.getBundle("jav/gui/actions/Bundle").getString("ten_page_back"));
        //putValue(SMALL_ICON, new ImageIcon( ImageUtilities.loadImage("jav/test/groß.gif")));
        putValue("iconBase", "jav/gui/actions/left10.png");
    }

    @Override
    public Class<PageCookie> contextClass() {
        return PageCookie.class;
    }

    @Override
    public void performAction(PageCookie context) {
        int n = context.getPageN();
        int i = 0;
        if (n > 10)
            i = n - 11;
        context.gotoPage(i);
    }

    @Override
    public Action createContextAwareInstance(Lookup lkp) {
        return new TenPageBackward(lkp);
    }

    @Override
    public boolean enable(PageCookie context) {
        if(context.isReady() && context.getPageN() > 10) {
            return true;
        } else {
            return false;
        }
    }
}
