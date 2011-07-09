/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package template.screen.component;

import component.LabelPallete;

/**
 *
 * @author Entokwaa
 */
public class MergePanelRenderer extends AbstractComponentRenderer {
    LabelPallete f = new LabelPallete();

    @Override
	public Object getRenderedLabel() {
		return f;
	}

	@Override
    public Object getRenderedField() {
        return f;
    }
}