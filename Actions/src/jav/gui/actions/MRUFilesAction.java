package jav.gui.actions;

import jav.gui.cookies.CorrectionSystemReadyCookie;
import jav.gui.main.MRUFilesMenu;
import javax.swing.Action;
import javax.swing.JMenuItem;
import org.openide.util.Lookup;
import org.openide.util.Utilities;
import org.openide.util.actions.Presenter;

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
public final class MRUFilesAction extends ContextAction<CorrectionSystemReadyCookie> implements Presenter.Menu { //CallableSystemAction {

    public MRUFilesAction() {
        this(Utilities.actionsGlobalContext());
    }

    public MRUFilesAction(Lookup context) {
        super(context);
        putValue(NAME, java.util.ResourceBundle.getBundle("jav/gui/actions/Bundle").getString("MRUFiles"));
    }

    @Override
    public Class<CorrectionSystemReadyCookie> contextClass() {
        return CorrectionSystemReadyCookie.class;
    }

    @Override
    public void performAction(CorrectionSystemReadyCookie context) {
    }

    @Override
    public void setEnabled(boolean bln) {
        if(!bln) {
            MRUFilesMenu.findInstance().setenabled(false);
        }
    }

    @Override
    public boolean enable(CorrectionSystemReadyCookie context) {
        MRUFilesMenu.findInstance().setenabled(true);
        return true;
    }

    @Override
    public Action createContextAwareInstance(Lookup lkp) {
        return new MRUFilesAction(lkp);
    }

    /** {@inheritDoc}
    * Overide to provide SubMenu for MRUFiles (Most Recently Used Files)
     */
    @Override
    public JMenuItem getMenuPresenter() {
        return MRUFilesMenu.findInstance();
    }
}
