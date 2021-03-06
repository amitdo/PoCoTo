package jav.gui.actions;

import jav.gui.cookies.DocumentLoadedCookie;
import jav.gui.dialogs.CustomErrorDialog;
import jav.gui.dialogs.UnsavedChangesDialog;
import jav.gui.main.MainController;
import jav.logging.log4j.Log;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import javax.swing.Action;
import javax.swing.JFileChooser;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressRunnable;
import org.netbeans.api.progress.ProgressUtils;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Utilities;

/**
 * Copyright (c) 2012, IMPACT working group at the Centrum für Informations- und
 * Sprachverarbeitung, University of Munich. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * This file is part of the ocr-postcorrection tool developed by the IMPACT
 * working group at the Centrum für Informations- und Sprachverarbeitung,
 * University of Munich. For further information and contacts visit
 * http://ocr.cis.uni-muenchen.de/
 *
 * @author thorsten (thorsten.vobl@googlemail.com)
 */
public class ExportAll extends ContextAction<DocumentLoadedCookie> {

    public ExportAll() {
        this(Utilities.actionsGlobalContext());
    }

    public ExportAll(Lookup context) {
        super(context);
        putValue(NAME, java.util.ResourceBundle.getBundle("jav/gui/actions/Bundle")
                .getString("exportall"));
    }

    @Override
    public Class<DocumentLoadedCookie> contextClass() {
        return DocumentLoadedCookie.class;
    }

    @Override
    public void performAction(DocumentLoadedCookie context) {
        if (MainController.findInstance().hasUnsavedChanges()) {
            Object retval = new UnsavedChangesDialog().showDialog();
            if (retval.equals(java.util.ResourceBundle.getBundle("jav/gui/dialogs/Bundle").getString("save"))) {
                try {
                    MainController.findInstance().getSaver().save();
                    doIt();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else if (retval.equals(java.util.ResourceBundle.getBundle("jav/gui/dialogs/Bundle").getString("discard"))) {
                doIt();
            }
        } else {
            doIt();
        }
    }

    private void doIt() {
        JFileChooser jfc = new JFileChooser(getBaseDirectory());
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                File f = jfc.getSelectedFile();
                NativeMethodRunner runner = new NativeMethodRunner(
                        f.getCanonicalPath()
                );
                int retval = ProgressUtils.showProgressDialogAndRun(runner, java.util.ResourceBundle.getBundle("jav/gui/main/Bundle").getString("exporting"), true);
                if (retval == 0) {
                    new CustomErrorDialog().showDialog("Error while exporting the document!");
                }
            } catch (IOException ex) {
                new CustomErrorDialog().showDialog("Error while exporting the document: " + ex.getLocalizedMessage());
            }
        }
    }

    private File getBaseDirectory() {
        String dbpath = MainController.findInstance()
                .getDocumentProperties()
                .getProperty("databasepath", "");
        if (!"".equals(dbpath)) {
            File f = new File(dbpath);
            if (f.getParent() != null) {
                return new File(f.getParent());
            }
        }
        return new File(System.getProperty("user.dir", ""));
    }

    private class NativeMethodRunner implements ProgressRunnable<Integer> {

        private final String fromDir, toDir, fileType;

        public NativeMethodRunner(String toDir) {
            this.toDir = toDir;
            fromDir = MainController.findInstance()
                    .getDocumentProperties()
                    .getProperty("xmlbasepath", "");
            fileType = MainController.findInstance()
                    .getDocumentProperties()
                    .getProperty("filetype", "");
        }

        @Override
        public Integer run(ProgressHandle ph) {
            try {
                ph.progress(ResourceBundle.getBundle("jav/gui/main/Bundle")
                        .getString("exporting"));
                ph.setDisplayName(ResourceBundle.getBundle("jav/gui/main/Bundle")
                        .getString("exporting"));
                MainController.findInstance().getDocument().exportAll(
                        fromDir,
                        toDir,
                        fileType
                );
                return 1;
            } catch (Exception e) {
                Log.error(this, e);
                return 0;
            }
        }
    }

    @Override
    public boolean enable(DocumentLoadedCookie context) {
        return true;
    }

    @Override
    public Action createContextAwareInstance(Lookup lkp) {
        return new ExportAll();
    }
}
