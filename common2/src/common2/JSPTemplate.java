package common2;

import java.util.List;

import javax.swing.JFrame;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import springbean.AAAConfig;
import template.UITemplate;
import template.screen.AbstractTemplatePanel;
import template.screen.AbstractTemplatePanel.FieldCompose;
import template.screen.TemplateParserUtil;
import util.PanelUtil;

import common2.UITemplateCreator;

public class JSPTemplate {

    JWebBrowser webBrowser = new JWebBrowser();
	UITemplateCreator template;

    protected void updateHTMLUI() {
		webBrowser.setHTMLContent(template.getCode());
    }

    public static void main(String [] args) {
        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();

    	JSPTemplate t = new JSPTemplate();
		t.runFrame();
	}

	private String convertToJSP(String str) {
        AAAConfig.getInstance();
        Class bean = PanelUtil.getBeanClass(str);
        UITemplate template = (UITemplate) bean.getAnnotation(UITemplate.class);
        AbstractTemplatePanel tmp;
		try {
			tmp = (AbstractTemplatePanel) template.template().newInstance();
	        TemplateParserUtil parser = TemplateParserUtil.getInstance(tmp);
	        StringBuffer sb = new StringBuffer();
	        StringBuffer js = new StringBuffer();
	        js.append("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js'></script>\n");
	        js.append("<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/jquery-ui.min.js' type='text/javascript'></script>\n");
	        js.append("<script src='http://fgelinas.com/code/timepicker/jquery.ui.timepicker.js?v=0.1.0' type='text/javascript'></script>\n");
	        js.append("<script>\n$(document).ready(function() {\n");
	        StringBuffer css = new StringBuffer();
	        css.append("<link rel='stylesheet' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.11/themes/base/jquery-ui.css' type='text/css' media='all' />\n");
	        css.append("<link rel='stylesheet' href='http://static.jquery.com/ui/css/demo-docs-theme/ui.theme.css' type='text/css' media='all' />\n");
	        css.append("<link rel='stylesheet' href='http://fgelinas.com/code/timepicker/jquery-ui-timepicker.css?v=0.1.0' type='text/css' media='all' />\n");
	        css.append("<style>\n");
        	css.append("\n.beanForm span{\n\tfloat: left; \n\twidth: 100px;\n}");
	        List<FieldCompose> lst = parser.getAllFields(bean, tmp);
	        sb.append("<div id='bean").append(str).append("' class='beanForm'>\n\t<form id='form").append(str).append("'>\n");
	        for (FieldCompose comp: lst) {
	        	String type = comp.display.type();
	        	css.append("\n#div").append(comp.field.getName()).append(" {\n}");
	        	if (type.equals("Any")) {
		            Class cls = comp.field.getType();
		            if (cls.getSimpleName().equalsIgnoreCase("Date")) {
			        	sb.append("\t\t").append(renderAny(comp)).append("\n");  
			        	js.append("\n\t$('#").append(comp.field.getName()).append("').datepicker();\n");
		            } else if (cls.getSimpleName().equalsIgnoreCase("Boolean")) {
			        	sb.append("\t\t").append(renderCheckBox(comp)).append("\n");
		            } else {
			        	sb.append("\t\t").append(renderAny(comp)).append("\n");  
		            }
	        	}
	        	else if (type.equals("Text")) {
		        	sb.append("\t\t").append(renderText(comp)).append("\n");
	        	}
	        	else if (type.equals("TextArea")) {
		        	sb.append("\t\t").append(renderTextArea(comp)).append("\n");
	        	}
	        	else if (type.equals("Text2")) {
		        	sb.append("\t\t").append(renderText2(comp)).append("\n");
	        	}
	        	else if (type.equals("TextDouble")) {
		        	sb.append("\t\t").append(renderTextDouble(comp)).append("\n");
		        	js.append("\n\t$('#").append(comp.field.getName()).append("').filter_input({regex:'[0-9.]'});");
	        	}
	        	else if (type.equals("TextInt")) {
		        	sb.append("\t\t").append(renderTextInt(comp)).append("\n");
		        	js.append("\n\t$('#").append(comp.field.getName()).append("').filter_input({regex:'[0-9]'});");
	        	}
	        	else if (type.equals("Time")) {
		        	sb.append("\t\t").append(renderTime(comp)).append("\n");
		        	js.append("\n\t$('#").append(comp.field.getName()).append("').timepicker();\n");
	        	}
	        	else if (type.equals("Calendar")) {
		        	sb.append("\t\t").append(renderCalendar(comp)).append("\n");
		        	js.append("\n\t$('#").append(comp.field.getName()).append("').datepicker();");
	        	}
	        	else if (type.equals("CheckBox")) {
		        	sb.append("\t\t").append(renderCheckBox(comp)).append("\n");
	        	}
	        	else if (type.equals("Combo")) {
		        	sb.append("\t\t").append(renderCombo(comp)).append("\n");
	        	}
	        	else if (type.equals("LabelHTML")) {
		        	sb.append("\t\t").append(renderLabelHTML(comp)).append("\n");
	        	}
	        	else if (type.equals("NumberCombo")) {
		        	sb.append("\t\t").append(renderNumberCombo(comp)).append("\n");
	        	}
	        	else if (type.equals("PopSearch")) {
		        	sb.append("\t\t").append(renderPopSearch(comp)).append("\n");
	        	}
	        }
	        js.append("\n});\n</script>");
	        css.append("\n</style>");
	        sb.append("\t</form>\n</div>\n");
	        return toHTML(sb.toString(), js.toString(), css.toString());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Error in bean -> "+str;
	}

	protected String toHTML(String str, String js, String css) {
		StringBuffer sb = new StringBuffer();
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n<html>\n<head>\n").append(css).append(js).append("</head>\n<body>\n\n").append(str).append("\n\n</body>\n</html>");
		return sb.toString();
	}

	protected String renderAny(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='divText'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='inputText'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderText(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderTextArea(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderText2(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <textarea id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'></textarea>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderTextDouble(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderTextInt(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderTime(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderCalendar(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderCheckBox(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='checkbox' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderCombo(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span>");
		String[] arr = f.display.modelCombo();
		sb.append("<select id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'>");
		for (String s:arr) {
			sb.append("<option value='").append(s).append("'>").append(s).append("</option>");
		}
		sb.append("</select>");
		sb.append("</div>");
		return sb.toString();
	}
	protected String renderLabelHTML(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	protected String renderNumberCombo(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span>");
		sb.append("<select id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("'>");
		for (int i=f.display.startCount(); i<=f.display.endCount(); i++) {
			sb.append("<option value='").append(i).append("'>").append(i).append("</option>");
		}
		sb.append("</select>");
		sb.append("</div>");
		return sb.toString();
	}
	protected String renderPopSearch(FieldCompose f) {
		StringBuffer sb = new StringBuffer();
		sb.append("<div id='div").append(f.field.getName()).append("' class='div").append(f.display.type()).append("'>")
			.append("<span>").append(PanelUtil.getLabel(f.field, f.display)).append("</span> <input type='text' id='").append(f.field.getName()).append("' class='input").append(f.display.type()).append("' entity='").append(f.display.linktoBean().getSimpleName()).append("'/>")
			.append("</div>");
		return sb.toString();
	}
	private void runFrame() {
		template = new UITemplateCreator() {
			@Override
			protected String convertBean(String bean) {
				String str = convertToJSP(bean);
				webBrowser.setHTMLContent(str);
				return str;
			}

			@Override
			protected void updateRenderUI() {
				updateHTMLUI();
			}
		};
		template.getDesktop().add(webBrowser);
		JFrame frame = new JFrame("UI Template Creator");
		frame.getContentPane().add(template);
//		frame.pack();
		frame.setVisible(true);
		NativeInterface.runEventPump();
		webBrowser.setVisible(true);
	}
}
