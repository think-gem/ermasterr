package org.insightech.er.editor.view.action.category;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import org.insightech.er.ResourceString;
import org.insightech.er.editor.ERDiagramEditor;
import org.insightech.er.editor.controller.command.settings.ChangeSettingsCommand;
import org.insightech.er.editor.model.ERDiagram;
import org.insightech.er.editor.model.settings.Settings;
import org.insightech.er.editor.view.action.AbstractBaseAction;
import org.insightech.er.editor.view.dialog.category.CategoryManageDialog;

public class CategoryManageAction extends AbstractBaseAction {

    public static final String ID = CategoryManageAction.class.getName();

    public CategoryManageAction(final ERDiagramEditor editor) {
        super(ID, ResourceString.getResourceString("action.title.category.manage"), editor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Event event) {
        final ERDiagram diagram = getDiagram();

        final Settings settings = diagram.getDiagramContents().getSettings().clone();

        final CategoryManageDialog dialog = new CategoryManageDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), settings, diagram);

        if (dialog.open() == IDialogConstants.OK_ID) {
            final ChangeSettingsCommand command = new ChangeSettingsCommand(diagram, settings, true);
            this.execute(command);
        }
    }

}
