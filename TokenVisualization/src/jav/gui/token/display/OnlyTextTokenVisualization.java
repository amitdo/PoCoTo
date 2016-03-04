package jav.gui.token.display;

import jav.correctionBackend.Token;
import jav.gui.main.MainController;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;

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
public class OnlyTextTokenVisualization extends TokenVisualization {

    public OnlyTextTokenVisualization(Token t, int fontSize) {
        super();
        this.tokenID = t.getID();
        this.init(t, fontSize);
    }

    private void init(Token t, int fontSize) {

        this.hasImage = false;
        this.setBackground(Color.white);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        this.tokenTextLabel = new TokenTextLabel(t);
        this.tokenTextLabel.setBackground(Color.white);
        this.tokenTextLabel.setFont(MainController.findInstance().getMainFont(fontSize));

        this.add(Box.createVerticalGlue());
        this.add(tokenTextLabel);
        instance = this;

    }

    @Override
    public void calculateSizeNormMode() {
        this.setPreferredSize(tokenTextLabel.getPreferredSize());
        this.setMaximumSize(tokenTextLabel.getPreferredSize());
    }

    @Override
    public void calculateSizeEditMode() {
        this.setPreferredSize(box.getPreferredSize());
        this.setMaximumSize(box.getPreferredSize());
    }
}
