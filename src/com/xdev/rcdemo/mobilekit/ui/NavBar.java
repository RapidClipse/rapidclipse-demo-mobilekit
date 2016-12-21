package com.xdev.rcdemo.mobilekit.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevHorizontalLayout;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.navigation.Navigation;

public class NavBar extends XdevHorizontalLayout {

	/**
	 *
	 */
	public NavBar() {
		super();
		this.initUI();
	}


	public void setTitle(final String title)
	{
		this.lblTitle.setValue(title);
	}


	public String getTitle()
	{
		return this.lblTitle.getValue();
	}


	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #cmdHome}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmdHome_buttonClick(final Button.ClickEvent event) {
		Navigation.to("").navigate();
	}


	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.lblTitle = new XdevLabel();
		this.cmdHome = new XdevButton();

		this.setMargin(new MarginInfo(false));
		this.lblTitle.setStyleName("huge colored");
		this.lblTitle.setValue("Title");
		this.cmdHome.setIcon(FontAwesome.ANGLE_DOUBLE_LEFT);
		this.cmdHome.setCaption("Back");
		this.cmdHome.setStyleName("borderless");

		this.lblTitle.setWidth(100, Unit.PERCENTAGE);
		this.lblTitle.setHeight(-1, Unit.PIXELS);
		this.addComponent(this.lblTitle);
		this.setComponentAlignment(this.lblTitle, Alignment.MIDDLE_CENTER);
		this.setExpandRatio(this.lblTitle, 10.0F);
		this.cmdHome.setSizeUndefined();
		this.addComponent(this.cmdHome);
		this.setComponentAlignment(this.cmdHome, Alignment.MIDDLE_CENTER);
		this.setWidth(100, Unit.PERCENTAGE);
		this.setHeight(-1, Unit.PIXELS);

		this.cmdHome.addClickListener(event -> this.cmdHome_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevButton cmdHome;
	private XdevLabel lblTitle;
	// </generated-code>

}
