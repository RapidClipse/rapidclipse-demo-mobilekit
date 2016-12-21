package com.xdev.rcdemo.mobilekit.ui;

import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClientConnector;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;
import com.xdev.mobile.service.MobileServiceError;
import com.xdev.mobile.service.nfc.Ndef;
import com.xdev.mobile.service.nfc.NfcService;
import com.xdev.mobile.service.nfc.Record;
import com.xdev.mobile.service.nfc.Tag;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevView;

public class NfcView extends XdevView {

	private boolean listenerOn = false;

	/**
	 *
	 */
	public NfcView() {
		super();
		this.initUI();
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent event) {
		super.enter(event);

	}

	/**
	 * Event handler delegate method for the {@link XdevButton}
	 * {@link #cmdSwitchListener}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmdSwitchListener_buttonClick(final Button.ClickEvent event) {

		if (this.listenerOn) {
			stopListening();
		} else {
			startListening();
		}
	}

	private void startListening() {
		NfcService.getInstance().startNdefListener(this::ndefListenerCallback, this::startNdefListenerSuccess,
				this::startNdefListenerError);
	}

	private void ndefListenerCallback(final Ndef ndef) {
		final Tag tag = ndef.getTag();

		final StringBuilder sb = new StringBuilder();

		try {
			sb.append("Type: ").append(tag.getType()).append("\n");
			sb.append("Id: ").append(new String(tag.getId(), "UTF-8")).append("\n");
			sb.append("Tech types: ").append(tag.getTechTypes().stream().collect(Collectors.joining(", ")))
					.append("\n");

			sb.append("\nRecords:\n");

			for (final Record record : tag.getNdefMessage()) {
				sb.append("\nId: ").append(record.getIdAsString()).append("\n");
				sb.append("Type: ").append(record.getTypeAsString()).append("\n");
				sb.append("Payload: ").append(record.getPayloadAsString()).append("\n");
			}
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		this.lblFeedback.setValue(sb.toString());
	}

	private void startNdefListenerSuccess(final String message) {
		this.listenerOn = true;
		this.cmdSwitchListener.removeStyleName(ValoTheme.BUTTON_DANGER);
		this.cmdSwitchListener.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		this.lblFeedback.setValue("Ready to scan...");
	}

	private void startNdefListenerError(final MobileServiceError error) {
		this.listenerOn = false;
		this.cmdSwitchListener.addStyleName(ValoTheme.BUTTON_DANGER);
		this.cmdSwitchListener.removeStyleName(ValoTheme.BUTTON_FRIENDLY);
		this.lblFeedback.setValue(error.getMessage());
	}

	private void stopListening() {
		NfcService.getInstance().stopNdefListener(this::stopNdefListenerSuccess, this::stopNdefListenerError);
	}

	private void stopNdefListenerSuccess(final String message) {
		this.listenerOn = false;
		this.cmdSwitchListener.removeStyleName(ValoTheme.BUTTON_DANGER);
		this.cmdSwitchListener.removeStyleName(ValoTheme.BUTTON_FRIENDLY);
		this.lblFeedback.setValue("");
	}

	private void stopNdefListenerError(final MobileServiceError error) {
		this.listenerOn = false;
		this.cmdSwitchListener.addStyleName(ValoTheme.BUTTON_DANGER);
		this.cmdSwitchListener.removeStyleName(ValoTheme.BUTTON_FRIENDLY);
		this.lblFeedback.setValue(error.getMessage());
	}

	/**
	 * Event handler delegate method for the {@link XdevView}.
	 *
	 * @see ClientConnector.DetachListener#detach(ClientConnector.DetachEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void this_detach(final ClientConnector.DetachEvent event) {

		if (this.listenerOn) {
			NfcService.getInstance().stopNdefListener(success -> {
			});
		}
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated
	 * by the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.gridLayout = new XdevGridLayout();
		this.navBar = new NavBar();
		this.cmdSwitchListener = new XdevButton();
		this.lblFeedback = new XdevLabel();

		this.navBar.setTitle("NFC");
		this.cmdSwitchListener.setCaption("Switch Listener");
		this.lblFeedback.setContentMode(ContentMode.PREFORMATTED);

		this.gridLayout.setColumns(1);
		this.gridLayout.setRows(3);
		this.navBar.setWidth(100, Unit.PERCENTAGE);
		this.navBar.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.navBar, 0, 0);
		this.cmdSwitchListener.setWidth(100, Unit.PERCENTAGE);
		this.cmdSwitchListener.setHeight(-1, Unit.PIXELS);
		this.gridLayout.addComponent(this.cmdSwitchListener, 0, 1);
		this.lblFeedback.setSizeFull();
		this.gridLayout.addComponent(this.lblFeedback, 0, 2);
		this.gridLayout.setColumnExpandRatio(0, 10.0F);
		this.gridLayout.setRowExpandRatio(2, 10.0F);
		this.gridLayout.setSizeFull();
		this.setContent(this.gridLayout);
		this.setSizeFull();

		this.addDetachListener(event -> this.this_detach(event));
		this.cmdSwitchListener.addClickListener(event -> this.cmdSwitchListener_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevButton cmdSwitchListener;
	private XdevLabel lblFeedback;
	private NavBar navBar;
	private XdevGridLayout gridLayout;
	// </generated-code>

}
