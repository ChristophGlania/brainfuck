package org.birenheide.bf.help;

import org.eclipse.help.ILiveHelpAction;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ActivHelpOpenViewAction implements ILiveHelpAction {
	
	private String viewId = null;

	@Override
	public void run() {
		if (viewId == null) {
			return;
		}
		
		Display.getDefault().syncExec(new Runnable() {
			@Override
			public void run() {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				if (window != null) {
					 Shell shell = window.getShell();
                     shell.setMinimized(false);
                     shell.forceActive();
                     try {
						window.getActivePage().showView(viewId);
					} 
                     catch (PartInitException ex) {
						HelpActivator.getDefault().logError("View could not be opened: " + viewId, ex);
					}
				}
			}
		});
	}

	@Override
	public void setInitializationString(String data) {
		this.viewId = data;
	}

}
