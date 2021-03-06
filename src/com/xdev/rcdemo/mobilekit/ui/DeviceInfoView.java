package com.xdev.rcdemo.mobilekit.ui;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClientConnector;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.xdev.mobile.service.deviceinfo.DeviceInfoService;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevView;

public class DeviceInfoView extends XdevView {

	/**
	 *
	 */
	public DeviceInfoView() {
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

		DeviceInfoService.getInstance().getDeviceInfo(info->{

			final StringBuilder sb = new StringBuilder();
			sb.append("Manufacturer: ").append(info.getManufacturer()).append("\n");
			sb.append("Model: ").append(info.getModel()).append("\n");
			sb.append("Platform: ").append(info.getPlatform()).append("\n");
			sb.append("Version: ").append(info.getVersion()).append("\n");
			sb.append("UUID: ").append(info.getUuid()).append("\n");
			sb.append("Serial: ").append(info.getSerial()).append("\n");
			this.lblResult.setValue(sb.toString());
		});
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.gridLayout = new XdevGridLayout();
		this.navBar = new NavBar();
		this.lblResult = new XdevLabel();

		this.navBar.setTitle("Device Info");
		this.lblResult.setValue("Result");
		this.lblResult.setContentMode(ContentMode.PREFORMATTED);

		this.gridLayout.setColumns(1);
		this.gridLayout.setRows(3);
		this.navBar.setWidth(100, Unit.PERCENTAGE);
		this.navBar.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.navBar, 0, 0);
		this.lblResult.setSizeUndefined();
		this.gridLayout.addComponent(this.lblResult, 0, 1);
		this.gridLayout.setColumnExpandRatio(0, 10.0F);
		final CustomComponent gridLayout_vSpacer = new CustomComponent();
		gridLayout_vSpacer.setSizeFull();
		this.gridLayout.addComponent(gridLayout_vSpacer, 0, 2, 0, 2);
		this.gridLayout.setRowExpandRatio(2, 1.0F);
		this.gridLayout.setSizeFull();
		this.setContent(this.gridLayout);
		this.setSizeFull();

		this.addAttachListener(event -> this.this_attach(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevLabel lblResult;
	private NavBar navBar;
	private XdevGridLayout gridLayout;
	// </generated-code>

}
