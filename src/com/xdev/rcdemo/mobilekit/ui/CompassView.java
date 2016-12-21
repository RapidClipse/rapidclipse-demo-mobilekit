package com.xdev.rcdemo.mobilekit.ui;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClientConnector;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.xdev.mobile.service.compass.CompassOptions;
import com.xdev.mobile.service.compass.CompassService;
import com.xdev.mobile.service.compass.Heading;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevView;

public class CompassView extends XdevView {

	private String watchId;

	/**
	 *
	 */
	public CompassView() {
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

		CompassService.getInstance().watchHeading(CompassOptions.withFrequency(250), watch -> {
			this.watchId = watch.getWatchID();
			showHeading(watch.getHeading());
		}, error -> {
			Notification.show(error.getMessage(), Type.ERROR_MESSAGE);
		});
	}

	private void showHeading(final Heading h) {

		this.lblHeading.setValue(String.valueOf(h.getTrueHeading()));
		this.lblAccuracy.setValue(String.valueOf(h.getHeadingAccuracy()));
	}

	/**
	 * Event handler delegate method for the {@link XdevView}.
	 *
	 * @see ClientConnector.DetachListener#detach(ClientConnector.DetachEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void this_detach(final ClientConnector.DetachEvent event) {

		if (this.watchId != null) {
			CompassService.getInstance().clearWatch(this.watchId);
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
		this.lblHeading = new XdevLabel();
		this.lblAccuracy = new XdevLabel();

		this.navBar.setTitle("Compass");
		this.label.setValue("Move your phone to update the heading values.");
		this.lblHeading.setCaption("Heading:");
		this.lblHeading.setValue("HeadingValue");
		this.lblAccuracy.setCaption("Accuracy:");
		this.lblAccuracy.setValue("AccuracyValue");

		this.gridLayout.setColumns(1);
		this.gridLayout.setRows(5);
		this.navBar.setWidth(100, Unit.PERCENTAGE);
		this.navBar.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.navBar, 0, 0);
		this.label.setWidth(100, Unit.PERCENTAGE);
		this.label.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.label, 0, 1);
		this.lblHeading.setSizeUndefined();
		this.gridLayout.addComponent(this.lblHeading, 0, 2);
		this.lblAccuracy.setSizeUndefined();
		this.gridLayout.addComponent(this.lblAccuracy, 0, 3);
		this.gridLayout.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout_vSpacer = new CustomComponent();
		gridLayout_vSpacer.setSizeFull();
		this.gridLayout.addComponent(gridLayout_vSpacer, 0, 4, 0, 4);
		this.gridLayout.setRowExpandRatio(4, 1.0F);
		this.gridLayout.setSizeFull();
		this.setContent(this.gridLayout);
		this.setSizeFull();

		this.addAttachListener(event -> this.this_attach(event));
		this.addDetachListener(event -> this.this_detach(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevLabel label, lblHeading, lblAccuracy;
	private NavBar navBar;
	private XdevGridLayout gridLayout;
	// </generated-code>

}
