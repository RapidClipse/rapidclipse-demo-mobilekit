package com.xdev.rcdemo.mobilekit.ui;

import org.vaadin.addon.leaflet.LMap;
import org.vaadin.addon.leaflet.LMarker;
import org.vaadin.addon.leaflet.LOpenStreetMapLayer;
import org.vaadin.addon.leaflet.shared.Point;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.xdev.mobile.service.geolocation.Coordinates;
import com.xdev.mobile.service.geolocation.GeolocationOptions;
import com.xdev.mobile.service.geolocation.GeolocationService;
import com.xdev.mobile.service.geolocation.Position;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevView;

public class GeolocationView extends XdevView {

	private LMarker marker;

	/**
	 *
	 */
	public GeolocationView() {
		super();

		this.initUI();

		this.map.addBaseLayer(new LOpenStreetMapLayer(), "Open Street Map");
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent event) {
		super.enter(event);

		this.map.flyTo(new Point(0,0), 0.0);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #cmdLocation}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmdLocation_buttonClick(final Button.ClickEvent event) {

		this.cmdLocation.setEnabled(false);

		GeolocationService.getInstance().getCurrentPosition(GeolocationOptions.withHighAccuracy(), position -> {
			showLocation(position);
			this.cmdLocation.setEnabled(true);
		}, error -> {
			Notification.show(error.getMessage(), Type.ERROR_MESSAGE);
			this.cmdLocation.setEnabled(true);
		});

	}

	private void showLocation(final Position position) {

		if(this.marker != null)
		{
			this.map.removeLayer(this.marker);
		}

		final Coordinates coordinates = position.getCoordinates();
		// convert mobilekit coordinates to leaflet point
		final Point point = new Point(coordinates.getLatitude(), coordinates.getLongitude());

		this.marker = new LMarker(point);
		this.marker.setTitle("You are here!");
		this.map.addLayer(this.marker);

		final double zoom = 18.0;
		this.map.flyTo(point, zoom);
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated
	 * by the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.gridLayout = new XdevGridLayout();
		this.navBar = new NavBar();
		this.cmdLocation = new XdevButton();
		this.map = new LMap();

		this.navBar.setTitle("Geolocation");
		this.cmdLocation.setCaption("Show current location");

		this.gridLayout.setColumns(1);
		this.gridLayout.setRows(3);
		this.navBar.setWidth(100, Unit.PERCENTAGE);
		this.navBar.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.navBar, 0, 0);
		this.cmdLocation.setWidth(100, Unit.PERCENTAGE);
		this.cmdLocation.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.cmdLocation, 0, 1);
		this.map.setSizeFull();
		this.gridLayout.addComponent(this.map, 0, 2);
		this.gridLayout.setColumnExpandRatio(0, 10.0F);
		this.gridLayout.setRowExpandRatio(2, 10.0F);
		this.gridLayout.setSizeFull();
		this.setContent(this.gridLayout);
		this.setSizeFull();

		this.cmdLocation.addClickListener(event -> this.cmdLocation_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevButton cmdLocation;
	private NavBar navBar;
	private LMap map;
	private XdevGridLayout gridLayout;
	// </generated-code>

}
