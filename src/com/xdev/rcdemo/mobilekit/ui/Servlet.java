
package com.xdev.rcdemo.mobilekit.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.xdev.communication.XdevServlet;

@WebServlet(value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = MainUI.class,
	widgetset = "com.xdev.rcdemo.mobilekit.ui.widgetset.rapidclipse_demo_mobilekitWidgetset")
public class Servlet extends XdevServlet {
	public Servlet() {
		super();
	}
}