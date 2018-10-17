/*
 * The MIT License
 *
 * Copyright 2017 junichi11.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.junichi11.netbeans.modules.noextresolver;

import com.junichi11.netbeans.modules.noextresolver.parser.ParserFactory;
import com.junichi11.netbeans.modules.noextresolver.utils.ShebangUtils;
import com.junichi11.netbeans.modules.noextresolver.utils.Utils;
import java.beans.PropertyVetoException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.EditorRegistry;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.modules.csl.api.UiUtils;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.netbeans.spi.editor.document.OnSaveTask;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.text.Line;
import org.openide.util.RequestProcessor;
import com.junichi11.netbeans.modules.noextresolver.parser.spi.NoExtMIMEResolverParser;

/**
 *
 * @author junichi11
 */
public class ResolveMIMETypeOnSaveTask implements OnSaveTask {

    private final Context context;
    private static final RequestProcessor RP = new RequestProcessor(ResolveMIMETypeOnSaveTask.class);
    private static final Logger LOGGER = Logger.getLogger(ResolveMIMETypeOnSaveTask.class.getName());

    private ResolveMIMETypeOnSaveTask(Context context) {
        this.context = context;
    }

    @Override
    public void performTask() {
        Document document = context.getDocument();
        FileObject fileObject = NbEditorUtilities.getFileObject(document);
        if (!fileObject.getExt().isEmpty()) {
            return;
        }

        JTextComponent editor = EditorRegistry.lastFocusedComponent();
        if (editor == null) {
            return;
        }
        int caretPosition = editor.getCaretPosition();

        Line line = NbEditorUtilities.getLine(document, 0, false);
        String text = line.getText();
        if (!ShebangUtils.isShebang(text) && !Utils.isCommentLine(text)) {
            return;
        }

        String mimeType = null;
        List<NoExtMIMEResolverParser> parsers = ParserFactory.createParsers(text);
        for (NoExtMIMEResolverParser parser : parsers) {
            NoExtMIMEResolverParser.Result result = parser.parse().getResult();
            if (result.getMimeType() != null) {
                mimeType = result.getMimeType();
                break;
            }
        }
        if (mimeType == null) {
            return;
        }

        String originalMimeType = NbEditorUtilities.getMimeType(document);
        if(mimeType.equals(originalMimeType)) {
            return;
        }

        reopen(document, fileObject, caretPosition);
    }

    private void reopen(Document document, FileObject fileObject, int caretPosition) {
        DataObject dataObject = NbEditorUtilities.getDataObject(document);
        RP.schedule(() -> {
            try {
                dataObject.setValid(false);
                UiUtils.open(fileObject, caretPosition);
            } catch (PropertyVetoException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void runLocked(Runnable r) {
        r.run();
    }

    @Override
    public boolean cancel() {
        return true;
    }

    @MimeRegistration(mimeType = "", service = OnSaveTask.Factory.class, position = Integer.MAX_VALUE)
    public static final class FactoryImpl implements Factory {

        @Override
        public OnSaveTask createTask(Context context) {
            return new ResolveMIMETypeOnSaveTask(context);
        }
    }
}
