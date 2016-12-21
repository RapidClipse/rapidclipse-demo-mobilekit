package com.xdev.rcdemo.mobilekit.ui;

import java.util.Arrays;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.xdev.mobile.service.vibrate.VibrateService;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevTextField;
import com.xdev.ui.XdevView;

public class VibrationView extends XdevView {

	/**
	 *
	 */
	public VibrationView() {
		super();
		this.initUI();
	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		super.enter(event);
	
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #cmdVibrateSimple}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmdVibrateSimple_buttonClick(final Button.ClickEvent event) {

		VibrateService.getInstance().vibrate(1000);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #cmdVibratePattern}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmdVibratePattern_buttonClick(final Button.ClickEvent event) {

		final String[] elements = this.txtPattern.getValue().split("\\s");
		final int[] pattern = Arrays.stream(elements).mapToInt(value->{
			try {
				return Integer.parseInt(value);
			} catch (final NumberFormatException e) {
				return 0;
			}
		}).toArray();
		VibrateService.getInstance().vibrate(pattern);
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.gridLayout = new XdevGridLayout();
		this.navBar = new NavBar();
		this.cmdVibrateSimple = new XdevButton();
		this.txtPattern = new XdevTextField();
		this.cmdVibratePattern = new XdevButton();

		this.navBar.setTitle("Vibration");
		this.cmdVibrateSimple.setCaption("Vibrate 1 sec");
		this.txtPattern.setCaption("Pattern:");
		this.txtPattern.setValue("500 200 300");
		this.cmdVibratePattern.setCaption("Vibrate Pattern");

		this.gridLayout.setColumns(1);
		this.gridLayout.setRows(5);
		this.navBar.setWidth(100, Unit.PERCENTAGE);
		this.navBar.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.navBar, 0, 0);
		this.cmdVibrateSimple.setWidth(100, Unit.PERCENTAGE);
		this.cmdVibrateSimple.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.cmdVibrateSimple, 0, 1);
		this.txtPattern.setWidth(100, Unit.PERCENTAGE);
		this.txtPattern.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.txtPattern, 0, 2);
		this.cmdVibratePattern.setWidth(100, Unit.PERCENTAGE);
		this.cmdVibratePattern.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.cmdVibratePattern, 0, 3);
		this.gridLayout.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout_vSpacer = new CustomComponent();
		gridLayout_vSpacer.setSizeFull();
		this.gridLayout.addComponent(gridLayout_vSpacer, 0, 4, 0, 4);
		this.gridLayout.setRowExpandRatio(4, 1.0F);
		this.gridLayout.setSizeFull();
		this.setContent(this.gridLayout);
		this.setSizeFull();

		this.cmdVibrateSimple.addClickListener(event -> this.cmdVibrateSimple_buttonClick(event));
		this.cmdVibratePattern.addClickListener(event -> this.cmdVibratePattern_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevButton cmdVibrateSimple, cmdVibratePattern;
	private NavBar navBar;
	private XdevGridLayout gridLayout;
	private XdevTextField txtPattern;
	// </generated-code>

}
