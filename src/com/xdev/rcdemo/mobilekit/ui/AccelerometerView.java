package com.xdev.rcdemo.mobilekit.ui;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClientConnector;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.xdev.mobile.service.accelerometer.Acceleration;
import com.xdev.mobile.service.accelerometer.AccelerometerOptions;
import com.xdev.mobile.service.accelerometer.AccelerometerService;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevView;

public class AccelerometerView extends XdevView {

	private String watchId;

	/**
	 *
	 */
	public AccelerometerView() {
		super();
		this.initUI();
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent event) {
		super.enter(event);

	}

	/**
	 * Event handler delegate method for the {@link XdevView}.
	 *
	 * @see ClientConnector.AttachListener#attach(ClientConnector.AttachEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void this_attach(final ClientConnector.AttachEvent event) {

		AccelerometerService.getInstance().watchAcceleration(AccelerometerOptions.withFrequency(250), watch -> {
			this.watchId = watch.getWatchID();
			showAcceleration(watch.getAcceleration());
		}, error -> {
			Notification.show(error.getMessage(), Type.ERROR_MESSAGE);
		});
	}

	private void showAcceleration(final Acceleration a) {

		this.lblX.setValue("X: " + a.getX());
		this.lblY.setValue("X: " + a.getY());
		this.lblZ.setValue("Z: " + a.getZ());
	}

	/**
	 * Event handler delegate method for the {@link XdevView}.
	 *
	 * @see ClientConnector.DetachListener#detach(ClientConnector.DetachEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void this_detach(final ClientConnector.DetachEvent event) {

		if (this.watchId != null) {
			AccelerometerService.getInstance().clearWatch(this.watchId);
		}
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.gridLayout = new XdevGridLayout();
		this.navBar = new NavBar();
		this.label = new XdevLabel();
		this.lblX = new XdevLabel();
		this.lblY = new XdevLabel();
		this.lblZ = new XdevLabel();

		this.navBar.setTitle("Accelerometer");
		this.label.setValue("Move your phone to update the acceleration values.");
		this.lblX.setCaption("X:");
		this.lblX.setValue("xValue");
		this.lblY.setCaption("Y:");
		this.lblY.setValue("yValue");
		this.lblZ.setCaption("Z:");
		this.lblZ.setValue("zValue");

		this.gridLayout.setColumns(1);
		this.gridLayout.setRows(6);
		this.navBar.setWidth(100, Unit.PERCENTAGE);
		this.navBar.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.navBar, 0, 0);
		this.label.setWidth(100, Unit.PERCENTAGE);
		this.label.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.label, 0, 1);
		this.lblX.setSizeUndefined();
		this.gridLayout.addComponent(this.lblX, 0, 2);
		this.lblY.setSizeUndefined();
		this.gridLayout.addComponent(this.lblY, 0, 3);
		this.lblZ.setSizeUndefined();
		this.gridLayout.addComponent(this.lblZ, 0, 4);
		this.gridLayout.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout_vSpacer = new CustomComponent();
		gridLayout_vSpacer.setSizeFull();
		this.gridLayout.addComponent(gridLayout_vSpacer, 0, 5, 0, 5);
		this.gridLayout.setRowExpandRatio(5, 1.0F);
		this.gridLayout.setSizeFull();
		this.setContent(this.gridLayout);
		this.setSizeFull();

		this.addAttachListener(event -> this.this_attach(event));
		this.addDetachListener(event -> this.this_detach(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevLabel label, lblX, lblY, lblZ;
	private NavBar navBar;
	private XdevGridLayout gridLayout;
	// </generated-code>

}